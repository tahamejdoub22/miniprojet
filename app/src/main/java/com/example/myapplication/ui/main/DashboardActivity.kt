package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityInformationBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding :ActivityInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> replaceFragment(HomeFragment())
                R.id.miLearn -> replaceFragment(LearnFragment())
                R.id.miLocation -> replaceFragment(mapFragment())
                R.id.miProfile -> replaceFragment(ProfileFragment())

                else -> {

                }
            }
            true


        }
         binding.fab.setOnClickListener {
            val myIntent = Intent(this, photoActivity::class.java)

            startActivity(myIntent)     }


    }


private fun replaceFragment(fragment:Fragment){
    val fragmentManager = supportFragmentManager
    val fragmentTransaction=fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.FrameLayout,fragment)
    fragmentTransaction.commit()
}

}