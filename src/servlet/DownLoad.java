package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downLoad")
public class DownLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getParameter("fileName");
		String realpath = request.getServletContext().getRealPath("/upload");
		File file = new File(realpath+"/"+filename);
		
		InputStream is = new FileInputStream(file);
		//图片
		byte [] b = new byte[is.available()];
		is.read(b);
		
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		ServletOutputStream sos = response.getOutputStream();
		sos.write(b);
	}

}
