package Transform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static sun.management.jmxremote.ConnectorBootstrap.PropertyNames.PORT;

public class test {

    public static void connectJDBCToAWSEC2() {

        System.out.println("----MySQL JDBC Connection Testing -------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://" + "my-first-instance.chr2fkeh9su8.eu-west-1.rds.amazonaws.com" + ":" + PORT + "/" + 3306, "masteruser", "FgTbaukSwX8a4wPM");
        } catch (SQLException e) {
            System.out.println("Connection Failed!:\n" + e.getMessage());
        }

        if (connection != null) {
            System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        } else {
            System.out.println("FAILURE! Failed to make connection!");
        }

    }

    public static void main(String[] args) {

        connectJDBCToAWSEC2();






}
}
