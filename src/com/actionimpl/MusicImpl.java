package com.actionimpl;

import java.util.ArrayList;

import com.javabean.Music;

public interface MusicImpl {

	void saveMusic(Music music);
	ArrayList<Music> findMusicById(ArrayList<Integer> idList);
}
