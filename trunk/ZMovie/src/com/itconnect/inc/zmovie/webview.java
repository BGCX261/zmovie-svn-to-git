package com.itconnect.inc.zmovie;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends Activity {
	private WebView movie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = getIntent().getStringExtra("imbdurl");
		setContentView(R.layout.activity_player);
		movie = (WebView) findViewById(R.id.trailer_view);
		movie.getSettings().setJavaScriptEnabled(true);
		movie.getSettings().setDomStorageEnabled(true);
		movie.getSettings().setJavaScriptEnabled(true);
		movie.getSettings().setAllowFileAccess(true);
		movie.setWebViewClient(new WebViewClient());
		movie.loadUrl(url);

	}
}
