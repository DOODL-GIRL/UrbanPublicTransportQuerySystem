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
 
@WebServlet("/Station")
public class StationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    
    
        /**
         * 接收前台传来的值 站点名和线路名
         */
    	req.setCharacterEncoding("UTF-8");
        String station_name=req.getParameter("station_name");
        if(station_name.equals(""))//检查站点名称是否为空
        {
        	req.setAttribute("message", "请填入站点名称！");
            req.getRequestDispatcher("station_query.jsp").forward(req, resp);
        }
        String line_name=req.getParameter("line_name");//线路名称可以为空
        DbUtil db= new DbUtil();
        
        StationDAO dao=new StationDAO();
        try {
            //数据库连接
            Connection con=db.getCon();
            Station station=dao.station_query(con, station_name,line_name);
            
            if(station!=null) {
            	req.setAttribute("station", station);
                req.setAttribute("station_name", station.getName());
                if(station.getType()==true)
                	req.setAttribute("station_type", "公交站");
                else
                	req.setAttribute("station_type", "地铁站");
                
                req.setAttribute("line_name", station.getLinename());
                req.getRequestDispatcher("station_query.jsp").forward(req, resp);
                
            }else {
            	req.setAttribute("result_message", "没有查询到结果！");
                req.getRequestDispatcher("station_query.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}