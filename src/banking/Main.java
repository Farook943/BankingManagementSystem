package banking;

//Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
public Main() {
}

public static void main(String[] var0) {
   Scanner var1 = new Scanner(System.in);
   BankOperations var2 = new BankOperations();
   var2.loadData();

   while(true) {
      printMenu();
      int var3 = readInt(var1, "Enter Choice: ");
      System.out.println();
      switch (var3) {
         case 1:
            createAccount(var1, var2);
            break;
         case 2:
            depositMoney(var1, var2);
            break;
         case 3:
            withdrawMoney(var1, var2);
            break;
         case 4:
            checkBalance(var1, var2);
            break;
         case 5:
            var2.displayAccounts();
            break;
         case 6:
            viewTransactionHistory(var1, var2);
            break;
         case 7:
            var2.saveData();
            System.out.println("Data Saved Successfully");
            break;
         case 8:
            var2.saveData();
            System.out.println("Thank you for using Banking Management System.");
            var1.close();
            return;
         default:
            System.out.println("Invalid choice. Please try again.");
      }

      System.out.println();
   }
}

private static void printMenu() {
   System.out.println("===== BANK MANAGEMENT SYSTEM =====");
   System.out.println("1. Create Account");
   System.out.println("2. Deposit Money");
   System.out.println("3. Withdraw Money");
   System.out.println("4. Check Balance");
   System.out.println("5. View All Accounts");
   System.out.println("6. Transaction History");
   System.out.println("7. Save Data");
   System.out.println("8. Exit");
   System.out.println("==================================");
}

private static void createAccount(Scanner var0, BankOperations var1) {
   int var2 = readInt(var0, "Enter Account Number: ");
   String var3 = readText(var0, "Enter Name: ");
   double var4 = readAmount(var0, "Enter Initial Balance: ");
   var1.createAccount(var2, var3, var4);
}

private static void depositMoney(Scanner var0, BankOperations var1) {
   int var2 = readInt(var0, "Enter Account Number: ");
   double var3 = readAmount(var0, "Enter Amount to Deposit: ");
   var1.deposit(var2, var3);
}

private static void withdrawMoney(Scanner var0, BankOperations var1) {
   int var2 = readInt(var0, "Enter Account Number: ");
   double var3 = readAmount(var0, "Enter Amount to Withdraw: ");
   var1.withdraw(var2, var3);
}

private static void checkBalance(Scanner var0, BankOperations var1) {
   int var2 = readInt(var0, "Enter Account Number: ");
   var1.checkBalance(var2);
}

private static void viewTransactionHistory(Scanner var0, BankOperations var1) {
   int var2 = readInt(var0, "Enter Account Number: ");
   var1.displayTransactionHistory(var2);
}

private static int readInt(Scanner var0, String var1) {
   while(true) {
      System.out.print(var1);

      try {
         int var2 = var0.nextInt();
         var0.nextLine();
         return var2;
      } catch (InputMismatchException var3) {
         System.out.println("Please enter a valid number.");
         var0.nextLine();
      }
   }
}

private static double readAmount(Scanner var0, String var1) {
   while(true) {
      System.out.print(var1);

      try {
         double var2 = var0.nextDouble();
         var0.nextLine();
         if (!(var2 < (double)0.0F)) {
            return var2;
         }

         System.out.println("Amount cannot be negative.");
      } catch (InputMismatchException var4) {
         System.out.println("Please enter a valid amount.");
         var0.nextLine();
      }
   }
}

private static String readText(Scanner var0, String var1) {
   while(true) {
      System.out.print(var1);
      String var2 = var0.nextLine().trim();
      if (!var2.isEmpty()) {
         return var2;
      }

      System.out.println("This field cannot be empty.");
   }
}
}
