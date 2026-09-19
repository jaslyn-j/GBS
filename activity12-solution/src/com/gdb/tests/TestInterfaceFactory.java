package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestInterfaceFactory {
    public static void main(String[] args) {

        // NOTE: If you completed Activity 11 successfully, paste your working IAccount.java and AccountFactory.java into src/com/gdb/domain (replacing the provided versions).

        // TODO: Step 1 - Instantiate Savings, Current, and FixedDeposit accounts exclusively through AccountFactory.createAccount()
        IAccount savings = AccountFactory.createAccount("SAVINGS", "AC-101", "Aishwarya", 25, 5000.0, "ACTIVE", "1234");
        IAccount current = AccountFactory.createAccount("CURRENT", "AC-202", "Benny", 30, 2000.0, "ACTIVE", "5678");
        IAccount fixedDeposit = AccountFactory.createAccount("FD", "AC-303", "John", 45, 10000.0, "ACTIVE", "9999");
        // TODO: Step 2 - Perform deposits and withdrawals through the IAccount interface reference
        try {
            savings.displayAccountInfo();

            System.out.println("\nDepositing Rs 1500.00 to Savings:");
            savings.deposit(1500.00);
            System.out.println("New Savings Balance is Rs " + savings.getBalance());

            System.out.println("\nWithdrawing Rs 2000.00 from Savings:");
            savings.withdraw(2000.00, "1234");
            System.out.println("New Savings Balance is Rs " + savings.getBalance());

        } catch (Exception e) {
            System.err.println("Unexpected failure during standard interface operations: " + e.getMessage());
        }

        // TODO: Step 3 - Verify Savings minimum balance rule enforcement through the interface
        try {
            System.out.println("\nTesting Savings Account Rule:");
            System.out.println("Current balance: Rs " + savings.getBalance());
            savings.withdraw(4000.00, "1234");
            System.err.println("FAIL");
        } catch (AccountException e) {
            System.out.println("SUCCESS, Exception thrown: " + e.getMessage());
        }

        try {
            System.out.println("\nTesting Current Account Rule:");
            System.out.println("Current balance: Rs " + current.getBalance());
            current.withdraw(10000.00, "5678");
            System.out.println("SUCCESS, Overdraft permitted. New balance: Rs " + current.getBalance());
        } catch (AccountException e) {
            System.err.println("FAIL: Overdraft within standard limits was incorrectly blocked: " + e.getMessage());
        }

        try {
            System.out.println("\nTesting Fixed Deposit Account Rule:");
            fixedDeposit.withdraw(1000.00, "9999");
            System.err.println("FAIL: Fixed deposit permitted an active premature debit transaction");
        } catch (AccountException e) {
            System.out.println("SUCCESS: Rule caught correctly. Exception thrown: " + e.getMessage());
        }

        try {
            System.out.println("\nTesting Unkown Accounts :");
            AccountFactory.createAccount("NRI", "CR-999", "Eve", 21, 50.0, "ACTIVE", "0000");
            System.err.println("FAIL: Factory quietly yielded or missed handling an unsupported type");
        } catch (IllegalArgumentException e) {
            System.out.println("SUCCESS: Rule caught correctly. Exception thrown: " + e.getMessage());
        }

        // TODO: Step 4 - Verify Current overdraft limit enforcement through the interface

        // TODO: Step 5 - Verify FixedDeposit premature withdrawal rejection through the interface

        // TODO: Step 6 - Verify requesting an invalid account type from AccountFactory throws IllegalArgumentException

    }
}
