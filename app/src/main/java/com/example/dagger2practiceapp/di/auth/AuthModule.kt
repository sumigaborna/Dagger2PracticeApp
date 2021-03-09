package com.example.dagger2practiceapp.di.auth

import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    fun someUser(): UserItem = UserItem(1,"test","test","test")

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit):AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

}