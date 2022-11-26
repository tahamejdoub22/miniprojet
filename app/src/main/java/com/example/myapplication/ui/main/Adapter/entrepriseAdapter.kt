package com.example.myapplication.ui.main.Adapter

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.main.data.model.entreprise
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


class entrepriseAdapter(val entrepriseList: ArrayList<entreprise>, val context: Context?):RecyclerView.Adapter<entrepriseAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val name:TextView= itemView.findViewById(R.id.name)
        val photos:ImageView= itemView.findViewById(R.id.imageSpacing)
        val distance:TextView= itemView.findViewById(R.id.distance)
        val address:TextView= itemView.findViewById(R.id.address)
        val time:TextView= itemView.findViewById(R.id.time)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //val view =
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_listspacing, parent, false)
        )
    }
    private fun  getImageBitmap(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {
            val aURL = URL(url)
            val conn: URLConnection = aURL.openConnection()
            conn.connect()
            val `is`: InputStream = conn.getInputStream()
            val bis = BufferedInputStream(`is`)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            `is`.close()
        } catch (e: IOException) {
            Log.e(ContentValues.TAG, "Error getting bitmap", e)
        }
        return bm
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = entrepriseList[position].name
        val distance = entrepriseList[position].distance
        val address = entrepriseList[position].address
        val time = entrepriseList[position].time

        //  val image = materialList[position].materialImage
        val newsgirl = URL(entrepriseList[position].Photo)

        holder.name.text = name;
        holder.distance.text = distance;
        holder.address.text = address;
        holder.time.text = time;
        holder.photos.setImageBitmap( getImageBitmap(newsgirl.toString()))







    }

    override fun getItemCount() = entrepriseList.size


}

