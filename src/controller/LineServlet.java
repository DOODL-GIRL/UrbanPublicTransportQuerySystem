package controller;
 
import java.io.IOException;
import java.sql.Connection;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.LineDAO;
import model.DbUtil;
import model.Line;
 
@WebServlet("/Line")
public class LineServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
    
    
        /**
         * 接收前台传来的值 线路名和线路类型
         */
    	req.setCharacterEncoding("UTF-8");
        String line_name=req.getParameter("line_name");
        if(line_name.equals(""))//检查线路名称是否为空
        {
        	req.setAttribute("message", "请填入线路名称！");
            req.getRequestDispatcher("line_query.jsp").forward(req, resp);
        }
        String line_type_string=req.getParameter("line_type");
        Boolean line_type = line_type_string.equals("bus") ? true:false;
        
        
        
        DbUtil db= new DbUtil();
        
        LineDAO dao=new LineDAO();
        try {
            //数据库连接
            Connection con=db.getCon();
            Line line=dao.line_query(con, line_name,line_type);
            
            
            
            if(line!=null) {
            	System.out.println(line.getStations().size());
                for(int i=0;i<line.getStations().size();i++)
                	System.out.println(line.getStations().get(i).getNumber());
                req.setAttribute("line", line);
                req.setAttribute("starting_time", line.getStartingTime());
                req.setAttribute("ending_time", line.getEndingTime());
                
                
                req.setAttribute("stations",line.getStations());
                
                
                req.getRequestDispatcher("line_query.jsp").forward(req, resp);
                
            }else {
            	req.setAttribute("result_message", "没有查询到结果！");
                req.getRequestDispatcher("line_query.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}