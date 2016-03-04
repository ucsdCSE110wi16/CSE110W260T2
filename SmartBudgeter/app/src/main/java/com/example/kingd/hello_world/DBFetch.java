package com.example.kingd.hello_world;
//import android.app.Activity;
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.*;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

/**
 * Created by James on 2/3/2016.
 */

/*
 DBFetch class contains all getter and setter methods used on the DB
 */
public class DBFetch extends Activity {

    private static final String FILENAME = "config.txt";
    private static String name, stored, read;
    private static double balance;
    private static ArrayList<Unpaid> future;
    private static ArrayList<History> past;
    private static String curDate;

    //private GoogleApiClient client;

    public DBFetch() {
        balance = 0.00;
        name = "Fuheng Deng";
        past = new ArrayList<History>();
        future = new ArrayList<Unpaid>();
        read = "";//"James|800.00|2016/02/26%fruits%-20.00%AA%^2016/02/25%clothes%-60%BB%^|2016/03/27%rent%-500.0%CC%false%^2016/03/29%salary%8000%DD%false%^|";
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        curDate = format.format(d);
    }

    public static void setBalance(double bal) {
        balance = bal;
    }

    public static void setCurrentDate() {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        curDate = format.format(d);
    }

    public static String getCurrentDate(){
        return curDate;
    }

    public static double getBalance() {
        return balance;
    }

    public static void addBalance(double add) {
        balance += add;
    }

    /*public static void subBalance(double sub) {
        balance -= sub;
    }*/

    public static void setName(String n) {
        name = n;
    }

    public static String getName() {
        return name;
    }

    public void addToHistory(String date, String cate, double amt, String notes ) {
        Payments hist = new History();
        hist.addDate(date);
        hist.addCategories(cate);
        hist.addTransaction(amt);
        hist.addNotes(notes);
        past.add((History) hist);
    }

    public void addToUnpaid(String date, String cate, double amt, String notes) {
        Payments toPay = new Unpaid();
        toPay.addDate(date);
        toPay.addCategories(cate);
        toPay.addTransaction(amt);
        toPay.addNotes(notes);
        //toPay.setUnPayed();
        future.add((Unpaid) toPay);
    }

