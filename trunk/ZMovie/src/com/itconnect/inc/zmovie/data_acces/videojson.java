package com.itconnect.inc.zmovie.data_acces;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.model.Video;

public class videojson {

	public static Video[] getVideolist(Context ctx, int position) throws Exception {
		JSONObject jObject = new JSONObject(postRequest(ctx,position));
		JSONArray contestantObjects = jObject.getJSONArray("MovieList");
		Video[] videolist = new Video[contestantObjects.length()];
		for (int i = 0; i < contestantObjects.length(); i++) {
			JSONObject c = contestantObjects.getJSONObject(i);
			Video a = new Video();
			a.setTitle(c.getString("title"));
			a.setYt_id(c.getString("yt_id"));
			a.setPoster_med(c.getString("poster_med"));
			a.setImdb_id(c.getString("imdbID"));
			a.setImdb_rating(c.getString("imdbRating"));
			a.setYear(c.getString("year"));
			a.setDescription(c.getString("description"));
			a.setDirector(c.getString("director"));
			a.setActors(c.getString("actors"));
			a.setWriter(c.getString("writer"));
			a.setLang(c.getString("lang"));
			videolist[i] = a;
		}
		return videolist;

	}

	public static Video[] getVideolist(Context ctx, String query) throws Exception {
		JSONObject jObject = new JSONObject(postRequest(ctx,query));
		JSONArray contestantObjects = jObject.getJSONArray("MovieList");
		Video[] videolist = new Video[contestantObjects.length()];
		for (int i = 0; i < contestantObjects.length(); i++) {
			JSONObject c = contestantObjects.getJSONObject(i);
			Video a = new Video();
			a.setTitle(c.getString("title"));
			a.setYt_id(c.getString("yt_id"));
			a.setPoster_med(c.getString("poster_med"));
			a.setImdb_id(c.getString("imdbID"));
			a.setImdb_rating(c.getString("imdbRating"));
			a.setYear(c.getString("year"));
			a.setDescription(c.getString("description"));
			a.setDirector(c.getString("director"));
			a.setActors(c.getString("actors"));
			a.setWriter(c.getString("writer"));
			a.setLang(c.getString("lang"));
			videolist[i] = a;
		}
		return videolist;

	}

	
	private static String postRequest(Context ctx, int position)
			throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		
		String URL = ctx.getResources().getString(R.string.url_api);
		URL=URL+"?c="+position;
		HttpGet request = new HttpGet(URL);
		HttpResponse response = httpclient.execute(request);
		HttpEntity resEntity = response.getEntity();
		String _response = EntityUtils.toString(resEntity);

		return _response;
	}


	private static String postRequest(Context ctx, String query)
			throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		
		String URL = ctx.getResources().getString(R.string.search_url_api);
		URL=URL+"?q="+query;
		HttpGet request = new HttpGet(URL);
		HttpResponse response = httpclient.execute(request);
		HttpEntity resEntity = response.getEntity();
		String _response = EntityUtils.toString(resEntity);

		return _response;
	}

	
}
