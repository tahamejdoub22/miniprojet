package com.example.myapplication.ui.main.Adapter

import android.content.ContentValues.TAG
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
import com.example.myapplication.ui.main.data.model.Materials
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


class MaterialAdapter(val materialList: ArrayList<Materials>, val context: Context?):RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val subjectText:TextView= itemView.findViewById(R.id.textView)
        val subjectImage:ImageView= itemView.findViewById(R.id.imageview)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

      //  val view
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.each_item, parent, false)
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
            Log.e(TAG, "Error getting bitmap", e)
        }
        return bm
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = materialList[position].materialName
      //  val image = materialList[position].materialImage
        val newsgirl = URL(materialList[position].materialImage)

        holder.subjectText.text = name;
        holder.subjectImage.setImageBitmap( getImageBitmap(newsgirl.toString()))







    }

    override fun getItemCount() = materialList.size


}
