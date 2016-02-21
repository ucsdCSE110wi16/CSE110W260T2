package com.example.kingd.hello_world;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DBFetch dbFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dbFetch = new DBFetch();
        dbFetch.readFromFile();
        if(!dbFetch.isReadEmpty()){
            dbFetch.rePopulateFromRead();
            dbFetch.sortHistoryByDate();
            dbFetch.sortUnpaidByDate();
        }
        else {
            dbFetch.setBalance(0.00);
        }


        // Update Next Payment fields
        TextView dateField = (TextView) findViewById(R.id.amountField);
        TextView categoryField = (TextView) findViewById(R.id.categoryField);
        TextView amountField = (TextView) findViewById(R.id.categoryField);
        TextView notesField = (TextView) findViewById(R.id.notesField);
        dateField.setText(""); // Replace with getters
        categoryField.setText("");
        amountField.setText("");
        notesField.setText("");


        // Update Next Income fields
        TextView dateField2 = (TextView) findViewById(R.id.amountField2);
        TextView categoryField2 = (TextView) findViewById(R.id.categoryField2);
        TextView amountField2 = (TextView) findViewById(R.id.categoryField2);
        TextView notesField2 = (TextView) findViewById(R.id.notesField2);
        dateField2.setText(""); // Replace with getters
        categoryField2.setText("");
        amountField2.setText("");
        notesField2.setText("");

        //Calendar Part
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                             @Override
                                             public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                                 int index = 0;
                                                 Date tempD = new Date(year, month, dayOfMonth);
                                                 String selectedDate = Integer.toString(year) + '/' + Integer.toString(month) + '/' + Integer.toString(dayOfMonth);
                                                 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                                 Date date = new Date();
                                                 if (date.compareTo(tempD) == -1) {
                                                     TextView calendarCategory = (TextView) findViewById(R.id.CategoryShow);
                                                     TextView calendarAmount = (TextView) findViewById(R.id.AmountShow);
                                                     while (index < dbFetch.getFuture().size() - 1) {
                                                         if (dbFetch.getFuture().get(index).getPaymentDate().equals(selectedDate)) {
                                                             //if(calendarCategory.getText() != null){
                                                             calendarCategory.setText(dbFetch.getFuture().get(index).getCategories());
                                                             calendarAmount.setText(Double.toString(dbFetch.getFuture().get(index).getTransactionAmt()));
                                                             //}

                                                         } else {
                                                             calendarCategory.setText("N/A");
                                                             calendarAmount.setText("N/A");
                                                         }
                                                     }
                                                 }
                                             }
                                         }

        );




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
