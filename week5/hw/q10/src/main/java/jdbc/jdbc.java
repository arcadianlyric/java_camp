package jdbc;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;


public class jabc {


 public static void main(String args[]) throws SQLException
 {
    /* table: student
    ID	AGE	CLASS  DATE
    11	24	class1 01012020
    12	30	class2 01012020
    13	26	class1 01012020
    */
    // using JDBC interface, achieve database CRUD functions
    Connection conn = DriverManager.getConnection("mysql:\\localhost:1520", 
    "root","root");

    // improve the above by Transaction, PrepareStatement，Spring Batch
    PreparedStatement preStatement = conn.prepareStatement("select distinct age from student where class=?");
    preStatement.setString(1, "class2");

    ResultSet result = preStatement.executeQuery();

    while(result.next())
    {
        System.out.println("age: " + result.getString("age"));
    }       
    }

    // improve the above by configuring Hikari connection pool  
    HikariConfig config = new HikariConfig("/hikari.properties");
    DataSource dataSource = new HikariDataSource(config);
    Connection conn = dataSource.getConnection();
    String sql = "insert into student values(?, ?, ?, ?)";
    PreparedStatement preStatement = conn.prepareStatement(sql);
    preStatement.setString(1, 14);
    preStatement.setInt(2, 18);
    preStatement.setString(3, "class3");
    preStatement.setDate(4, new Date(System.currentTimeMillis()));
    preStatement.executeUpdate();
    conn.commit();

}