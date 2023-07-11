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
 
@WebServlet("/station_add")
public class AdminStationAddServlet extends HttpServlet{
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
    	//获取站点名称
        String name=req.getParameter("placename");
        System.out.println(name);
        if(name.equals(""))//检查用户名是否为空
        {
        	req.setAttribute("message", "请输入站点名！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //获取站点类型
        String placesort=req.getParameter("placesort");
        System.out.println(placesort);
        if(placesort.equals(""))//检查站点类型是否为空
        {
        	req.setAttribute("message", "请输入站点类型！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        boolean type=false;
        try {
        	type = Boolean.parseBoolean(placesort);//将站点类型转换为bool类型
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "请输入正确的站点类型！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //获取站点纬度
        String placelat=req.getParameter("placelat");
        System.out.println(placelat);
        if(placelat.equals(""))//检查站点纬度是否为空
        {
        	req.setAttribute("message", "请输入站点纬度！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        double latitude=0;
        try {
        	latitude = Double.parseDouble(placelat);//将纬度转换为double类型
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "请输入正确的纬度！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //获取站点经度
        String placelng=req.getParameter("placelng");
        System.out.println(placelng);
        if(placelng.equals(""))//检查站点经度是否为空
        {
        	req.setAttribute("message", "请输入站点纬度！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        double longitude=0;
        try {
        	longitude = Double.parseDouble(placelng);//将经度转换为double类型
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "请输入正确的经度！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //获取线路编号
        String placeline=req.getParameter("placeline");
        System.out.println(placeline);
        if(placeline.equals(""))//检查线路编号是否为空
        {
        	req.setAttribute("message", "请输入线路编号！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        int linenumber=0;
        try {
            linenumber = Integer.parseInt(placeline);
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "请输入正确的线路编号！");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        DbUtil db= new DbUtil();
        Station station=new Station(name,type,longitude,latitude);
        StationDAO dao=new StationDAO();
        try {
            //数据库连接
            Connection con=db.getCon();
            int flag=dao.add_station(con,station,linenumber);
            if(flag==1) {
            	resp.sendRedirect("admin_station.jsp");
            }
            else
            {
            	req.setAttribute("message", "站点添加失败");
            	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}