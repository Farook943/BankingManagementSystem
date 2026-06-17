package banking;

//Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
private final int accountNumber;
private final String accountHolderName;
private double balance;
private final ArrayList<String> transactions;

public BankAccount(int var1, String var2, double var3) {
   this.accountNumber = var1;
   this.accountHolderName = var2;
   this.balance = var3;
   this.transactions = new ArrayList();
}

public int getAccountNumber() {
   return this.accountNumber;
}

public String getAccountHolderName() {
   return this.accountHolderName;
}

public double getBalance() {
   return this.balance;
}

public List<String> getTransactions() {
   return Collections.unmodifiableList(this.transactions);
}

public void addTransaction(String var1) {
   this.transactions.add(var1);
}

public void deposit(double var1) {
   this.balance += var1;
}

public boolean withdraw(double var1) {
   if (this.balance >= var1) {
      this.balance -= var1;
      return true;
   } else {
      return false;
   }
}

public String toString() {
   return this.accountNumber + "," + this.accountHolderName + "," + this.balance;
}
}
