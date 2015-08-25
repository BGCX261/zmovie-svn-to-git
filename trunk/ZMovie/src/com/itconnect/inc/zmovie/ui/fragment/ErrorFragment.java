package com.itconnect.inc.zmovie.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.VideoActivity;
import com.itconnect.inc.zmovie.activity_main;

public class ErrorFragment extends Fragment {
	private TextView noConnection;
	private Button retry;
	private typeActivity type;
	
	public ErrorFragment(typeActivity type) {
		super();
		this.type = type;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_no_connection, container, false);
		noConnection = (TextView) view.findViewById(R.id.no_connection_label);
		retry = (Button) view.findViewById(R.id.connection_retry_btn);
		retry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(type==typeActivity.activity_main)
				((activity_main)getActivity()).selecteGenre(0);
				else
					((VideoActivity)getActivity()).selecteVoid();;
					
			}
		});

		return view;
	}
	public enum typeActivity {
		activity_main, VideoActivity
	}
}

