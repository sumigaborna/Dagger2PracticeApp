package com.example.dagger2practiceapp.di.auth

import androidx.lifecycle.ViewModel
import com.example.dagger2practiceapp.di.ViewModelKey
import com.example.dagger2practiceapp.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel):ViewModel
}