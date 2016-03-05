package com.example.kingd.hello_world;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditEvent_Dates} interface
 * to handle interaction events.
 * Use the {@link EditEvent_Dates#} factory method to
 * create an instance of this fragment.
 */
public class EditEvent_Dates extends Fragment {

    private static ArrayList<Payments> cateList = new ArrayList<Payments>();

    public EditEvent_Dates() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_edit_event_dates, container,
                false);

        System.out.println("I'm here");

        cateList = DBFetch.getEventsByCategory(EditEvent_PickCategory.getCategory());


        return llayout;
    }

}
