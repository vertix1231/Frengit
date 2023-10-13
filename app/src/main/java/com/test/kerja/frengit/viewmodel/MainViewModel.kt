package com.test.kerja.frengit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.kerja.frengit.model.ItemsItem
import com.test.kerja.frengit.model.User
import com.test.kerja.frengit.repository.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val mutGituser = MutableLiveData<List<ItemsItem>>()
    val Gituser: LiveData<List<ItemsItem>> = mutGituser
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"

    }

    fun findGitUser(search: String) {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getUserbyusername(search)
        client.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        mutGituser.postValue(responseBody.items as List<ItemsItem>?)
                        Log.i(TAG, "onPassSearchName: ${responseBody.items}")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _isLoading.postValue(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

}