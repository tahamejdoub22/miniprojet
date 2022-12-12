package com.gmail.apigeoneer.miniprojets.fragment

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.main.data.retrofit.RetrofitClient
import com.gmail.apigeoneer.miniprojets.onboarding.model.MList
import com.gmail.apigeoneer.miniprojets.onboarding.model.RecyclerList
import com.gmail.apigeoneer.miniprojets.onboarding.network.UserApi
import com.gmail.apigeoneer.miniprojets.onboarding.network.matRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewViewModel constructor(private val repository: matRepository)  : ViewModel() {

    val movieList = MutableLiveData<MList>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<MList> {
            override fun onResponse(call: Call<MList>, response: Response<MList>) {
                movieList.postValue(response.body())
            }
            override fun onFailure(call: Call<MList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}