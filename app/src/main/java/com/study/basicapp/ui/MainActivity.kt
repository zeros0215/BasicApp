package com.study.basicapp.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.study.basicapp.R
import com.study.basicapp.common.BaseActivity
import com.study.basicapp.databinding.ActivityMainBinding
import com.study.basicapp.ui.basiclist.BasicFragment
import com.study.basicapp.ui.basiclist.LocalListFragment
import com.study.basicapp.ui.webview.BasicWebViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    companion object{
        private val TAG = "MainActivity"
    }
    private val REQUEST_CODE_PERMISSIONS = 100
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGrantPermission()

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, BasicFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_basiclist -> {
                    supportFragmentManager.popBackStackImmediate() //clear all stack
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BasicFragment()).commit()
                    true
                }

                R.id.navigation_locallist -> {
                    supportFragmentManager.popBackStackImmediate() //clear all stack
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LocalListFragment()).commit()
                    true
                }

                R.id.navigation_basicwebview -> {
                    supportFragmentManager.popBackStackImmediate() //clear all stack
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BasicWebViewFragment()).commit()
                    true
                }

                else -> false
            }
        }
    }


    fun initGrantPermission() : Boolean{
        // 권한 목록
        var grant : Boolean = false
        val REQUIRED_PERMISSIONS  = arrayOf(
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        grant = REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }

        Log.d(TAG, "initGrantPermission grant: " + grant)

        if(!grant){
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        return grant;
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // 모든 권한이 부여됨
                    Toast.makeText(this, "모든 권한이 부여되었습니다.", Toast.LENGTH_LONG).show()
                } else {
                    // 일부 권한이 거부됨
                    Toast.makeText(this, "필요한 모든 권한을 부여해야 합니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}