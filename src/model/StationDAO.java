package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class StationDAO {
    
    //վ���ѯ������վ�����ơ���·���ƣ�
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
        int line_number=0;//��·���
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
        
        //��ȡ��Ӧ��·����
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
    
    //���վ�㣨����վ�����ơ�վ�����͡����ȡ�γ�ȣ�
    public int add_station(Connection con,Station station,int line_number) throws Exception{
        
        int flag=0;//���ʧ��
        PreparedStatement pstmt = null;
        String sql="INSERT INTO station(name,type,longitude,latitude)VALUES(?,?,?,?)";//�޸�վ���
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
            	int station_number=rs.getInt("LAST_INSERT_ID()");//��ȡ�ող���վ������
            	sql="INSERT INTO line_station(line_number,station_number)VALUES(?,?)";//�޸���·_վ���
            	pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, line_number);
                pstmt.setInt(2, station_number);
                if (pstmt.executeUpdate() > 0) 
                	flag=1;//��ӳɹ�
            }
        }
        return flag;
    }
    
    //ɾ��վ�㣨����վ���ţ�
    public int delete_station(Connection con,int number) throws Exception{
        
        int flag=0;//ɾ��ʧ��
        PreparedStatement pstmt = null;
        String sql="DELETE FROM station WHERE number=?";//�޸�վ���
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, number);
        if (pstmt.executeUpdate() > 0) {
        	sql="DELETE FROM line_station WHERE station_number=?";//�޸���·_վ���
        	pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, number);
            if (pstmt.executeUpdate() > 0) {
            	flag=1;//ɾ���ɹ�
            }
        }
        return flag;
    }
    
    //��ȡ����վ����Ϣ
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