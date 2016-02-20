package com.example.kingd.hello_world;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by James on 2/4/2016.
 */
public class Payments {
    private double transactionAmount;
    private boolean payed;
    private String paymentNotes, dates, categories; // TODO: implement cata

    public void addCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // HH:mm:ss");
        Date d = new Date();
        addDate(dateFormat.format(d));
    }

    public void addDate(String d) {
        dates = d;
    }
    //King Added the three methods based on Jame's cods
    public void addCategories(String Cate) {
        categories = Cate;
    }
    public void addTransaction(double amt) {
        transactionAmount = amt;
    }
    public void addNotes(String notes) {
        paymentNotes = notes;
    }

    public void setTransaction(double amt) {
        transactionAmount = amt;
    }

    public void setUnPayed() {
        payed = false;
    }

    public void setPayed() {
        payed = true;
    }
    public void setCategories(String cate) {
        categories = cate;
    }

    public void setNotes(String note) {
        paymentNotes = note;
    }

    public double getTransactionAmt() {
        return this.transactionAmount;
    }

    //public ArrayList<Double> getAllTransAmounts() {
    //    return this.transactionAmount;
    //}

    public boolean isPayedOrNot() {
        return this.payed;
    }

    //public ArrayList<Boolean> getAllPayedOrNot() {
    //   return this.payed;
    //}

    public String getCategories() {
        return this.categories;
    }

    //public ArrayList<String> getAllCatagories() {
    //    return this.catagories;
    //}

    public String getNotes() {
        return this.paymentNotes;
    }

    //public ArrayList<String> getAllNotes() {
    //    return this.paymentNotes;
    //}

    public String getPaymentDate() {
        return this.dates;
    }

    //public ArrayList<String> getAllDates() {
    //    return this.dates;
    //}

    //public int getNumOfTransactions() {
    //    return dates.size();
    //} PUT THIS IN DBFETCH AND USE FOR BOTH

    public void printOneLine() {}

    public Payments rePopulateFromString(String st){return this;}

    //public String toString() {}

}