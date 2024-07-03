package com.study.basicapp.ui.remotelist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.basicapp.database.UserEntity
import com.study.basicapp.repository.RemoteRespository
import com.study.basicapp.repository.UserTableRespository
import com.study.basicapp.ui.remotelist.model.user_item
import com.study.basicapp.remote.UsersDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RemoteListViewModel @Inject constructor(
    private val repository: UserTableRespository,
    private val remoteRepository: RemoteRespository
) : ViewModel(){

    private val TAG = "RemoteListViewModel"
    private val list = mutableListOf<user_item>()
    var liveData : MutableLiveData<List<user_item>> = MutableLiveData<List<user_item>>()
    private val itemPage = mutableListOf<List<user_item>>()
    val searchLiveDate : MutableLiveData<String> = MutableLiveData<String>()

    init {
        liveData.value = list

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
                            list.add(user_item(it.name, it.phone))
                        }
                        liveData.value = list
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

    private fun addItem(item: user_item) {
        list.add(item)
        liveData.value = list
    }

    private fun removeItem(item: user_item) {
        list.remove(item)
        liveData.value = list
    }

    private fun clearAllItem(){
        list.clear()
        liveData.value = list
    }

    fun isertToDbItem(item: user_item) {
        viewModelScope.launch {
            repository.insertUser(UserEntity(0, item.name, item.number))
        }
    }

}