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

        for (int index = 0; index < numPaymentsToShow; index++){
            List<String> payment = new ArrayList<String>();

            listDataHeader.add("Next Payment " + (index + 1));   // Replace with getters
            payment.add("Date:" + (index + 1));
            payment.add("Category:");
            payment.add("Amount:");
            payment.add("Notes:");
            listDataChild.put(listDataHeader.get(index), payment);
        }
    }
}
