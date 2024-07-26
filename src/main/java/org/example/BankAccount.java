package org.example;

import java.util.Scanner;

public class BankAccount {
    private String accountHolder;
    private double balance;
    private int accountNumber;
    private static int nextAccountNumber = 1000; // Static variable to keep track of account numbers

    // Constructor with all parameters
    public BankAccount(String accountHolder, double balance, int accountNumber) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    // Constructor with no parameters
    public BankAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account holder's name: ");
        this.accountHolder = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        this.balance = scanner.nextDouble();
        this.accountNumber = nextAccountNumber++;
    }

    // Getter for accountHolder
    public String getAccountHolder() {
        return accountHolder;
    }

    // Getter for accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited to the account. New balance: " + balance);
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println(amount + " withdrawn from the account. New balance: " + balance);
        }
    }

    // Method to print account details
    public void printAccountDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    // Method to transfer money between accounts
    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds for transfer.");
        } else {
            balance -= amount;
            targetAccount.deposit(amount);
            System.out.println(amount + " transferred from account " + this.accountNumber +
                    " to account " + targetAccount.getAccountNumber() + ". New balance: " + balance);
        }
    }
}
