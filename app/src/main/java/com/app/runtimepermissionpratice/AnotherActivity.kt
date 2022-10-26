package com.app.runtimepermissionpratice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        //dyanamic data pass
       // setResult(RESULT_OK,intent)
    }
}