package com.gmail.apigeoneer.miniprojets.adapter

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.databinding.ActivityCameraBinding.bind
import com.gmail.apigeoneer.miniprojets.onboarding.model.MList
import com.gmail.apigeoneer.miniprojets.onboarding.model.Materials
import com.squareup.picasso.Picasso
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class RecycleViewAdapter : RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>(){

    var items = mutableListOf<Materials>()

    fun setarticleList(items: MList) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val imageThumb: ImageView =view.findViewById<ImageView>(R.id.imageThumb)
        val tvtitle: TextView =view.findViewById<TextView>(R.id.tvTitle)


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val view=LayoutInflater.from(parent.context).inflate(R.layout.recycle_list_row,parent,false)
        return  MyViewHolder(view)


    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = items[position]
        holder.tvtitle.text=article.materialName
        val newsgirl = URL(article.materialImage)
        holder.imageThumb.setImageBitmap( getImageBitmap(newsgirl.toString()))

    }
    override fun getItemCount(): Int {
return items.size   }
}