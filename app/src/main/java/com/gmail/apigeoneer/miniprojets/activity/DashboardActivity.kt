package com.gmail.apigeoneer.miniprojets.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.apigeoneer.miniprojets.First2Fragment
import com.gmail.apigeoneer.miniprojets.HomeFragment
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.Second2Fragment

import com.gmail.apigeoneer.miniprojets.databinding.DashboardBinding
import com.gmail.apigeoneer.miniprojets.fragment.LearnFragment
import com.gmail.apigeoneer.miniprojets.fragment.mapFragment
import com.gmail.apigeoneer.miniprojets.fragment.profileFragment
import com.gmail.apigeoneer.miniprojets.onboarding.network.ProfileActivity


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding :DashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(com.gmail.apigeoneer.miniprojets.fragment.HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> replaceFragment(com.gmail.apigeoneer.miniprojets.fragment.HomeFragment())
                R.id.miLearn -> replaceFragment(LearnFragment())
                R.id.miLocation -> replaceFragment(mapFragment())
                R.id.miProfile -> replaceFragment(profileFragment())

                else -> {

                }
            }
            true


        }
        binding.fab.setOnClickListener {
            val myIntent = Intent(this, ProfileActivity::class.java)

            startActivity(myIntent)     }


    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout,fragment)
            commit()
        }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLayout,fragment)
        fragmentTransaction.commit()
    }


}