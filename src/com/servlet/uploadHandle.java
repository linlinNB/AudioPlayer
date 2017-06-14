package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.actionimpl.MusicImpl;
import com.actionimpl.SongImpl;
import com.actionimpl.action.MusicDo;
import com.javabean.Music;


/**
 * Servlet implementation class uploadHandle
 */
@WebServlet("/uploadHandle")
public class uploadHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadHandle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取服务器部署的路径，发布的时候这样保存文件，使用相对路径。
//		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		//指定固定的文件夹保存文件，使用绝对路径。
		String savePath = "D:\\Java\\workspace\\audioDemo\\WebContent\\WEB-INF\\upload";
	
		System.out.println(savePath+"文件夹路径");
		File file = new File(savePath);
		
		//文件夹不存在，并且
		if(!file.exists() && !file.isDirectory()){
			file.mkdir();
		}
		String message="";
		try {
			//文件传输框架的使用
			//怎么理解这里的文件流文件传输功能？？？
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			if(!ServletFileUpload.isMultipartContent(request)){
				return;
			}
			List<FileItem> list = upload.parseRequest(request);
			//定义歌曲对象。保存文件。
			Music music = new Music();
			int i=0;
			for(FileItem item:list){
				//这里循环输出K_V的参数
				//需要将参数写入到数据库中，在这里进行截取参数
				System.out.println(i++);
				if (item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println(name+"="+value);
					//在这里set 对象的参数。
//					music.setMname(name);
					if(name == "songname" || name.equals("songname")){
						music.setMname(value);
					}else if(name == "songauthor" || name.equals("songauthor")){
						music.setMauthor(value);
					}else{
						System.out.println("其他的内容没有保存到对象中。");
					}
					
				}else{
					String filename = item.getName();
					System.out.println(filename+"文件名");
					music.setMparh(savePath+"\\"+filename);
					if(filename == null || filename.trim().equals("")){
						continue;
//						music.setMparh(savePath+filename);
					}
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					
					//获取表单文件的文件流
					InputStream in = item.getInputStream();
					//创建文件输出流
					FileOutputStream out = new FileOutputStream(savePath+"\\"+filename);
					byte buffer[] = new byte[1024];
					int len = 0;
					while((len = in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					System.out.println("文件上传结束，应该上传到了指定的文件夹中。");
					in.close();
					out.close();
					item.delete();
					message = "文件上传成功！";
					
					//问题：怎么才能自动从文件名中提取 作者的名字？？？不需要输入
					MusicImpl musicImpl = new MusicDo();
					musicImpl.saveMusic(music);
					System.out.println("完成保存到数据库成功。");
					//调用save函数保存对象的值，文件上传到指定文件夹，并保存在数据库中。
				}
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			message = "文件上传失败";
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/items.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
