package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout



class loginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()

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
}