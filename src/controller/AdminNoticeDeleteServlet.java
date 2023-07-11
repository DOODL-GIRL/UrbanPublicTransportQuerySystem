package controller;
 
import java.io.IOException;
import java.sql.Connection;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.NoticeDAO;
import model.DbUtil;
import model.Notice;
 
@WebServlet("/notice_delete")
public class AdminNoticeDeleteServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    
    	/**
         * ����ǰ̨������ֵ
         */
    	req.setCharacterEncoding("UTF-8");
    	//��ȡ������
        String noticeId=req.getParameter("noticeId");
        System.out.println(noticeId);
        if(noticeId.equals(""))//��鹫�����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�����빫���ţ�");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }
        int number=0;
        try {
            number = Integer.parseInt(noticeId);
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "��������ȷ�Ĺ����ţ�");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }

        
        
        DbUtil db= new DbUtil();
        NoticeDAO dao=new NoticeDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            int flag=dao.delete_notice(con, number);
            if(flag==1) {
            	resp.sendRedirect("admin_notice.jsp");
            }
            else
            {
            	req.setAttribute("message", "ɾ��ʧ��");
            	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}