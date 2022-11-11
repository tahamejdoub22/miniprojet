package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information1)
        val image_view = findViewById(R.id.nextImgButton) as ImageView


        image_view.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            val myIntent = Intent(this, informationActivity::class.java)

            startActivity(myIntent)     }
    }
}