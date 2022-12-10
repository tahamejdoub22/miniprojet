package com.gmail.apigeoneer.miniprojets.onboarding.ui.login


import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.ui.main.data.retrofit.RetrofitClient
import com.gmail.apigeoneer.miniprojets.DashboardActivity
import com.gmail.apigeoneer.miniprojets.HomeFragment
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.databinding.ActivityLoginBinding
import com.gmail.apigeoneer.miniprojets.databinding.ActivityMainBinding
import com.gmail.apigeoneer.miniprojets.onboarding.model.BaseResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginRequest
import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.sessionManager
import com.gmail.apigeoneer.miniprojets.onboarding.network.UserApi
import com.gmail.apigeoneer.miniprojets.onboarding.network.userRepository
import com.gmail.apigeoneer.miniprojets.onboarding.ui.login.viewmodel.LoginViewModel
import com.gmail.apigeoneer.miniprojets.onboarding.util.CustomeProgressDialog


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    lateinit var binding1: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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







