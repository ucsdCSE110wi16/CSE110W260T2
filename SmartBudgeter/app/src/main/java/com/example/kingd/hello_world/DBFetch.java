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

    private static final String FILENAME = "/config.txt";
    private static String name, stored, read;
    private static double balance;
    private static ArrayList<Payments> future;
    private static ArrayList<Payments> past;
    private static String curDate;
    public static boolean change;
    public static String[] spinnerList = {"food", "clothes", "rent", "salary", "miscellaneous"};;

    //private GoogleApiClient client;

    public DBFetch() {
        balance = 0.00;
        name = "";
        past = new ArrayList<Payments>();
        future = new ArrayList<Payments>();
        read = "";//"James|800.00|2016/02/26%fruits%-20.00%AA%^2016/02/25%clothes%-60%BB%^|2016/03/27%rent%-500.0%CC%false%^2016/03/29%salary%8000%DD%false%^|";
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        curDate = format.format(d);
        change = false;
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

    public static void setChangeTrue(){
        change = true;
    }

    public static void setChangeFalse(){
        change = false;
    }

    public static boolean getChange(){
        return change;
    }


    public static void addToHistory(String date, String cate, double amt, String notes ) {
        Payments hist = new Payments();
        hist.setDate(date);
        hist.setCategories(cate);
        hist.setTransaction(amt);
        hist.setNotes(notes);
        past.add(hist);
    }

    public static void addToHistory(Payments hist) {
        past.add(hist);
    }

    public static void addToUnpaid(String date, String cate, double amt, String notes) {
        Payments toPay = new Payments();
        toPay.setDate(date);
        toPay.setCategories(cate);
        toPay.setTransaction(amt);
        toPay.setNotes(notes);
        future.add(toPay);
    }

    public static void addToUnpaid(Payments toPay) {
        future.add(toPay);
    }

    public void writeStoreUser(Context context) throws IOException {
        if (name.equals("") && balance == 0 && past.size() == 0 && future.size() == 0) {
            System.out.println("Attempting to write empty object to config file - Terminating method.");
            return;
        }
        File file = context.getFilesDir();
        FileOutputStream outputStream; //= context.openFileOutput(FILENAME, context.MODE_WORLD_READABLE);
        try {
            outputStream = new FileOutputStream(new File(file.getPath() + FILENAME));
            outputStream.write(StoreToString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to write");
        }
    }

    //the stored format: name|balance|past1^...past2^|future1^...future2^|
    public static String StoreToString() {
        if (name == "" && balance == 0.0) {
            stored = "";
        }else {
            stored = name + "|" + balance + "|";
            for (int i = 0; i < past.size(); i++) {
                stored += past.get(i).toString() + "^";
            }
            stored += "|";
            for (int j = 0; j < future.size(); j++) {
                stored += future.get(j).toString() + "^";
            }
            stored += "|";
            System.out.println(past.size() + " " + future.size());
        }
        return stored;
    }

    public void readFromFile(Context context) {
        String ret = "", myPath = "";
        File varTmpDir = context.getFilesDir();
        if (varTmpDir.exists()) {
            try {
                myPath = varTmpDir.getPath() + FILENAME;
                FileInputStream inputStream = new FileInputStream(new File(myPath));

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

    }
    public static ArrayList<Payments> getFuture(){
        return future;
    }

    public static ArrayList<Payments> getPast(){
        return past;
    }

    public static boolean isReadEmpty(){
        return read.equals("");
    }

    public static void rePopulateFromRead(){
        int currentIndex = 0, counter = 0, index = 0, carrotAt;
        int pipeAt = 0, percentAt = 0;
        Payments payments;
        while (counter < 4) {
            pipeAt = read.indexOf("|", currentIndex);
            if (counter == 0)
                setName(read.substring(currentIndex, pipeAt));
            else if (counter == 1)
                setBalance(Double.parseDouble(read.substring(currentIndex, pipeAt)));
            else if (counter == 2) {
                while(true) {
                    payments = new Payments();
                    carrotAt = read.indexOf("^", currentIndex);
                    if(carrotAt == -1 || carrotAt > pipeAt){
                        break;
                    }
                    payments = payments.rePopulateFromString(read.substring(currentIndex, carrotAt));
                    past.add(payments);
                    currentIndex = carrotAt + 1;
                    System.out.println("I'm reading the history...");
                }
            }
            else {
                while(true){
                    payments = new Payments();
                    carrotAt = read.indexOf("^", currentIndex);
                    if(carrotAt == -1 || carrotAt > pipeAt){
                        break;
                    }
                    if (carrotAt > percentAt) {
                        payments = payments.rePopulateFromString(read.substring(currentIndex, carrotAt));
                        future.add(payments);
                        currentIndex = carrotAt + 1;
                    }
                    System.out.println("I'm reading the unpaid...");
                }
                System.out.println("File reading finished!");
            }
            currentIndex = pipeAt + 1;
            counter++;
        }
    }

    public static void printAccount() {
        System.out.println("Account Owner: " + name);
        System.out.println("Balance: " + balance);
        for (int i = 0; i < past.size(); i++)
            past.get(i).printOneLine();
        for (int j = 0; j < future.size(); j++)
            future.get(j).printOneLine();
    }

    //check if the future has been the past, if so, move them to the past and modify the balance
    public static void checkAndMoveFuture() {
        int index = 0;
        while (index < getFuture().size()) {
            if(getFuture().get(index).getPaymentDate().compareTo(curDate) <= 0) {
                Payments temp = getFuture().get(index);
                past.add(temp);
                future.remove(index);
                addBalance(temp.getTransactionAmt());
            }
            else if(getFuture().get(index).getPaymentDate().compareTo(curDate) > 0){
                break;
            }
            index++;
        }
    }

    public static Payments getCurrentEvent(){
        if(getPast().size() != 0 && getCurrentDate().compareTo(getPast().get(0).getPaymentDate()) == 0){
            return getPast().get(0);
        }
        else {
            System.err.println("Current date event doesn't exist!");
            return null;
        }
    }

    public static String changeToCorrectDateForm(int year, int month, int day){
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

    public static ArrayList<Payments> getIncome(ArrayList<Payments> list){
        ArrayList<Payments> income = new ArrayList<Payments>();
        Payments temp;
        for(int i = 0 ; i < list.size() ; i++){
            temp = list.get(i);
            if(temp.getTransactionAmt() > 0){
                income.add(temp);
            }
        }
        return income;
    }

    public static ArrayList<Payments> getPayment(ArrayList<Payments> list){
        ArrayList<Payments> payment = new ArrayList<Payments>();
        Payments temp;
        for(int i = 0 ; i < list.size() ; i++){
            temp = list.get(i);
            if(temp.getTransactionAmt() <= 0){
                payment.add(temp);
            }
        }
        return payment;
    }

    public static ArrayList<Payments> getEventsByCategory(String category){
        ArrayList<Payments> cateList = new ArrayList<Payments>();
        if(getFuture().size() != 0){
            for(int i = 0; i < getFuture().size(); i++){
                if(getFuture().get(i).getCategories().equals(category)){
                    cateList.add(getFuture().get(i));
                }
            }
        }
        if(getPast().size() != 0){
            for(int i = 0; i < getPast().size(); i++){
                if (getPast().get(i).getCategories().equals(category)){
                    cateList.add(getPast().get(i));
                }
            }
        }
        return cateList;
    }

    public static Payments fromStringToPayments(String str){
        ArrayList<String> content = new ArrayList<String>();
        int indexOf = 0, currentIndex = 0;
        for(int i = 0; i < 4; i++){
            indexOf = str.indexOf("%",currentIndex);
            content.add(str.substring(currentIndex, indexOf));
            currentIndex = indexOf + 1;
        }
        Payments payments = new Payments(content.get(0),Double.parseDouble(content.get(2)), content.get(1), content.get(3));
        return payments;
    }

    public static ArrayList<Integer> getDateItems(String str){
        ArrayList<Integer> content = new ArrayList<Integer>();
        content.add(Integer.parseInt(str.substring(0,4)));
        content.add(Integer.parseInt(str.substring(5,7)));
        content.add(Integer.parseInt(str.substring(8,10)));
        return content;
    }

    public static void deleteEvent(Payments payments){
        for(int i = 0; i < past.size(); i++){
            if(past.get(i).isEqual(payments)){
                past.remove(i);
                break;
            }
        }
        for(int i = 0; i < future.size(); i++){
            if(future.get(i).isEqual(payments)){
                future.remove(i);
                break;
            }
        }
    }

}



