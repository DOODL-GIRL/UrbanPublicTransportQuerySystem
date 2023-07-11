package controller;
 
import java.io.IOException;
import java.sql.Connection;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.AdminDAO;
import model.DbUtil;
import model.Admin;

import java.util.List;
import java.util.ArrayList;
 
@WebServlet("/admin_admin")
public class AdminAdminServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    

        
        DbUtil db= new DbUtil();
        AdminDAO dao=new AdminDAO();
        try {
        	//数据库连接
            Connection con=db.getCon();
            List<Admin> admins=dao.get_all_admins(con);
            if(admins!=null) {
                req.setAttribute("admins", admins);
                req.getRequestDispatcher("admin_admin.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    	
    }
}