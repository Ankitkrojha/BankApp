package com.bank.entity;

import java.util.Objects;

public class Account {
    private int cust_id;
    private String accountNumber;
    private String customerName;
    private int amount;

    public Account() {
    }

    public Account(int cust_id, String account_no, String cust_name, int amount) {
        this.cust_id = cust_id;
        this.accountNumber = account_no;
        this.customerName = cust_name;
        this.amount = amount;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return cust_id == account.cust_id && amount == account.amount && Objects.equals(accountNumber, account.accountNumber) && Objects.equals(customerName, account.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cust_id, accountNumber, customerName, amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "cust_id=" + cust_id +
                ", accountNumber='" + accountNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                '}';
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
