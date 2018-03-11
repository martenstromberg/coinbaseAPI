package Transform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlCon {

    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://my-first-instance.chr2fkeh9su8.eu-west-1.rds.amazonaws.com:3306/my_first_db","masteruser","FgTbaukSwX8a4wPM");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO my_first_db.pet (name, owner, species, sex, birth, death) VALUES ('hello', 'from', 'java', 'M', '2017-10-10', '2018-10-12')");
            ResultSet rs=stmt.executeQuery("SELECT * FROM my_first_db.pet");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }


}
