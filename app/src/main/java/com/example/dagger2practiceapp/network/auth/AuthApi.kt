package com.example.dagger2practiceapp.network.auth

import com.example.dagger2practiceapp.models.UserItem
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUsers(@Path("id") userId: Int) : Flowable<UserItem>
}