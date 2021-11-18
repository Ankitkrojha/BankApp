package com.bank.operations;

import java.sql.SQLException;
import java.text.ParseException;

public interface CheckBalance {
    public void checkBalance(String accountNo)throws SQLException, ParseException;
}
