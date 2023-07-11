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

import java.util.List;
import java.util.ArrayList;
 
@WebServlet("/notice")
public class NoticeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    

        
        DbUtil db= new DbUtil();
        NoticeDAO dao=new NoticeDAO();
        try {
        	//数据库连接
            Connection con=db.getCon();
            List<Notice> notices=dao.get_all_notices(con);
            if(notices!=null) {
                req.setAttribute("notices", notices);
                req.getRequestDispatcher("notice.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    	
    }
}