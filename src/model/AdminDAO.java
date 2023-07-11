package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class AdminDAO {
    //����Ա��¼�������û��������룩
    public Admin login(Connection con,Admin user) throws Exception{
    	Admin resultUser=null;
        String sql="select * from admin_info where username=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultUser=new Admin();
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
    
    //��ӹ���Ա�������û��������롢�ֻ��ţ�
    public int add_admin(Connection con,Admin admin) throws Exception{
        
        int flag=0;//���ʧ��
        PreparedStatement pstmt = null;
        String sql="select * from admin_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, admin.getUsername());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
        	flag=1;//�û����Ѵ���
        }
        else
        {
            sql="INSERT INTO admin_info(username,password,phone_number)VALUES(?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getPhonenumber());
            if (pstmt.executeUpdate() > 0) {
                flag=2;//��ӳɹ�
            }
        }
        return flag;
    }
    
    //ɾ������Ա�������û�����
    public int delete_admin(Connection con,String username) throws Exception{
        
        int flag=0;//ɾ��ʧ��
        PreparedStatement pstmt = null;
        String sql="select * from admin_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()){
        	flag=1;//�û���������
        }
        else
        {
            sql="DELETE FROM admin_info WHERE username=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            if (pstmt.executeUpdate() > 0) {
                flag=2;//ɾ���ɹ�
            }
        }
        return flag;
    }
    
    //�޸Ĺ���Ա���루�����û����������롢�����룩
    public int modify_admin(Connection con,String username,String new_password) throws Exception{
        
        int flag=0;//�޸�ʧ��
        PreparedStatement pstmt = null;
        String sql="select * from admin_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()){
        	flag=1;//�û���������
        }
        else
        {
            sql="UPDATE admin_info SET password = ? WHERE username = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, new_password);
            pstmt.setString(2, username);
            if (pstmt.executeUpdate() > 0) {
                flag=2;//ɾ���ɹ�
            }
        }
        return flag;
    }
    
    //��ȡ���й���Ա��Ϣ
    public List<Admin> get_all_admins(Connection con) throws Exception{
    	
    	PreparedStatement pstmt = null;
        String sql="select * from admin_info";
        pstmt = con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        List<Admin> temp_admins=new ArrayList<>();
        while(rs.next())
        {
        	Admin temp_admin=new Admin();
        	temp_admin.setUsername(rs.getString("username"));
        	temp_admin.setPassword(rs.getString("password"));
        	temp_admin.setPhonenumber(rs.getString("phone_number"));
        	temp_admins.add(temp_admin);
        }
        return temp_admins;
    }
}