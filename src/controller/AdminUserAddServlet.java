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
 
@WebServlet("/user_add")
public class AdminUserAddServlet extends HttpServlet{
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
        System.out.println(username);
        if(username.equals(""))//����û����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�������û�����");
        	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
        }
        //��ȡע���û�����
        String password=req.getParameter("password");
        System.out.println(password);
        if(password.equals(""))//��������Ƿ�Ϊ��
        {
        	req.setAttribute("message", "���������룡");
        	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
        }
        //��ȡע���û��ֻ���
        String phone_number=req.getParameter("phone_number");
        System.out.println(phone_number);
        if(phone_number.equals(""))//����ֻ����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�������ֻ��ţ�");
        	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
        }
        
        
        DbUtil db= new DbUtil();
        User user=new User(username,password,phone_number);
        UserDAO dao=new UserDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            int flag=dao.regist(con,user);
            if(flag==2) {
            	resp.sendRedirect("admin_user.jsp");
            }
            else if(flag==1)
            {
            	req.setAttribute("message", "�û����Ѵ���");
            	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
            }
            else
            {
            	req.setAttribute("message", "δ֪ԭ��ע��ʧ��");
            	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}