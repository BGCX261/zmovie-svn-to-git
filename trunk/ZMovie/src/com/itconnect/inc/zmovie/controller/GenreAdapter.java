package com.itconnect.inc.zmovie.controller;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.model.genreData;

public class GenreAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private genreData genres;
	public GenreAdapter(Context context,  genreData strings) {
		genres=strings;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return genres.getListgenr().length;
	}


	
	@Override
	public String getItem(int position) {
		if (genres.getListgenr() != null) {
			return genres.getListgenr()[position];
		}

		return "";
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GenreHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_list_genre, parent,
					false);
			holder = new GenreHolder();
			holder.name = (TextView) convertView.findViewById(R.id.genre_name);
			convertView.setTag(holder);
		} else {
			holder = (GenreHolder) convertView.getTag();
		}

		String name = getItem(position);
		if (genres.getPosition() == position) {
			holder.name.setText(Html.fromHtml("<b>" + name + "</b>"));
		} else {
			holder.name.setText(name);
		}

		return convertView;
	}

	private class GenreHolder {
		public TextView name;
	}

}
