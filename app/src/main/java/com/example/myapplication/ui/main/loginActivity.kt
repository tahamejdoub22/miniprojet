package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.ui.main.data.model.UserRequest
import com.example.myapplication.ui.main.data.retrofit.APIClient
import com.example.myapplication.ui.main.data.retrofit.RetrofitClient


class loginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        passwordFocusListener()
        binding.loginButton.setOnClickListener {

            var email = binding.usernameInput.text.toString()
            var password = binding.passwordInput.text.toString()
            loginAction(email,password)
        }


    }






    private fun passwordFocusListener(){
        binding.passwordInput.setOnFocusChangeListener { v, focused ->
            if(!focused)
            {
                binding.textPasswordInput.helperText = validPassword()
            }
        }


    }
    private fun validPassword():String?{
        val passwordText =binding.passwordInput.text.toString()
       if (passwordText.length<8)
       {
           return "Minimum 8 character Password"
       }
        if (!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case character"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case character"
        }
        if (!passwordText.matches(".*[@#\$%&^+=].*".toRegex()))
        {
            return "Must Contain 1 Special character(@#\$%&^+=)"
        }
        return null}
    fun loginAction(email: String, password: String) {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(APIClient::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val user = UserRequest(email,password)
                val response = apiInterface.signIn(user)

                if(response.isSuccessful()){
                    Log.d("LoggingActivity",response.body().toString())
                    Log.d("LoggingActivity",""+response.body()?.username)

                    //change informationactivity to homeactivity wala haja hakka
                    val intent = Intent(this@loginActivity, DashboardActivity::class.java)
                    intent.putExtra("username",response.body()?.username)
                    startActivity(intent)
                    }
                else {
                    Log.d("LoggingActivity","errorReponse")
                }
            } catch (ex: Exception) {
                Log.d("LoggingActivity", "error")

            }}
    }

}