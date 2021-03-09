package com.example.dagger2practiceapp.di.main

import com.example.dagger2practiceapp.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit):MainApi{
        return retrofit.create(MainApi::class.java)
    }

}