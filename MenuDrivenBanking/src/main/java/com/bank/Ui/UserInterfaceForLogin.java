package com.bank.Ui;

import com.bank.operations.TransApp;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class UserInterfaceForLogin {
    public  void loginApplication() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        int choice;
        TransApp transApp = new TransApp();

        while (true) {
            System.out.println("Choose 1 to Check Balance," +
                    "Choose 2 to deposit Money,Choose " +
                    "3 to withdraw,Choose 4 to transfer,choose 5 to see transaction History");
            System.out.println("6 to LOGOUT");
            choice = sc.nextInt();
            if(choice==6)
            {
                System.out.println("loging out...");
                System.out.println("logged out Successfully");
                System.out.println();
                break;
            }
            else {

                switch (choice) {
                    case 1:
                        String accountNumber;
                        System.out.println("Please Provide the account Number ");
                        accountNumber = sc.next();
                        transApp.checkBalance(accountNumber);
                        break;
                    case 2:

                        System.out.println("Please Provide the account Number ");
                        System.out.println("Amount to be deposited");

                        transApp.deposit(sc.next(), sc.nextInt());
                        break;
                    case 3:
                        System.out.println("Please Provide the account Number ");
                        System.out.println("Amount to be withdrawn");
                        transApp.withdraw(sc.next(), sc.nextInt());
                        break;
                    case 4:
                        String accountNo1;
                        String accountNo2;
                        int amount;
                        System.out.println("Enter your account no.");
                        accountNo1 = sc.next();
                        System.out.println("Enter account no. to transfer funds");
                        accountNo2 = sc.next();
                        System.out.println("Enter amount to be  transferred");
                        amount = sc.nextInt();
                        if (accountNo1.equals(accountNo2)) {
                            System.out.println("You cannot transfer money to same account"
                            );
                        } else {
                            transApp.transfer(accountNo1, accountNo2, amount);
                        }
                            break;
                    case 5:
                        System.out.println("Enter your account Number");
                        transApp.transaction(sc.next());
                        break;
                        default:
                            System.out.println("Choose no. between 1 to 5 for operation and 6 to logout the application");
                            break;

                }
            }
        }
    }
}
