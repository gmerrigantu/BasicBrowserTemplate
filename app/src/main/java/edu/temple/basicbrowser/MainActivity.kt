package edu.temple.basicbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var goButton: ImageButton
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)

        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                var safeURL = "https://"
                if (url != null && !url.startsWith("https://"))
                    safeURL.plus(url)
                else
                    safeURL = url.toString()
                Log.d("URL", safeURL)
                urlEditText.setText(safeURL)
                super.onPageFinished(view, url)
            }
        }

        goButton.setOnClickListener {
            val url = urlEditText.text.toString()
            var safeURL = "https://"
            if (url != null && !url.startsWith("https://"))
                safeURL = safeURL.plus(url)
            else
                safeURL = url.toString()
            Log.d("URL", safeURL)
            urlEditText.setText(safeURL)
            webView.loadUrl(safeURL)
        }


    }
}