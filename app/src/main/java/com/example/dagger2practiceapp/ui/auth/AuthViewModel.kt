package com.example.dagger2practiceapp.ui.auth

import androidx.lifecycle.*
import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<AuthResource<UserItem?>>()

    fun observeUser(): LiveData<AuthResource<UserItem?>> = authUser

    fun authenticateWithId(userId: Int) {
        authUser.value = AuthResource.loading(data = null)

        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getUsers(userId)
                .onErrorReturn {
                    UserItem(-1,"")
                }
                .map {
                    if(it.id == -1){
                        AuthResource.error("Could not authenticate",data = it)
                    }else AuthResource.authenticated(data = it)
                }
                .subscribeOn(Schedulers.io())
        )

        authUser.addSource(
            source
        ) {
            authUser.value = it
            authUser.removeSource(source)
        }
    }
}