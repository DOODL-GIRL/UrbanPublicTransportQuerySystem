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
 
@WebServlet("/user_delete")
public class AdminUserDeleteServlet extends HttpServlet{
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
        String username=req.getParameter("userId");
        System.out.println(username);
        if(username.equals(""))//����û����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�������û�����");
        	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
        }

        
        
        DbUtil db= new DbUtil();
        UserDAO dao=new UserDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            int flag=dao.delete_user(con, username);
            if(flag==2) {
            	resp.sendRedirect("admin_user.jsp");
            }
            else if(flag==1)
            {
            	req.setAttribute("message", "�û���������");
            	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
            }
            else
            {
            	req.setAttribute("message", "δ֪ԭ��ɾ��ʧ��");
            	req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}