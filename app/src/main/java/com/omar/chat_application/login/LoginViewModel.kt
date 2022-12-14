package com.omar.chat_application.login


import android.util.Log
import androidx.databinding.ObservableField
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omar.chat_application.base.BaseViewModel

class LoginViewModel: BaseViewModel<Navigator>() {

    val email      = ObservableField<String>()
    val password   = ObservableField<String>()

    val passwordError   = ObservableField<String>()
    val emailError      = ObservableField<String>()

    private val firebaseAuth = Firebase.auth

    fun login(){
        if(validate()){
            authWithFirebaseAuth()
        }
        else{

        }
    }

    fun authWithFirebaseAuth(){
        showLoading.value = true
        firebaseAuth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
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
                    // navigation to home screen
                    messageLiveDate.value = "Successful Login"
                    navigator?.openHomeScreen()
                    Log.e("", "Error cause" + task.exception?.localizedMessage)
                    Log.i("", "Successfully signed in")
                }
            }

    }

    private fun validate(): Boolean{
        var isValid = true

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

    fun openRegister(){
        navigator?.openRegisterScreen()
    }
}