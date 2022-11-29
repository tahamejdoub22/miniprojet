package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class pageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_click_me = findViewById(R.id.btnConnexion) as Button
        val btn_click_me1 = findViewById(R.id.btnInscription) as Button

// set on-click listener
        btn_click_me.setOnClickListener {
            val myIntent = Intent(this, loginActivity::class.java)

            startActivity(myIntent)
        }
        btn_click_me1.setOnClickListener {
            val myIntent = Intent(this, registerActivity::class.java)

            startActivity(myIntent)
        }
    }
}