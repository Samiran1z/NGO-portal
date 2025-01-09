package adminpkg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbpkg.dbcon;

/**
 * Servlet implementation class Listcategory
 */
@WebServlet("/listcategory")
public class Listcategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listcategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection conn=dbcon.mycon();
			Statement st=conn.createStatement();
			String sql="SELECT * FROM categories";
			ResultSet rs=st.executeQuery(sql);
			ArrayList<HashMap<String ,String>> ar=new ArrayList<HashMap<String,String>>();
			while(rs.next()) {
				HashMap<String,String> hm=new HashMap<String,String>();
				hm.put("catid", rs.getString("cid"));
				hm.put("catname", rs.getString("cat_name"));
				ar.add(hm);
			}
			
			request.setAttribute("allcat", ar);
			request.getRequestDispatcher("listcategory.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
