package com.example.dagger2practiceapp.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.dagger2practiceapp.R
import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.ui.main.MainActivity
import com.example.dagger2practiceapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity(),View.OnClickListener {

    private lateinit var viewModel : AuthViewModel
    private lateinit var etUserId : EditText
    private lateinit var progressBar: ProgressBar
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var logo: Drawable
    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    @field:Named("app_user")
    lateinit var userItem1 : UserItem

    @Inject
    @field:Named("auth_user")
    lateinit var userItem2 : UserItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        println("User 1: ${userItem1}")
        println("User 1 hash: ${userItem1.hashCode()}")

        println("User 2: ${userItem2}")
        println("User 2 hash: ${userItem2.hashCode()}")

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        etUserId = findViewById(R.id.user_id_input)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        setLogo()

        viewModel.observeAuthState().observe(this, {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d("AuthActivity", "Login Success: ${it.data?.email}")
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, "Failed to login: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    private fun showProgressBar(isVisible : Boolean){
        if(isVisible) progressBar.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun setLogo() {
        requestManager.load(logo).into(findViewById(R.id.ivLoginLogo))
    }

    private fun attemptLogin() {
        if(etUserId.text.isNotBlank()){
            viewModel.authenticateWithId(etUserId.text.toString().toInt())
        }
    }

    private fun onLoginSuccess(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.login_button -> attemptLogin()
        }
    }
}