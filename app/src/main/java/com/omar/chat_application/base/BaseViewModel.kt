package com.omar.chat_application.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N>: ViewModel() {
    val messageLiveDate = MutableLiveData<String>()
    val showLoading     = MutableLiveData<Boolean>()
    var navigator:N?    = null
}