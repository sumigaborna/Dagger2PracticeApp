package com.example.dagger2practiceapp.di.main

import com.example.dagger2practiceapp.ui.main.posts.PostsFragment
import com.example.dagger2practiceapp.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment() : PostsFragment
}