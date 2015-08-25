package com.itconnect.inc.zmovie.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.controller.VideoAdapter;


@SuppressLint("NewApi")
public class LoadingFragment extends Fragment {
	private VideoAdapter mVideosAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_loading, container, false);
		return view;
	}
}