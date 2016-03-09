package com.example.kingd.hello_world;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class showMorePayments extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    // Determine how many payments to show
    private int numPaymentsToShow = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more_payments);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // Prepare list data
        preparePaymentListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // Set list adapter
        expListView.setAdapter(listAdapter);

    }

    /*
    * Prepare the list data
    */
    private void preparePaymentListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<>();
        int numberOfPaymentEvents = MainActivity.payment.size();

        for (int index = 0; index < numPaymentsToShow; index++){
            List<String> payment = new ArrayList<String>();

            if(index < numberOfPaymentEvents){
                listDataHeader.add("Next Payment " + (index + 1));   // Replace with getters
                payment.add("Date: " + MainActivity.payment.get(index).getPaymentDate());
                payment.add("Category: " + MainActivity.payment.get(index).getCategories());
                payment.add("Amount: " + MainActivity.payment.get(index).getTransactionAmt());
                payment.add("Notes: " + MainActivity.payment.get(index).getNotes());
                listDataChild.put(listDataHeader.get(index), payment);
            }
            else {
                listDataHeader.add("Next Payment " + (index + 1));   // Replace with getters
                payment.add("Date: " + "N/A");
                payment.add("Category: " + "N/A");
                payment.add("Amount: " + "N/A");
                payment.add("Notes: " + "N/A");
                listDataChild.put(listDataHeader.get(index), payment);
            }
        }
    }
}
