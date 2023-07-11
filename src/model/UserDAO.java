package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
 
public class UserDAO {
    //顾客登录(给：用户名、密码)
    public User login(Connection con,User user) throws Exception{
        User resultUser=null;
        String sql="select * from user_info where username=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultUser=new User();
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
    
    //顾客注册（给：用户名、密码、手机号）
    public int regist(Connection con,User user) throws Exception{
        
        int flag=0;//注册失败
        PreparedStatement pstmt = null;
        String sql="select * from user_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
        	flag=1;//用户名已存在
        }
        else
        {
            sql="INSERT INTO user_info(username,password,phone_number)VALUES(?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhonenumber());
            if (pstmt.executeUpdate() > 0) {
                flag=2;//注册成功
            }
        }
        return flag;
    }
    
   //删除用户（给：用户名）
    public int delete_user(Connection con,String username) throws Exception{
        
        int flag=0;//删除失败
        PreparedStatement pstmt = null;
        String sql="select * from user_info where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()){
        	flag=1;//用户名不存在
        }
        else
        {
            sql="DELETE FROM user_info WHERE username=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            if (pstmt.executeUpdate() > 0) {
                flag=2;//删除成功
            }
        }
        return flag;
    }
    
    //获取所有用户信息
    public List<User> get_all_users(Connection con) throws Exception{
    	
    	PreparedStatement pstmt = null;
        String sql="select * from user_info";
        pstmt = con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        List<User> temp_users=new ArrayList<>();
        while(rs.next())
        {
        	User temp_user=new User();
        	temp_user.setUsername(rs.getString("username"));
        	temp_user.setPassword(rs.getString("password"));
        	temp_user.setPhonenumber(rs.getString("phone_number"));
        	temp_users.add(temp_user);
        }
        return temp_users;
    }
}