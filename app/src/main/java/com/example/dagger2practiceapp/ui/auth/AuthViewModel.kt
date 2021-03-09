package com.example.dagger2practiceapp.ui.auth

import androidx.lifecycle.*
import com.example.dagger2practiceapp.SessionManager
import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    fun observeAuthState(): LiveData<AuthResource<UserItem?>> = sessionManager.getAuthUser

    fun authenticateWithId(userId: Int) =
        sessionManager.authenticateWithId(queryUserId(userId))

    private fun queryUserId(userId: Int): LiveData<AuthResource<UserItem?>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUsers(userId)
                .onErrorReturn {
                    UserItem(-1, "","","")
                }
                .map {
                    if (it.id == -1) {
                        AuthResource.error("Could not authenticate", data = it)
                    } else AuthResource.authenticated(data = it)
                }
                .subscribeOn(Schedulers.io()))
    }
}