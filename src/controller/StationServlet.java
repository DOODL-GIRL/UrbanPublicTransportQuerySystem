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
         * ����ǰ̨������ֵ վ��������·��
         */
    	req.setCharacterEncoding("UTF-8");
        String station_name=req.getParameter("station_name");
        if(station_name.equals(""))//���վ�������Ƿ�Ϊ��
        {
        	req.setAttribute("message", "������վ�����ƣ�");
            req.getRequestDispatcher("station_query.jsp").forward(req, resp);
        }
        String line_name=req.getParameter("line_name");//��·���ƿ���Ϊ��
        DbUtil db= new DbUtil();
        
        StationDAO dao=new StationDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            Station station=dao.station_query(con, station_name,line_name);
            
            if(station!=null) {
            	req.setAttribute("station", station);
                req.setAttribute("station_name", station.getName());
                if(station.getType()==true)
                	req.setAttribute("station_type", "����վ");
                else
                	req.setAttribute("station_type", "����վ");
                
                req.setAttribute("line_name", station.getLinename());
                req.getRequestDispatcher("station_query.jsp").forward(req, resp);
                
            }else {
            	req.setAttribute("result_message", "û�в�ѯ�������");
                req.getRequestDispatcher("station_query.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}