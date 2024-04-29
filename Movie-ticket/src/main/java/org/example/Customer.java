package org.example;
import java.sql.*;
import java.util.Scanner;

public class Customer {
    static int cid=0;

    public static void customer(){
//        System.out.println("Inside Customer...");
        Scanner inp = new Scanner(System.in);
        System.out.print("Are you a new(1) or an existing(2) customer?(Exit(0)) : ");
        int n=inp.nextInt();
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movieticket", "root", "Arjun11@");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet= statement.executeQuery("select * from customers");
            switch (n) {
                case 1 -> {
                    System.out.print("Name : ");
                    String name = inp.next();
                    System.out.print("Email : ");
                    String em = inp.next();
                    System.out.print("Phone Number : ");
                    String pno = inp.next();
                    String s = "insert into customers(Name, email, phone_number) values(\"" + name + "\",\"" + em + "\",\"" + pno + "\")";
                    statement.executeUpdate(s);
                    System.out.println("\nNew customer added...\n");
                    customer();
                }
                case 2 -> {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("CustomerID")+": "+resultSet.getString("name"));
                    }
                    System.out.println("Exit(0)");
                    System.out.print("Enter your customer ID: ");
                    cid= inp.nextInt();
                    resultSet.beforeFirst();
                    while (resultSet.next()) {
                        if(resultSet.getInt("CustomerID") == cid)
                            System.out.println("You are customer: " + resultSet.getString("name"));
                    }
                    System.out.println();
                    customer();
                }
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

//        inp.close();
    }
}