package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Tickets extends Movies{
    public static void tickets(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Redirecting for purchase...");

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movieticket", "root", "Arjun11@");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet2= statement.executeQuery("select * from customers");
            System.out.print("\n\nYou are customer: ");
            while (resultSet2.next()) {
                if(resultSet2.getInt("customerID") == cid)
                    System.out.println("\""+resultSet2.getString("name")+"\"");
            }
            resultSet2.close();

            System.out.print("\nPlease enter the Movie ID: ");
            int mid = sc.nextInt();

            ResultSet resultSet3= statement.executeQuery("select * from movies");
            System.out.print("\nYou have chosen movie: ");
            while (resultSet3.next()) {
                if(resultSet3.getInt("movieID") == mid)
                    System.out.println("\""+resultSet3.getString("title")+"\"");
            }

            System.out.println("\n");

            System.out.println("Ticket type: ");
            System.out.println("1. Child\n2. Adult\n3. Senior");
            int tt = sc.nextInt();
            double price=0.0;
            String tt_str="";
            resultSet3.beforeFirst();
            if(tt == 1){
                while (resultSet3.next()) {
                    if(resultSet3.getInt("MovieID") == mid)
                        price=resultSet3.getInt("base_price");
                }
                tt_str="Child";
            }
            else if(tt == 2){
                while (resultSet3.next()) {
                    if(resultSet3.getInt("MovieID") == mid) {
                        price = resultSet3.getInt("base_price") * 1.2;
                    }
                }
                tt_str="Adult";
            }
            else if(tt == 3){
                while (resultSet3.next()) {
                    if(resultSet3.getInt("MovieID") == mid)
                        price=resultSet3.getInt("base_price") * 0.8;
                }
                tt_str="Senior";
            }
            resultSet3.close();

            System.out.println("\nPrice: "+price+"\n");

            String s = "insert into tickets(movieID,customerID,ticket_type,price) " +
                    "values("+mid+","+cid+",\""+tt_str+"\","+price+")";
            statement.executeUpdate(s);

            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}