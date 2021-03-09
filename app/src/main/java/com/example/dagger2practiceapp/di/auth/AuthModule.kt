package com.example.dagger2practiceapp.di.auth

import com.example.dagger2practiceapp.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit):AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

}