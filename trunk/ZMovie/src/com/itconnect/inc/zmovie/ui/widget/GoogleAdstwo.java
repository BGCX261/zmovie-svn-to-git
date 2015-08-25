package com.itconnect.inc.zmovie.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.itconnect.inc.zmovie.R;

public class GoogleAdstwo extends RelativeLayout {
	AdView mAdView;

	public GoogleAdstwo(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.googleadstwo, this);
		mAdView = (AdView) this.findViewById(R.id.adView);
				AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
	
	}

	
}
