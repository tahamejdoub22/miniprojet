package com.gmail.apigeoneer.miniprojets.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.databinding.AcitivityRegisterBinding
import com.gmail.apigeoneer.miniprojets.onboarding.model.BaseResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.RegisterResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.sessionManager
import com.gmail.apigeoneer.miniprojets.onboarding.ui.login.viewmodel.RegisterViewModel


class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: AcitivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = AcitivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val textanimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        binding.txtH4BoldDesktop.startAnimation(textanimation)
        val createaccregisteranimation = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        binding.txtCreateyourAcc.startAnimation(createaccregisteranimation)
        val usernameanimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.usernameRegister.startAnimation(usernameanimation)
        val emailanimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.emailRegister.startAnimation(emailanimation)
        val passwordanimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.txtPassregister.startAnimation(passwordanimation)
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



                    val intent = Intent(this@RegisterActivity, DashboardActivity::class.java)

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

        binding.btnRegister.setOnClickListener {
            doRegister()


        }



    }

    private fun navigateToHome() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun doRegister() {
        val username= binding.usernameRegister.text.toString()
        val email =  binding.emailRegister.text.toString()
        val pwd = binding.txtPassregister.text.toString()
        viewModel.registerUser(name=username , email = email, pwd = pwd)

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