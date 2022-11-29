package com.example.myapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myapplication.R

/**
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information1)
        val imageview = findViewById(R.id.nextImgButton) as ImageView


        imageview.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            val myIntent = Intent(this, informationActivity::class.java)

            startActivity(myIntent)     }
    }
}