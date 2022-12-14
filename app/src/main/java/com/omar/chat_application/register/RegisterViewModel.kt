package com.omar.chat_application.register

import android.util.Log
import androidx.databinding.ObservableField
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omar.chat_application.base.BaseViewModel

class RegisterViewModel: BaseViewModel() {

    // we will bind it with xml using two way dataBinding
    val firstName  = ObservableField<String>()
    val lastName   = ObservableField<String>()
    val email      = ObservableField<String>()
    val username   = ObservableField<String>()
    val password   = ObservableField<String>()

    // we will set Error for each observer of the registrationScreen
    val firstNameError  = ObservableField<String>()
    val lastNameError   = ObservableField<String>()
    val userNameError   = ObservableField<String>()
    val passwordError   = ObservableField<String>()
    val emailError      = ObservableField<String>()


    // Initialize the firebase Authentication
    val auth = Firebase.auth


    fun createAccount(){
        if(validate()){
            addAccountToFirebase()
        }
    }

    private fun addAccountToFirebase() {
        showLoading.value = true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener{
                task->
                showLoading.value = false
                if(!task.isSuccessful){
                    // Show error message
                    messageLiveDate.value = task.exception?.localizedMessage
                    Log.e("", "Error cause" + task.exception?.localizedMessage)
                    Log.i("", "Error adding account")
                }
                else {
                    // Show success message
                    messageLiveDate.value = task.exception?.localizedMessage
                    Log.e("", "Error cause" + task.exception?.localizedMessage)
                    Log.i("", "Successfully added account")
                }
            }
    }

    private fun validate(): Boolean{
        var isValid = true

        if(firstName.get().isNullOrBlank()){
            firstNameError.set("Please enter first name")
            isValid = false
        }
        else{
            firstNameError.set(null)
        }

         if(lastName.get().isNullOrBlank()){
            lastNameError.set("Please enter last name")
            isValid = false
        }

         else{
            lastNameError.set(null)
        }

         if(username.get().isNullOrBlank()){
            userNameError.set("Please enter user name")
            isValid = false
        }

         else{
            userNameError.set(null)
        }

         if(email.get().isNullOrBlank()){
            emailError.set("Please enter email")
            isValid = false
        }

         else{
             emailError.set(null)
        }

         if(password.get().isNullOrBlank()){
            passwordError.set("Please enter password")
            isValid = false
        }

         else{
            passwordError.set(null)
        }

        return isValid
    }

}