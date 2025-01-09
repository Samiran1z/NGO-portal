package adminpkg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dbpkg.dbcon;

/**
 * Servlet implementation class Insevent
 */
@WebServlet("/insevent")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class Insevent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public Insevent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String evcat=request.getParameter("evcat");
		String evname=request.getParameter("evname");
		String evdate=request.getParameter("evdate");
		String evvenue=request.getParameter("evvenue");
		String evdetails=request.getParameter("evdetails");
		 Part filePart = request.getPart("evimg");
		    String fileName = filePart.getSubmittedFileName();
		    for (Part part : request.getParts()) {
		      part.write("C:\\Users\\DELL\\eclipse-workspace\\java_project\\src\\main\\webapp\\event_img\\" + fileName);
		    }
		    try {
				Connection conn=dbcon.mycon();
				Statement st=conn.createStatement();
				String sql="INSERT INTO events SET event_category='"+evcat+ "',event_venue='"+evvenue+"',event_name='"+evname+"',event_date='"+evdate+"',event_img='"+fileName+"',event_details='"+evdetails+"'";
				
				st.executeUpdate(sql);
				response.sendRedirect("listevent");
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
