package com.example.kingd.hello_world;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    public static DBFetch dbFetch = new DBFetch();

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_calendar, container,
                false);

        CalendarView calendar = (CalendarView) llayout.findViewById(R.id.calendarView);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                             @Override
                                             public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                                 int index = 0;
                                                 Date tempD = new Date(year, month, dayOfMonth);
                                                 String selectedDate = Integer.toString(year) + '/' + Integer.toString(month) + '/' + Integer.toString(dayOfMonth);
                                                 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                                 Date date = new Date();
                                                 if (date.compareTo(tempD) == -1) {
                                                     while (index < dbFetch.getFuture().size() - 1) {
                                                         TextView calendarCategory = (TextView) getActivity().findViewById(R.id.CategoryShow);
                                                         TextView calendarAmount = (TextView) getActivity().findViewById(R.id.AmountShow);
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

        return llayout;
    }
}
