package com.example.kingd.hello_world;
import java.util.ArrayList;

/**
 * Created by James on 2/4/2016.
 */
public class Unpaid extends Payments{
    private double transactionAmount;
    private boolean payed;
    private String paymentNotes, dates, categories;

    public Unpaid() {
        transactionAmount = 0;;
        payed = false;
        paymentNotes = "";
        dates = "";
        categories = "";
    }

    @Override
    public void printOneLine() {
        System.out.println("Date of Transaction: " + getPaymentDate());
        System.out.println("Category of Transaction: " + getCategories());
        System.out.println("Amount of Transaction: " + getTransactionAmt());
        System.out.println("Notes: " + getNotes());
    }

    @Override // Need to rework
    public Unpaid rePopulateFromString(String st) {
        int currentIndex = 0, counter = 0;
        int percentAt;
        while (true) {
            percentAt = st.indexOf("%", currentIndex);
            if (percentAt > -1) {
                if (counter == 0)
                    addDate(st.substring(currentIndex, percentAt));
                else if (counter == 1)
                    addCategories(st.substring(currentIndex, percentAt));
                else if (counter == 2)
                    addTransaction(Double.parseDouble(st.substring(currentIndex, percentAt)));
                else if (counter == 3)
                    addNotes(st.substring(currentIndex, percentAt));
                else {
                    if (Boolean.parseBoolean(st.substring(currentIndex, percentAt)))
                        setPayed();
                    else
                        setUnPayed();
                }

                currentIndex = percentAt + 1; // +1?
                counter++;
            }
            else {
                if (Boolean.parseBoolean(st.substring(currentIndex, st.length() - 1)))
                    setPayed();
                else
                    setUnPayed();

                break;
            }
        }
        return this;
    }
    @Override  // Need to rework
    public String toString() {
        String formattedString = "";
        //int indexCounter = 0;
        //while (indexCounter < dates.length()) {
            formattedString += dates + "%" + categories + "%"
                    + transactionAmount + "%" + paymentNotes
                    + "%" + payed;
            //++indexCounter;
        //}

        return formattedString;
    }

}
