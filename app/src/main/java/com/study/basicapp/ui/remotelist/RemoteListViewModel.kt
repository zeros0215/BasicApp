package com.study.basicapp.ui.remotelist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.basicapp.database.UserEntity
import com.study.basicapp.repository.RemoteRespository
import com.study.basicapp.repository.UserItemRespository
import com.study.basicapp.remote.UsersDto
import com.study.basicapp.di.module.DiUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RemoteListViewModel @Inject constructor(
    //private val itemRepository: UserItemRespository,
    private val remoteRepository: RemoteRespository
) : ViewModel(){

    private val TAG = "RemoteListViewModel"
    private val list = mutableListOf<UserItem>()
    var liveData : MutableLiveData<List<UserItem>> = MutableLiveData<List<UserItem>>()
    //private val itemPage = mutableListOf<List<UserItem>>()
    //val searchLiveDate : MutableLiveData<String> = MutableLiveData<String>()
    val itemRepository: UserItemRespository = DiUtil.userItemRepository

    init {
        //liveData.value = list
        loadRemoteUser()
    }

    private fun loadRemoteUser() {
        Log.d(TAG, "loadRemoteUser")
        viewModelScope.launch {
            remoteRepository.getUsers().enqueue(object : Callback<List<UsersDto>>{
                override fun onResponse(
                    call: Call<List<UsersDto>>,
                    response: Response<List<UsersDto>>
                ) {
                    if(response.isSuccessful){
                        val users = response.body()
                        users?.forEach {
                            Log.d(TAG, "User: $it")
                            list.add(UserItem(it.name, it.phone))
                        }
                        liveData.postValue(list)
                    }else{
                        Log.e(TAG, "Response not successful: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<List<UsersDto>>, t: Throwable) {
                    Log.e(TAG, "Error: ${t.message}")
                }
            })
        }
    }

    private fun addItem(item: UserItem) {
        list.add(item)
        liveData.postValue(list)
    }

    private fun removeItem(item: UserItem) {
        list.remove(item)
        liveData.postValue(list)
    }

    private fun clearAllItem(){
        list.clear()
        liveData.postValue(list)
    }

    fun isertToDbItem(item: UserItem) {
        viewModelScope.launch {
            itemRepository.insertUser(UserEntity(0, item.name, item.number))
        }
    }

}