package com.example.dagger2practiceapp.network.main

import com.example.dagger2practiceapp.models.PostsApiResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getPosts(@Query("userId") userId:Int): Flowable<PostsApiResponse>
}