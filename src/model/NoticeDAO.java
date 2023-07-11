package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class NoticeDAO {
    //添加公告（给：公告标题、公告内容、公告日期）
    public int add_notice(Connection con,Notice notice) throws Exception{
        int flag=0;//添加失败
        PreparedStatement pstmt = null;
        String sql="INSERT INTO notice(title,content,time)VALUES(?,?,?)";//修改站点表
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, notice.getTitle());
        pstmt.setString(2, notice.getContent());
        pstmt.setString(3, notice.getTime());
        if (pstmt.executeUpdate() > 0) 
            flag=1;//添加成功
        return flag;
    }
    
    //删除公告（给：公告编号）
    public int delete_notice(Connection con,int number) throws Exception{
        int flag=0;//删除失败
        PreparedStatement pstmt = null;
        String sql="DELETE FROM notice WHERE number=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, number);
        if (pstmt.executeUpdate() > 0) 
            flag=1;//删除成功
        return flag;
    }
    
    //获取所有公告信息
    public List<Notice> get_all_notices(Connection con) throws Exception{
    	
    	PreparedStatement pstmt = null;
        String sql="select * from notice";
        pstmt = con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        List<Notice> temp_notices=new ArrayList<>();
        while(rs.next())
        {
        	Notice temp_notice=new Notice();
        	temp_notice.setNumber(rs.getInt("number"));
        	temp_notice.setTitle(rs.getString("title"));
        	temp_notice.setContent(rs.getString("content"));
        	temp_notice.setTime(rs.getString("time"));
        	temp_notices.add(temp_notice);
        }
        return temp_notices;
    }
}