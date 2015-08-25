package com.itconnect.inc.zmovie.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.itconnect.inc.zmovie.VideoActivity;
import com.itconnect.inc.zmovie.model.Video;

public class VideoItemListener implements OnClickListener {

	private Context context;
	private Video info;

	public VideoItemListener(Context context, Video info) {
		this.context = context;
		this.info = info;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, VideoActivity.class);
		Bundle extras = new Bundle();
		extras.putString("yt_id", info.getYt_id());
		extras.putString("description", info.getDescription());
		extras.putString("imdb", info.getImdb_id());
		extras.putString("year", info.getYear());
		extras.putString("titel", info.getTitle());
		extras.putString("poster", info.getPoster_med());
		extras.putString("rating", info.getImdb_rating());
		extras.putString("actors", info.getActors());
		extras.putString("lang", info.getLang());
		
		intent.putExtras(extras);
		context.startActivity(intent);
	}
}