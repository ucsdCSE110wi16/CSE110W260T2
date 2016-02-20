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

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private int yy = 0;
    private int mm = 0;
    private int dd = 0;

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

        //The code for calendar
        Payments payment = new Payments();
        //initialize the date info selected on the calendar

        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        TextView calendarCategory = (TextView)findViewById(R.id.CategoryShow);
        TextView calendarAmount = (TextView)findViewById(R.id.AmountShow);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
                                             @Override
                                             public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                                 yy = year;
                                                 mm = month;
                                                 dd = dayOfMonth;
                                             }
                                         }
        );
        dbFetch.readFromFile();
        dbFetch.rePopulateFromRead();

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
