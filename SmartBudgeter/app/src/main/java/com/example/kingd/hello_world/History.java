package com.example.kingd.hello_world;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by James on 2/4/2016.
 */
public class History extends Payments {
    private double transactionAmount;
    //private boolean payed;
    private String categories, dates, paymentNotes;


    public History() {
        transactionAmount = 0;
        //payed = true;
        paymentNotes = "";
        categories = "";;

    }

    @Override
    public void printOneLine() {
        System.out.println("Date of Transaction: " + getPaymentDate());
        System.out.println("Category of Transaction: " + getCategories());
        System.out.println("Amount of Transaction: " + getTransactionAmt());
        System.out.println("Notes: " + getNotes());
        //System.out.println("Payed or not?" + isPayedOrNot());
    }

    @Override // Need to rework
    public History rePopulateFromString(String stored) {
        int currentIndex = 0, counter = 0;
        int percentAt;
        while (counter <= 3) {
            percentAt = stored.indexOf("%", currentIndex);
            if (percentAt > -1) {
                if (counter == 0)
                    this.addDate(stored.substring(currentIndex, percentAt));
                else if (counter == 1)
                    this.addCategories(stored.substring(currentIndex, percentAt));
                else if (counter == 2)
                    this.addTransaction(Double.parseDouble(stored.substring(currentIndex, percentAt)));
                else
                    this.addNotes(stored.substring(currentIndex, percentAt));

                currentIndex = percentAt + 1;
                counter++;
            }
            else {
                //addNotes(stored.substring(currentIndex, stored.length() - 1));
                break;
            }
        }
        return this;
    }

    @Override    // Need to rework
    public String toString() {
        String formattedString = "";
        formattedString += dates + "%" + categories + "%" + transactionAmount + "%" + paymentNotes + "%";
        return formattedString;
    }
    //TODO:: set up all parsing methods and converting to string methods. Getters and setters too.



}
