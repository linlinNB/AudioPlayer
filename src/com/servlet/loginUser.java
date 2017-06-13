package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.actionimpl.UserImpl;
import com.actionimpl.action.UserDo;
import com.javabean.User;

/**
 * Servlet implementation class loginUser
 */
@WebServlet("/loginUser")
public class loginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginUser() {
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
//		doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:password:"+username+"+"+password);
		//调用user类的接口实现登录账户的验证。
		if(!username.equals("") && !password.equals("")){
			UserImpl userImpl = new UserDo();
			int temp = userImpl.login(username, password);
			if(temp > 0){
				User user = userImpl.find(temp);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("user","登录失败，账号或密码错误！！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			//现在处理的情况是，将user对象通过session的方式返回给前端，
			//可以修改为通过json的数据格式传输给前端的ajax请求。
		} else {
			request.getSession().setAttribute("user", "请输入用户名或者密码。");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
