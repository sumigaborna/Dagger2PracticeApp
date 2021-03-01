package com.example.dagger2practiceapp.network.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi {

    @GET("/users")
    fun getUsers() : Call<ResponseBody>
}