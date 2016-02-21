package com.example.kingd.hello_world;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.kingd.hello_world.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DBFetch dbFetch = new DBFetch();

        //Button calendarButton = (Button)findViewById(R.id.calendarButton);
        //calendarButton.setOnClickListener(new View.OnClickListener(){

        //});

        //The code for calendar
        //Payments payment = new Payments();
        //initialize the date info selected on the calendar

        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        TextView calendarCategory = (TextView)findViewById(R.id.CategoryShow);
        TextView calendarAmount = (TextView)findViewById(R.id.AmountShow);
        dbFetch.readFromFile();
        dbFetch.rePopulateFromRead();
        dbFetch.sortHistoryByDate();
        dbFetch.sortUnpaidByDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String selectedDate = sdf.format(new Date(calendar.getDate()));
        //calendar.

        //dbFetch.sortByDate();
        //calendarCategory.setText(payment.getCategories());
        //calendarAmount.setText(payment.getTransactionAmt());


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
