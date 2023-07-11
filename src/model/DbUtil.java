package model;
 
import java.sql.Connection;
import java.sql.DriverManager;
 
public class DbUtil {
    
    /**
     *
     * @DbUtil������� jdbc ��һЩ������
     * dbUrl
     * dbUsername
     * dbPassword
     * jdbcName
     */
 
    private String dbUrl="jdbc:mysql://localhost:3306/bus-master?useUnicode=true&characterEncoding=utf8&serverTimezone=CTT&useSSL=false";
    private String dbUsername="root";
    private String dbPassword="123456";  
    private String jdbcName="com.mysql.cj.jdbc.Driver";
    
    /**
     * ��ȡ���ݿ�����
     * @return
     * @throws Exception
     */
    public Connection getCon() throws Exception{
        Class.forName(jdbcName);
        Connection con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        return con;
    }
    
    /**
     * �ر����ݿ�����
     * @param con
     * @throws Exception
     */
    public void closeCon(Connection con) throws Exception{
        if(con!=null){
            con.close();
        }
    }
    
    /**
     * ����һ��main���������Ƿ�����������ݿ�ɹ���
     */
    
    public static void main(String[] args) {
        DbUtil dbUtil=new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("���ݿ����ӳɹ�");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}