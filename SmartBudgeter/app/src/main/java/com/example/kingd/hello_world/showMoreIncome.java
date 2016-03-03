package com.example.kingd.hello_world;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

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

        for (int index = 0; index < numIncomesToShow; index++){
            List<String> income = new ArrayList<String>();

            listDataHeader.add("Next Income " + (index + 1));   // Replace with getters
            income.add("Date:" + (index + 1));
            income.add("Category:");
            income.add("Amount:");
            income.add("Notes:");
            listDataChild.put(listDataHeader.get(index), income);
        }
    }

}
