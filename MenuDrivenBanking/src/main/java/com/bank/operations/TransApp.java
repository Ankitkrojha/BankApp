package com.bank.operations;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
public class TransApp {
    public static final String URLTOCONNECT = "jdbc:mysql://localhost:3303/Bank";

    public static final String USERNAME = "root";

    public static final String USERPASSWORD = "root";

    String qry;


    Connection dbCon;

    Statement theStatement;

    ResultSet theResultSet;

    PreparedStatement thePreparedStatement;
    public TransApp(){
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

    public void checkBalance(String accountNo) throws SQLException,ParseException {
        qry="Select amount from Account where accountNumber='"+accountNo+"'";
        String amount="";
        try {
            theResultSet = theStatement.executeQuery(qry);
            while(theResultSet.next())
            {
                amount=theResultSet.getString("amount");
                System.out.println("Available Balance: "+theResultSet.getString("amount"));
            }
            //Setting acc no,action and date for tranaction detail
            String Action="Checked Balance "+amount;
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String transactionDate = formatter.format(date);
            TransApp app=new TransApp();
            app.transactionImpl(accountNo,Action,transactionDate);

        }
        catch (SQLException e) {
            System.out.println("Can't execute the query : " + e.getMessage());
        }

    }
    public void deposit(String accountNo,int amount) {
        try {

            thePreparedStatement = dbCon.prepareStatement("select amount from Account where accountNumber=?");
            int totalAmount = amount;

            thePreparedStatement.setString(1, accountNo);

            theResultSet = thePreparedStatement.executeQuery();

            while (theResultSet.next()) {
                totalAmount = totalAmount + theResultSet.getInt("amount");
            }

            qry = "Update Account set amount=? where accountNumber=?";
            thePreparedStatement = dbCon.prepareStatement(qry);
            thePreparedStatement.setInt(1, totalAmount);
            thePreparedStatement.setString(2, accountNo);
            if (thePreparedStatement.executeUpdate() > 0) {
                System.out.println("Amount " + amount + " Deposited Successfully to  A/c " + accountNo);
                System.out.println("Your Net Balance: " + totalAmount);
                System.out.println();

                //Setting acc no,action and date for transaction detail
                String Action = "Deposited " + amount;
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                String transactionDate = formatter.format(date);
                TransApp app = new TransApp();
                app.transactionImpl(accountNo, Action, transactionDate);
            }

        } catch (SQLException e) {
            System.out.println("Sql exception occured");
        }
    }
        public void withdraw(String accountNo,int amount)
        {
            try {

                thePreparedStatement = dbCon.prepareStatement("select amount from Account where accountNumber=?");
                int totalAmount = amount;

                thePreparedStatement.setString(1, accountNo);

                theResultSet = thePreparedStatement.executeQuery();
                int check = 0;
                while (theResultSet.next()) {
                    if (theResultSet.getInt("amount") < amount) {
                        System.out.println("You dont have enough balance check your balance by typing one");
                        check = 1;
                        break;
                    } else {
                        totalAmount = -totalAmount+theResultSet.getInt("amount");
                    }
                }
                if (check == 0) {
                    qry = "Update Account set amount=? where accountNumber=?";
                    thePreparedStatement = dbCon.prepareStatement(qry);
                    thePreparedStatement.setInt(1, totalAmount);
                    thePreparedStatement.setString(2, accountNo);
                    if (thePreparedStatement.executeUpdate() > 0) {
                        System.out.println("Amount " + amount + " withdrawn Successfully from  A/c " + accountNo);
                        System.out.println("Your Net Balance: " + totalAmount);
                        System.out.println();


                        //Setting acc no,action and date for transaction detail
                        String Action = "Withdrawn " + amount;
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                        String transactionDate = formatter.format(date);
                        TransApp app = new TransApp();
                        app.transactionImpl(accountNo, Action, transactionDate);
                    }

                }
            }catch(SQLException e){
                    System.out.println("Sql exception occured during withdrawal");
                }
            }




//Transfer the amount from one account to the another account


        public void transfer(String accountNo1,String accountNo2,int amount)
        {
            TransApp app=new TransApp();
            app.withdraw(accountNo1,amount);
            app.deposit(accountNo2,amount);
//Setting acc no,action and date for transaction detail
            String Action="Transfered "+Integer.toString(amount)+"to  A/c "+accountNo2;
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String transactionDate = formatter.format(date);
            app.transactionImpl(accountNo1,Action,transactionDate);

        }
//Transaction details
        public void transaction(String accountName)
        {

            try {
                qry="select * from transactions where accountNumber=?";
                thePreparedStatement = dbCon.prepareStatement(qry);
                thePreparedStatement.setString(1,accountName);
                theResultSet = thePreparedStatement.executeQuery();
                if(!theResultSet.next())
                {
                    System.out.println("NO Transaction History");
                }
                else {
                    while (theResultSet.next()) {
                        System.out.println(theResultSet.getString("accountNumber") + "  " + theResultSet.getString("ActionTaken") +
                                "  " + theResultSet.getString("Date"));
                        System.out.println();
                    }
                }


            } catch (SQLException e) {
               System.out.println("Exception occured inside transaction");
            }


        }

    public void transactionImpl(String accountName,String Action,String date)
    {
        try {
            qry="insert into transactions values(?,?,?)";
            thePreparedStatement = dbCon.prepareStatement(qry);
            thePreparedStatement.setString(1,accountName);
            thePreparedStatement.setString(2,Action);
            thePreparedStatement.setString(3,date);
            if(thePreparedStatement.executeUpdate()>0)
            {
                System.out.println("Your transaction Details updated:  ");
            }
        } catch (SQLException e) {
            System.out.println("Exception inside transactionImpl");
        }



    }










}
