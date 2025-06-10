package alexus.studio.animekaiapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var webView: WebView
    private lateinit var errorLayout: LinearLayout
    private lateinit var retryButton: Button
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var fullscreenContainer: FrameLayout
    
    private var customView: View? = null
    private var customViewCallback: WebChromeClient.CustomViewCallback? = null
    private var originalOrientation: Int = 0
    private var originalSystemUiVisibility: Int = 0
    
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViews()
        setupWebView()
        loadWebsite()
    }
    
    private fun initViews() {
        webView = findViewById(R.id.webView)
        errorLayout = findViewById(R.id.errorLayout)
        retryButton = findViewById(R.id.retryButton)
        loadingIndicator = findViewById(R.id.loadingIndicator)
        fullscreenContainer = findViewById(R.id.fullscreenContainer)
        
        retryButton.setOnClickListener {
            loadWebsite()
        }
    }
    
    private fun loadWebsite() {
        showLoading()
        webView.loadUrl("https://animekai.to/home")
    }
    
    private fun showLoading() {
        webView.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
    }
    
    private fun showError() {
        webView.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        loadingIndicator.visibility = View.GONE
    }
    
    private fun showContent() {
        webView.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
        loadingIndicator.visibility = View.GONE
    }

    private fun setupWebView() {
        // Nastavení WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.userAgentString = "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36"
        webSettings.mediaPlaybackRequiresUserGesture = false
        
        // Nastavení WebViewClient
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url?.toString()
                return if (url != null && url.contains("animekai.to")) {
                    view?.loadUrl(url)
                    true
                } else {
                    false
                }
            }
            
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
            }
            
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showContent()
            }
            
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                if (request?.isForMainFrame == true) {
                    showError()
                }
            }
            
            override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                super.onReceivedHttpError(view, request, errorResponse)
                if (request?.isForMainFrame == true && errorResponse?.statusCode != 200) {
                    showError()
                }
            }
        }
        
        // Nastavení WebChromeClient pro fullscreen video podporu
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                // Zde můžete přidat progress bar pokud chcete
            }
            
            override fun onShowCustomView(view: View, callback: CustomViewCallback) {
                // Pokud už je fullscreen aktivní, ukončit ho
                if (customView != null) {
                    onHideCustomView()
                    return
                }
                
                // Uložení aktuálního stavu
                customView = view
                originalSystemUiVisibility = window.decorView.systemUiVisibility
                originalOrientation = requestedOrientation
                customViewCallback = callback
                
                // Přidání custom view do fullscreen containeru
                fullscreenContainer.addView(view, FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                ))
                
                // Skrytí ostatních views a zobrazení fullscreen
                webView.visibility = View.GONE
                fullscreenContainer.visibility = View.VISIBLE
                
                // Nastavení fullscreen módu
                window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )
                
                // Keep screen on během video přehrávání
                window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
            
            override fun onHideCustomView() {
                // Ukončení fullscreen módu
                if (customView == null) return
                
                // Skrytí fullscreen containeru
                fullscreenContainer.visibility = View.GONE
                fullscreenContainer.removeView(customView)
                
                // Zobrazení WebView
                webView.visibility = View.VISIBLE
                
                // Obnovení původního stavu
                window.decorView.systemUiVisibility = originalSystemUiVisibility
                requestedOrientation = originalOrientation
                
                // Clear flags
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                
                // Cleanup
                customView = null
                customViewCallback?.onCustomViewHidden()
                customViewCallback = null
            }
            
            override fun getVideoLoadingProgressView(): View? {
                // Můžete vrátit custom loading view pro video
                return null
            }
        }
    }
    
    override fun onBackPressed() {
        // Pokud je aktivní fullscreen video, ukončit ho
        if (customView != null) {
            webView.webChromeClient?.onHideCustomView()
            return
        }
        
        if (webView.canGoBack() && errorLayout.visibility == View.GONE) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
    
    override fun onPause() {
        super.onPause()
        webView.onPause()
    }
    
    override fun onResume() {
        super.onResume()
        webView.onResume()
    }
    
    override fun onDestroy() {
        // Cleanup fullscreen pokud je aktivní
        if (customView != null) {
            webView.webChromeClient?.onHideCustomView()
        }
        webView.destroy()
        super.onDestroy()
    }
}