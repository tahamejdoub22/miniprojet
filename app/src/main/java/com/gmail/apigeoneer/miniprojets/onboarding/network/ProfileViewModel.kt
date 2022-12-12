package com.gmail.apigeoneer.miniprojets.onboarding.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class ProfileViewModel(private val mProfileRepository: ProfileRepository) : ViewModel(){

    fun getAllProfile() = liveData {
        emit(Resource.loading(null))
        try{
            emit(Resource.success(mProfileRepository.getUserProfile()))
        } catch (e:Exception){
            emit(Resource.error(null,e.message.toString()))
        }
    }

}