package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.main_fragment.MainFragment
import com.example.myapplication.widgets.web_view.CustomWebView

class CustomFragment : Fragment(R.layout.fragment_custom) {
    private var url: String? = null
    private val webView: CustomWebView by lazy { requireView().findViewById(R.id.webView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        url?.let { url ->
            webView.settings.apply {
                javaScriptEnabled = true
                builtInZoomControls = true
                displayZoomControls = false
                loadWithOverviewMode = true
                useWideViewPort = true
                setSupportZoom(false)
            }.also {
                webView.webChromeClient = WebChromeClient()
                webView.webViewClient = WebViewClient()
                webView.isScrollContainer = false
                webView.isVerticalScrollBarEnabled = false
                webView.isHorizontalScrollBarEnabled = false
                webView.setViewPager((parentFragment as MainFragment).viewPager)
                webView.loadUrl(url)
            }
        }
    }

    companion object {
        private const val ARG_URL = "url"

        @JvmStatic
        fun newInstance(url: String) =
            CustomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL, url)
                }
            }
    }
}