package com.omar.chat_application.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.omar.chat_application.R
import com.omar.chat_application.base.BaseActivity
import com.omar.chat_application.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]

    }
}