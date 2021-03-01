package com.example.dagger2practiceapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.dagger2practiceapp.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

    init {
        val response = authApi.getUsers(2).toObservable().doOnNext {
            println("Response is: $it")
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}