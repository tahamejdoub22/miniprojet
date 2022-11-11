package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class informationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information2)
        val btn_click_me = findViewById(R.id.start) as Button
// set on-click listener
        btn_click_me.setOnClickListener {
            val myIntent = Intent(this, pageActivity::class.java)

            startActivity(myIntent)
        }
    }
}