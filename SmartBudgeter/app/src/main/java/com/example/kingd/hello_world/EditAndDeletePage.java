package com.example.kingd.hello_world;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditAndDeletePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditAndDeletePage#} factory method to
 * create an instance of this fragment.
 */
public class EditAndDeletePage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Payments payment;
    private static String modified_date;
    private static double modified_amt;
    private static String modified_cate;
    private static String modified_note;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditAndDeletePage() {
        // Required empty public constructor
        payment = EditEvent_Dates.getPayment();
        modified_date = payment.getPaymentDate();
        modified_amt = payment.getTransactionAmt();
        modified_cate = payment.getCategories();
        modified_note = payment.getNotes();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_edit_and_delete_page, container,
                false);


        //The date modification
        DatePicker datePicker = (DatePicker)llayout.findViewById(R.id.editDatePicker);
        ArrayList<Integer> dateItem = DBFetch.getDateItems(payment.getPaymentDate());
        int year = dateItem.get(0);
        int month = dateItem.get(1)-1;
        int day = dateItem.get(2);
        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                modified_date = DBFetch.changeToCorrectDateForm(year,monthOfYear,dayOfMonth);
            }
        });

        //the category modification
        final Spinner categoriesSpinner = (Spinner)llayout.findViewById(R.id.EditSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,DBFetch.spinnerList);
        categoriesSpinner.setAdapter(arrayAdapter);
        if (!payment.getCategories().equals(null)) {
            int spinnerPosition = arrayAdapter.getPosition(payment.getCategories());
            categoriesSpinner.setSelection(spinnerPosition);
        }
        categoriesSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modified_cate = categoriesSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //the amount modification
        final EditText amountAdd = (EditText)llayout.findViewById(R.id.EditAmountText);
        amountAdd.setText(Double.toString(payment.getTransactionAmt()));

        //the note modification
        final EditText noteAdd = (EditText)llayout.findViewById(R.id.EditNotesText);
        noteAdd.setText(payment.getNotes());

        //click on button events
        Button edit_button = (Button)llayout.findViewById(R.id.EditButton);
        Button delete_button = (Button)llayout.findViewById(R.id.DeleteButton);
        edit_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                modified_amt = Double.parseDouble(amountAdd.getText().toString());
                modified_note = noteAdd.getText().toString();
                DBFetch.deleteEvent(payment);
                if(payment.getPaymentDate().compareTo(DBFetch.getCurrentDate()) <= 0){
                    DBFetch.addBalance(-payment.getTransactionAmt());
                }
                payment.setDate(modified_date);
                payment.setCategories(modified_cate);
                payment.setTransaction(modified_amt);
                payment.setNotes(modified_note);
                if(modified_date.compareTo(DBFetch.getCurrentDate()) <= 0){
                    DBFetch.addToHistory(payment);
                    DBFetch.addBalance(modified_amt);
                }
                else {
                    DBFetch.addToUnpaid(payment);
                }
                DBFetch.setChangeTrue();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        delete_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBFetch.deleteEvent(payment);
                if(payment.getPaymentDate().compareTo(DBFetch.getCurrentDate()) <= 0){
                    DBFetch.addBalance(-payment.getTransactionAmt());
                }
                DBFetch.setChangeTrue();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });



        return llayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
