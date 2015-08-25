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

public class GoogleAds extends RelativeLayout {
	AdView mAdView;
	Button clsbutton;
	LinearLayout layoutads;
	private Handler handler = new Handler();

	public GoogleAds(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.googleads, this);
		mAdView = (AdView) this.findViewById(R.id.adView);
		clsbutton = (Button) this.findViewById(R.id.close_player_ads);
		layoutads = (LinearLayout) this.findViewById(R.id.layoutads);
		clsbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hidevideoads();
			}
		});

		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		Runnable runnable = new Runnable() {
			public void run() {
				showvideoads();
				handler.postDelayed(this,
						getResources().getInteger(R.integer.refresh_delay));
			}
		};
		handler.postDelayed(runnable,
				getResources().getInteger(R.integer.refresh_delay));

	}

	private void showvideoads() {

		layoutads.setVisibility(View.VISIBLE);

	}

	private void hidevideoads() {

		layoutads.setVisibility(View.INVISIBLE);

	}

}
