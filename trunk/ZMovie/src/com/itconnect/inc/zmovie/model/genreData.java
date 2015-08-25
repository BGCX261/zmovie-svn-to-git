package com.itconnect.inc.zmovie.model;

public class genreData {
	private int position;
	private String[] listgenr;

	public genreData(int position, String[] listgenr) {
		super();
		this.position = position;
		this.listgenr = listgenr;
	}

	public String[] getListgenr() {
		return listgenr;
	}

	public void setListgenr(String[] listgenr) {
		this.listgenr = listgenr;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
