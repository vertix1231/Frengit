package com.test.kerja.frengit.data.retrofit

import com.test.kerja.frengit.BuildConfig
import com.test.kerja.frengit.data.response.TabUserDetail
import com.test.kerja.frengit.data.response.User
import com.test.kerja.frengit.data.response.UserDetail
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/search/users")
    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    fun getUserbyusername(
        @Query("q") q: String
    ): Call<User>

    @GET("/users/{loginName}")
    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    fun getDetailUserbyusername(
        @Path("loginName") loginName: String
    ): Call<UserDetail>

    @GET("/users/{loginName}/followers")
    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    fun getFollowerDetailUserbyusername(
        @Path("loginName") loginName: String
    ): Call<List<TabUserDetail>>

    @GET("/users/{loginName}/following")
    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    fun getFollowingDetailUserbyusername(
        @Path("loginName") loginName: String
    ): Call<List<TabUserDetail>>
}