package com.example.kingd.hello_world;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by James on 2/4/2016.
 */
public class History extends Payments {
    private double transactionAmount;
    //private boolean payed;
    private String categories, paymentNotes, dates;


    public History() {
        transactionAmount = 0;
        //payed = true;
        paymentNotes = "";
        categories = "";
        dates = "";
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
                    addDate(stored.substring(currentIndex, percentAt));
                else if (counter == 1)
                    addCategories(stored.substring(currentIndex, percentAt));
                else if (counter == 2)
                    addTransaction(Double.parseDouble(stored.substring(currentIndex, percentAt)));
                else
                    addNotes(stored.substring(currentIndex, percentAt));

                currentIndex = percentAt + 1; // +1?
                counter++;
            }
            else {
                addNotes(stored.substring(currentIndex, stored.length() - 1));
                break;
            }
        }
        return this;
    }

    @Override    // Need to rework
    public String toString() {
        String formattedString = "";
        //int indexCounter = 0;
        //while (indexCounter < dates.length()) {
            formattedString += dates + "%" + categories + "%" + transactionAmount + "%" + paymentNotes;
            //、++indexCounter;
        //}

        return formattedString;
    }
    //TODO:: set up all parsing methods and converting to string methods. Getters and setters too.



}
