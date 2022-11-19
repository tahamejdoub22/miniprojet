package com.example.myapplication.ui.main.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.main.data.model.entreprise

class entrepriseAdapter (private val entrepriseList: List<entreprise>):
    RecyclerView.Adapter<entrepriseAdapter.entrepriseViewHolder>() {

    /**
     *
     */
    class entrepriseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationImageView: ImageView = itemView.findViewById(R.id.imageSpacing)
        val time: TextView = itemView.findViewById(R.id.txtH6MediumDeskt)
        val adress: TextView = itemView.findViewById(R.id.txtH5BoldDesktop)

        val distance: TextView = itemView.findViewById(R.id.txtPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): entrepriseAdapter.entrepriseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_listspacing, parent, false)
        return entrepriseAdapter.entrepriseViewHolder(view)

    }

    override fun onBindViewHolder(holder: entrepriseAdapter.entrepriseViewHolder, position: Int) {
        val entreprise = entrepriseList[position]
        holder.locationImageView.setImageResource(entreprise.Photo)
        holder.adress.text = entreprise.address
        holder.distance.text = entreprise.distance.toString()
        holder.time.text = entreprise.time

    }

    override fun getItemCount(): Int {
        return entrepriseList.size
    }

}

