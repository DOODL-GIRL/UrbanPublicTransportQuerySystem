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
 
@WebServlet("/Login")
public class LoginServlet extends HttpServlet{
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
    	req.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        
        String password=req.getParameter("password");
        
        DbUtil db= new DbUtil();
        User user=new User(username,password);
        UserDAO dao=new UserDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            
            if(dao.login(con, user)!=null) {
                resp.sendRedirect("index.jsp");
            }else {
            	req.setAttribute("message", "�û��������벻��ȷ");
            	req.getRequestDispatcher("login.jsp").forward(req, resp);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}