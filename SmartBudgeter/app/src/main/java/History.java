import java.util.ArrayList;

/**
 * Created by James on 2/4/2016.
 */
public class History extends Payments {
    private ArrayList<Double> transactionAmount;
    private ArrayList<Boolean> payed;
    private ArrayList<String> paymentNotes, dates;
    private double balance;

    public History() {
        transactionAmount = new ArrayList<Double>();
        payed = new ArrayList<Boolean>();
        paymentNotes = new ArrayList<String>();
        dates = new ArrayList<String>();
        balance = 0;
    }

    public void rePopulateFromString(String stored) {

    }
    //TODO:: set up all parsing methods and converting to string methods. Getters and setters too.

}
