package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.model.UserSignUpRequest
import com.example.myapplication.data.retrofit.APIClient
import com.example.myapplication.data.retrofit.RetrofitClient
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.RegisterBinding

class registerActivity : AppCompatActivity() {
    private lateinit var binding: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=RegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()
        confirmpasswordFocusListener()
        binding.registerButton.setOnClickListener {

            var email = binding.loginInput.text.toString()
            var password = binding.passwordInput.text.toString()
            registerAction(email,password)
        }
    }
    private fun emailFocusListener(){
        binding.loginInput.setOnFocusChangeListener { v, focused ->
            if(!focused)
            {
                binding.textEmailLayout.helperText = validEmail()
            }
        }


    }
    private fun validEmail():String?{
        val emailText =binding.loginInput.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }
    private fun passwordFocusListener() {
        binding.passwordInput.setOnFocusChangeListener { v, focused ->
            if (!focused) {
                binding.textPasswordInput.helperText = validPassword()
            }
        }
    }
        private fun confirmpasswordFocusListener(){
            binding.confirmPassword.setOnFocusChangeListener { v, focused ->
                if(!focused)
                {
                    binding.confirmPasswordTextInputLayout.helperText =validConfirmPassword()
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
    private fun validConfirmPassword():String?{
        val confirmpasswordText =binding.confirmPassword.text.toString()
        val passwordText =binding.passwordInput.text.toString()


        if (confirmpasswordText!=passwordText)
        {
            return "password not the same"
        }
        return null}
    fun registerAction(email: String, password: String){
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(APIClient::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val user = UserSignUpRequest(email,password,role="entreprise")
                val response = apiInterface.singUp(user)

                if(response.isSuccessful()){
                    Log.d("RegisterActivity",response.body().toString())
                    //change informationactivity to homeactivity wala haja hakka
                    val intent = Intent(this@registerActivity, informationActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Log.d("registerActivity","errorReponse")
                }
            } catch (ex: Exception) {
                Log.d("registerActivity", "error")

            }

        }


    }

}