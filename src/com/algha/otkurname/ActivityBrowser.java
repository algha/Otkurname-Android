package com.algha.otkurname;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ActivityBrowser   extends Activity {
	
	private String url;
	private String title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_browser);
		
		url = getIntent().getExtras().getString("url");
        title = getIntent().getExtras().getString("title");
        if (title == "") {
			title = "مەزمۇنى";
		}
		
		TextView textView = (TextView)findViewById(R.id.title);
        textView.setTypeface(App.TYPE_FACE);
        textView.setText(title);
        
        final View loading = (View)findViewById(R.id.loading);
        final WebView webview = (WebView)findViewById(R.id.webview);
        webview.setVisibility(View.GONE);
        webview.getSettings().setJavaScriptEnabled(true);
        
        final Activity activity = this;
        webview.setWebChromeClient(new WebChromeClient() {
          public void onProgressChanged(WebView view, int progress) {
            // Activities and WebViews measure progress with different scales.
            // The progress meter will automatically disappear when we reach 100%
            activity.setProgress(progress * 1000);
          }
        });
        webview.setWebViewClient(new WebViewClient() {
	        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	          
	        }
	        @Override
	        public void onPageFinished(WebView view, String url) {
	       		// TODO Auto-generated method stub
	       		super.onPageFinished(view, url);
	       		loading.setVisibility(View.GONE);
	        	webview.setVisibility(View.VISIBLE);
	        }
	        @Override
	        public void onPageStarted(WebView view, String url, Bitmap favicon) {
	        	// TODO Auto-generated method stub
	        	super.onPageStarted(view, url, favicon);
	        	loading.setVisibility(View.VISIBLE);
	        	webview.setVisibility(View.GONE);
	        }
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        	// TODO Auto-generated method stub
	        	if (url.endsWith(".apk") || url.endsWith(".pdf")) {
					Uri uri = Uri.parse(url);
					Intent it = new Intent(Intent.ACTION_VIEW,uri);
					startActivity(it);
					return false;
				}
	        	return super.shouldOverrideUrlLoading(view, url);
	        }
        });
        webview.loadUrl(url);
	}
}
