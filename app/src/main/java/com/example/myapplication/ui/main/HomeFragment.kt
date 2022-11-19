package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.myapplication.R
import com.example.myapplication.ui.main.Adapter.MaterialAdapter
import com.example.myapplication.ui.main.Adapter.entrepriseAdapter
import com.example.myapplication.ui.main.data.model.Materials
import com.example.myapplication.ui.main.data.model.entreprise


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var materialList: ArrayList<Materials>
    private lateinit var materialAdapter: MaterialAdapter
    private lateinit var recyclerView1: RecyclerView
    private lateinit var entrepriseList: ArrayList<entreprise>
    private lateinit var entrepriseAdapter: entrepriseAdapter
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    // Inflate the layout for this fragment


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        super.onCreate(savedInstanceState)

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.RecyclerView)
        val recyclerView1 = requireView().findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.setHasFixedSize(true)
        recyclerView1.setHasFixedSize(true)

        recyclerView.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView1.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        snapHelper.attachToRecyclerView(recyclerView1)
        materialList = ArrayList()
        entrepriseList = ArrayList()

        entrepriseList.add(entreprise(R.drawable.img_spacing, address = "ariana", distance = 20, time = "15min"))
        entrepriseList.add(entreprise(R.drawable.img_computer, address = "MOHAMED", distance = 20, time = "30min"))
        entrepriseList.add(entreprise(R.drawable.img_spacing, address = "KK", distance = 20, time = "50min"))
        entrepriseAdapter = entrepriseAdapter (entrepriseList)
        recyclerView1.adapter = entrepriseAdapter
        materialList.add(Materials(R.drawable.img_untitleddesign_102x72, materialName = "Metal"))
        materialList.add(Materials(R.drawable.img_untitleddesign_108x69, materialName = "Paper"))
        materialList.add(Materials(R.drawable.img_untitleddesign_145x89, materialName = "Plastic"))
        materialList.add(
            Materials(
                R.drawable.img_untitleddesign_108x76,
                materialName = "Glass"
            )
        )
        materialAdapter = MaterialAdapter (materialList)
        recyclerView.adapter = materialAdapter

    }


}
