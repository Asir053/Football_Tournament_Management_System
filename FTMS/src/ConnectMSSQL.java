
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asir
 */
public class ConnectMSSQL {

    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
                    .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:"
                    + connection.getMetaData().getDatabaseProductName());
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT FirstName FROM Customer");
            while (resultSet.next()) {
                System.out.println("Customer NAME:"
                        + resultSet.getString("FirstName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
