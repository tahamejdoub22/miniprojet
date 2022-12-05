package com.example.myapplication.ui.main.activities


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityInformationBinding
import com.example.myapplication.ui.main.utils.BitmapHelper
import com.example.myapplication.ui.main.Adapter.MarkerInfoWindowAdapter
import com.example.myapplication.ui.main.fragments.HomeFragment
import com.example.myapplication.ui.main.fragments.LearnFragment
import com.example.myapplication.ui.main.fragments.MapFragment
import com.example.myapplication.ui.main.fragments.ProfileFragment
import com.example.myapplication.ui.main.place.Place
import com.example.myapplication.ui.main.place.PlaceRenderer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.codelabs.buildyourfirstmap.place.PlacesReader
import com.google.maps.android.clustering.ClusterManager


class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding
    var loggedInUser: String = "default"
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContentView(binding.root)
        loggedInUser = intent.getStringExtra("username").toString()
        //TODO: change this to home fragment when you continue working
        setCurrentFragment(MapFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> replaceFragment(HomeFragment())
                R.id.miLearn -> replaceFragment(LearnFragment())
                R.id.miLocation -> replaceFragment(MapFragment())
                R.id.miProfile -> replaceFragment(ProfileFragment())
                else -> {
                }
            }
            true

        }
        binding.fab.setOnClickListener {
            val myIntent = Intent(this, PhotoActivity::class.java)

            startActivity(myIntent)

        }

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
        mBundle.putString("username", loggedInUser)
        fragment.arguments = mBundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLayout, fragment)
        fragmentTransaction.commit()
    }

}


