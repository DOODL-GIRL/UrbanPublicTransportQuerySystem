package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class LineDAO {
    //ÏßÂ·²éÑ¯
    public Line line_query(Connection con,String line_name,Boolean line_type) throws Exception{
    	Line resultLine=null;
        String sql="select * from line where name=? and type=?";
        
        
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, line_name);
        pstmt.setBoolean(2, line_type);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultLine=new Line();
            resultLine.setName(rs.getString("name"));
            resultLine.setType(rs.getBoolean("type"));
            resultLine.setStartingTime(rs.getString("starting_time"));
            resultLine.setEndingTime(rs.getString("ending_time"));
            int line_number=rs.getInt("number");
            if (pstmt != null) {
	    		pstmt.close();
	    	}
	    	if (rs != null) {
	    		rs.close();
	    	}
	    	sql="select * from station where number in (select station_number from line_station where line_number=?)";
	    	pstmt=con.prepareStatement(sql);
	        pstmt.setInt(1, line_number);
	        rs=pstmt.executeQuery();
	        List<Station> temp_stations=new ArrayList<>();
	        while(rs.next())
	        {
	        	Station temp_station=new Station();
	        	temp_station.setNumber(rs.getInt("number"));
	        	temp_station.setName(rs.getString("name"));
	        	temp_station.setType(rs.getBoolean("type"));
	        	temp_station.setLongitude(rs.getDouble("longitude"));
	        	temp_station.setLatitude(rs.getDouble("latitude"));
	        	temp_stations.add(temp_station);  
	        }
	        resultLine.setStations(temp_stations);
        }
        return resultLine;
    }
}