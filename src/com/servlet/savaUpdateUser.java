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
 * Servlet implementation class savaUpdateUser
 */
@WebServlet("/savaUpdateUser")
public class savaUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public savaUpdateUser() {
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
		//保存更新信息的用户信息、。有用户头像文件。
		User user = new User();
		//将字符串强制转换为int.
		int inttemp = Integer.parseInt(request.getParameter("uid"));
		System.out.println("int lei xin zhuan huan :"+inttemp);
		user.setUid(inttemp);
		user.setUantograph(request.getParameter("uantograph"));
		user.setPassword(request.getParameter("password"));
		user.setUsername(request.getParameter("username"));
		user.setUemail(request.getParameter("uemail"));
		//InputStream inputStream = request.getInputStream();
		//怎么能直接将file文件的二进制文件保存起来，在上传音频文件的时候直接通过inputstream接受的数据只能接收一次，
		//先实现了功能，一会修改为通过inputstream接收数据实现。
		String uimage = request.getParameter("uimage");
		System.out.println("uimage的path name："+uimage);
		UserImpl userImpl = new UserDo();
		int temp = userImpl.update(user);
		if(temp != 0){
			request.getSession().setAttribute("user",user);
			request.getSession().setAttribute("updateMessage", "用户信息更新成功。");
			response.sendRedirect("message.jsp");
		} else {
			request.getSession().setAttribute("updateMessage", "用户信息更新失败了，失败了。");
			response.sendRedirect("message.jsp");
			System.out.println("user info update save fail.");
		}
	}

}
