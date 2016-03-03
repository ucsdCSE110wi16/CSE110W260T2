package com.example.kingd.hello_world;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditEvent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditEvent extends Fragment {
    public EditEvent() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_edit_event, container,
                false);
        return llayout;
    }
}