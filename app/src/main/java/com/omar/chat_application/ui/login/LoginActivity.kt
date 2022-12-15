package com.omar.chat_application.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.omar.chat_application.ui.HomeActivity
import com.omar.chat_application.R
import com.omar.chat_application.base.BaseActivity
import com.omar.chat_application.databinding.ActivityLoginBinding
import com.omar.chat_application.ui.register.RegisterActivity


class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.navigator = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun openRegisterScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}