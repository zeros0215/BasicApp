package com.study.basicapp.ui.basiclist

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

class BasicListViewModel : ViewModel(){

    private val TAG = "BasicListViewModel"
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
        RetrofitClient.instance.getUsers().enqueue(object : Callback<List<users>>{
            override fun onResponse(call: Call<List<users>>, response: Response<List<users>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    users?.forEach {
                        Log.d(TAG, "User: $it")
                        list.add(user_item(it.name, it.phone))
                    }
                    liveData.value = list
                    liveData.postValue(list)
                } else {
                    Log.e(TAG, "Response not successful: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<users>>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }
        })
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
        val userDao = MyApplication.database?.userDao()
        viewModelScope.launch {
            userDao?.insertUser(UserEntity(0, item.name, item.number))
        }
    }

}