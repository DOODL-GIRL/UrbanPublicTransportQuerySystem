package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class NoticeDAO {
    //��ӹ��棨����������⡢�������ݡ��������ڣ�
    public int add_notice(Connection con,Notice notice) throws Exception{
        int flag=0;//���ʧ��
        PreparedStatement pstmt = null;
        String sql="INSERT INTO notice(title,content,time)VALUES(?,?,?)";//�޸�վ���
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, notice.getTitle());
        pstmt.setString(2, notice.getContent());
        pstmt.setString(3, notice.getTime());
        if (pstmt.executeUpdate() > 0) 
            flag=1;//��ӳɹ�
        return flag;
    }
    
    //ɾ�����棨���������ţ�
    public int delete_notice(Connection con,int number) throws Exception{
        int flag=0;//ɾ��ʧ��
        PreparedStatement pstmt = null;
        String sql="DELETE FROM notice WHERE number=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, number);
        if (pstmt.executeUpdate() > 0) 
            flag=1;//ɾ���ɹ�
        return flag;
    }
    
    //��ȡ���й�����Ϣ
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