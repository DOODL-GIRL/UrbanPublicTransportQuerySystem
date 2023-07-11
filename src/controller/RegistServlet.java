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
         * ����ǰ̨������ֵ �˺ź�����
         */
    	req.setCharacterEncoding("UTF-8");
    	//��ȡע���û���
        String username=req.getParameter("username");
        if(username.equals(""))//����û����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�������û�����");
        	req.getRequestDispatcher("regist.jsp").forward(req, resp);
        }
        //��ȡע���û�����
        String password=req.getParameter("password");
        if(password.equals(""))//��������Ƿ�Ϊ��
        {
        	req.setAttribute("message", "���������룡");
        	req.getRequestDispatcher("regist.jsp").forward(req, resp);
        }
        //��ȡע���û��ֻ���
        String phone_number=req.getParameter("phone_number");
        if(phone_number.equals(""))//����ֻ����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�������ֻ��ţ�");
        	req.getRequestDispatcher("regist.jsp").forward(req, resp);
        }
        
        
        DbUtil db= new DbUtil();
        User user=new User(username,password,phone_number);
        UserDAO dao=new UserDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            
            if(dao.regist(con,user)==2) {
            	resp.sendRedirect("login.jsp");
            }
            else if(dao.regist(con,user)==1)
            {
            	req.setAttribute("message", "�û����Ѵ���");
            	req.getRequestDispatcher("regist.jsp").forward(req, resp);
            }
            else
            {
            	req.setAttribute("message", "δ֪ԭ��ע��ʧ��");
            	req.getRequestDispatcher("regist.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}