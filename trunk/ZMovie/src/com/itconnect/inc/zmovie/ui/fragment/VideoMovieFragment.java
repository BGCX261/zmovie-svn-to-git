package com.itconnect.inc.zmovie.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.itconnect.inc.zmovie.PlayerActivity;
import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.webview;
import com.itconnect.inc.zmovie.data_acces.FavoritVidoeDAO;
import com.itconnect.inc.zmovie.model.Video;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class VideoMovieFragment extends Fragment {

	protected TextView title;
	protected TextView description;
	private ImageButton trailer;
	private TextView trailerText;
	private Button video_watchitnow;
	private RatingBar ratebare;
	protected ToggleButton favorites;
	private Video movieInfo;
	protected ImageView poster;
	protected boolean isFavorites;
	ImageLoader imageLoader;

	public VideoMovieFragment(Video movieInfo) {
		super();
		this.movieInfo = movieInfo;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_video_movie, container,
				false);

		populateView(view);
		
		return view;
	}

	protected void populateView(View view) {
		poster = (ImageView) view.findViewById(R.id.video_poster);
		TextView actors = (TextView) view.findViewById(R.id.video_movie_actors);
		title = (TextView) view.findViewById(R.id.video_title);
		ratebare = (RatingBar) view.findViewById(R.id.video_rating);
		favorites = (ToggleButton) view.findViewById(R.id.video_favorites);
		isFavorites = FavoritVidoeDAO.contain(movieInfo, getActivity());
		favorites.setChecked(isFavorites);
		favorites.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				try {
					if (isFavorites)
						FavoritVidoeDAO.remove(getActivity(), movieInfo);
					else
						FavoritVidoeDAO.save(getActivity(), movieInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		if (!movieInfo.getImdb_rating().isEmpty())
			try {
				ratebare.setRating(Float.parseFloat(movieInfo.getImdb_rating()));
			} catch (Exception c) {

			}
		else
			ratebare.setVisibility(View.GONE);

		if (movieInfo.getImdb_id().isEmpty())
			view.findViewById(R.id.video_imdblink).setVisibility(View.GONE);
		else {
			view.findViewById(R.id.video_imdblink).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(getActivity(),
									webview.class);
							Bundle extras = new Bundle();
							extras.putString(
									"imbdurl",
									"http://www.imdb.com/title/"
											+ movieInfo.getImdb_id());
							intent.putExtras(extras);
							getActivity().startActivity(intent);
						}
					});
		}

		title.setText(Html.fromHtml("<b>" + movieInfo.getTitle() + "</b>"));
		actors.setText(getResources().getString(R.string.actors) + ":\n"
				+ movieInfo.getActors());
		description = (TextView) view.findViewById(R.id.video_description);
		description.setText(Html.fromHtml(movieInfo.getDescription()));
		DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(false).cacheOnDisk(true).build();
		ImageLoader.getInstance().displayImage(movieInfo.getPoster_med(),
				poster, imageOptions);
		video_watchitnow = (Button) view.findViewById(R.id.video_watchitnow);
		video_watchitnow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), PlayerActivity.class);
				Bundle extras = new Bundle();
				extras.putString("MOVIE_URL", movieInfo.getYt_id());
				extras.putString("titel", movieInfo.getTitle());
				intent.putExtras(extras);
				v.getContext().startActivity(intent);
			}
		});
	

	}

}