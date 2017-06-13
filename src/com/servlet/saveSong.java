package com.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actionimpl.SongImpl;
import com.actionimpl.action.SongDo;
import com.javabean.Song;

/**
 * Servlet implementation class saveSong
 */
@WebServlet("/saveSong")
public class saveSong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saveSong() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		/**
		 * 实例化impl中的方法，进行数据保存操作，
		 * 跳转到页面item中。
		 */
		Song song = new Song();
		song.setSname(request.getParameter("sname"));
		song.setSongname(request.getParameter("songname"));
		song.setSongpath(request.getParameter("songpath"));
		
		//这里的filepath需要通过js取到文件的绝对地址，相对地址在后台不能取到对应的文件。
		String filepath = request.getParameter("simage");
		SongImpl songImpl = new SongDo();
		
		//request.getInputStream()的作用是什么了？为什么放在前面，getParameter就不能接受前端的数据了？？？
		//这个得到的数据是什么了？接受post方式提交的数据。只能提取一次，然后清空、
//		InputStream file = request.getInputStream();
//		byte[] byt = new byte[file.available()];
//		System.out.println("filecontenttype:"+byt);
		songImpl.saveSong(song, filepath);
		request.getRequestDispatcher("/items.jsp").forward(request, response);
	}

}
