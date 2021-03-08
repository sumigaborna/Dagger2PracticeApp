package com.example.dagger2practiceapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager @Inject constructor() {
    // data
    private val cachedUser: MediatorLiveData<AuthResource<UserItem?>> =
        MediatorLiveData<AuthResource<UserItem?>>()

    fun authenticateWithId(source: LiveData<AuthResource<UserItem?>>) {
        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading(null as UserItem?))
            cachedUser.addSource(source) { userAuthResource ->
                cachedUser.setValue(userAuthResource)
                cachedUser.removeSource(source)
            }
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthResource.logout()
    }

    val getAuthUser = cachedUser

    companion object {
        private const val TAG = "DaggerExample"
    }
}