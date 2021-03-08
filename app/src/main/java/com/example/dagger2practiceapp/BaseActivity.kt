package com.example.dagger2practiceapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.dagger2practiceapp.ui.auth.AuthActivity
import com.example.dagger2practiceapp.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        sessionManager.getAuthUser.observe(this,{
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.LOADING -> {
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d("AuthActivity", "Login Success: ${it.data?.email}")
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        Log.d("AuthActivity", "Login Error: ${it.message}")
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                }
            }
        })
    }

    private fun navLoginScreen(){
        val intent = Intent(this,AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}