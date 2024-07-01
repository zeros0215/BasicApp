package com.study.basicapp.ui.webview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log


class UtilCamera : Activity() {
    private val TAG = "UtilCamera"
    public val CAMERA_VIEW = 2000
    private var mFileUri : Uri = Uri.parse("file://path/to/file.txt")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "UtilCamera")

        val intent : Intent = Intent()
        intent.setAction(
            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(
            MediaStore.EXTRA_OUTPUT, mFileUri);
        startActivityForResult(intent, CAMERA_VIEW);
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        finish()
    }

}