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
 
@WebServlet("/AdminLogin")
public class AdminLoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    
    
        /**
         * ����ǰ̨������ֵ �˺ź�����
         */
        String username=req.getParameter("username");
        
        String password=req.getParameter("password");
        
        DbUtil db= new DbUtil();
        Admin user=new Admin(username,password);
        AdminDAO dao=new AdminDAO();
        try {
        	//���ݿ�����
            Connection con=db.getCon();
            
            if(dao.login(con, user)!=null) {
                resp.sendRedirect("admin_user.jsp");
            }else {
            	req.setAttribute("message", "�û��������벻��ȷ");
            	req.getRequestDispatcher("admin_login.jsp").forward(req, resp);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}