package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Bank!");
            System.out.println("1. Create new account");
            System.out.println("2. Select existing account");
            System.out.println("3. Exit");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    BankAccount newAccount = new BankAccount();
                    accounts.add(newAccount);
                    mainMenu(newAccount);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    BankAccount account = findAccountByNumber(accountNumber);
                    if (account != null) {
                        mainMenu(account);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void mainMenu(BankAccount account) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome, " + account.getAccountHolder() + "!");
        while (!exit) {
            System.out.println("1. Check account balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdrawal");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.printAccountDetails();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter target account number: ");
                    int targetAccountNumber = scanner.nextInt();
                    BankAccount targetAccount = findAccountByNumber(targetAccountNumber);
                    if (targetAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(targetAccount, transferAmount);
                    } else {
                        System.out.println("Target account not found.");
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static BankAccount findAccountByNumber(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
