package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class AdminDAO {
    //管理员登录（给：用户名、密码）
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
    
    //添加管理员（给：用户名、密码、手机号）
    public int add_admin(Connection con,Admin admin) throws Exception{
        
        int flag=0;//添加失败
        PreparedStatement pstmt = null;
        String sql="select * from admin_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, admin.getUsername());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
        	flag=1;//用户名已存在
        }
        else
        {
            sql="INSERT INTO admin_info(username,password,phone_number)VALUES(?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getPhonenumber());
            if (pstmt.executeUpdate() > 0) {
                flag=2;//添加成功
            }
        }
        return flag;
    }
    
    //删除管理员（给：用户名）
    public int delete_admin(Connection con,String username) throws Exception{
        
        int flag=0;//删除失败
        PreparedStatement pstmt = null;
        String sql="select * from admin_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()){
        	flag=1;//用户名不存在
        }
        else
        {
            sql="DELETE FROM admin_info WHERE username=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            if (pstmt.executeUpdate() > 0) {
                flag=2;//删除成功
            }
        }
        return flag;
    }
    
    //修改管理员密码（给：用户名、旧密码、新密码）
    public int modify_admin(Connection con,String username,String new_password) throws Exception{
        
        int flag=0;//修改失败
        PreparedStatement pstmt = null;
        String sql="select * from admin_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()){
        	flag=1;//用户名不存在
        }
        else
        {
            sql="UPDATE admin_info SET password = ? WHERE username = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, new_password);
            pstmt.setString(2, username);
            if (pstmt.executeUpdate() > 0) {
                flag=2;//删除成功
            }
        }
        return flag;
    }
    
    //获取所有管理员信息
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