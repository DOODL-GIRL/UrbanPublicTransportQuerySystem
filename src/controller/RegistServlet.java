package controller;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.UserDAO;
import model.DbUtil;
import model.User;
@WebServlet("/Regist")
public class RegistServlet extends HttpServlet{
 
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
        String username=req.getParameter("username");
        if(username.equals(""))//检查用户名是否为空
        {
        	req.setAttribute("message", "请输入用户名！");
        	req.getRequestDispatcher("regist.jsp").forward(req, resp);
        }
        //获取注册用户密码
        String password=req.getParameter("password");
        if(password.equals(""))//检查密码是否为空
        {
        	req.setAttribute("message", "请输入密码！");
        	req.getRequestDispatcher("regist.jsp").forward(req, resp);
        }
        //获取注册用户手机号
        String phone_number=req.getParameter("phone_number");
        if(phone_number.equals(""))//检查手机号是否为空
        {
        	req.setAttribute("message", "请输入手机号！");
        	req.getRequestDispatcher("regist.jsp").forward(req, resp);
        }
        
        
        DbUtil db= new DbUtil();
        User user=new User(username,password,phone_number);
        UserDAO dao=new UserDAO();
        try {
            //数据库连接
            Connection con=db.getCon();
            
            if(dao.regist(con,user)==2) {
            	resp.sendRedirect("login.jsp");
            }
            else if(dao.regist(con,user)==1)
            {
            	req.setAttribute("message", "用户名已存在");
            	req.getRequestDispatcher("regist.jsp").forward(req, resp);
            }
            else
            {
            	req.setAttribute("message", "未知原因注册失败");
            	req.getRequestDispatcher("regist.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}