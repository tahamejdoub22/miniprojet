package com.gmail.apigeoneer.miniprojets.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.apigeoneer.miniprojets.R

class newFragment : Fragment() {

    companion object {
        fun newInstance() = newFragment()
    }

    private lateinit var viewModel: NewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

    }

}