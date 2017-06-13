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
 * Servlet implementation class registerUser
 */
@WebServlet("/registerUser")
public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerUser() {
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
		//用户注册登录
		String uemail = request.getParameter("uemail");
		String password = request.getParameter("password");
		User user = new User();
		user.setUemail(uemail);
		user.setPassword(password);
		UserImpl userImpl = new UserDo();
		int temp = userImpl.register(user);
		if(temp != 0){
			User user2 = userImpl.find(temp);
			request.getSession().setAttribute("user", user2);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("message", "register failed .");
			request.getRequestDispatcher("/registerUser.jsp").forward(request, response);
		}
	}

}
