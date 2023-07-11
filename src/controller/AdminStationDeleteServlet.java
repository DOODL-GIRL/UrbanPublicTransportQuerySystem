package controller;
 
import java.io.IOException;
import java.sql.Connection;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.StationDAO;
import model.DbUtil;
import model.Station;
 
@WebServlet("/station_delete")
public class AdminStationDeleteServlet extends HttpServlet{
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
    	//获取要删除的站点编号
        String stationId=req.getParameter("stationId");
        if(stationId.equals(""))//检查用户名是否为空
        {
        	req.setAttribute("message", "请输入用户名！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        int number=0;
        try {
            number = Integer.parseInt(stationId);
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "请输入正确的站点编号！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }

        
        
        DbUtil db= new DbUtil();
        StationDAO dao=new StationDAO();
        try {
            //数据库连接
            Connection con=db.getCon();
            int flag=dao.delete_station(con, number);
            if(flag==1) {
            	resp.sendRedirect("admin_station.jsp");
            }
            else
            {
            	req.setAttribute("message", "删除失败");
            	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}