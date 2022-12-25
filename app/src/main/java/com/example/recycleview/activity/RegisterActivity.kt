package com.example.recycleview.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleview.databinding.AcitivityRegisterBinding
import com.example.recycleview.pojo.BaseResponse
import com.example.recycleview.pojo.RegisterResponse
import com.example.recycleview.retrofit.sessionManager
import com.example.recycleview.viewModel.RegisterViewModel


class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: AcitivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = AcitivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val token = sessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.registerResult.observe(this) {
            when (it) {



                is BaseResponse.Loading -> {
                    showLoading()
                }


                is BaseResponse.Success -> {
                    stopLoading()

                    processRegister(it.data)



                    val intent = Intent(this@RegisterActivity, BaseActivity::class.java)

                    startActivity(intent)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

       /* binding.btnRegister.setOnClickListener {
            doRegister()


        }*/



    }

    private fun navigateToHome() {
        val intent = Intent(this, BaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun doRegister() {
      //  val username= binding.usernameRegister.text.toString()
      //  val email =  binding.emailRegister.text.toString()
     //   val pwd = binding.txtPassregister.text.toString()
     //   viewModel.registerUser(name=username , email = email, pwd = pwd)

    }


    private fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE

    }

    private fun stopLoading() {
        binding.prgbar.visibility = View.GONE

    }

    private fun processRegister(data: RegisterResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.data?.token.isNullOrEmpty()) {


            data?.data?.token?.let { sessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }

    }

    private fun processError(msg: String?) {
        showToast("Error:$msg")
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}