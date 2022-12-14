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
        subscribetoLiveData()
    }

    private fun subscribetoLiveData() {
        viewModel.messageLiveDate.observe(this) { message ->
            showDialog(message,"ok")
        }
        viewModel.showLoading.observe(this) { show ->
            if (show)
                showLoading()
            else
                hideLoading()
        }
    }
    private var alertDialog:AlertDialog?=null
    private fun showDialog(message:String,
                           posActionName:String?=null,
                           posAction:DialogInterface.OnClickListener?=null,
                           negActionName:String?=null,
                           negAction:DialogInterface.OnClickListener?=null,
                           cancelable:Boolean=true
    ){
        val defAction = DialogInterface.OnClickListener { dialog, p1 -> dialog?.dismiss() }
        val builder = AlertDialog.Builder(this).setMessage(message)
        if (posActionName!=null){
            builder.setPositiveButton(posActionName,posAction ?: defAction)
        }
        if (negActionName!=null){
            builder.setPositiveButton(negActionName,negAction ?: defAction)
        }
        builder.setCancelable(cancelable)

        alertDialog = builder.show()
    }
    fun hideAlertDialog() {
        alertDialog?.dismiss()
        alertDialog = null
    }

    private var progressDialog:ProgressDialog?=null
    private fun showLoading() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading..")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }
    private fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM

}