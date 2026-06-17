package banking;

//Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BankOperations {
private static final String ACCOUNTS_FILE = "accounts.txt";
private static final String TRANSACTIONS_FILE = "transactions.txt";
private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
private final ArrayList<BankAccount> accounts = new ArrayList();

public BankOperations() {
}

public void loadData() {
   this.accounts.clear();
   this.loadAccounts();
   this.loadTransactions();
}

public void saveData() {
   this.saveAccounts();
   this.saveTransactions();
}

public void createAccount(int var1, String var2, double var3) {
   if (this.findAccount(var1) != null) {
      System.out.println("Account number already exists.");
   } else {
      BankAccount var5 = new BankAccount(var1, var2, var3);
      var5.addTransaction(this.buildTransaction("ACCOUNT CREATED", var3, var3));
      this.accounts.add(var5);
      this.saveData();
      System.out.println("Account Created Successfully");
   }
}

public void deposit(int var1, double var2) {
   BankAccount var4 = this.findAccount(var1);
   if (var4 == null) {
      System.out.println("Account Not Found");
   } else {
      var4.deposit(var2);
      var4.addTransaction(this.buildTransaction("DEPOSIT", var2, var4.getBalance()));
      this.saveData();
      System.out.println("Amount Deposited Successfully");
   }
}

public void withdraw(int var1, double var2) {
   BankAccount var4 = this.findAccount(var1);
   if (var4 == null) {
      System.out.println("Account Not Found");
   } else {
      if (var4.withdraw(var2)) {
         var4.addTransaction(this.buildTransaction("WITHDRAW", var2, var4.getBalance()));
         this.saveData();
         System.out.println("Amount Withdrawn Successfully");
      } else {
         var4.addTransaction(this.buildTransaction("FAILED WITHDRAW", var2, var4.getBalance()));
         this.saveData();
         System.out.println("Insufficient Balance");
      }

   }
}

public void checkBalance(int var1) {
   BankAccount var2 = this.findAccount(var1);
   if (var2 == null) {
      System.out.println("Account Not Found");
   } else {
      System.out.printf("Current Balance: %.2f%n", var2.getBalance());
   }
}

public void displayAccounts() {
   if (this.accounts.isEmpty()) {
      System.out.println("No accounts available.");
   } else {
      for(BankAccount var2 : this.accounts) {
         System.out.println("Account Number : " + var2.getAccountNumber());
         System.out.println("Name           : " + var2.getAccountHolderName());
         System.out.printf("Balance        : %.2f%n", var2.getBalance());
         System.out.println("------------------------------");
      }

   }
}

public void displayTransactionHistory(int var1) {
   BankAccount var2 = this.findAccount(var1);
   if (var2 == null) {
      System.out.println("Account Not Found");
   } else if (var2.getTransactions().isEmpty()) {
      System.out.println("No transactions found for this account.");
   } else {
      System.out.println("Transaction History for Account " + var1);
      System.out.println("----------------------------------------");

      for(String var4 : var2.getTransactions()) {
         System.out.println(var4);
      }

   }
}

private BankAccount findAccount(int var1) {
   for(BankAccount var3 : this.accounts) {
      if (var3.getAccountNumber() == var1) {
         return var3;
      }
   }

   return null;
}

private void loadAccounts() {
   try {
      BufferedReader var1 = new BufferedReader(new FileReader("accounts.txt"));

      String var2;
      try {
         while((var2 = var1.readLine()) != null) {
            if (!var2.trim().isEmpty()) {
               String[] var3 = var2.split(",");
               if (var3.length == 3) {
                  try {
                     int var4 = Integer.parseInt(var3[0].trim());
                     String var5 = var3[1].trim();
                     double var6 = Double.parseDouble(var3[2].trim());
                     this.accounts.add(new BankAccount(var4, var5, var6));
                  } catch (NumberFormatException var9) {
                     System.out.println("Skipping invalid account record: " + var2);
                  }
               }
            }
         }
      } catch (Throwable var10) {
         try {
            var1.close();
         } catch (Throwable var8) {
            var10.addSuppressed(var8);
         }

         throw var10;
      }

      var1.close();
   } catch (IOException var11) {
   }

}

private void loadTransactions() {
   try {
      BufferedReader var1 = new BufferedReader(new FileReader("transactions.txt"));

      String var2;
      try {
         while((var2 = var1.readLine()) != null) {
            if (!var2.trim().isEmpty()) {
               String[] var3 = var2.split(",", 2);
               if (var3.length == 2) {
                  try {
                     int var4 = Integer.parseInt(var3[0].trim());
                     BankAccount var5 = this.findAccount(var4);
                     if (var5 != null) {
                        var5.addTransaction(var3[1].trim());
                     }
                  } catch (NumberFormatException var7) {
                     System.out.println("Skipping invalid transaction record: " + var2);
                  }
               }
            }
         }
      } catch (Throwable var8) {
         try {
            var1.close();
         } catch (Throwable var6) {
            var8.addSuppressed(var6);
         }

         throw var8;
      }

      var1.close();
   } catch (IOException var9) {
   }

}

private void saveAccounts() {
   try {
      BufferedWriter var1 = new BufferedWriter(new FileWriter("accounts.txt"));

      try {
         for(BankAccount var3 : this.accounts) {
            var1.write(var3.toString());
            var1.newLine();
         }
      } catch (Throwable var5) {
         try {
            var1.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      var1.close();
   } catch (IOException var6) {
      System.out.println("Unable to save account data: " + var6.getMessage());
   }

}

private void saveTransactions() {
   try {
      BufferedWriter var1 = new BufferedWriter(new FileWriter("transactions.txt"));

      try {
         for(BankAccount var3 : this.accounts) {
            for(String var5 : var3.getTransactions()) {
               int var10001 = var3.getAccountNumber();
               var1.write(var10001 + "," + var5);
               var1.newLine();
            }
         }
      } catch (Throwable var7) {
         try {
            var1.close();
         } catch (Throwable var6) {
            var7.addSuppressed(var6);
         }

         throw var7;
      }

      var1.close();
   } catch (IOException var8) {
      System.out.println("Unable to save transaction data: " + var8.getMessage());
   }

}

private String buildTransaction(String var1, double var2, double var4) {
   String var6 = LocalDateTime.now().format(DATE_FORMAT);
   return String.format("%s | %s | Amount: %.2f | Balance: %.2f", var6, var1, var2, var4);
}
}
