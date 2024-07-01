package com.study.basicapp.webview.nativbridge

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.telephony.SmsManager
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AndroidBridge (private val context : Activity) {
    private val TAG = "AndroidBridge"

    @JavascriptInterface
    fun showToast(message : String){
        Log.d(TAG, "message: " + message)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}