package com.omar.chat_application.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<DB:ViewDataBinding, VM:ViewModel>: AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding  : DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = initViewModel()
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel():VM


}