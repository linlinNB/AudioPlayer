package com.javabean;

import java.io.File;
import java.util.Date;

public class Song {
	private int sid;
	private File simage;
	private String sname;
	private String songname;
	private Date sdate;
	private String songpath;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public File getSimage() {
		return simage;
	}
	public void setSimage(File simage) {
		this.simage = simage;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public String getSongpath() {
		return songpath;
	}
	public void setSongpath(String songpath) {
		this.songpath = songpath;
	}
	

}
