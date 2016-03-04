package com.example.kingd.hello_world;
import java.util.ArrayList;

/**
 * Created by James on 2/4/2016.
 */
public class Unpaid extends Payments{
    private double transactionAmount;
    //private boolean payed;
    private String paymentNotes, dates, categories;

    public Unpaid() {
        transactionAmount = 0;;
        //payed = false;
        paymentNotes = "";
        dates = "";
        categories = "";
    }
/*
    @Override // Need to rework
    public Unpaid rePopulateFromString(String st) {
        int currentIndex = 0, counter = 0;
        int percentAt;
        while (counter <= 3) {
            percentAt = st.indexOf("%", currentIndex);
            if (percentAt > -1) {
                if (counter == 0) {
                    this.addDate(st.substring(currentIndex, percentAt));
                }
                else if (counter == 1) {
                    this.addCategories(st.substring(currentIndex, percentAt));
                }
                else if (counter == 2) {
                    this.addTransaction(Double.parseDouble(st.substring(currentIndex, percentAt)));
                }
                else {
                    this.addNotes(st.substring(currentIndex, percentAt));
                }
                currentIndex = percentAt + 1;
                counter++;
            }
            else {
                break;
            }
        }
        return this;
    }*/
    @Override
    public String toString() {
        String formattedString = "";
        formattedString += dates + "%" + categories + "%"
                    + transactionAmount + "%" + paymentNotes
                    + "%" ;
        return formattedString;
    }

}
