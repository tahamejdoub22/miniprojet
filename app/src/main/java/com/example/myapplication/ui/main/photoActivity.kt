package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.Recording
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityPhotoBinding
import com.example.myapplication.ui.main.utils.Draw
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import java.util.concurrent.ExecutorService


/**
 * Photo activity
 *
 * @constructor Create empty Photo activity
 */
class photoActivity:  AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    private var recording: Recording? = null
    private lateinit var binding: ActivityPhotoBinding
    private lateinit var objectDetector:ObjectDetector
    private lateinit var cameraProviderFuture:ListenableFuture<ProcessCameraProvider>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        // Request camera permissions
        val localModel = LocalModel.Builder()
            .setAbsoluteFilePath("objectdetect.tflite").build()
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider) },ContextCompat.getMainExecutor(this))

            val customObjectDetectorOptions =
                CustomObjectDetectorOptions.Builder(localModel)
                    .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
                    .enableClassification()
                    .setClassificationConfidenceThreshold(0.5f)
                    .setMaxPerObjectLabelCount(3)
                    .build()

            objectDetector = ObjectDetection.getClient(customObjectDetectorOptions)

        /*  if (allPermissionsGranted()) {
            }
            },ContextCompat.getMainExecutor(this))



        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)}
        cameraExecutor = Executors.newSingleThreadExecutor()

*/

    }
    @SuppressLint("UnsafeOptInUsageError")
    private fun bindPreview(cameraProvider: ProcessCameraProvider){
        val preview =Preview.Builder().build()
        val cameraSelector=CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280,720)
                )
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this)) { imageProxy ->
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                objectDetector.process(image).addOnSuccessListener {
                    objects -> for (i in objects) {
                        //if(binding.parentLayout.childCount>1)binding.parentLayout.removeViewAt(1)
val element = Draw(context = this,
    rect = i.boundingBox,
    text = i.labels.firstOrNull()?.text ?:"undefined")
binding.parentLayout.addView(element)

                    }
                }.addOnFailureListener {
Log.v("photoActivity","Error _ ${it.message}")
                    imageProxy.close()
                }

            }
        }
        cameraProvider.bindToLifecycle(this as LifecycleOwner,cameraSelector,imageAnalysis,preview)
    }
 /*   private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }
    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }*/
}

