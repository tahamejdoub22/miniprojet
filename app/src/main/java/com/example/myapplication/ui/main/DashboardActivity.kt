package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityInformationBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding
    lateinit var loggedInUser:String
    private val PERTH = LatLng(35.419272, 9.594808)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInformationBinding.inflate(layoutInflater)
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
        }
        setContentView(binding.root)
         loggedInUser = intent.getStringExtra("username").toString()
        setCurrentFragment(HomeFragment())

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

            startActivity(myIntent)
        }

    }




    /**
     * Adds marker representations of the places list on the provided GoogleMap object
     */
    private fun addMarkers(googleMap: GoogleMap) {
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title("test")
                    .position(PERTH)
            )

    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            val mBundle = Bundle()
            mBundle.putString("username", loggedInUser)
            fragment.arguments = mBundle
            replace(R.id.FrameLayout, fragment)
            commit()
        }

    private fun replaceFragment(fragment: Fragment) {
        val mBundle = Bundle()
        mBundle.putString("username",loggedInUser )
        fragment.arguments = mBundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLayout, fragment)
        fragmentTransaction.commit()
    }

}