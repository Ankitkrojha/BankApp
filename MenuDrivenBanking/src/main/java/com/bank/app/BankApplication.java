package com.bank.app;

import com.bank.login.SignApplication;

import java.util.Scanner;

public class BankApplication {

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        while(true) {
            System.out.println("JHARKHAND NATIONAL BANK");
            System.out.println();
            System.out.println("1)sign up");
            System.out.println("2)sign in");
            System.out.println("3)EXIT ");
            int choice;
            choice = sc.nextInt();

            if(choice==3)
            {
                break;
            }
            else {
                switch (choice) {
                    case 1:
                        SignApplication signApplication = new SignApplication();
                        signApplication.userSign();
                        break;
                    case 2:
                        SignApplication signApplicat = new SignApplication();
                        signApplicat.userLogin();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Wrong Choice please select either 1 or 2");
                        break;

                }
            }
        }
    }

}
