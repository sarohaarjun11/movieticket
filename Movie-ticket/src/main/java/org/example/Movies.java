package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Movies extends Customer{
    public static void movies(){
        System.out.println("Following movies are available:-\n");

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movieticket", "root", "Arjun11@");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet= statement.executeQuery("select * from movies");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("MovieID")+
                        ": " + resultSet.getString("Title")
                        +"\t\t"+resultSet.getString("Genre")
                        +"\t\t"+resultSet.getInt("Released")
                        +"\t"+resultSet.getBigDecimal("Duration")
                        +"\t"+resultSet.getInt("base_price"));
            }

            System.out.println("\n");

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}