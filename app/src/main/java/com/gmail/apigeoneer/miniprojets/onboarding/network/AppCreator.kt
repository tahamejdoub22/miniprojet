package com.gmail.apigeoneer.miniprojets.onboarding.network

import android.app.Application
import com.gmail.apigeoneer.miniprojets.onboarding.network.ApiClient.client

class AppCreator : Application() {
    companion object {

        private var mApiHelper:ApiHelper? = null
        fun getApiHelperInstance():ApiHelper{
            if(mApiHelper==null){
                mApiHelper = client?.create(ApiInterface::class.java)?.let { ApiHelper(it) }
            }
            return mApiHelper!!
        }

    }
}
