package com.itconnect.inc.zmovie.data_acces;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itconnect.inc.zmovie.R;
import com.itconnect.inc.zmovie.model.Video;
import com.itconnect.inc.zmovie.utils.tools;

public class FavoritVidoeDAO {
    private static final String PREFIX = "json";

    public static void save(Context c, Video v) throws Exception {
            SharedPreferences settings = c.getSharedPreferences(c.getResources()
                            .getString(R.string.app_name), 0);
            SharedPreferences.Editor editor = settings.edit();
            Gson gson = new Gson();
            int i = 0;
            List<Video> lst = null;
            try {
                    lst = get(c);
            } catch (JSONException e) {
                    // TODO Auto-generated catch block
            }

            if (lst == null) {
                    lst = new ArrayList<Video>();
            }
            // if video allready exist
            if (!tools.contains(lst,v) )
                    lst.add(v);
             else 
                    throw new Exception();
            
                    String json = gson.toJson(lst);
                    editor.putString(FavoritVidoeDAO.PREFIX, json);
                    editor.commit();
           
    }
    public static void remove(Context c, Video v) throws Exception {
            SharedPreferences settings = c.getSharedPreferences(c.getResources()
                            .getString(R.string.app_name), 0);
            SharedPreferences.Editor editor = settings.edit();
            Gson gson = new Gson();
            int i = 0;
            List<Video> lst = null;
            try {
                    lst = get(c);
            } catch (JSONException e) {
                    // TODO Auto-generated catch block
            }

            if (lst == null) {
                    lst = new ArrayList<Video>();
            }
            // if video allready exist
                    lst.remove(tools.getposition(lst,v));
                    String json = gson.toJson(lst);
                    editor.putString(FavoritVidoeDAO.PREFIX, json);
                    editor.commit();
           
    }
    public static void clear(Context c) {
            SharedPreferences settings = c.getSharedPreferences(c.getResources()
                            .getString(R.string.app_name), 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.commit();
    }
    public static boolean contain(Video v,Context c) {
    	try {
			return tools.contains(get(c),v);
		} catch (JSONException e) {
			return false;
		}
    }
    public static List<Video> get(Context c) throws JSONException {
            SharedPreferences settings = c.getSharedPreferences(c.getResources()
                            .getString(R.string.app_name), 0);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Video>>() {
            }.getType();
            String json = settings.getString(FavoritVidoeDAO.PREFIX, "");
            List<Video> obj = gson.fromJson(json, listType);
            return obj;
    }

    public static String loadJSONArray(Context c) throws JSONException {
            SharedPreferences settings = c.getSharedPreferences(c.getResources()
                            .getString(R.string.app_name), 0);
            return settings.getString(FavoritVidoeDAO.PREFIX, null);
    }

}


