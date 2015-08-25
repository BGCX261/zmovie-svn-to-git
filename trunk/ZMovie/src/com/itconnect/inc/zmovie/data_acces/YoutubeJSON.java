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
import org.json.JSONObject;

import android.content.Context;

import com.itconnect.inc.zmovie.model.Video;

public class YoutubeJSON {
	public static Video getYTVideo(Context ctx,String yb_id) throws Exception {
		JSONObject jObject = new JSONObject(postRequest(ctx,yb_id));
	
		JSONObject ja = jObject.getJSONObject("data");
		   
		Video video = new Video();
		video.setId(ja.getString("id"));
		video.setYt_id(ja.getString("id"));
		video.setTitle(ja.getString("title"));
		video.setDescription(ja.getString("description"));
		video.setPoster_med(ja.getJSONObject("thumbnail").getString("hqDefault"));
//		video.setMovie_url(ja.getJSONObject("player").getString("mobile"));
		return video;

	}

	private static String postRequest(Context ctx, String yb_id)
				throws ClientProtocolException, IOException {
			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

			String URL = "http://gdata.youtube.com/feeds/api/videos/[youtube_id]?v=2&alt=jsonc".replace("[youtube_id]", yb_id);
			HttpGet request = new HttpGet(URL);
			HttpResponse response = httpclient.execute(request);
			HttpEntity resEntity = response.getEntity();
			String _response = EntityUtils.toString(resEntity);

			return _response;
		}
	

}
