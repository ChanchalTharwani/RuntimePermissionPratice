package com.app.runtimepermissionpratice

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

//An ActivityResultContract defines the input type needed to produce a result along with the output type of the result
class Contract : ActivityResultContract<String, String>() {

    //for input
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, AnotherActivity::class.java)
        intent.putExtra("REQUEST_KEY", input)
        return intent
    }

    //for output
    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return "FROM ACTIVITY ANOTHER ACTIVITY"


    }
}