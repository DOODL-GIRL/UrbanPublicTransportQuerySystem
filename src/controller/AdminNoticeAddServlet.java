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
         * 接收前台传来的值
         */
    	req.setCharacterEncoding("UTF-8");
    	//获取公告标题
        String noticetitle=req.getParameter("noticetitle");
        System.out.println(noticetitle);
        if(noticetitle.equals(""))//检查公告标题是否为空
        {
        	req.setAttribute("message", "请输入公告标题！");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }
        //获取公告内容
        String noticecontent=req.getParameter("noticecontent");
        System.out.println(noticecontent);
        if(noticecontent.equals(""))//检查公告内容是否为空
        {
        	req.setAttribute("message", "请输入公告内容！");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }
        //获取公告时间
        String noticetime=req.getParameter("noticetime");
        System.out.println(noticetime);
        if(noticetime.equals(""))//检查公告时间是否为空
        {
        	req.setAttribute("message", "请输入公告时间！");
        	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
        }
        
        
        DbUtil db= new DbUtil();
        Notice notice=new Notice(noticetitle,noticecontent,noticetime);
        NoticeDAO dao=new NoticeDAO();
        try {
            //数据库连接
            Connection con=db.getCon();
            int flag=dao.add_notice(con,notice);
            if(flag==1) {
            	resp.sendRedirect("admin_notice.jsp");
            }
            else
            {
            	req.setAttribute("message", "添加公告失败");
            	req.getRequestDispatcher("admin_notice.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}