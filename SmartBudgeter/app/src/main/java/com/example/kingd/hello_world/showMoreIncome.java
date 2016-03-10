package com.example.kingd.hello_world;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class showMoreIncome extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    // Determine how many incomes to show
    private int numIncomesToShow = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_more_income);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // Prepare list data
        prepareIncomeListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // Set list adapter
        expListView.setAdapter(listAdapter);

    }

    /*
     * Prepare the income list data
     */
    private void prepareIncomeListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<>();
        int numberOfIncomeEvents = MainActivity.income.size();

        for (int index = 0; index < numIncomesToShow; index++){
            List<String> income = new ArrayList<String>();
            if(index < numberOfIncomeEvents){
                listDataHeader.add("Next Income " + (index + 1));   // Replace with getters
                income.add("Date: " + MainActivity.income.get(index).getPaymentDate());
                income.add("Category: " + MainActivity.income.get(index).getCategories());
                income.add("Amount: " + MainActivity.income.get(index).getTransactionAmt());
                income.add("Notes: " + MainActivity.income.get(index).getNotes());
                listDataChild.put(listDataHeader.get(index), income);
            }
            else {
                listDataHeader.add("Next Income " + (index + 1));   // Replace with getters
                income.add("Date: " + "N/A");
                income.add("Category: " + "N/A");
                income.add("Amount: " + "N/A");
                income.add("Notes: " + "N/A");
                listDataChild.put(listDataHeader.get(index), income);
            }

        }
    }


}
