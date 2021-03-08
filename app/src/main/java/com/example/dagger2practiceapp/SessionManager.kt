package com.example.dagger2practiceapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cachedUser = MediatorLiveData<AuthResource<UserItem?>>()

    fun authenticateWithId(source : LiveData<AuthResource<UserItem?>>){
        if(cachedUser != null){
            cachedUser.value = AuthResource.loading(null)
            cachedUser.addSource(source) {
                cachedUser.value = it
                cachedUser.removeSource(source)
            }
        }
    }


    fun logOut(){
        cachedUser.value = AuthResource.logout()
    }

    val getAuthUser = cachedUser
}