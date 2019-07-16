package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//commons-upload,smartUpload
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fu = new ServletFileUpload(factory);
		
		//String loginname = request.getParameter("loginname");
		//System.out.println("loginname:"+loginname);
		
		
		try {
			List<FileItem> list = fu.parseRequest(request);
			for (int i = 0; i < list.size(); i++) {
				//得到其中的某一个表单元素
				FileItem item = list.get(i);
				
				//判断是否是一个普通的表单元素
				if(item.isFormField()){
					//如果是普通的表单元素
					System.out.println(item.getFieldName()+"----"+item.getString());
				}else{
					//如果是文件
					System.out.println("接收到文件："+item.getName());//文件名
					//获取upload文件夹的绝对路径(从盘符开始的路径)
					String realpath = request.getServletContext().getRealPath("/upload");
					
					String uuid = UUID.randomUUID().toString();
					String oldfilename = item.getName();//客户端的文件名
					String endname = oldfilename.substring(oldfilename.lastIndexOf("."), oldfilename.length());
					
					
					File file = new File(realpath+"/"+uuid+endname);
					
					item.write(file);
					
					request.setAttribute("filename", uuid+endname);
				}
				
				
			}
			
			
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("show.jsp").forward(request, response);
		
	}

}
