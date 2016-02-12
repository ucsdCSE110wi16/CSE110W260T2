import java.util.ArrayList;

/**
 * Created by James on 2/4/2016.
 */
public class Unpaid extends Payments{
    private ArrayList<Double> transactionAmount;
    private ArrayList<Boolean> payed;
    private ArrayList<String> paymentNotes, dates, catagories;
    //private double balance;

    public Unpaid() {
        transactionAmount = new ArrayList<Double>();
        payed = new ArrayList<Boolean>();
        paymentNotes = new ArrayList<String>();
        dates = new ArrayList<String>();
        //balance = 0;
    }

    @Override
    public void printOneLine(int index) {
        System.out.println("Date of Transaction: " + getPaymentDate(index));
        System.out.println("Category of Transaction: " + getCatagories(index));
        System.out.println("Amount of Transaction: " + getTransactionAmt(index));
        System.out.println("Notes: " + getNotes(index));
    }

    @Override
    public void rePopulateFromString(String st) {
        int currentIndex = 0, counter = 0;
        int percentAt;
        while (true) {
            percentAt = st.indexOf("%", currentIndex);
            if (percentAt > -1) {
                if (counter == 0)
                    addDate(st.substring(currentIndex, percentAt));
                else if (counter == 1)
                    addCatagories(st.substring(currentIndex, percentAt));
                else if (counter == 2)
                    addTransaction(Double.parseDouble(st.substring(currentIndex, percentAt)));
                else if (counter == 3)
                    addNotes(st.substring(currentIndex, percentAt));
                else {
                    if (Boolean.parseBoolean(st.substring(currentIndex, percentAt)))
                        addPayed();
                    else
                        addUnPayed();
                }

                currentIndex = percentAt + 1; // +1?
                counter++;
            }
            else {
                if (Boolean.parseBoolean(st.substring(currentIndex, st.length() - 1)))
                    addPayed();
                else
                    addUnPayed();

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
                    + transactionAmount.get(indexCounter) + "%" + paymentNotes.get(indexCounter)
                    + "%" + payed.get(indexCounter);
            ++indexCounter;
        }

        return formattedString;
    }

}
