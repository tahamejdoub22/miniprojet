package com.gmail.apigeoneer.miniprojets.onboarding.ui.login.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.main.data.retrofit.RetrofitClient
import com.gmail.apigeoneer.miniprojets.DashboardActivity
import com.gmail.apigeoneer.miniprojets.onboarding.model.*
import com.gmail.apigeoneer.miniprojets.onboarding.network.WebServiceClient
import com.gmail.apigeoneer.miniprojets.onboarding.network.userRepository
import com.gmail.apigeoneer.miniprojets.onboarding.util.SingleLiveEvent
import com.gmail.apigeoneer.miniprojets.onboarding.util.Util
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = userRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    Log.d("LoggingActivity",response.body().toString())
                    Log.d("LoggingActivity",""+response.body()?.data)
                    loginResult.value = BaseResponse.Success(response.body())


                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}