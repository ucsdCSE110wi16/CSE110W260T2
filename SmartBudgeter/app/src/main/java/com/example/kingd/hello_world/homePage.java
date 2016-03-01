package com.example.kingd.hello_world;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link homePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link homePage#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class homePage extends Fragment {

    public static DBFetch dbFetch = new DBFetch();

/*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homePage.
     */
    // TODO: Rename and change types and number of parameters
    /*
    public static homePage newInstance(String param1, String param2) {
        homePage fragment = new homePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public homePage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.content_main
                , container, false);

        // Update Next Payment fields
        TextView dateField = (TextView) llayout.findViewById(R.id.amountField);
        TextView categoryField = (TextView) llayout.findViewById(R.id.categoryField);
        TextView amountField = (TextView) llayout.findViewById(R.id.categoryField);
        TextView notesField = (TextView) llayout.findViewById(R.id.notesField);
        dateField.setText(""); // Replace with getters
        categoryField.setText("");
        amountField.setText("");
        notesField.setText("");


        // Update Next Income fields
        TextView dateField2 = (TextView) llayout.findViewById(R.id.amountField2);
        TextView categoryField2 = (TextView) llayout.findViewById(R.id.categoryField2);
        TextView amountField2 = (TextView) llayout.findViewById(R.id.categoryField2);
        TextView notesField2 = (TextView) llayout.findViewById(R.id.notesField2);
        dateField2.setText(""); // Replace with getters
        categoryField2.setText("");
        amountField2.setText("");
        notesField2.setText("");

        // Show more payments button listener
        Button showMorePaymentsBtn = (Button)llayout.findViewById(R.id.showMorePayments);

        showMorePaymentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getActivity(), showMorePayments.class));
            }
        });

        // Show more income button listener
        Button showMoreIncomeBtn = (Button)llayout.findViewById(R.id.showMoreIncome);

        showMoreIncomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), showMoreIncome.class));
            }
        });

        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
