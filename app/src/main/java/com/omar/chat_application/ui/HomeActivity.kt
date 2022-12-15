package com.omar.chat_application.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omar.chat_application.R
import com.omar.chat_application.databinding.HomeActivityBinding

class HomeActivity: AppCompatActivity(){

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.home_activity)
    }
}