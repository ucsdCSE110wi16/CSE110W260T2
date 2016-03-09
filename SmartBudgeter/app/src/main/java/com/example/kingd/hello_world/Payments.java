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
    //private boolean payed;
    private String paymentNotes, dates, categories; // TODO: implement cata

    /*public void addCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // HH:mm:ss");
        Date d = new Date();
        addDate(dateFormat.format(d));
    }*/
    public Payments(){

    }

    public Payments(String date, double amt, String cate, String note){
        dates = date;
        transactionAmount = amt;
        categories = cate;
        paymentNotes = note;
    }

    public void setDate(String d){dates = d;}
    public void setTransaction(double amt) {
        transactionAmount = amt;
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
    public String getCategories() {
        return this.categories;
    }
    public String getNotes() {
        return this.paymentNotes;
    }
    public String getPaymentDate() {
        return this.dates;
    }

    public void printOneLine() {
        System.out.println("Date of Transaction: " + getPaymentDate());
        System.out.println("Category of Transaction: " + getCategories());
        System.out.println("Amount of Transaction: " + getTransactionAmt());
        System.out.println("Notes: " + getNotes());
    }

    public Payments rePopulateFromString(String st){
        int currentIndex = 0, counter = 0;
        int percentAt;
        while (counter <= 3) {
            percentAt = st.indexOf("%", currentIndex);
            if (percentAt > -1) {
                if (counter == 0) {
                    this.setDate(st.substring(currentIndex, percentAt));
                }
                else if (counter == 1) {
                    this.setCategories(st.substring(currentIndex, percentAt));
                }
                else if (counter == 2) {
                    this.setTransaction(Double.parseDouble(st.substring(currentIndex, percentAt)));
                }
                else {
                    this.setNotes(st.substring(currentIndex, percentAt));
                }
                currentIndex = percentAt + 1;
                counter++;
            }
            else {
                break;
            }
        }
        return this;
    }

    public String toString() {
        String formattedString = "";
        formattedString += dates + "%" + categories + "%" + transactionAmount + "%" + paymentNotes + "%";
        return formattedString;
    }

    public boolean isEqual(Payments payments){
        if(categories.equals(payments.getCategories()) && dates.equals(payments.getPaymentDate()) && transactionAmount==payments.getTransactionAmt() && paymentNotes.equals(payments.getNotes())){
            return true;
        }
        else {
            return false;
        }
    }

}
