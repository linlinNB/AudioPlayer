package com.actionimpl;

import java.io.InputStream;
import java.util.List;

import com.javabean.Music;
import com.javabean.Song;

public interface SongImpl {

	void saveSong(Song song,String filepath);
	void removeSong(int id);
	void updataSong(Song song);
	List<Song> selectSong();
	Song selectByName(String sname);
	InputStream getImageBySname(String sname);
	void saveMusic(Music music);
	
}
