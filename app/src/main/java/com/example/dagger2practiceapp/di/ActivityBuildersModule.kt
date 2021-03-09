package com.example.dagger2practiceapp.di

import com.example.dagger2practiceapp.di.auth.AuthModule
import com.example.dagger2practiceapp.di.auth.AuthViewModelsModule
import com.example.dagger2practiceapp.di.main.MainFragmentBuildersModule
import com.example.dagger2practiceapp.di.main.MainModule
import com.example.dagger2practiceapp.di.main.MainViewModelsModule
import com.example.dagger2practiceapp.network.main.MainApi
import com.example.dagger2practiceapp.ui.auth.AuthActivity
import com.example.dagger2practiceapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}