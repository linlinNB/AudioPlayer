package com.actionimpl;

import java.util.ArrayList;
import java.util.List;

public interface LikeImpl {
	int add(int uid,int mid);
	int remove(int uid,int mid);
	ArrayList<Integer> likelist(int uid);
}
