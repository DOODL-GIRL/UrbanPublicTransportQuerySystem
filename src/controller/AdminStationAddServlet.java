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
         * ����ǰ̨������ֵ
         */
    	req.setCharacterEncoding("UTF-8");
    	//��ȡվ������
        String name=req.getParameter("placename");
        System.out.println(name);
        if(name.equals(""))//����û����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "������վ������");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //��ȡվ������
        String placesort=req.getParameter("placesort");
        System.out.println(placesort);
        if(placesort.equals(""))//���վ�������Ƿ�Ϊ��
        {
        	req.setAttribute("message", "������վ�����ͣ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        boolean type=false;
        try {
        	type = Boolean.parseBoolean(placesort);//��վ������ת��Ϊbool����
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "��������ȷ��վ�����ͣ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //��ȡվ��γ��
        String placelat=req.getParameter("placelat");
        System.out.println(placelat);
        if(placelat.equals(""))//���վ��γ���Ƿ�Ϊ��
        {
        	req.setAttribute("message", "������վ��γ�ȣ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        double latitude=0;
        try {
        	latitude = Double.parseDouble(placelat);//��γ��ת��Ϊdouble����
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "��������ȷ��γ�ȣ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //��ȡվ�㾭��
        String placelng=req.getParameter("placelng");
        System.out.println(placelng);
        if(placelng.equals(""))//���վ�㾭���Ƿ�Ϊ��
        {
        	req.setAttribute("message", "������վ��γ�ȣ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        double longitude=0;
        try {
        	longitude = Double.parseDouble(placelng);//������ת��Ϊdouble����
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "��������ȷ�ľ��ȣ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        //��ȡ��·���
        String placeline=req.getParameter("placeline");
        System.out.println(placeline);
        if(placeline.equals(""))//�����·����Ƿ�Ϊ��
        {
        	req.setAttribute("message", "��������·��ţ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        int linenumber=0;
        try {
            linenumber = Integer.parseInt(placeline);
        } catch (NumberFormatException e) {
        	req.setAttribute("message", "��������ȷ����·��ţ�");
        	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
        }
        
        DbUtil db= new DbUtil();
        Station station=new Station(name,type,longitude,latitude);
        StationDAO dao=new StationDAO();
        try {
            //���ݿ�����
            Connection con=db.getCon();
            int flag=dao.add_station(con,station,linenumber);
            if(flag==1) {
            	resp.sendRedirect("admin_station.jsp");
            }
            else
            {
            	req.setAttribute("message", "վ�����ʧ��");
            	req.getRequestDispatcher("admin_station.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}