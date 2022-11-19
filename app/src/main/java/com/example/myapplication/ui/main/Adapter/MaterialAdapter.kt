package com.example.myapplication.ui.main.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.main.data.model.Materials

/**
 *
 */
class MaterialAdapter(private val materialList: List<Materials>) :
    RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    /**
     *
     */
    class MaterialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val materialImageView: ImageView = itemView.findViewById(R.id.imageview)
        val materialNameTv: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return MaterialViewHolder(view)

    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
       val material = materialList[position]
        holder.materialImageView.setImageResource(material.materialImage)
        holder.materialNameTv.text = material.materialName

    }

    override fun getItemCount(): Int {
       return materialList.size
    }

}