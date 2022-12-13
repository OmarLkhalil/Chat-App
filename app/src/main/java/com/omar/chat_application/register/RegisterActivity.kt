package com.omar.chat_application.register

import android.os.Bundle
import com.omar.chat_application.R
import com.omar.chat_application.base.BaseActivity
import com.omar.chat_application.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = RegisterViewModel()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return RegisterViewModel()
    }
}