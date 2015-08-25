package com.itconnect.inc.zmovie;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.analytics.tracking.android.EasyTracker;
import com.itconnect.inc.zmovie.data_acces.YoutubeJSON;
import com.itconnect.inc.zmovie.model.Video;
import com.itconnect.inc.zmovie.ui.fragment.ErrorFragment;
import com.itconnect.inc.zmovie.ui.fragment.ErrorFragment.typeActivity;

public class VideoActivity extends Activity {
	private Video Seletctedvideo;
	private String Seletctetyid;
	private String SeletctetVideoDescription;
	private String SeletctetVideoImdb_id;
	private String SeletctetVideoTitle;
	private String SeletctetVideoPoster;
	private String SeletctetVideoImdb_ratingr;
	private String SeletctetVideoyear;
	private String SeletctetVideoActors;
	private String SeletctetVideolang;
	private EasyTracker easyTracker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Popcorn_Classic);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_detaill);
		easyTracker = EasyTracker.getInstance(VideoActivity.this);
		selecteVoid();
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

	public void selecteVoid() {
		Seletctetyid = getIntent().getExtras().getString("yt_id");
		SeletctetVideoDescription = getIntent().getExtras().getString(
				"description");
		SeletctetVideoImdb_id = getIntent().getExtras().getString("imdb");
		SeletctetVideoTitle = getIntent().getExtras().getString("titel");
		SeletctetVideoPoster = getIntent().getExtras().getString("poster");
		SeletctetVideoyear = getIntent().getExtras().getString("year");
		SeletctetVideoImdb_ratingr = getIntent().getExtras()
				.getString("rating");
		SeletctetVideoActors = getIntent().getExtras().getString("actors");
		SeletctetVideolang = getIntent().getExtras().getString("lang");
		new TheTask().execute();
	}

	public void showLoading() {
		replaceFragment(new com.itconnect.inc.zmovie.ui.fragment.LoadingFragment());
	}

	protected void replaceFragment(Fragment fragment) {
		FragmentTransaction tr = getFragmentManager().beginTransaction();
		tr.replace(R.id.main_content_frame, fragment);
		tr.commit();

	}

	private void loadVideoData() throws Exception {
		// TODO Auto-generated method stub
		// Seletctedvideo = YoutubeJSON.getYTVideo(VideoActivity.this,
		// Seletctetyid);
		Seletctedvideo = new Video();
		Seletctedvideo.setYt_id(Seletctetyid);
		Seletctedvideo.setTitle(SeletctetVideoTitle);
		Seletctedvideo.setYear(SeletctetVideoyear);
		Seletctedvideo.setImdb_id(SeletctetVideoImdb_id);
		Seletctedvideo.setImdb_rating(SeletctetVideoImdb_ratingr);
		Seletctedvideo.setDescription(SeletctetVideoDescription);
		Seletctedvideo.setPoster_med(SeletctetVideoPoster);
		Seletctedvideo.setActors(SeletctetVideoActors);
		Seletctedvideo.setLang(SeletctetVideolang);

	}

	class TheTask extends AsyncTask<Void, Void, Void> {
		Exception exception = null;

		@Override
		protected void onPostExecute(Void result) {
			if (this.exception == null)
				replaceFragment(new com.itconnect.inc.zmovie.ui.fragment.VideoMovieFragment(
						Seletctedvideo));
			else
				replaceFragment(new ErrorFragment(typeActivity.VideoActivity));
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			// pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			showLoading();
			try {
				loadVideoData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				exception = e;
			}
			return null;
		}

	}

}
