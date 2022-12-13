package com.omar.chat_application.register

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    // we will bind it with xml using two way dataBinding
    val firstName  = ObservableField<String>()
    val lastName   = ObservableField<String>()
    val email      = ObservableField<String>()
    val username   = ObservableField<String>()
    val password   = ObservableField<String>()


    fun createAccount(){
        if(validate()){
        }
    }

    private fun validate(): Boolean{
        var isValid = true

        if(firstName.get().isNullOrBlank()){
            error("It cannot be empty")
            isValid = false
        }
         if(lastName.get().isNullOrBlank()){
            error("It cannot be empty")
        }
         if(email.get().isNullOrBlank()){
            error("It cannot be empty")
        }


        return isValid
    }
}