    public void writeStoreUser() throws IOException {
        if (name == "" && balance == 0 && past.size() == 0 && future.size() == 0) {
            System.out.println("Attempting to write empty object to config file - Terminating method.");
            return;
        }

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(StoreToString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //the stored format: name|balance|past1^...past2^|future1^...future2^|
    public String StoreToString() {
        stored = name + "|" + balance + "|";
        for(int i = 0; i < past.size(); i++){
            stored += past.get(i).toString() + "^";
        }
        stored += "|";
        for(int j = 0; j < future.size(); j++){
            stored += future.get(j).toString() + "^";
        }
        stored += "|";
        System.out.println(past.size() + " " + future.size());
        return stored;
    }

    public void readFromFile() {

        String ret = "";
        File varTmpDir = new File(FILENAME);
        if (varTmpDir.exists()) {
            try {
                InputStream inputStream = openFileInput(FILENAME);

                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((receiveString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(receiveString);
                    }

                    inputStream.close();
                    ret = stringBuilder.toString();
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.toString());
            } catch (IOException e) {
                System.err.println("Can not read file: " + e.toString());
            }
            read = ret;
        }

        else {
            try {
                PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
                writer.close();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.toString());
            } catch (IOException e) {
                System.err.println("Can not read file: " + e.toString());
            }
        }

    }

    public ArrayList<Unpaid> getFuture(){
        return future;
    }

    public ArrayList<History> getPast(){
        return past;
    }

    public boolean isReadEmpty(){
        return read.equals("");
    }

    public void rePopulateFromRead(){
        future = new ArrayList<Unpaid>();
        past = new ArrayList<History>();
        int currentIndex = 0, counter = 0, index = 0, carrotAt;
        int pipeAt = 0, percentAt = 0;
        Payments payments;
        int a = 0;
        while (pipeAt != -1) {
            pipeAt = read.indexOf("|", currentIndex);
            if (pipeAt > -1) {
                if (counter == 0)
                    setName(read.substring(currentIndex, pipeAt));
                else if (counter == 1)
                    setBalance(Double.parseDouble(read.substring(currentIndex, pipeAt)));
                else if (counter == 2) {
                    do {
                        payments = new History();
                        carrotAt = read.indexOf("^", currentIndex);
                        percentAt = read.indexOf("%", currentIndex);
                        if (carrotAt > percentAt) {
                            payments = payments.rePopulateFromString(read.substring(currentIndex, carrotAt));
                            past.add((History)payments);
                            currentIndex = carrotAt + 1;
                        }

                    } while(carrotAt < pipeAt - 1);
                }
                else {
                    do {
                        payments = new Unpaid();
                        carrotAt = read.indexOf("^", currentIndex);
                        percentAt = read.indexOf("%", currentIndex);
                        if (carrotAt > percentAt) {
                            payments = payments.rePopulateFromString(read.substring(currentIndex, carrotAt));
                            future.add((Unpaid)payments);
                            currentIndex = carrotAt + 1;
                        }
                    } while(carrotAt < pipeAt - 1);
                }
                currentIndex = pipeAt + 1;
                counter++;
            }
            else {
                System.out.println("File reading finished!");
                break;
            }
        }
    }

    public void printAccount() {
        System.out.println("Account Owner: " + name);
        System.out.println("Balance: " + balance);
        for (int i = 0; i < past.size(); i++)
            past.get(i).printOneLine();
        for (int j = 0; j < future.size(); j++)
            future.get(j).printOneLine();
    }

    //from the nearest unpaid to the furthest-away unpaid
    public void sortHistoryByDate() {
        for(int i = 0; i < past.size() ; i++) {
            for (int j = i + 1; j < past.size(); j++) {
                String dateI = past.get(i).getPaymentDate();
                String dateJ = past.get(j).getPaymentDate();
                if (dateI.substring(0, 4).compareTo(dateJ.substring(0, 4)) < 0) {
                    Collections.swap(past, i, j);
                }
                else if(dateI.substring(0, 4).compareTo(dateJ.substring(0, 4)) == 0){
                    if(dateI.substring(5, 7).compareTo(dateJ.substring(5, 7)) < 0) {
                        Collections.swap(past, i, j);
                    }
                    else if(dateI.substring(5, 7).compareTo(dateJ.substring(5, 7)) == 0){
                        if(dateI.substring(8, 10).compareTo(dateJ.substring(8, 10)) < 0) {
                            Collections.swap(past, i, j);
                        }
                    }
                }
            }
        }
    }

    //from the nearest unpaid to the furthest-away unpaid
    public void sortUnpaidByDate() {
        for(int i = 0; i < future.size() ; i++) {
            for (int j = i + 1; j < future.size(); j++) {
                String dateI = future.get(i).getPaymentDate();
                String dateJ = future.get(j).getPaymentDate();
                if (dateI.substring(0, 4).compareTo(dateJ.substring(0, 4)) > 0) {
                    Collections.swap(future, i, j);
                }
                else if(dateI.substring(0, 4).compareTo(dateJ.substring(0, 4)) == 0){
                    if(dateI.substring(5, 7).compareTo(dateJ.substring(5, 7)) > 0) {
                        Collections.swap(future, i, j);
                    }
                    else if(dateI.substring(5, 7).compareTo(dateJ.substring(5, 7)) == 0){
                        if(dateI.substring(8, 10).compareTo(dateJ.substring(8, 10)) > 0) {
                            Collections.swap(future, i, j);
                        }
                    }
                }
            }
        }
    }

    //check if the future has been the past, if so, move them to the past and modify the balance
    public void checkAndMoveFuture() {
        int index = 0;
        while (index < getFuture().size()) {
            if(getFuture().get(index).getPaymentDate().compareTo(curDate) <= 0) {
                Payments temp = getFuture().get(index);
                past.add((History)temp);
                future.remove(index);
                addBalance(temp.getTransactionAmt());
            }
            else if(homePage.dbFetch.getFuture().get(index).getPaymentDate().compareTo(curDate) > 0){
                break;
            }
            index++;
        }
    }

    public History getCurrentEvent(){
        int i = 0;
        if(homePage.dbFetch.getPast().size() != 0 && homePage.dbFetch.getCurrentDate().compareTo(homePage.dbFetch.getPast().get(0).getPaymentDate()) == 0){
            return homePage.dbFetch.getPast().get(0);
        }
        else {
            return null;
        }
    }

    public String changeToCorrectDateForm(int year, int month, int day){
        String date = "";
        String yyyy = Integer.toString(year);
        String mm = Integer.toString(month + 1);
        String dd = Integer.toString(day);
        if (month + 1 < 10) {
            mm = "0" + mm;
        }
        if(day < 10) {
            dd = "0" + dd;
        }
        date += yyyy + "/" + mm + "/" + dd;
        return date;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    /*
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
    } */
}

