package com.example.dagger2practiceapp.di.main

import com.example.dagger2practiceapp.di.auth.AuthScope
import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.network.main.MainApi
import com.example.dagger2practiceapp.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit):MainApi{
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @Provides
    fun provideRecyclerAdapter() : PostsRecyclerAdapter = PostsRecyclerAdapter()
}