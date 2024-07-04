package com.study.basicapp.ui.locallist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.basicapp.repository.UserItemRespository
import com.study.basicapp.ui.remotelist.UserItem
import com.study.basicapp.di.module.DiUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalListViewModel @Inject constructor(
    //private val respository: UserItemRespository
) : ViewModel(){

    private val TAG = "LocalListViewModel"
    private val list = mutableListOf<UserItem>()
    var liveData : MutableLiveData<List<UserItem>> = MutableLiveData<List<UserItem>>()
    private val respository: UserItemRespository = DiUtil.userItemRepository

    init {
        Log.d(TAG, "LocalListViewModel init")
        liveData.value = list
        loadLocalUser()
    }

    private fun loadLocalUser() {
        Log.d(TAG, "loadLocalUser")
        viewModelScope.launch {
            val users = respository.getAllUsers()
            users.forEach{
                list.add(UserItem(it.name, it.number))
            }
            liveData.value = list
            liveData.postValue(list)
            Log.d(TAG, "postValue list: " + list)
        }
    }

    fun addItem(item: UserItem) {
        list.add(item)
        liveData.value = list
    }

    fun removeItem(item: UserItem) {
        list.remove(item)
        liveData.value = list
    }

    fun clearAllItem(){
        list.clear()
        liveData.value = list
    }

    fun deleteToDbItem(item: UserItem) {
        viewModelScope.launch {
            item.name?.let { respository.deleteUserByName(it) }
        }
        removeItem(item)
    }


}