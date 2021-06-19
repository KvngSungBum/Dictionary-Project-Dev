package com.example.projectdictionary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.projectdictionary.databinding.FragmentTranslateBinding


class TranslateFragment : Fragment() {
    private var binding: FragmentTranslateBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTranslateBinding.inflate(inflater,container,false)
        return binding!!.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding?.apply {
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.settings.builtInZoomControls = true
            webView.settings.defaultTextEncodingName="utf-8"
            webView.loadUrl("https://papago.naver.com/")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}