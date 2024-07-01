package com.study.basicapp.ui.locallist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.study.basicapp.MyApplication
import com.study.basicapp.local.UserEntity
import com.study.basicapp.ui.basiclist.model.user_item
import com.study.hybridbasic.model.users
import com.study.hybridbasic.remote.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocalListViewModel : ViewModel(){

    private val TAG = "LocalListViewModel"
    private val list = mutableListOf<user_item>()
    var liveData : MutableLiveData<List<user_item>> = MutableLiveData<List<user_item>>()
    private val itemPage = mutableListOf<List<user_item>>()
    val searchLiveDate : MutableLiveData<String> = MutableLiveData<String>()
    val userDao = MyApplication.database?.userDao()

    init {
        Log.d(TAG, "LocalListViewModel init")
        liveData.value = list
        loadLocalUser()
    }

    private fun loadLocalUser() {
        Log.d(TAG, "loadLocalUser")

        viewModelScope.launch {
            val users = userDao?.getAllUsers()
            users?.forEach{
                list.add(user_item(it.name, it.number))
            }
            liveData.value = list
            liveData.postValue(list)
        }
    }

    fun addItem(item: user_item) {
        list.add(item)
        liveData.value = list
    }

    fun removeItem(item: user_item) {
        list.remove(item)
        liveData.value = list
    }

    fun clearAllItem(){
        list.clear()
        liveData.value = list
    }

    fun deleteToDbItem(item: user_item) {
        val userDao = MyApplication.database?.userDao()
        viewModelScope.launch {
            item.name?.let { userDao?.deleteUserByName(it) }
        }
        removeItem(item)
    }


}