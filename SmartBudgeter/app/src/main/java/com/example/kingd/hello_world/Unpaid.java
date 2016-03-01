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

    public boolean getPayed() {
        return this.payed;
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
        while (counter <= 4) {
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
                else if (counter == 3) {
                    this.addNotes(st.substring(currentIndex, percentAt));
                }
                else {
                    if (Boolean.parseBoolean(st.substring(currentIndex, percentAt)))
                        this.setPayed();
                    else
                        this.setUnPayed();
                }

                currentIndex = percentAt + 1;
                counter++;
            }
            else {
                /*if (Boolean.parseBoolean(st.substring(currentIndex, st.length() - 1)))
                    setPayed();
                else
                    setUnPayed();*/

                break;
            }
        }
        return this;
    }
    @Override  // Need to rework
    public String toString() {
        String formattedString = "";
        formattedString += dates + "%" + categories + "%"
                    + transactionAmount + "%" + paymentNotes
                    + "%" + payed + "%";
        return formattedString;
    }

}
