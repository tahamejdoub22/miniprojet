package com.gmail.apigeoneer.miniprojets.fragment

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.adapter.RecycleViewAdapter
import com.gmail.apigeoneer.miniprojets.onboarding.network.matRepository


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

private lateinit var recycleAdapter:RecycleViewAdapter
    private val TAG = "MainActivity"
    lateinit var viewModel: NewViewModel
    private val retrofitService = com.gmail.apigeoneer.miniprojets.onboarding.screens.RetrofitService.getInstance()
    val adapter = RecycleViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home2, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }
    private fun initViewModel(view:View){
      val recyclerView= view.findViewById<RecyclerView>(R.id.recyclerview)
       recyclerView.layoutManager=LinearLayoutManager(activity)
       val decoration= DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recycleAdapter=RecycleViewAdapter()
        recyclerView.adapter=recycleAdapter

    }
    private fun initViewModel(){
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val viewModel = ViewModelProvider(this, MyViewModelFactory(matRepository(retrofitService = retrofitService))).get(NewViewModel::class.java)
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setarticleList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
        })
        viewModel.getAllMovies()
    }




    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }
}