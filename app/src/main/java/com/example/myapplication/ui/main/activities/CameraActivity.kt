package com.example.myapplication.ui.main.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CameraActivity : AppCompatActivity() {
    private var our_request_code: Int = 123 //can number can be given
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val image_view = findViewById(R.id.image) as ImageView

        val btn_click_me1 = findViewById(R.id.Button) as Button
        btn_click_me1.setOnClickListener {
            TakePhoto(image_view)
        }
    }

    fun TakePhoto(view: View) {


        //start an intent to capture image
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //start the result
        //check if the task can be performed
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, our_request_code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == our_request_code && resultCode == RESULT_OK) {
            //if the result is ok our request code is equal to request code
            val imageview: ImageView = findViewById(R.id.image)
            //start bitmap
            val bitmap = data?.extras?.get("data") as Bitmap

            //set image bitmap
            imageview.setImageBitmap(bitmap)
        }
    }

}
