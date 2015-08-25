package com.itconnect.inc.zmovie.utils;

import java.net.URL;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.itconnect.inc.zmovie.model.Video;

public class tools {
	public static Bitmap getFromUrl(String thumbUrl) throws Exception {

		URL url1 = new URL(thumbUrl);
		Bitmap bmp = BitmapFactory.decodeStream(url1.openConnection()
				.getInputStream());
		return bmp;
	}

	public static boolean contains(final List<com.itconnect.inc.zmovie.model.Video> array, final com.itconnect.inc.zmovie.model.Video v) {
		try {
			for (final com.itconnect.inc.zmovie.model.Video e : array) {
				if (e == v || v != null && v.getYt_id().equals(e.getYt_id()))
					return true;
			}
		} catch (Exception d) {
			return false;
		}
		return false;
	}

	public static int getposition(final List<com.itconnect.inc.zmovie.model.Video> array, final com.itconnect.inc.zmovie.model.Video v) {

		for (int i = 0; i < array.size(); i++)
			if (array.get(i) == v || v != null
					&& v.getYt_id().equals(array.get(i).getYt_id()))
				return i;

		return -2399;
	}

}
