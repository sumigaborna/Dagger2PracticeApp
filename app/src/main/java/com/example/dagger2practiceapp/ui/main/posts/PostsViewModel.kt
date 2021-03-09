package com.example.dagger2practiceapp.ui.main.posts

import androidx.lifecycle.ViewModel
import com.example.dagger2practiceapp.SessionManager
import com.example.dagger2practiceapp.network.main.MainApi
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val sessionManager: SessionManager, private val mainApi: MainApi): ViewModel() {
}