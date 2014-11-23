package com.example.jianzhitao.bean;

import java.sql.Date;

public class article {
	private int id;
	private String title;
	private String contpre;
	private String contmain;
	private String pdata;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContpre() {
		return contpre;
	}
	public article(int id, String title, String contpre, String contmain,
			String pdata) {
		super();
		this.id = id;
		this.title = title;
		this.contpre = contpre;
		this.contmain = contmain;
		this.pdata = pdata;
	}
	public void setContpre(String contpre) {
		this.contpre = contpre;
	}
	public String getContmain() {
		return contmain;
	}
	public void setContmain(String contmain) {
		this.contmain = contmain;
	}
	public String getPdata() {
		return pdata;
	}
	public void setPdata(String pdata) {
		this.pdata = pdata;
	}
	
}
