package com.itconnect.inc.zmovie;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.google.analytics.tracking.android.EasyTracker;
import com.itconnect.inc.zmovie.ui.widget.LocalePopupMenu;

@SuppressLint("NewApi")
public class PlayerActivity extends Activity {
	private WebView movie;
	String titel;
	private EasyTracker easyTracker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = getIntent().getStringExtra("MOVIE_URL");
		titel = getIntent().getStringExtra("titel");
		setContentView(R.layout.activity_player);
		easyTracker = EasyTracker.getInstance(PlayerActivity.this);
		movie = (WebView) findViewById(R.id.trailer_view);
		movie.getSettings().setJavaScriptEnabled(true);
		movie.getSettings().setDomStorageEnabled(true);
		movie.getSettings().setJavaScriptEnabled(true);
		movie.getSettings().setAllowFileAccess(true);

		String playVideo = "<html> <style>"
				+ "body {margin-top: 0px; margin-bottom: 0px; margin-left: 0px; margin-right: 0px;"
				+ " padding: 0;}"
				+ "</style> <body bgcolor='#000000'><iframe class=\"youtube-player\" type=\"text/html\" width=\"100%\" height=\"100%\" src=\"http://www.youtube.com/embed/"
				+ url + "\" frameborder=\"0\"></body></html>";
		movie.loadData(playVideo, "text/html", "utf-8");
		findViewById(R.id.popcorn_action_overflow).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						onOverflowPressed(v);
					}
				});

	}

	private void onOverflowPressed(final View v) {
		LocalePopupMenu popup = new LocalePopupMenu(PlayerActivity.this, v);
		popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				switch (item.getItemId()) {
				case R.id.action_share:
					Intent sharingIntent = new Intent(
							android.content.Intent.ACTION_SEND);
					sharingIntent.setType("text/plain");
					String utl = "https://play.google.com/store/apps/details?id="
							+ getApplicationContext().getPackageName();
					String shareBody = String.format(v.getResources()
							.getString(R.string.share_video_msg), titel, utl);
					sharingIntent.putExtra(
							android.content.Intent.EXTRA_SUBJECT, titel);
					sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
							shareBody);

					startActivity(sharingIntent);
					break;

				default:
					return false;
				}

				return false;

			}
		});
		popup.inflate(R.menu.player);
		popup.show();
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

	@Override
	protected void onDestroy() {
		movie.loadUrl("about:blank");
		super.onDestroy();
	}

}
