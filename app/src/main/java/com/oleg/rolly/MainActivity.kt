package com.oleg.rolly

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.webview_holder.*

class MainActivity : AppCompatActivity() {

	private var url: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

//		url = "https://rolly-darom.ru/deserty"
		url = "https://rolly-darom.ru"
//        url = "https://yandex.ru"

		webView.webViewClient = MyWebViewClient()
//		webView.clearCache(true)
		webView.isHorizontalScrollBarEnabled = false
		// включаем поддержку JavaScript
		webView.settings.javaScriptEnabled = true
		webView.settings.domStorageEnabled = true
		webView.settings.loadWithOverviewMode = true
		webView.settings.useWideViewPort = true
		webView.webChromeClient = object : WebChromeClient() {
			override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
//				return super.onJsAlert(view, url, message, result)
				return false
			}
		}

		// указываем страницу загрузки
		webView.loadUrl(url)
	}


	private inner class MyWebViewClient : WebViewClient() {

		override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
			super.onPageStarted(view, url, favicon)
			progressBar.visibility = View.VISIBLE
		}

		override fun onPageFinished(view: WebView, url: String) {
			super.onPageFinished(view, url)
			progressBar.visibility = View.GONE
		}

	}

	override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack()
			return true
		}
		return super.onKeyDown(keyCode, event)
	}

}
