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
import com.study.basicapp.ui.webview.UtilCamera

class NativeBridge(private val context : Activity) {
    private val TAG = "NativeBridge"

    @JavascriptInterface
    fun callPhone(phoneNumber : String){

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, arrayOf(android.Manifest.permission.CALL_PHONE), 1)
        }

        Log.d(TAG, "callPhone phoneNumber: " + phoneNumber)
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phoneNumber")
        try{
            context.startActivity(intent)
        }catch (e: Exception){
            Toast.makeText(context, "Exception error", Toast.LENGTH_LONG)
        }
    }

    @JavascriptInterface
    fun callSMS(phoneNumber: String, message: String) {
        try {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, arrayOf(android.Manifest.permission.SEND_SMS), 1)
            }

            Log.d(TAG, "sendSms phoneNumber: " + phoneNumber)
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(context, "SMS sent.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "SMS failed, please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    // 카메라 호출
    @JavascriptInterface
    fun callCamera() {
        Log.d(TAG, "callCamerar")
        val intent = Intent(context, UtilCamera::class.java)
        context.startActivity(intent)
    }


//
//    //  위치정보
//    @JavascriptInterface
//    fun callLocationPos(final strCallbackFunc : String) {
//
//        Log.d("CALL NativeBridge callLocationPos", "START");
//        try {
//            if (bCmdProcess) return;
//            bCmdProcess = true;
//            mHandler.post(new Runnable() {
//                public void run() {
//                    try {
//                        LocationManager locMgr =
//                        (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                        Criteria criteria = new Criteria();
//                        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//                        String bestProv = locMgr.getBestProvider(criteria, true);
//                        int result1= ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION);
//                        int result2= ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION);
//
//
//                        if ( result1 != PackageManager.PERMISSION_GRANTED &&
//
//                            result2 != PackageManager.PERMISSION_GRANTED) {
//
//                            return;
//                        }
//
//
//                        Location loc = locMgr.getLastKnownLocation(bestProv);
//                        String strJavascript = strCallbackFunc + "('" +
//                                loc.getLatitude() + "','" +
//                                loc.getLongitude() + "')";
//                        Log.e("", strJavascript);
//                        mWebView.loadUrl("javascript:" + strJavascript);
//                    }
//                    catch(Exception exLoc)
//                    {
//                        String strJavascript = "alert('위치확인오류')";
//                        mWebView.loadUrl("javascript:" + strJavascript);
//                    }
//                }
//            });
//        }
//        catch(Exception ex)
//        {
//            bCmdProcess = false;
//            Log.e("Error", ex.toString());
//        }
//        finally
//        {
//            bCmdProcess = false;
//        }
//    }

//
//
//    fun initGrantPermission(){
//        // 권한 목록
//        val permissions = arrayOf(
//            android.Manifest.permission.CALL_PHONE,
//            android.Manifest.permission.SEND_SMS,
//            android.Manifest.permission.CAMERA,
//            android.Manifest.permission.ACCESS_COARSE_LOCATION,
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//        )
//        Log.d(TAG, "initGrantPermission permissions: " + permissions)
//
//        // 권한 체크
//        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED ||
//            ContextCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED ||
//            ContextCompat.checkSelfPermission(this, permissions[2]) != PackageManager.PERMISSION_GRANTED ||
//            ContextCompat.checkSelfPermission(this, permissions[3]) != PackageManager.PERMISSION_GRANTED ||
//            ContextCompat.checkSelfPermission(this, permissions[4]) != PackageManager.PERMISSION_GRANTED      ) {
//            // 권한이 부여되어 있지 않으면 요청
//            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS)
//        } else {
//            // 권한이 이미 부여되어 있음
//            // 권한을 사용하는 코드 작성
//            startLoadWebUrl()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            var granted = true
//            for (result in grantResults) {
//                if (result != PackageManager.PERMISSION_GRANTED) {
//                    granted = false
//                    break
//                }
//            }
//
//            Log.d(TAG, "onRequestPermissionsResult granted : " + granted)
//            if (granted) {
//                // 모든 권한이 부여됨
//                // 권한을 사용하는 코드 작성
//            } else {
//                // 하나 이상의 권한이 거부됨
//                // 사용자에게 권한이 필요한 이유를 설명하고 다시 요청할지 결정
//            }
//            startLoadWebUrl()
//        }
//    }


}