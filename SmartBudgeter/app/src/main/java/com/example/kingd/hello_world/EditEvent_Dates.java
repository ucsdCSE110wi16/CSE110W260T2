package com.example.kingd.hello_world;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    private ListView lv;

    private static ArrayList<Payments> cateList = new ArrayList<Payments>();

    public EditEvent_Dates() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_edit_event_dates, container,
                false);

        lv = (ListView) llayout.findViewById(R.id.chooseEventList);

        cateList = DBFetch.getEventsByCategory(EditEvent_PickCategory.getCategory());

        Activity activity = (Activity) container.getContext();
        ArrayAdapter <Payments> arrayAdapter = new ArrayAdapter <Payments> (activity, android.R.layout.simple_list_item_1,
                cateList);
        lv.setAdapter(arrayAdapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView) view).getText().toString();

                Toast.makeText(getActivity().getBaseContext(), item, Toast.LENGTH_LONG).show();

            }
        });

        return llayout;
    }

}
