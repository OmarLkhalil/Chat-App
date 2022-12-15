package com.omar.chat_application.ui.login


import android.util.Log
import androidx.databinding.ObservableField
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omar.chat_application.base.BaseViewModel
import com.omar.chat_application.database.model.AppUser
import com.omar.chat_application.database.signIn

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
                    checkUserInFireStore(task.result.user!!.uid)
                    navigator?.openHomeScreen()
                    Log.e("", "Error cause" + task.exception?.localizedMessage)
                    Log.i("", "Successfully signed in")
                }
            }

    }

    private fun checkUserInFireStore(uid: String?) {
        showLoading.value = true
        signIn(uid!!,
           OnSuccessListener{   docSnapshot ->
                showLoading.value = false
                val user = docSnapshot.toObject(AppUser::class.java)
                if(user == null){
                    messageLiveDate.value = "Invalid Email or Password"
                    return@OnSuccessListener
                }
               navigator?.openHomeScreen()
            }
        ) {
            showLoading.value = false
            messageLiveDate.value = it.localizedMessage
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