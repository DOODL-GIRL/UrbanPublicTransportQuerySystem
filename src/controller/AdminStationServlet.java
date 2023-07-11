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

import java.util.List;
import java.util.ArrayList;
 
@WebServlet("/admin_station")
public class AdminStationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    

        
        DbUtil db= new DbUtil();
        StationDAO dao=new StationDAO();
        try {
        	//数据库连接
            Connection con=db.getCon();
            List<Station> admins=dao.get_all_stations(con);
            if(admins!=null) {
                req.setAttribute("stations", admins);
                req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    	
    }
}