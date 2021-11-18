package com.bank.loginSign;

import com.bank.Ui.UserInterfaceForLogin;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class SignApplication {
    public static final String URLTOCONNECT = "jdbc:mysql://localhost:3303/Bank";

    public static final String USERNAME = "root";

    public static final String USERPASSWORD = "root";

    String qry;

    Connection dbCon;

    Statement theStatement;

    ResultSet theResultSet;

    PreparedStatement thePreparedStatement;

    public SignApplication() {
        try {
//          Load the DB Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            dbCon = DriverManager.getConnection(URLTOCONNECT, USERNAME, USERPASSWORD);
            theStatement = dbCon.createStatement();

//			System.out.println("Connected to the database now...");


        } catch (ClassNotFoundException e) {
            System.out.println("Can't load the driver : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Can't connect to the database : " + e.getMessage());
        }
    }
    public void userSign()
    {
        Scanner sc=new Scanner(System.in);
        String userName;
        String userCity;
        String userState;
        String userPassword;
        System.out.println("WELCOME TO SIGNIN PAGE");
        System.out.println();
        System.out.println("Enter your name");
        userName=sc.next();
        System.out.println("Enter your city");
        userCity=sc.next();
        System.out.println("Enter your state");
        userState=sc.next();
        System.out.println("Set your Password");
        userPassword=sc.next();


        try {
            qry="insert into user(user_name,user_city,user_state,user_password) values(?,?,?,?)";
            thePreparedStatement=dbCon.prepareStatement(qry);
            thePreparedStatement.setString(1,userName);
            thePreparedStatement.setString(2,userCity);
            thePreparedStatement.setString(3,userState);
            thePreparedStatement.setString(4,userPassword);
            if(thePreparedStatement.executeUpdate()>0)
            {
                System.out.println("User added Successfully");
            }
        } catch (SQLException e) {
            System.out.println("Exception inside userSign");
        }


    }
    public void userLogin()
    {
        Scanner sc=new Scanner(System.in);
        String userName;
        String password;
        System.out.println("Enter username");
        userName=sc.next();
        System.out.println("Enter the password");
        password=sc.next();
        qry="select user_name from user where user_name=? and user_password=?";
        try {
            thePreparedStatement=dbCon.prepareStatement(qry);
            thePreparedStatement.setString(1,userName);
            thePreparedStatement.setString(2,password);
            theResultSet=thePreparedStatement.executeQuery();
            if(!theResultSet.next())
            {
                System.out.println("Wrong username or Password");
            }
            else{
                System.out.println("Succussefully Logged in with username:  "+userName);


                UserInterfaceForLogin app=new UserInterfaceForLogin();
                app.loginApplication();
            }
        } catch (SQLException | ParseException e) {
            System.out.println("Exception inside login");
        }
    }
}