package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class listFile
 */
@WebServlet("/listFile")
public class listFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//保存上传文件的目录。
		String uploadFilePath = "D:\\Java\\workspace\\audioDemo\\WebContent\\WEB-INF\\upload";
		Map<String, String> fileNameMap = new HashMap<String,String>();
		
		//这里不需要不需要这样写，访问数据库，查找数据库中存在的歌单。不能直接访问文件夹中存在的文件。
		//查出的歌名在这里存入list在前端显示，将path传入downLoad中在下载的时候进行使用（现在指定的固定的文件夹）。
		
		listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表一个目录
	    //将Map集合发送到listfile.jsp页面进行显示
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/listfile.jsp").forward(request, response);
	}
	
	 public void listfile(File file,Map<String,String> map){
	        if(!file.isFile()){
	            File files[] = file.listFiles();
	            for(File f : files){
	                listfile(f,map);
	            }
	        }else{
	            /**
	             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
	                file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
	                那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
	             */
//	            String realName = file.getName().substring(file.getName().indexOf("_")+1);
	        	String realName = file.getName();
	            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
	            map.put(file.getName(), realName);
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
