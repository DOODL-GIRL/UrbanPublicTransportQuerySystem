package controller;
 
import java.io.IOException;
import java.sql.Connection;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.UserDAO;
import model.DbUtil;
import model.User;

import java.util.List;
import java.util.ArrayList;
 
@WebServlet("/admin_user")
public class AdminUserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        
        DbUtil db= new DbUtil();
        UserDAO dao=new UserDAO();
        try {
        	//数据库连接
            Connection con=db.getCon();
            List<User> users=dao.get_all_users(con);
            if(users!=null) {
                req.setAttribute("users", users);
                req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}