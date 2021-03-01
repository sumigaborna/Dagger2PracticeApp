package com.example.dagger2practiceapp.ui.auth

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
import com.example.dagger2practiceapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        etUserId = findViewById(R.id.user_id_input)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        setLogo()

        viewModel.observeUser().observe(this, {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d("AuthActivity", "Login Success: ${it.data?.email}")
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

    override fun onClick(v: View) {
        when(v.id){
            R.id.login_button -> attemptLogin()
        }
    }
}