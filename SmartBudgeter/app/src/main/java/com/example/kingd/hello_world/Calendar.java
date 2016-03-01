package com.example.kingd.hello_world;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar extends AppCompatActivity {

    //public static DBFetch dbFetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Calendar Part
        /*CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);

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

        );*/
    }


}