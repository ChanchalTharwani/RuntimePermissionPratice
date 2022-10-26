package com.app.runtimepermissionpratice

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.app.runtimepermissionpratice.databinding.ActivityMainBinding
import org.jetbrains.annotations.Contract

class MainActivity : AppCompatActivity() {
    val permission = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.CALL_PHONE,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.USE_FINGERPRINT
    )
    lateinit var button: Button
    private lateinit var binding:ActivityMainBinding
    lateinit var imgView: ImageView
    lateinit var videoview:VideoView
    lateinit var btnchnage: Button



    //registerForActivityResult() takes an ActivityResultContract and an ActivityResultCallback and returns an
    // ActivityResultLauncher which you’ll use to launch the other activity.

    //An ActivityResultContract defines the input type needed to produce a result along with the output type of the result
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imgView.setImageURI(it)
        videoview.setVideoURI(it)
        videoview.stopPlayback();
        videoview.start();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        button = binding.btnchange

        binding.btnchange.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permission, 80)
                Log.d("tag", "helllooo")
            }



        }
        binding.btnselectfromgallry.setOnClickListener{
            videoview.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
                mp.seekTo(0);//go to second 0
                mp.start()// start again
            })
//n ActivityResultLauncher which you’ll use to launch the other activity.
            contract.launch("image/* video/*" )

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //The method requestPermissions(String[] permissions, int requestCode); is a public method that is used to request dangerous permissions.
            // We can ask for multiple dangerous permissions by passing a string array of permissions.
            requestPermissions(permission, 80)
        }
        imgView = binding.imageView
        videoview = binding.videoView
        button = binding.btnchange


    }

    //Callback for the result from requesting permissions. This method is invoked for every call on requestPermissions.
    override fun onRequestPermissionsResult(
        requestCode: Int,//The request code passed in requestPermissions
        permissions: Array<out String>,//The requested permissions. Never null.
        grantResults: IntArray//The grant results for the corresponding permissions which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 80) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Download code", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Download cancel", Toast.LENGTH_SHORT).show()
            }

        }

    }

}