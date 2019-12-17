package com.example.MyApplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.MyApplication2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        et_url.setOnKeyListener { v, keyCode, event ->

            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                var getURL  = "http://"+et_url.text.toString()
                reqUrl(getURL)
                return@setOnKeyListener true
            }
            false
        }

        btn_go.setOnClickListener {

            var getURL = "http://"+et_url.text.toString()
            reqUrl(getURL)

        }

    }

    fun reqUrl(url : String){

        webView.webViewClient = object : WebViewClient(){
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)

                try {

                    var getURL = "https://"+et_url.text.toString()
                    reqUrl(getURL)

                }catch (er: Exception){

                    Toast.makeText(context,"Load ไม่ได้นะจ๊ะ!!",Toast.LENGTH_LONG).show()
                }

            }
        }

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

    override fun onBackPressed() {

        if(webView.canGoBack()){

            webView.goBack()

        }else{
            super.onBackPressed()
        }
    }
}
