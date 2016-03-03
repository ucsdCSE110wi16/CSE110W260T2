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

    //public static DBFetch dbFetch;

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
                                                 boolean setIf = false;
                                                 String selectedDate = "";
                                                 if(month+1 < 10) {
                                                     selectedDate += Integer.toString(year) + "/0" + Integer.toString(month + 1) + "/" + Integer.toString(dayOfMonth);
                                                 }
                                                 else {
                                                     selectedDate += Integer.toString(year) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(dayOfMonth);
                                                 }
                                                 System.out.println("The selected Date is: " + selectedDate);
                                                 TextView calendarCategory = (TextView) getActivity().findViewById(R.id.CategoryShow);
                                                 TextView calendarAmount = (TextView) getActivity().findViewById(R.id.AmountShow);
                                                 while (index < homePage.dbFetch.getFuture().size()) {
                                                     //System.out.println(dbFetch.getFuture().get(index).getPaymentDate());
                                                     if(homePage.dbFetch.getFuture().get(index).getPaymentDate().equals(selectedDate)) {
                                                         calendarCategory.setText(homePage.dbFetch.getFuture().get(index).getCategories());
                                                         calendarAmount.setText(Double.toString(homePage.dbFetch.getFuture().get(index).getTransactionAmt()));
                                                         setIf = true;
                                                     }
                                                     else if(homePage.dbFetch.getFuture().get(index).getPaymentDate().compareTo(selectedDate) < 0){
                                                         break;
                                                     }
                                                     index++;
                                                 }
                                                 if(setIf == false) {
                                                     calendarCategory.setText("N/A");
                                                     calendarAmount.setText("N/A");
                                                 }

                                             }
                                         }

        );

        return llayout;
    }
}
