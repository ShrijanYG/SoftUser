package com.shrijan.esoft.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shrijan.esoft.R


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var wbView : WebView
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        binding(root)
        initialize()
        return root
    }

    private fun binding(v:View?)
    {
        wbView = v!!.findViewById(R.id.wbView)
    }

    private fun initialize()
    {
        var websettings: WebSettings = wbView.settings
        websettings.javaScriptEnabled = true
        // wbView.webViewClient =
        wbView.loadUrl("https://softwarica.edu.np/")
    }
}