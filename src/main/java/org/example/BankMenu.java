package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BankMenu {
    private ArrayList<BankAccount> accounts;

    public BankMenu() {
        this.accounts = new ArrayList<>();
    }

    public void mainMenu(BankAccount currentAccount) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Hello " + currentAccount.getAccountHolder() + "!");
        System.out.println("Welcome to the Main Menu, What would you like to do today?");
        do {
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: " + currentAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    currentAccount.withdraw(withdrawalAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter the account number to transfer to: ");
                    int targetAccountNumber = scanner.nextInt();
                    BankAccount targetAccount = findAccountByNumber(targetAccountNumber);
                    if (targetAccount != null) {
                        System.out.print("Enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        if (currentAccount.getBalance() >= transferAmount) {
                            currentAccount.withdraw(transferAmount);
                            targetAccount.deposit(transferAmount);
                            System.out.println("Transfer successful. New balance: " + currentAccount.getBalance());
                        } else {
                            System.out.println("Insufficient funds for transfer.");
                        }
                    } else {
                        System.out.println("Target account not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting the main menu. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount findAccountByNumber(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BankMenu bankMenu = new BankMenu();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello world! Welcome to the Bank of Matt!");

        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == -1) {
                System.out.println("Thank you for visiting the Bank of Matt. Goodbye!");
                break;
            } else if (choice == 1) {
                System.out.println("Please enter your account number: ");
                int accountNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                BankAccount existingAccount = bankMenu.findAccountByNumber(accountNumber);
                if (existingAccount != null) {
                    bankMenu.mainMenu(existingAccount);
                } else {
                    System.out.println("Account not found. Please try again.");
                }
            } else if (choice == 2) {
                System.out.println("Let's make a new account!");
                BankAccount newAccount = new BankAccount();
                bankMenu.addAccount(newAccount);
                System.out.println("Account created successfully! Your account details:");
                newAccount.printAccountDetails();
                bankMenu.mainMenu(newAccount);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
