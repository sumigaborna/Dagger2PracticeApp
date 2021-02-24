package com.example.dagger2practiceapp.di

import com.example.dagger2practiceapp.di.auth.AuthViewModelsModule
import com.example.dagger2practiceapp.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class]
    )
    abstract fun contributeAuthActivity() : AuthActivity
}