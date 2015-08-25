package com.itconnect.inc.zmovie.model;

import java.io.Serializable;

public class Video  implements Serializable{
	
	private static long serialVersionUID = 1L;
	private String id, title, year, imdb_id,imdb_rating="0", description, poster_med,
	genres, yt_id, movie_url,director,writer,actors,lang,country;
	public Video(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	public String getImdb_rating() {
		return imdb_rating;
	}
	public void setImdb_rating(String imdb_rating) {
		this.imdb_rating = imdb_rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPoster_med() {
		return poster_med;
	}
	public void setPoster_med(String poster_med) {
		this.poster_med = poster_med;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getYt_id() {
		return yt_id;
	}
	public void setYt_id(String yt_id) {
		this.yt_id = yt_id;
	}
	public String getMovie_url() {
		return movie_url;
	}
	public void setMovie_url(String movie_url) {
		this.movie_url = movie_url;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}


}
