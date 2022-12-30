package com.example.recycleview.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProviders
import com.example.recycleview.R
import com.example.recycleview.databinding.AcitivityRegisterBinding
import com.example.recycleview.databinding.ActivityMaterielPostBinding
import com.example.recycleview.pojo.BaseResponse
import com.example.recycleview.viewModel.EventModelView
import com.example.recycleview.viewModel.RegisterViewModel
import com.example.recycleview.viewModel.UpdateViewModel

class MaterielPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaterielPostBinding
    private lateinit var homeMvvm: UpdateViewModel
    private val viewModel by viewModels<UpdateViewModel>()
var points=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaterielPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        homeMvvm= ViewModelProviders.of(this)[UpdateViewModel::class.java]
        viewModel.updateResult.observe(this) {
            when (it) {


                is BaseResponse.Loading -> {
                    showLoading()
                }


                is BaseResponse.Success -> {
                    stopLoading()

                    homeMvvm.updatepoints(points = points+2)


                    val intent = Intent(this@MaterielPostActivity, BaseActivity::class.java)

                    startActivity(intent)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }






    }
    private fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE

    }
    private fun processError(msg: String?) {
        showToast("Error:$msg")
    }
    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding.prgbar.visibility = View.GONE

    }
}