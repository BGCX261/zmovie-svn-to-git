package com.itconnect.inc.zmovie;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.google.analytics.tracking.android.EasyTracker;
import com.itconnect.inc.zmovie.controller.GenreAdapter;
import com.itconnect.inc.zmovie.data_acces.FavoritVidoeDAO;
import com.itconnect.inc.zmovie.data_acces.videojson;
import com.itconnect.inc.zmovie.model.Video;
import com.itconnect.inc.zmovie.model.genreData;
import com.itconnect.inc.zmovie.ui.fragment.ErrorFragment;
import com.itconnect.inc.zmovie.ui.fragment.ErrorFragment.typeActivity;
import com.itconnect.inc.zmovie.ui.fragment.GridVideoFragment;
import com.itconnect.inc.zmovie.ui.widget.BlockTouchFrameLayout;
import com.itconnect.inc.zmovie.ui.widget.LocalePopupMenu;

@SuppressLint("NewApi")
public class activity_main extends Activity implements OnClickListener {
	private BlockTouchFrameLayout header;
	private View headerLine;
	private BlockTouchFrameLayout content;
	private DrawerLayout mDrawerLayout;
	private BlockTouchFrameLayout mContentFrame;
	private RelativeLayout mDrawer;
	private genreData genres;
	private ListView GenreList;
	private final int VIDEO_LIST_LOADER_ID = 1001;
	private Video[] listallvideo;
	private EditText searchView;
	TextView titrl;
	private EasyTracker easyTracker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Popcorn_Classic);

		super.onCreate(savedInstanceState);
		init();
		// Header
		View header = setPopcornHeaderView(R.layout.header_main);
		header.findViewById(R.id.popcorn_action_menu).setOnClickListener(this);
		header.findViewById(R.id.popcorn_action_overflow).setOnClickListener(
				this);
		titrl = (TextView) header.findViewById(R.id.heder_titel);
		Typeface face = Typeface
				.createFromAsset(getAssets(), "font/Kokila.ttf");
		titrl.setTypeface(face);
		Spannable WordtoSpan = new SpannableString(titrl.getText());
		WordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 1,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		titrl.setText(WordtoSpan);
		// Content
		View content = setPopcornContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) content
				.findViewById(R.id.main_drawer_layout);

		mDrawerLayout.setFocusableInTouchMode(false);
		mDrawerLayout.setDrawerListener(drawerListener);
		mContentFrame = (BlockTouchFrameLayout) content
				.findViewById(R.id.main_content_frame);
		mDrawer = (RelativeLayout) content.findViewById(R.id.main_drawer);
		searchView = (EditText) mDrawer.findViewById(R.id.main_drawer_search);
		searchView.setOnEditorActionListener(searchListener);
		GenreList = (ListView) mDrawer.findViewById(R.id.main_drawer_list);
		genres = new genreData(1, getResources().getStringArray(R.array.genres));
		GenreAdapter adapter = new GenreAdapter(activity_main.this, genres);
		GenreList.setAdapter(adapter);
		GenreList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selecteGenre(position);
				TextView a = (TextView) view;
				Spannable WordtoSpan = new SpannableString(a.getText());
				WordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 1,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				titrl.setText(WordtoSpan);

			}
		});
		selecteGenre(0);
	}

	private void LoadDatat(final int position) {

		GenreList.setItemChecked(position, true);
		GenreList.clearFocus();
		GenreList.post(new Runnable() {
			@Override
			public void run() {
				GenreList.setSelection(position);
			}
		});
		// searchView.clearFocus();
		restartVideosLoader(0);
	}

	private void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_base);
		header = (BlockTouchFrameLayout) findViewById(R.id.popcorn_header);
		headerLine = findViewById(R.id.popcorn_header_line);
		content = (BlockTouchFrameLayout) findViewById(R.id.popcorn_content);
		easyTracker = EasyTracker.getInstance(activity_main.this);
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

	public View setPopcornHeaderView(int layoutResID) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(layoutResID, null, false);
		header.addView(view);
		return view;
	}

	public View setPopcornContentView(int layoutResID) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(layoutResID, null, false);
		content.addView(view);
		return view;
	}

	private void onOverflowPressed(View v) {
		LocalePopupMenu popup = new LocalePopupMenu(activity_main.this, v);
		popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.action_favorites:

					try {
						replaceFragment(new GridVideoFragment(FavoritVidoeDAO
								.get(getApplicationContext())));
					} catch (JSONException e) {
						replaceFragment(new ErrorFragment(
								typeActivity.activity_main));
						e.printStackTrace();
					}
					return true;
				case R.id.action_share:
					Intent sharingIntent = new Intent(
							android.content.Intent.ACTION_SEND);
					sharingIntent.setType("text/plain");
					String utl = "https://play.google.com/store/apps/details?id="
							+ getApplicationContext().getPackageName();
					String shareBody = String.format(
							getResources().getString(R.string.share_msg),
							getResources().getString(R.string.app_name), utl);
					sharingIntent.putExtra(
							android.content.Intent.EXTRA_SUBJECT,
							getResources().getString(R.string.app_name));
					sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
							shareBody);

					startActivity(sharingIntent);
					return true;
				default:
					return false;
				}

			}
		});
		popup.inflate(R.menu.popup_main);
		popup.show();
	}

	private void onMenuClick() {
		if (mDrawerLayout.isDrawerOpen(mDrawer)) {
			mDrawerLayout.closeDrawer(mDrawer);

		} else {
			mDrawerLayout.openDrawer(mDrawer);
			// if (currentVideoData != null) {
			// mGenreList.setSelection(currentVideoData.getGenrePosition());
			// }
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.popcorn_action_menu:
			onMenuClick();
			break;
		case R.id.popcorn_action_overflow:
			if (mDrawerLayout.isDrawerOpen(mDrawer)) {
				mDrawerLayout.closeDrawer(mDrawer);
			}
			onOverflowPressed(v);
			break;
		default:
			break;
		}
	}

	private DrawerListener drawerListener = new DrawerListener() {

		@Override
		public void onDrawerStateChanged(int arg0) {

		}

		@Override
		public void onDrawerSlide(View arg0, float arg1) {

		}

		@Override
		public void onDrawerOpened(View arg0) {
			mContentFrame.setBlockTouchEvent(true);
		}

		@Override
		public void onDrawerClosed(View arg0) {
			mContentFrame.setBlockTouchEvent(false);
		}
	};

	private OnItemClickListener genreListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selecteGenre(position);
		}
	};

	public void selecteGenre(int position) {
		// TODO Auto-generated method stub
		genres.setPosition(position);
		restartVideosLoader(position);
	}

	private void restartVideosLoader(int position) {
		new TheTask(position).execute();

	}

	public void showLoading() {
		replaceFragment(new com.itconnect.inc.zmovie.ui.fragment.LoadingFragment());
	}

	protected void replaceFragment(Fragment fragment) {
		FragmentTransaction tr = getFragmentManager().beginTransaction();
		tr.replace(R.id.main_content_frame, fragment);
		tr.commit();

	}

	private void loadVideo(int position) throws Exception {
		// TODO Auto-generated method stub
		listallvideo = videojson.getVideolist(activity_main.this, position);

	}

	private Video[] loadVideo(String query) throws Exception {
		// TODO Auto-generated method stub
		return videojson.getVideolist(activity_main.this, query);
	}

	private OnEditorActionListener searchListener = new OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (actionId == EditorInfo.IME_ACTION_SEARCH) {
				String keywords = v.getText().toString();
				new SearchTask(keywords).execute();
				searchView.setText("");
				v.clearFocus();

				return true;
			}

			return false;
		}
	};

	class TheTask extends AsyncTask<Void, Void, Void> {
		int position;
		Exception exception = null;

		public TheTask(int position) {
			// TODO Auto-generated constructor stub
			this.position = position;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (this.exception == null)
				replaceFragment(new com.itconnect.inc.zmovie.ui.fragment.GridVideoFragment(
						listallvideo));
			else
				replaceFragment(new ErrorFragment(typeActivity.activity_main));
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
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mDrawerLayout.closeDrawers();
				}
			});

			try {
				loadVideo(this.position);
			} catch (Exception e) {
				exception = e;
			}
			return null;
		}

	}

	class SearchTask extends AsyncTask<Void, Void, Void> {
		String query;
		Exception exception = null;
		Video[] resukt = null;

		public SearchTask(String query) {
			// TODO Auto-generated constructor stub
			this.query = query;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (this.exception == null)
				replaceFragment(new com.itconnect.inc.zmovie.ui.fragment.SearchVideoFragment(
						resukt));
			else
				replaceFragment(new ErrorFragment(typeActivity.activity_main));

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
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mDrawerLayout.closeDrawers();
				}
			});

			try {
				resukt = loadVideo(this.query);
			} catch (Exception e) {
				exception = e;
			}
			return null;
		}

	}

}
