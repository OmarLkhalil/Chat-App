package com.omar.chat_application.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.omar.chat_application.R
import com.omar.chat_application.base.BaseActivity
import com.omar.chat_application.databinding.ActivityRegisterBinding
import com.omar.chat_application.ui.HomeActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.navigator  = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}