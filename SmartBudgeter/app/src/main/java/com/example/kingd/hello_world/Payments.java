package com.example.kingd.hello_world;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by James on 2/4/2016.
 */
public class Payments {
    //private static ArrayList<Date> dates;
    private ArrayList<Double> transactionAmount;
    private ArrayList<Boolean> payed; //addOrSubtract;
    private ArrayList<String> paymentNotes, dates, categories; // TODO: implement cata

    public void addDate(String d) {
        dates.add(d);
    }

    public void addCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // HH:mm:ss");
        Date d = new Date();
        dates.add(dateFormat.format(d));
    }

    public void addTransaction(double amt) {
        transactionAmount.add(amt);
    }

    public void addUnPayed() {
        payed.add(false);
    }

    public void addPayed() {
        payed.add(true);
    }
    public void addCategories(String cata) {
        categories.add(cata);
    }

    public void addNotes(String note) {
        paymentNotes.add(note);
    }

    public double getTransactionAmt(int index) {
        return this.transactionAmount.get(index);
    }

    public ArrayList<Double> getAllTransAmounts() {
        return this.transactionAmount;
    }

    public boolean isPayedOrNot(int index) {
        return this.payed.get(index);
    }

    public ArrayList<Boolean> getAllPayedOrNot() {
        return this.payed;
    }

    public String getCategories(int index) {
        return this.categories.get(index);
    }

    public ArrayList<String> getAllCategories() {
        return this.categories;
    }

    public String getNotes(int index) {
        return this.paymentNotes.get(index);
    }

    public ArrayList<String> getAllNotes() {
        return this.paymentNotes;
    }

    public String getPaymentDate(int index) {
        return this.dates.get(index);
    }

    public ArrayList<String> getAllDates() {
        return this.dates;
    }

    public int getNumOfTransactions() {
        return dates.size();
    }

    public void printOneLine(int index) {}

    public void rePopulateFromString(String st) {}

    //public String toString() {}

}
