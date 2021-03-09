package com.example.dagger2practiceapp.ui.main.profile

import androidx.lifecycle.ViewModel
import com.example.dagger2practiceapp.SessionManager
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {

    fun getAuthenticatedUser() = sessionManager.getAuthUser

}