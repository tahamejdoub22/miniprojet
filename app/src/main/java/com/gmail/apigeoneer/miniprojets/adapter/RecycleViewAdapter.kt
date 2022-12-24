package com.gmail.apigeoneer.miniprojets.adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gmail.apigeoneer.miniprojets.R
import com.gmail.apigeoneer.miniprojets.databinding.ActivityCameraBinding.bind
import com.gmail.apigeoneer.miniprojets.onboarding.model.MList
import com.gmail.apigeoneer.miniprojets.onboarding.model.Materials
import com.gmail.apigeoneer.miniprojets.onboarding.model.MaterielResponse
import com.gmail.apigeoneer.miniprojets.onboarding.network.ProfileActivity
import com.squareup.picasso.Picasso
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class RecycleViewAdapter  (val context: Context, private val imageModelArrayList: ArrayList<MaterielResponse>) : BaseAdapter()
{

    override fun getViewTypeCount( ): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return imageModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.recycle_list_row, null, true)

            if (convertView != null) {
                holder.tvname = convertView.findViewById(R.id.tvTitle) as TextView
            }
            if (convertView != null) {
                holder.iv = convertView.findViewById(R.id.imageThumb) as ImageView
            }

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.tvname!!.setText(imageModelArrayList[position].data.materielName)
        holder.iv!!.setImageResource(R.drawable.circle)
        if (convertView != null) {
            convertView.setOnClickListener{
                val intent = Intent(convertView.context, ProfileActivity::class.java)
                intent.apply {

                    val NAME = ""
                    putExtra(NAME, imageModelArrayList[position].data.materielName);
                    val PICTURE = ""
                    putExtra(PICTURE, R.drawable.circle);
                    if(imageModelArrayList[position].data.materielName.isNotEmpty()) {
                        val TEACHER=""
                        putExtra(TEACHER, imageModelArrayList[position].data.materielName.first());
                        val SUBJECT = ""
                        putExtra(SUBJECT, imageModelArrayList[position].data.matrielImage.first());
                    }else  if(imageModelArrayList[position].data.materielName.isEmpty()) {
                        val TEACHER = ""
                        putExtra(TEACHER, "empty");
                    }
                }

                convertView.context.startActivity(intent)
            }
        }
        return convertView
    }

    private inner class ViewHolder {

        var tvname: TextView? = null
        internal var iv: ImageView? = null

    }
}