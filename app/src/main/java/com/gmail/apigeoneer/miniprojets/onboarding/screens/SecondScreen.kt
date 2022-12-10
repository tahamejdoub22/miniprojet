package com.gmail.apigeoneer.miniprojets.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.databinding.FragmentSecondBinding


class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSecondBinding.inflate(inflater,container,false);

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        binding.start.setOnClickListener {
            viewPager?.currentItem = 2                     // 0-indexing, 2 is actually the 3rd screen
        }

        return binding.root;
    }

}