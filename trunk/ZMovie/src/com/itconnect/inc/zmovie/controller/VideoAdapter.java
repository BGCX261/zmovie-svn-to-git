package com.itconnect.inc.zmovie.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.model.Video;
import com.itconnect.inc.zmovie.utils.RoundDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class VideoAdapter extends BaseAdapter {

	private Activity activity;
	private List<Video> data;
	private LayoutInflater inflater;
	private DisplayImageOptions options;
	ImageLoader imageLoader;

	public VideoAdapter(Activity context, List<Video> data) {
		this.activity = context;
		this.data = data;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisk(true).displayer(new RoundDisplayer(6))
				.showImageOnLoading(R.drawable.ic_launcher).build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Video getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		VideoHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_grid_video, parent,
					false);
			holder = new VideoHolder();
			holder.poster = (ImageView) convertView
					.findViewById(R.id.video_poster);
			holder.name = (TextView) convertView.findViewById(R.id.video_name);
			holder.year = (TextView) convertView.findViewById(R.id.video_year);
			convertView.setTag(holder);
		} else {
			holder = (VideoHolder) convertView.getTag();
		}

		Video info = getItem(position);

		 holder.poster.setOnClickListener(new VideoItemListener(activity, info));
		// holder.poster.setOnLongClickListener(new FavoritesListener(activity,
		// info));
		imageLoader.displayImage(info.getPoster_med(),
				holder.poster, options);
		holder.name.setText(Html.fromHtml("<b>" + info.getTitle() + "</b>"));
		holder.year.setText(info.getYear());

		return convertView;
	}

	public void addData(List<Video> data) {
		this.data.addAll(data);
		notifyDataSetChanged();
	}

	public class VideoHolder {
		public ImageView poster;
		public TextView name;
		public TextView year;
	}
}