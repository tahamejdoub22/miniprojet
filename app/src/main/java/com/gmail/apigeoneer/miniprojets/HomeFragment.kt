package com.gmail.apigeoneer.miniprojets

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.databinding.FragmentHomeBinding
import com.gmail.apigeoneer.miniprojets.databinding.FragmentThirdBinding
import com.gmail.apigeoneer.miniprojets.onboarding.ui.login.LoginActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.btnConnexion.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }


        return binding.root;

    }

}