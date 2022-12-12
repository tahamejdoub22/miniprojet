package com.gmail.apigeoneer.miniprojets.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.apigeoneer.miniprojets.onboarding.network.matRepository

class MyViewModelFactory constructor(private val repository: matRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewViewModel::class.java)) {
            NewViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}