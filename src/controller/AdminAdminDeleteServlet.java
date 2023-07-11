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
 
@WebServlet("/admin_delete")
public class AdminAdminDeleteServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    
    	/**
         * 接收前台传来的值 账号和密码
         */
    	req.setCharacterEncoding("UTF-8");
    	//获取注册用户名
        String username=req.getParameter("userId");
        System.out.println(username);
        if(username.equals(""))//检查用户名是否为空
        {
        	req.setAttribute("message", "请输入用户名！");
        	req.getRequestDispatcher("admin_admin.jsp").forward(req, resp);
        }

        
        
        DbUtil db= new DbUtil();
        AdminDAO dao=new AdminDAO();
        try {
            //数据库连接
            Connection con=db.getCon();
            int flag=dao.delete_admin(con, username);
            if(flag==2) {
            	resp.sendRedirect("admin_admin.jsp");
            }
            else if(flag==1)
            {
            	req.setAttribute("message", "用户名不存在");
            	req.getRequestDispatcher("admin_admin.jsp").forward(req, resp);
            }
            else
            {
            	req.setAttribute("message", "未知原因删除失败");
            	req.getRequestDispatcher("admin_admin.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}