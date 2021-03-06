package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actionimpl.LikeImpl;
import com.actionimpl.MusicImpl;
import com.actionimpl.action.LikeDo;
import com.actionimpl.action.MusicDo;
import com.javabean.Music;

/**
 * Servlet implementation class likeList
 */
@WebServlet("/likeList")
public class likeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//在这里调用do的likelist函数，返回登录用户的喜欢的歌曲列表
		//登录的user已经写入了session中，通过getsession返回取出用户信息，uid作为参数传入后台查询mid和lid。
		int uid = 1 ;
		LikeImpl likeImpl = new LikeDo();
		ArrayList<Integer> arrayList = likeImpl.likelist(uid);
		System.out.println("用户喜欢的音乐的ID列表："+arrayList.size());
		for(int i=0;i<arrayList.size();i++){
			System.out.println(i+"--"+arrayList.get(i).toString());
		}
		System.out.println();
		//取到了每首音乐的ID,通过了个find(id)的方式，循环取出每首音乐的详细信息。
		//跳转到用户喜欢列表，显示用户喜欢的音乐。
		
		MusicImpl musicImpl = new MusicDo();
		ArrayList<Music> arrayList2 = musicImpl.findMusicById(arrayList);
		for(int i = 0; i<arrayList2.size(); i++ ) {
			System.out.println(i+" Music=="+arrayList2.get(i).getMname());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
