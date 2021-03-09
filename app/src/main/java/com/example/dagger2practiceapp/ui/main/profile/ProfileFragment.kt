package com.example.dagger2practiceapp.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.dagger2practiceapp.R
import com.example.dagger2practiceapp.models.UserItem
import com.example.dagger2practiceapp.ui.auth.AuthResource
import com.example.dagger2practiceapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var email : TextView
    private lateinit var username : TextView
    private lateinit var website : TextView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Profile fragment inflated")
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel::class.java)
        email = view.findViewById(R.id.email)
        username = view.findViewById(R.id.username)
        website = view.findViewById(R.id.website)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner,{
            if(it != null){
                when(it.status){
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                }
            }
        })
    }

    private fun setUserDetails(data:UserItem?){
        data?.let {
            email.text = it.email
            username.text = it.username
            website.text = it.website
        }
    }

    private fun setErrorDetails(message:String?){
        email.text = message
        username.text = "error"
        website.text = "error"
    }
}