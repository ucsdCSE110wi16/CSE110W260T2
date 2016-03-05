package com.example.kingd.hello_world;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class EditEvent_PickCategory extends Fragment {

    private static String category;

    public EditEvent_PickCategory() {
        // Required empty public constructor
    }

    public static String getCategory(){
        return category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_edit_event_pick_category, container,
                false);

        final Spinner categoriesSpinner = (Spinner)llayout.findViewById(R.id.spinner2);

        //initialize the spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,DBFetch.spinnerList);
        categoriesSpinner.setAdapter(arrayAdapter);

        //set onclicklistener
        categoriesSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = categoriesSpinner.getItemAtPosition(position).toString();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = null;
                Class eventDates = EditEvent_Dates.class;
                try {
                    fragment = (Fragment) eventDates.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = categoriesSpinner.getItemAtPosition(0).toString();
            }
        });

        return llayout;
    }
}