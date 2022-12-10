package com.gmail.apigeoneer.miniprojets.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.databinding.FragmentThirdBinding
import androidx.navigation.fragment.findNavController as findNavController1


class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentThirdBinding.inflate(inflater,container,false);

        binding.finishBtn.setOnClickListener {
            findNavController1().navigate(R.id.action_viewPagerFragment_to_homeFragment)                   // 0-indexing, 2 is actually the 3rd screen
            onBoardingFinished()
        }

        return binding.root;
    }

    // using a Shared preferences Object to save the value
    private fun onBoardingFinished() {
        // Using LiveTemplate for SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}