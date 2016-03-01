package com.example.kingd.hello_world;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.content_calendar, container,
                false);

        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}
