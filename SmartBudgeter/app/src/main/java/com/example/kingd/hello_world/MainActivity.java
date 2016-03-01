package com.example.kingd.hello_world;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static DBFetch dbFetch = new DBFetch();

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        if (savedInstanceState == null) {
            homePage hPage = new homePage();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, hPage)
                    .addToBackStack(null)
                    .commit();
        }
        else {

        }
        */

        //initialize the dbFetch project
        //dbFetch.readFromFile();
        dbFetch.rePopulateFromRead();
        dbFetch.sortHistoryByDate();
        dbFetch.sortUnpaidByDate();
        dbFetch.printAccount();

        // Code for Navigation Bar
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {

            }
        };


        // Update Next Payment and Next Income fields
        TextView dateField = (TextView) findViewById(R.id.dateField);
        TextView categoryField = (TextView) findViewById(R.id.categoryField);
        TextView amountField = (TextView) findViewById(R.id.amountField);
        TextView notesField = (TextView) findViewById(R.id.notesField);
        TextView dateField2 = (TextView) findViewById(R.id.dateField2);
        TextView categoryField2 = (TextView) findViewById(R.id.categoryField2);
        TextView amountField2 = (TextView) findViewById(R.id.amountField2);
        TextView notesField2 = (TextView) findViewById(R.id.notesField2);
        boolean paymentSet = false, incomeSet = false;
        for(int i = 0; i < dbFetch.getFuture().size(); i++) {
            if(dbFetch.getFuture().get(i).getTransactionAmt() > 0 && incomeSet == false){
                dateField2.setText(dbFetch.getFuture().get(i).getPaymentDate()); // Replace with getters
                categoryField2.setText(dbFetch.getFuture().get(i).getCategories());
                amountField2.setText(Double.toString(Math.abs(dbFetch.getFuture().get(i).getTransactionAmt())));
                notesField2.setText(dbFetch.getFuture().get(i).getNotes());
                incomeSet = true;
            }
            else if(dbFetch.getFuture().get(i).getTransactionAmt() <= 0 && paymentSet == false){
                dateField.setText(dbFetch.getFuture().get(i).getPaymentDate()); // Replace with getters
                categoryField.setText(dbFetch.getFuture().get(i).getCategories());
                amountField.setText(Double.toString(Math.abs(dbFetch.getFuture().get(i).getTransactionAmt())));
                notesField.setText(dbFetch.getFuture().get(i).getNotes());
                paymentSet = true;
            }
        }


        // Show more payments button listener
        Button showMorePaymentsBtn = (Button)findViewById(R.id.showMorePayments);

        showMorePaymentsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowMorePayments.class));
            }
        });

        // Show more income button listener
        Button showMoreIncomeBtn = (Button)findViewById(R.id.showMoreIncome);

        showMoreIncomeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowMoreIncome.class));
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void addDrawerItems() {
        String[] osArray = { "View Calendar", "Add Payment/Income", "Modify Payment/Income" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Calendar.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, AddNewEvent.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ModifyExistingEvent.class));
                        break;
                }


            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Smart Budgeter");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
