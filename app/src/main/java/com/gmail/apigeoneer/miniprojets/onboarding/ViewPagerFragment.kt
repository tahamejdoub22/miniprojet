package com.gmail.apigeoneer.miniprojets.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.apigeoneer.miniprojets.onboarding.screens.FirstScreen
import com.gmail.apigeoneer.miniprojets.onboarding.screens.SecondScreen
import com.gmail.apigeoneer.miniprojets.onboarding.screens.ThirdScreen
import com.gmail.apigeoneer.miniprojets.databinding.FragmentViewPagerBinding

/**
 * Contains the data that you want to display
 */

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentViewPagerBinding.inflate(inflater,container,false)

        val fragmentList = arrayListOf(
                FirstScreen(),
                SecondScreen(),
                ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
                fragmentList,
                requireActivity().supportFragmentManager,
                lifecycle
        )
        binding.viewPager.adapter = adapter

        return binding.root
    }

}