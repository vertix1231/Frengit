package com.test.kerja.frengit.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.kerja.frengit.data.response.TabUserDetail
import com.test.kerja.frengit.data.response.UserDetail
import com.test.kerja.frengit.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMainViewModel: ViewModel() {
    private val mutdetailFollowerGituser = MutableLiveData<List<TabUserDetail>?>()
    val detailFollowerGituser: LiveData<List<TabUserDetail>?> = mutdetailFollowerGituser
    private val mutdetailFollowingGituser = MutableLiveData<List<TabUserDetail>?>()
    val detailFollowingGituser: LiveData<List<TabUserDetail>?> = mutdetailFollowingGituser
    private val mutdetailGituser = MutableLiveData<UserDetail?>()
    val Gitdetailuser: LiveData<UserDetail?> = mutdetailGituser
    private val _isLoadingdetail = MutableLiveData<Boolean>()
    val isLoadingdetail: LiveData<Boolean> = _isLoadingdetail
    private val _isLoadingfollower = MutableLiveData<Boolean>()
    val isLoadingfollower: LiveData<Boolean> = _isLoadingfollower
    private val _isLoadingfollowing = MutableLiveData<Boolean>()
    val isLoadingfollowing: LiveData<Boolean> = _isLoadingfollowing
    companion object {
        private const val TAG = "DetailMainViewModel"

    }
    fun findDetailGitUser(search: String) {
        _isLoadingdetail.postValue(true)
        val client = ApiConfig.getApiService().getDetailUserbyusername(search)
        client.enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                _isLoadingdetail.postValue(false)
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody!=null){
                        mutdetailGituser.postValue(responseBody)
                        Log.i(TAG, "onDetailPassSearchName: ${responseBody.name}")
                    }
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                _isLoadingdetail.postValue(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
    fun findDetailFollowerGitUser(search: String) {
        _isLoadingfollower.postValue(true)
        val client = ApiConfig.getApiService().getFollowerDetailUserbyusername(search)
        client.enqueue(object :Callback<List<TabUserDetail>>{
            override fun onResponse(
                call: Call<List<TabUserDetail>>,
                response: Response<List<TabUserDetail>>
            ) {
                _isLoadingfollower.postValue(false)
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody!=null){
                        mutdetailFollowerGituser.postValue(responseBody)
                        Log.i(TAG, "onDetailFollower: $responseBody")
                    }
                }
            }

            override fun onFailure(call: Call<List<TabUserDetail>>, t: Throwable) {
                _isLoadingfollower.postValue(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
    fun findDetailFollowingGitUser(search: String) {
        _isLoadingfollowing.postValue(true)
        val client = ApiConfig.getApiService().getFollowingDetailUserbyusername(search)
        client.enqueue(object :Callback<List<TabUserDetail>>{
            override fun onResponse(
                call: Call<List<TabUserDetail>>,
                response: Response<List<TabUserDetail>>
            ) {
                _isLoadingfollowing.postValue(false)
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody!=null){
                        mutdetailFollowingGituser.postValue(responseBody)
                        Log.i(TAG, "onDetailFollower: ${responseBody}")
                    }
                }
            }

            override fun onFailure(call: Call<List<TabUserDetail>>, t: Throwable) {
                _isLoadingfollowing.postValue(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}