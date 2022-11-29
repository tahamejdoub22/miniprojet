package com.example.myapplication.ui.main

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class cameraFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

    }

    private var our_request_code: Int = 123 //can number can be given


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val image_view = requireView().findViewById<ImageView>(R.id.image)
        val btn = requireView().findViewById<Button>(R.id.Button)
        btn.setOnClickListener { view ->


            @Override
            fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                if (requestCode == our_request_code && resultCode == AppCompatActivity.RESULT_OK) {
                    //if the result is ok our request code is equal to request code
                    //start bitmap
                    val bitmap = data?.extras?.get("data") as Bitmap
                    //set image bitmap
                    image_view.setImageBitmap(bitmap)

                }
            }

            fun TakePhoto(view: View) {
                //start an intent to capture image
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                //start the result
                //check if the task can be performed
                if (activity?.let { intent.resolveActivity(it.packageManager) } != null) {

                    startActivityForResult(intent, our_request_code)
                }
            }
        }
        return inflater.inflate(R.layout.activity_camera, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment cameraFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            cameraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}






