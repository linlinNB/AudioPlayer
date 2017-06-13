package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

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

    User user = new User();
    //定义一个全局的user类在多个函数中共同完成将表单的数据添加到类中，然后参入后台写入数据库。
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
		//解决文件上传下载时的中文乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//one:检查表单是否为：multipart/form-data类型的提交
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if( !isMultipart ) {
			throw new RuntimeException("the form's enctype attribute value must be multipart/form-data");
			
		}
		
		//two:开始实现上传，创建文件上传时的需要用到的两个对象。
		//创建一个产生FileItem的工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		//创建一个IO文件流的解析器，因为解析器必须依赖于这个工厂，所以将参数传递为工厂。
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		
		// 限制上传文件大小  
//        sfu.setFileSizeMax(1024 * 196);// 限制单个文件的大小不超过200kb  
        //sfu.setSizeMax(5*1024*1024);//限制上传的总文件大小不超过5M  
		
		//third：创建一个容器来接收解析的内容。
		List<FileItem> items = new ArrayList<FileItem>();
		
		//four：将文件信息存放入容器中。
		try {
			items = servletFileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//fifth:遍历容器中的文件。处理遍历的每一项文件。
		// 对于每项item ，插件都封装了俩个方法，1.专门处理FormFiled的方法，2.专门处理UpLoadFile的方法
		//两个方法分别用于处理普通的字符串文件和处理专门的文件上传文件。
		for (FileItem item:items) {
			//处理不同类型的字段，通过两个不同的自定义方法来 处理。
			if (item.isFormField()) {
				//处理普通的字段
				processFormField(item);
			} else {
				//处理文件上传的字段
				processUpLoadField(item);
			}
		}
		
		//end.插件的文件上传表单调教的数据已经完整的取到了。
		//接下来将取到的数据打包传输到action中进行相应的处理。
				
		/*
		 * 没有插件普通的处理方式。
		 * ServletInputStream in = request.getInputStream();//此方法得到所有的提交信息，不仅仅只有内容
        //转换流
        InputStreamReader inReaser = new InputStreamReader(in);
        //缓冲流
        BufferedReader reader = new BufferedReader(inReaser);
        String str = null;
        while ((str=reader.readLine()) != null){
            System.out.println(str);
        }
              以上是通过没有插件的方式获取了前端提交的二进制表单，保存 到了buffer中通过string的方式进行了输出。
              这种方式不方便使用，修改为通过FileUpLoad插件进行表单文件流处理方式。
              
              =====一下内容完成后删除===
		user.setUid(Integer.parseInt(request.getParameter("uid")));
		user.setUantograph(request.getParameter("uantograph"));
		user.setPassword(request.getParameter("password"));
		user.setUsername(request.getParameter("username"));
		user.setUemail(request.getParameter("uemail"));
		//怎么能直接将file文件的二进制文件保存起来，在上传音频文件的时候直接通过inputstream接受的数据只能接收一次，
		//先实现了功能，一会修改为通过inputstream接收数据实现。
		String uimage = request.getParameter("uimage");
		System.out.println("uimage的path name："+uimage);
		*/
		
		
		UserImpl userImpl = new UserDo();
		int temp = userImpl.update(user);
		System.out.println("执行了用户更新的后台程序》》》》》user参数传递到了后台？？？？？");
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

	private void processUpLoadField(FileItem item) {
		// TODO 自动生成的方法存根
		//处理表单中提交的文件，用户头像文件
		//文件名的读取
		String fileName = item.getName();
		if (fileName != null) {
			if (!item.getContentType().startsWith("image")) {
				System.out.println("用户上传的文件不是图片文件，头像文件只能为图片文件");
				return;
			}
			System.out.println("获取的item中的文件的原始名称为："+fileName);
//			获取文件名称，包含后缀。并且添加文件标识
			fileName = FilenameUtils.getName(fileName);
//			fileName = ***这里可以对文件名进行处理，为了防止同名，可以添加时间戳。
		}
		System.out.println("hahaha上传的文件的名称为："+fileName);
		
		//如果需要将文件存入服务器的固定目录下，在这里进行服务器目录的创建于判断文件夹是否存在的操作。
		//1.获取文件夹路径，2.生成存放文件的子目录，3.判断文件夹是否存在,不存在就创建。
		
		//这里的item是二进制文件吗？？
		InputStream inputStream;
		try {
			inputStream = item.getInputStream();
			user.setUimage(inputStream);
			System.out.println("将二进制文件的头像添加到user类中成功。");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			System.out.println("+++++二进制头像添加到user类中失败了、、、、");
			e.printStackTrace();
		}
	}

	private void processFormField(FileItem item) {
		// TODO 自动生成的方法存根
		//处理普通的字符串
		String fieldName = item.getFieldName();
		String value = "";
		try {
			value = item.getString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("用户的普通字符串字段的取出结果为："+fieldName+" = "+value);
		createUser(fieldName,value);
	}

	private void createUser(String fieldName,String value) {
		// TODO 自动生成的方法存根
		//将取出的字段加入到user类中，传入action中写入到数据库中。
		//确定参数，switch选择，加入正确的属性中。
		System.out.println("create User函数中接收到的参数："+fieldName+" = "+value);
		switch (fieldName) {
		case "uid":
			user.setUid(Integer.parseInt(value));
			break;
		case "username":
			user.setUsername(value);			
			break;
		case "uemail":
			user.setUemail(value);
			break;
		case "password":
			user.setPassword(value);
			break;
		case "uantograph":
			user.setUantograph(value);
			break;
		default:
			System.out.println("表单数据全部写入到user类中的default情况，（end or excaption）传入后台。");
			break;
		}
	}

	
	
	

}
