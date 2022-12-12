package com.gmail.apigeoneer.miniprojets.onboarding.network

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.sessionManager

class ProfileActivity: AppCompatActivity() {

    private lateinit var mProfileViewModel: ProfileViewModel
    private lateinit var mProfileResponse : LoginResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile)
        val avatar: ImageView =findViewById<ImageView>(R.id.img_profile)
        val username: TextView =findViewById<TextView>(R.id.txt_username)
        val token = sessionManager.getToken(this)

        // this code for statusbar just like instagram
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
        //...
        initData()
        obtainListFromServer()
    }
    private fun obtainListFromServer() {

        mProfileViewModel.getAllProfile().observe(this)


        {
            when(it.status){
                Status.SUCCESS -> {

                    mProfileResponse = it.data!!

                    getData()

                    Toast.makeText(
                        this,
                        "Sucessfully load the data",
                        Toast.LENGTH_LONG
                    ).show()

                }

                Status.FAILURE -> {
                    Toast.makeText(
                        this,
                        "Failed to load the data ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                Status.LOADING -> {
                    Toast.makeText(
                        this,
                        "Loading...",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    private fun getData() {

        val avatar: ImageView =findViewById<ImageView>(R.id.img_profile)
        val username: TextView =findViewById<TextView>(R.id.txt_username)
        val email: TextView =findViewById<TextView>(R.id.email)

        val data  = mProfileResponse.data!!
        if (!data?.token.isNullOrEmpty()) {


            data?.token?.let { sessionManager.saveAuthToken(this, it) }

        }
        username.text = data.name.toString()
        email.text = data.email.toString()


        Glide.with(this@ProfileActivity).load(data.avatar).apply(RequestOptions.circleCropTransform()).into(avatar)


    }

    private fun initData() {
        //initialization of viewmodel instance,
        mProfileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(AppCreator.getApiHelperInstance())
        ).get(ProfileViewModel::class.java)


    }
}