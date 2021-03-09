package com.example.dagger2practiceapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.dagger2practiceapp.BaseActivity
import com.example.dagger2practiceapp.R
import com.example.dagger2practiceapp.ui.main.profile.ProfileFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun testFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer,ProfileFragment()).commitAllowingStateLoss()
    }
}