package com.omar.chat_application.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB:ViewDataBinding, VM:BaseViewModel>: AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding  : DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = initViewModel()

        subscribeToLiveDate()
    }

    fun subscribeToLiveDate(){

        viewModel.messageLiveDate.observe(this) {
            message ->
            showDialog(message, "ok")

        }
        viewModel.showLoading.observe(this) {
            show ->
            if(show){
                showLoading()
            }
            else {
                hideLoading()
            }
        }
    }

    var alertDialog: AlertDialog?=null
    fun showDialog(message:String,
                   posActionName:String? = null,
                   posAction: DialogInterface.OnClickListener? = null,
                   negActionName:String? = null,
                   negAction: DialogInterface.OnClickListener? = null,
                   cancelable:Boolean = true
                   ){
       val builder =  AlertDialog.Builder(this)
            .setMessage(message)
        val defaultAction = DialogInterface.OnClickListener { dialog, which -> dialog?.dismiss() }
        if(posActionName!= null)
        {
            builder.setPositiveButton(posActionName,
                posAction ?: defaultAction
            )
        }
        if(negActionName!= null)
        {
            builder.setNegativeButton(negActionName, negAction?: defaultAction)
        }
        builder.setCancelable(cancelable)

        alertDialog = builder.show()
    }

    fun hideAlertDialog(){
        alertDialog?.dismiss()
        alertDialog = null
    }

    var progressDialog : ProgressDialog?= null
    fun showLoading(){
        ProgressDialog(this)
            .setMessage("Loading...")
        ProgressDialog(this)
            .setCancelable(false)
        progressDialog?.show()
    }

    fun hideLoading(){
        progressDialog?.dismiss()
        progressDialog = null
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel():VM


}