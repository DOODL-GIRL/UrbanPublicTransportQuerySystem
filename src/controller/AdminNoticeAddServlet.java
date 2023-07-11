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
 
@WebServlet("/notice_add")
public class AdminNoticeAddServlet extends HttpServlet{
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
    	//��ȡ�������
        String noticetitle=req.getParameter("noticetitle");
        System.out.println(noticetitle);
        if(noticetitle.equals(""))//��鹫������Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�����빫����⣡");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }
        //��ȡ��������
        String noticecontent=req.getParameter("noticecontent");
        System.out.println(noticecontent);
        if(noticecontent.equals(""))//��鹫�������Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�����빫�����ݣ�");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }
        //��ȡ����ʱ��
        String noticetime=req.getParameter("noticetime");
        System.out.println(noticetime);
        if(noticetime.equals(""))//��鹫��ʱ���Ƿ�Ϊ��
        {
        	req.setAttribute("message", "�����빫��ʱ�䣡");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }
        
        
        DbUtil db= new DbUtil();
        Notice notice=new Notice(noticetitle,noticecontent,noticetime);
        NoticeDAO dao=new NoticeDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            int flag=dao.add_notice(con,notice);
            if(flag==1) {
            	resp.sendRedirect("admin_notice.jsp");
            }
            else
            {
            	req.setAttribute("message", "��ӹ���ʧ��");
            	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}