package com.study.basicapp.ui.webview

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JsPromptResult
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.firebase.messaging.FirebaseMessaging
import com.study.basicapp.R
import com.study.basicapp.common.BaseWebViewFragment
import com.study.basicapp.databinding.FragmentBasicwebviewBinding
import com.study.basicapp.ui.MainActivity
import com.study.basicapp.ui.webview.model.WebViewModel
import com.study.basicapp.webview.nativbridge.AndroidBridge
import com.study.basicapp.webview.nativbridge.NativeBridge
import com.study.hybridbasic.common.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicWebViewFragment : BaseWebViewFragment() {

    private val TAG = "BasicWebViewFragment"
    private lateinit var binding : FragmentBasicwebviewBinding
    private val REQUEST_CODE_PERMISSIONS = 100
    private val viewModel: WebViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasicwebviewBinding.inflate(inflater, container, false)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basicwebview, container, false)
        binding.viewModel = viewModel

        // 인터넷 연결상태 확인 후, 연결이 안되어있다면 앱 종료
        val status = NetworkManager(requireActivity()).checkNetworkState()
        if(!status) {
            val toast = Toast.makeText(requireActivity(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
            toast.show()
            //ActivityCompat.finishAffinity(this)
            //exitProcess(0)
        }

        initWebSetting()
        initWebViewClient()
        initWebChromeClient()
        initFcmMessage()
        if(initGrantPermission()){
            startLoadWebUrl()
        }
        return binding.root
    }

    private fun startLoadWebUrl(){
        Log.d(TAG, "startLoadWebUrl: " + Constants.WEBVIEW_URL)
        binding.webview.loadUrl(Constants.WEBVIEW_URL)
    }

    private fun initWebSetting() {
        Log.d(TAG, "initWebSetting")
        var webSettings: WebSettings = binding.webview.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.setAllowFileAccess(true);//for read assets folder

        // JavaScript Interface 추가
        binding.webview.addJavascriptInterface(NativeBridge(requireActivity()), "NativeBridge")

        // JavaScript Interface 추가
        binding.webview.addJavascriptInterface(AndroidBridge(requireActivity()), "Android")

    }

    private fun initWebViewClient() {
        Log.d(TAG, "initWebViewClient")
        // WebChromeClient 설정
        // 앱 내에서 링크를 열도록 설정
        binding.webview.setWebViewClient(WebViewClient())
        binding.webview.webViewClient = object : WebViewClient() {

            /*리소스 요청을 가로채기 위해 사용*/
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                val url = request?.url.toString()
                /* Log.d(TAG, "shouldInterceptRequest url: " + url) */
                return super.shouldInterceptRequest(view, request) // 기본 WebView 행동을 유지
            }

            /*URL 로딩을 제어하기 위해 사용*/
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                when {
                    url.startsWith("tel:") -> {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                        startActivity(intent)
                        return true
                    }
                    url.startsWith("mailto:") -> {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(url))
                        startActivity(intent)
                        return true
                    }
                    url.startsWith("geo:") -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                        return true
                    }
                    url.startsWith("sms:") -> {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(url))
                        startActivity(intent)
                        return true
                    }
                    url.startsWith("http://") || url.startsWith("https://") -> {
                        // 기본적으로 WebView에서 처리
                        return false
                    }
                    else -> return super.shouldOverrideUrlLoading(view, request)
                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.d(TAG, "onPageStarted Uri: " + url)
                //binding.progressBar.visibility = ProgressBar.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d(TAG, "onPageFinished Uri: " + url)
                //binding.progressBar.visibility = ProgressBar.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.d(TAG, "onReceivedError error: " + error)
                Toast.makeText(requireActivity(), "error : " + error?.description.toString(), Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun initWebChromeClient() {
        Log.d(TAG, "initWebChromeClient")

        // WebChromeClient 설정
        binding.webview.webChromeClient = object : WebChromeClient() {
            // 페이지 로딩 진행 상태 처리
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                Log.d(TAG, "onProgressChanged newProgress: " + newProgress)
                if (newProgress < 100) {
                    //binding.progressBar.visibility = ProgressBar.VISIBLE
                    binding.viewModel?.setLoading(true)
                } else {
                    //binding.progressBar.visibility = ProgressBar.GONE
                    binding.viewModel?.setLoading(false)
                }
                binding.progressBar.progress = newProgress
            }

            // 웹 페이지 제목 변경 처리
            override fun onReceivedTitle(view: WebView?, title: String?) {
                title?.let {
                    //ZEROS TEST setTitle(it)
                }
            }

            // 자바스크립트 경고 처리
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                Log.d(TAG, "onJsAlert url: " + url)
                Log.d(TAG, "onJsAlert message: " + message)
                Log.d(TAG, "onJsAlert result: " + result)
                AlertDialog.Builder(requireActivity())
                    .setTitle("Alert")
                    .setMessage(message)
                    .setPositiveButton("확인") { dialog, _ ->
                        result?.confirm()
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            // 자바스크립트 확인 처리
            override fun onJsConfirm(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                Log.d(TAG, "onJsConfirm url: " + url)
                Log.d(TAG, "onJsConfirm message: " + message)
                Log.d(TAG, "onJsConfirm result: " + result)
                AlertDialog.Builder(requireActivity())
                    .setTitle("Confirm")
                    .setMessage(message)
                    .setPositiveButton("확인") { dialog, _ ->
                        result?.confirm()
                        dialog.dismiss()
                    }
                    .setNegativeButton("취소") { dialog, _ ->
                        result?.cancel()
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onJsPrompt(
                view: WebView,
                url: String?,
                message: String?,
                defaultValue: String?,
                result: JsPromptResult
            ): Boolean {
                val input = EditText(view.context)

                AlertDialog.Builder(view.context)
                    .setCancelable(false)
                    .setMessage(message)
                    .setView(input)
                    .setPositiveButton("확인") { dialog, _ ->
                        result.confirm()
                        dialog.dismiss()
                    }
                    .setNegativeButton("취소") { dialog, _ ->
                        result.cancel()
                        dialog.dismiss()
                    }
                    .create()
                    .show()
                return true
            }

        }
    }

    private fun initFcmMessage() {
        // FCM 기기 토큰 가져오기
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d(TAG, "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                // 새로운 FCM 등록 토큰 가져오기
                val token = task.result
                Log.d(TAG, "FCM Registration Token: $token")

                // 서버에 토큰 전송하거나 다른 필요한 작업 수행
                sendRegistrationToServer(token)
            }
    }

    private fun sendRegistrationToServer(token: String?) {
        // 서버에 토큰을 전송하는 로직을 여기에 구현
        Log.d(TAG, "Token sent to server: $token")
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
            ContextCompat.checkSelfPermission(requireActivity(), it) == PackageManager.PERMISSION_GRANTED
        }

        Log.d(TAG, "initGrantPermission grant: " + grant)

        if(!grant){
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        return grant;
    }

    @Deprecated("Deprecated in Java")
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
                    Toast.makeText(requireContext(), "모든 권한이 부여되었습니다.", Toast.LENGTH_LONG).show()
                    startLoadWebUrl()
                } else {
                    // 일부 권한이 거부됨
                    Toast.makeText(requireContext(), "필요한 모든 권한을 부여해야 합니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}