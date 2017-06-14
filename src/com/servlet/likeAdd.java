package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actionimpl.LikeImpl;
import com.actionimpl.action.LikeDo;

/**
 * Servlet implementation class likeAdd
 */
@WebServlet("/likeAdd")
public class likeAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//添加用户喜欢的音乐
		int uid = 1;
		int mid = 1;
		LikeImpl likeImpl = new LikeDo();
		int temp = likeImpl.add(uid, mid);
		if(temp != 0){
			System.out.println("用户喜欢添加成功！");
		} else {
			System.out.println("用户喜欢添加 失败。。。");
		}
		//返回到喜欢列表
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
