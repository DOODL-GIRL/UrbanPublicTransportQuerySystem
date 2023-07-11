package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class StationDAO {
    
    //站点查询（给：站点名称、线路名称）
    public Station station_query(Connection con,String station_name,String line_name) throws Exception{
    	Station resultStation=null;
    	PreparedStatement pstmt=null;
    	if(line_name.equals(""))
    	{
    		String sql="select * from station where name=?";
    		pstmt=con.prepareStatement(sql);
    		pstmt.setString(1, station_name);
    	}
    	else
    	{
    		String sql="select * from station where name=? and number in (select station_number from line_station where line_number in (select number from line where name=?));";
    		pstmt=con.prepareStatement(sql);
    		pstmt.setString(1, station_name);
    		pstmt.setString(2, line_name);
    	}

        ResultSet rs=pstmt.executeQuery();
        int line_number=0;//线路序号
        if(rs.next()){
        	try {
                	resultStation=new Station();
                	resultStation.setNumber(rs.getInt("number"));
                	resultStation.setName(rs.getString("name"));
                	resultStation.setType(rs.getBoolean("type"));
                	resultStation.setLongitude(rs.getDouble("longitude"));
                	resultStation.setLatitude(rs.getDouble("latitude"));
                	line_number=rs.getInt("number");
        	}
        	finally {
        	    	if (pstmt != null) {
        	    		pstmt.close();
        	    	}
        	    	if (rs != null) {
        	    		rs.close();
        	    	}
            }
        }
        else
        {
        	return resultStation;
        }
        
        //获取对应线路名称
        String sql_line_name="select * from line where number in (select line_number from line_station where station_number=?)";
        PreparedStatement pstmt_line_name=con.prepareStatement(sql_line_name);
        pstmt_line_name.setInt(1,line_number);
        ResultSet rs_line_name=pstmt_line_name.executeQuery();
        if(rs_line_name.next())
        {
        	try {
        		resultStation.setLinename(rs_line_name.getString("name"));
        	}
        	finally
        	{
        		if (pstmt_line_name != null) {
    	    		pstmt_line_name.close();
    	    	}
    	    	if (rs_line_name != null) {
    	    		rs_line_name.close();
    	    	}
        	}
        }
        return resultStation;
    }
    
    //添加站点（给：站点名称、站点类型、经度、纬度）
    public int add_station(Connection con,Station station,int line_number) throws Exception{
        
        int flag=0;//添加失败
        PreparedStatement pstmt = null;
        String sql="INSERT INTO station(name,type,longitude,latitude)VALUES(?,?,?,?)";//修改站点表
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, station.getName());
        pstmt.setBoolean(2, station.getType());
        pstmt.setDouble(3, station.getLongitude());
        pstmt.setDouble(4, station.getLatitude());
        if (pstmt.executeUpdate() > 0) 
        {
            sql="SELECT LAST_INSERT_ID()";
            pstmt=con.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
            	int station_number=rs.getInt("LAST_INSERT_ID()");//获取刚刚插入站点的序号
            	sql="INSERT INTO line_station(line_number,station_number)VALUES(?,?)";//修改线路_站点表
            	pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, line_number);
                pstmt.setInt(2, station_number);
                if (pstmt.executeUpdate() > 0) 
                	flag=1;//添加成功
            }
        }
        return flag;
    }
    
    //删除站点（给：站点编号）
    public int delete_station(Connection con,int number) throws Exception{
        
        int flag=0;//删除失败
        PreparedStatement pstmt = null;
        String sql="DELETE FROM station WHERE number=?";//修改站点表
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, number);
        if (pstmt.executeUpdate() > 0) {
        	sql="DELETE FROM line_station WHERE station_number=?";//修改线路_站点表
        	pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, number);
            if (pstmt.executeUpdate() > 0) {
            	flag=1;//删除成功
            }
        }
        return flag;
    }
    
    //获取所有站点信息
    public List<Station> get_all_stations(Connection con) throws Exception{
    	PreparedStatement pstmt = null;
        String sql="select * from station";
        pstmt = con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
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
        return temp_stations;
    }
}