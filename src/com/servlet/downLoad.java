package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class downLoad
 */
@WebServlet("/downLoad")
public class downLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
        String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
       // fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
//        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        String fileSaveRootPath = "D:\\Java\\workspace\\audioDemo\\WebContent\\WEB-INF\\upload";
        //String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        String path = "D:\\Java\\workspace\\audioDemo\\WebContent\\WEB-INF\\upload";
        File file = new File(path + "\\" + fileName);
        System.out.println(path+"下载文件的路径##"+fileName+"文件名");
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        String realname = fileName;//.substring(fileName.indexOf("_")+1);
        System.out.println(realname+"处理后的文件名");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
	}

	//做文件名称的处理，防止文件同名
//	 public String findFileSavePathByFileName(String filename,String saveRootPath){
//	        int hashcode = filename.hashCode();
//	        int dir1 = hashcode&0xf;  //0--15
//	        int dir2 = (hashcode&0xf0)>>4;  //0-15
//	        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
//	        File file = new File(dir);
//	        if(!file.exists()){
//	            file.mkdirs();
//	        }
//	        return dir;
//	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
