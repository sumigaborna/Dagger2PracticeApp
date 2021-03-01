package com.example.dagger2practiceapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.dagger2practiceapp.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

}