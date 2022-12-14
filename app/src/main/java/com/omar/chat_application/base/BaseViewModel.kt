package com.omar.chat_application.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val messageLiveDate = MutableLiveData<String>()
    val showLoading     = MutableLiveData<Boolean>()
}