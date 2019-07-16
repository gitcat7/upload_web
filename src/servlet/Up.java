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

@WebServlet("/up")
public class Up extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sf = new ServletFileUpload(factory);
		try {
			List<FileItem> list = sf.parseRequest(request);
			for (int i = 0; i < list.size(); i++) {
				FileItem item = list.get(i);
				if(item.isFormField()) {
					System.out.println(item.getFieldName()+"-------"+item.getString());
				}else {
					String realPath = request.getServletContext().getRealPath("/upload");
					String oldName = item.getName();
					String uuid = UUID.randomUUID().toString();
					String endName = oldName.substring(oldName.lastIndexOf("."));
					File file = new File(realPath+"/"+uuid+endName);
					item.write(file);
					request.setAttribute("fileName", uuid+endName);
				}
				
			}
			request.getRequestDispatcher("show4.jsp").forward(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
