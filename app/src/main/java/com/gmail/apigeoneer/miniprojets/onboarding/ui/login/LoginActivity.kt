package com.gmail.apigeoneer.miniprojets.onboarding.ui.login


import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.activity.DashboardActivity
import com.gmail.apigeoneer.miniprojets.databinding.ActivityLoginBinding
import com.gmail.apigeoneer.miniprojets.onboarding.model.BaseResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.sessionManager
import com.gmail.apigeoneer.miniprojets.onboarding.ui.login.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val logoanim = AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.imagelogo.startAnimation(logoanim)
        val textanimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        binding.texrlogo.startAnimation(textanimation)
        val emailanimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.txtInputEmail.startAnimation(emailanimation)
        val passwordanimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.txtPass.startAnimation(passwordanimation)
        val token = sessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {

                    stopLoading()
                    processLogin(it.data)



                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java)

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

        binding.btnLogin.setOnClickListener {
            doLogin()


        }



    }

    private fun navigateToHome() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doLogin() {
        val email = binding.txtInputEmail.text.toString()
        val pwd = binding.txtPass.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    fun processLogin(data: LoginResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.data?.token.isNullOrEmpty()) {


            data?.data?.token?.let { sessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}







