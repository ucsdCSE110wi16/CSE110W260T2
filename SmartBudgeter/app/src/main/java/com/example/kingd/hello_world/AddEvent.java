package com.example.kingd.hello_world;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEvent} interface
 * to handle interaction events.
 * Use the {@link AddEvent#} factory method to
 * create an instance of this fragment.
 */
public class AddEvent extends Fragment {

    private String date;
    private String categories;
    private double amount;
    private String notes;

    public AddEvent() {
        date = DBFetch.getCurrentDate();
        categories = "N/A";
        amount = 0.0;
        notes = "N/A";
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_add_event, container,
                false);

        final FragmentManager fragmentManager = getFragmentManager();

        //findViewById
        DatePicker datePicker = (DatePicker)llayout.findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final Spinner categoriesSpinner = (Spinner)llayout.findViewById(R.id.spinner);
        final EditText amountAdd = (EditText)llayout.findViewById(R.id.AmountToAddText);
        final EditText notedAdd = (EditText)llayout.findViewById(R.id.AddNotesText);
        Button addButton = (Button)llayout.findViewById(R.id.AddButton);

        //initialize the spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,DBFetch.spinnerList);
        categoriesSpinner.setAdapter(arrayAdapter);

        //set onClickListener method
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = DBFetch.changeToCorrectDateForm(year,monthOfYear,dayOfMonth);
            }
        });
        categoriesSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categories = categoriesSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categories = categoriesSpinner.getItemAtPosition(0).toString();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                if (amountAdd.getText().toString().equals("")) {
                    amount = 0.0;
                } else {
                    amount = Double.parseDouble(amountAdd.getText().toString());
                }
                notes = notedAdd.getText().toString();
                String curDate = DBFetch.getCurrentDate();
                if (curDate.compareTo(date) >= 0) {
                    DBFetch.addToHistory(date, categories, amount, notes);
                    System.out.println("Added to the history");
                    DBFetch.addBalance(amount);
                } else {
                    DBFetch.addToUnpaid(date, categories, amount, notes);
                    System.out.println("Added to the unpaid");
                }
                DBFetch.setChangeTrue();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return llayout;
    }
}