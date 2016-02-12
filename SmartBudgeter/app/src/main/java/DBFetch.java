import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.*;
import java.util.Properties;

/**
 * Created by James on 2/3/2016.
 */

/*
 DBFetch class contains all getter and setter methods used on the DB
 */
public class DBFetch extends Activity {

    private static final String FILENAME = "config.txt";
    private static String name, stored;
    private static double balance;
    public static Payments past, future;

    private GoogleApiClient client;

    public DBFetch() {
        balance = 0.0;
        name = "";
        past = new History();
        future = new Unpaid();
    }

    public static void setBalance(double bal) {
        balance = bal;
    }

    public static double getBalance() {
        return balance;
    }

    public static void addFunds(double add) {
        balance += add;
    }

    public static void subFunds(double sub) {
        balance -= sub;
    }

    public static void setName(String n) {
        name = n;
    }

    public static String getName() {
        return name;
    }

    public void writeStoreUser() throws IOException {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(StoreToString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String StoreToString() {
        stored = name + "|" + balance + "|" + future.toString() + "|" + past.toString();
        return stored;
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput(FILENAME);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.toString());
        } catch (IOException e) {
            System.err.println("Can not read file: " + e.toString());
        }

        return ret;
    }

    private void rePopulateFromRead(){
        int currentIndex = 0, counter = 0;
        int pipeAt;
        while (true) {
            pipeAt = stored.indexOf("|", currentIndex);
            if (pipeAt > -1) {
                if (counter == 0)
                    setBalance(Double.parseDouble(stored.substring(currentIndex, pipeAt)));
                else if (counter == 1)
                    setName(stored.substring(currentIndex, pipeAt));
                else if (counter == 2)
                    past.rePopulateFromString(stored.substring(currentIndex, pipeAt));
                else
                    future.rePopulateFromString(stored.substring(currentIndex, pipeAt));

                currentIndex = pipeAt + 1; // +1?
                counter++;
            }
        }
    }
    public void printAccount() {
        System.out.println("Account Owner: " + name);
        System.out.println("Balance: " + balance);
        for (int i = 0; i < past.getNumOfTransactions(); i++)
            past.printOneLine(i);
        for (int i = 0; i < future.getNumOfTransactions(); i++)
            future.printOneLine(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DBFetch Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app:///http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DBFetch Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app:///http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

