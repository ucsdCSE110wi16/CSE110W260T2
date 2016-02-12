import java.util.ArrayList;

/**
 * Created by James on 2/4/2016.
 */
public class History extends Payments {
    private ArrayList<Double> transactionAmount;
    //private ArrayList<Boolean> payed;
    private ArrayList<String> paymentNotes, dates, catagories;
    //private double balance;

    public History() {
        transactionAmount = new ArrayList<Double>();
        //payed = new ArrayList<Boolean>();
        paymentNotes = new ArrayList<String>();
        dates = new ArrayList<String>();
        catagories = new ArrayList<String>();
        //balance = DBFetch.getBalance();
    }
    @Override
    public void printOneLine(int index) {
        System.out.println("Date of Transaction: " + getPaymentDate(index));
        System.out.println("Category of Transaction: " + getCatagories(index));
        System.out.println("Amount of Transaction: " + getTransactionAmt(index));
        System.out.println("Notes: " + getNotes(index));
        System.out.println("Payed or not?" + isPayedOrNot(index));
    }

    @Override
    public void rePopulateFromString(String stored) {
        int currentIndex = 0, counter = 0;
        int percentAt;
        while (true) {
            percentAt = stored.indexOf("%", currentIndex);
            if (percentAt > -1) {
                if (counter == 0)
                    addDate(stored.substring(currentIndex, percentAt));
                else if (counter == 1)
                    addCatagories(stored.substring(currentIndex, percentAt));
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
    }
    @Override
    public String toString() {
        String formattedString = "";
        int indexCounter = 0;
        while (indexCounter < dates.size()) {
            formattedString += dates.get(indexCounter) + "%" + catagories.get(indexCounter) + "%"
                    + transactionAmount.get(indexCounter) + "%" + paymentNotes.get(indexCounter);
            ++indexCounter;
        }

        return formattedString;
    }
    //TODO:: set up all parsing methods and converting to string methods. Getters and setters too.

}
