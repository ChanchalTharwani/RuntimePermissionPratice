package com.app.runtimepermissionpratice

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.app.runtimepermissionpratice.databinding.ActivityCameraOpenBinding
import java.io.File

class CameraOpenActivity : AppCompatActivity() {
    lateinit var imgview:ImageView
    lateinit var btnchange:Button
  //  lateinit var imageUri:Uri
    lateinit var imageUri: Uri
    private val contract =registerForActivityResult(ActivityResultContracts.TakePicture()){
   // private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()){
      binding.imageView.setImageURI(imageUri)
    }
    lateinit var binding:ActivityCameraOpenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_camera_open)

        imgview = binding.imageView
        btnchange = binding.btnchange
        imageUri =  createImageUri()!!


        btnchange.setOnClickListener {
            contract.launch(imageUri)
        }

    }

    private fun createImageUri(): Uri? {
        val image = File(applicationContext.filesDir,"camera_photo.png")
        return FileProvider.getUriForFile(applicationContext,
            "com.app.runtimepermissionpratice.fileProvider"
            ,image
        )
    }


}