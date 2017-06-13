package com.actionimpl;

import java.util.List;

public interface LikeImpl {
	void add(int uid,int mid);
	void remove(int lid);
	List<Integer> likelist(int uid);
}
