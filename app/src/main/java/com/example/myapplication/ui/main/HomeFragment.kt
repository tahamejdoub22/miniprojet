package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.main.Adapter.MaterialAdapter
import com.example.myapplication.ui.main.Adapter.entrepriseAdapter
import com.example.myapplication.ui.main.data.model.EList
import com.example.myapplication.ui.main.data.model.MList
import com.example.myapplication.ui.main.data.model.Materials
import com.example.myapplication.ui.main.data.model.entreprise
import com.example.myapplication.ui.main.data.retrofit.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var message: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val bundle = arguments
         message = bundle!!.getString("username")
        if (message != null) {
            Log.d("homefragment",message!!)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    var list1: ArrayList<entreprise> = arrayListOf()
    val adapter1 = entrepriseAdapter(list1, context)
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

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.RecyclerView)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.191:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api: APIClient = retrofit.create(APIClient::class.java)
        val call: Call<MList> = api.getMaterials()

        call.enqueue(object : Callback<MList?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<MList?>, response: retrofit2.Response<MList?>) {
                if (response.isSuccessful) {
                    list.clear()
                    for (myData in response.body()!!) {
                        list.add(myData)
                    }
                    recyclerView.layoutManager = layoutManager
                    adapter.notifyDataSetChanged()
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    recyclerView.adapter = MaterialAdapter(list, context)
                }
            }

            override fun onFailure(call: Call<MList?>, t: Throwable) {
                Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
            }

        })
        list1 = ArrayList()

        val recyclerView1 = requireView().findViewById<RecyclerView>(R.id.recycler_view)


        val call1: Call<EList> = api.getentreprise()

        call1.enqueue(object : Callback<EList?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call1: Call<EList?>, response: retrofit2.Response<EList?>) {
                if (response.isSuccessful) {
                    list1.clear()
                    for (myData in response.body()!!) {
                        list1.add(myData)
                    }
                    recyclerView1.layoutManager = layoutManager
                    adapter1.notifyDataSetChanged()
                    recyclerView1.layoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    recyclerView1.adapter = entrepriseAdapter(list1, context)
                }
            }

            override fun onFailure(call: Call<EList?>, t: Throwable) {
                Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
            }

        })

        val userNameTv = requireView().findViewById<TextView>(R.id.txtNastyaSamantha)
        userNameTv.text = message

    }


    /* val recyclerView1 = requireView().findViewById<RecyclerView>(R.id.recycler_view)

     recyclerView1.setHasFixedSize(true)

     recyclerView1.layoutManager =
         LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    // snapHelper.attachToRecyclerView(recyclerView1)
     entrepriseList = ArrayList()

     entrepriseList.add(entreprise(R.drawable.img_spacing, address = "ariana", distance = 20, time = "15min"))
     entrepriseList.add(entreprise(R.drawable.img_computer, address = "MOHAMED", distance = 20, time = "30min"))
     entrepriseList.add(entreprise(R.drawable.img_spacing, address = "KK", distance = 20, time = "50min"))
     entrepriseAdapter = entrepriseAdapter (entrepriseList)
     recyclerView1.adapter = entrepriseAdapter

*/


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}