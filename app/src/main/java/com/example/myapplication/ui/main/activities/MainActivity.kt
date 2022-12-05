package com.example.myapplication.ui.main.activities

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
            //TODO: change this to login/register activity when you continue working
            val myIntent = Intent(this, DashboardActivity::class.java)

            startActivity(myIntent)     }
    }
}