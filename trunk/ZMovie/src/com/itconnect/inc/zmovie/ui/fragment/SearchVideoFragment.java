package com.itconnect.inc.zmovie.ui.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.controller.VideoAdapter;
import com.itconnect.inc.zmovie.model.Video;

public class SearchVideoFragment extends Fragment {
	private Video[] listallvideo = null;
	private VideoAdapter mVideosAdapter;
	private GridView videoGrid;
	private boolean canLoadPage;

	private final int ROW_COUNT_FOR_NEXT_PAGE = 4;

	public SearchVideoFragment(Video[] listallvideo) {
		super();
		this.listallvideo = listallvideo;
	}

	public SearchVideoFragment(List<Video> list) {
		// TODO Auto-generated constructor stub
		if (list != null) {
			Video[] customs = new Video[list.size()];
			this.listallvideo = list.toArray(customs);

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_grid_video, container,
				false);
		if (listallvideo != null) {
			if(listallvideo.length==0)
				Toast.makeText(getActivity(),
						getString(R.string.data_not_available), Toast.LENGTH_SHORT)
						.show();
			List<Video> stringList = new ArrayList<Video>(
					Arrays.asList(listallvideo));
			mVideosAdapter = new VideoAdapter(getActivity(), stringList);
			canLoadPage = true;
		} else

			Toast.makeText(getActivity(),
					getString(R.string.data_not_available), Toast.LENGTH_SHORT)
					.show();
		populateView(view);
		return view;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// change orientation
		ViewGroup container = (ViewGroup) getView();
		container.removeAllViewsInLayout();
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_grid_video, container);
		populateView(view);
	}

	private void populateView(View view) {
		videoGrid = (GridView) view.findViewById(R.id.videos_content_view);
		videoGrid.setAdapter(mVideosAdapter);
		// videoGrid.setOnScrollListener(contentScrollListener);
	}
	//
	// private OnScrollListener contentScrollListener = new OnScrollListener() {
	//
	// @Override
	// public void onScrollStateChanged(AbsListView view, int scrollState) {
	//
	// }
	//
	// @Override
	// public void onScroll(AbsListView view, int firstVisibleItem,
	// int visibleItemCount, int totalItemCount) {
	// if (canLoadPage && totalItemCount != 0) {
	// int dec = totalItemCount - firstVisibleItem;
	// if (dec <= videoGrid.getNumColumns() * ROW_COUNT_FOR_NEXT_PAGE) {
	// canLoadPage = false;
	// // restartVideosPageLoader();
	// }
	// }
	// }
	// };

}
