package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.main.Adapter.MaterialAdapter
import com.example.myapplication.ui.main.data.model.MList
import com.example.myapplication.ui.main.data.model.Materials
import com.example.myapplication.ui.main.data.retrofit.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    var list: ArrayList<Materials> = arrayListOf()
    val adapter = MaterialAdapter(list, context)
    val layoutManager = LinearLayoutManager(context)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        list = ArrayList()

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerview)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.17:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api:APIClient=retrofit.create(APIClient::class.java)
        val call: Call<MList> = api.getMaterials()

        call.enqueue(object : Callback<MList?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<MList?>, response: retrofit2.Response<MList?>) {
                if(response.isSuccessful){
                    list.clear()
                    for(myData in response.body()!!) {
                        list.add(myData)
                    }
                    recyclerView.layoutManager = layoutManager
                    adapter.notifyDataSetChanged()
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    recyclerView.adapter= MaterialAdapter(list,context)
                }
            }

            override fun onFailure(call: Call<MList?>, t: Throwable) {
                Toast.makeText(context, "error hereeee:::",Toast.LENGTH_LONG).show()
            }

        })

    }




        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}