package com.example.kingd.hello_world;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link homePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link homePage#//newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class homePage extends Fragment {

    public static DBFetch dbFetch = new DBFetch();

    public homePage() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        View llayout = inflater.inflate(R.layout.fragment_home_page
                , container, false);


        //initialize the dbFetch project
        dbFetch.readFromFile();
        if(!dbFetch.isReadEmpty()) {
            System.out.println("read not empty");
            dbFetch.rePopulateFromRead();
            dbFetch.sortHistoryByDate();
            dbFetch.sortUnpaidByDate();
            dbFetch.printAccount();
        }

        //Update the balance
        TextView balanceField = (TextView)llayout.findViewById(R.id.balanceField);
        balanceField.setText(Double.toString(dbFetch.getBalance()));

        // Update Next Payment and Next Income fields
        TextView dateField = (TextView) llayout.findViewById(R.id.dateField);
        TextView categoryField = (TextView) llayout.findViewById(R.id.categoryField);
        TextView amountField = (TextView) llayout.findViewById(R.id.amountField);
        TextView dateField2 = (TextView) llayout.findViewById(R.id.dateField2);
        TextView categoryField2 = (TextView) llayout.findViewById(R.id.categoryField2);
        TextView amountField2 = (TextView) llayout.findViewById(R.id.amountField2);
        TextView notesField2 = (TextView) llayout.findViewById(R.id.notesField2);
        TextView notesField = (TextView) llayout.findViewById(R.id.notesField);
        boolean paymentSet = false, incomeSet = false;
        for(int i = 0; i < dbFetch.getFuture().size(); i++) {
            if(dbFetch.getFuture().get(i).getTransactionAmt() > 0 && incomeSet == false){
                dateField2.setText(dbFetch.getFuture().get(i).getPaymentDate()); // Replace with getters
                categoryField2.setText(dbFetch.getFuture().get(i).getCategories());
                amountField2.setText(Double.toString(Math.abs(dbFetch.getFuture().get(i).getTransactionAmt())));
                notesField2.setText(dbFetch.getFuture().get(i).getNotes());
                incomeSet = true;
            }
            else if(dbFetch.getFuture().get(i).getTransactionAmt() <= 0 && paymentSet == false){
                dateField.setText(dbFetch.getFuture().get(i).getPaymentDate()); // Replace with getters
                categoryField.setText(dbFetch.getFuture().get(i).getCategories());
                amountField.setText(Double.toString(Math.abs(dbFetch.getFuture().get(i).getTransactionAmt())));
                notesField.setText(dbFetch.getFuture().get(i).getNotes());
                paymentSet = true;
            }
        }




        // Show more payments button listener
        Button showMorePaymentsBtn = (Button) llayout.findViewById(R.id.showMorePayments);

        showMorePaymentsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), showMorePayments.class));
            }
        });

        // Show more income button listener
        Button showMoreIncomeBtn = (Button)llayout.findViewById(R.id.showMoreIncome);

        showMoreIncomeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), showMoreIncome.class);
                startActivity(intent);
            }
        });

        return llayout;
    }


        //Toast.makeText(this.getActivity().getApplicationContext(),"Thanks for using application!!", Toast.LENGTH_LONG).show();

    @Override
    public void onResume() {
        System.err.println("onResume of LoginFragment");

        dbFetch.readFromFile();
        if(!dbFetch.isReadEmpty()) {
            System.out.println("read not empty");
            dbFetch.rePopulateFromRead();
            dbFetch.sortHistoryByDate();
            dbFetch.sortUnpaidByDate();
            dbFetch.printAccount();
        }

        //Update the balance
        TextView balanceField = (TextView)getActivity().findViewById(R.id.balanceField);
        balanceField.setText(Double.toString(dbFetch.getBalance()));

        // Update Next Payment and Next Income fields
        TextView dateField = (TextView) getActivity().findViewById(R.id.dateField);
        TextView categoryField = (TextView) getActivity().findViewById(R.id.categoryField);
        TextView amountField = (TextView) getActivity().findViewById(R.id.amountField);
        TextView dateField2 = (TextView) getActivity().findViewById(R.id.dateField2);
        TextView categoryField2 = (TextView) getActivity().findViewById(R.id.categoryField2);
        TextView amountField2 = (TextView) getActivity().findViewById(R.id.amountField2);
        TextView notesField2 = (TextView) getActivity().findViewById(R.id.notesField2);
        TextView notesField = (TextView) getActivity().findViewById(R.id.notesField);
        boolean paymentSet = false, incomeSet = false;
        for(int i = 0; i < dbFetch.getFuture().size(); i++) {
            if(dbFetch.getFuture().get(i).getTransactionAmt() > 0 && incomeSet == false){
                dateField2.setText(dbFetch.getFuture().get(i).getPaymentDate()); // Replace with getters
                categoryField2.setText(dbFetch.getFuture().get(i).getCategories());
                amountField2.setText(Double.toString(Math.abs(dbFetch.getFuture().get(i).getTransactionAmt())));
                notesField2.setText(dbFetch.getFuture().get(i).getNotes());
                incomeSet = true;
            }
            else if(dbFetch.getFuture().get(i).getTransactionAmt() <= 0 && paymentSet == false){
                dateField.setText(dbFetch.getFuture().get(i).getPaymentDate()); // Replace with getters
                categoryField.setText(dbFetch.getFuture().get(i).getCategories());
                amountField.setText(Double.toString(Math.abs(dbFetch.getFuture().get(i).getTransactionAmt())));
                notesField.setText(dbFetch.getFuture().get(i).getNotes());
                paymentSet = true;
            }
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        System.err.println("OnPause of loginFragment");
        try {
            dbFetch.writeStoreUser();
            System.out.println("writing successful");
        }
        catch(Exception ec){
            ec.printStackTrace();
        }
        super.onPause();
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
