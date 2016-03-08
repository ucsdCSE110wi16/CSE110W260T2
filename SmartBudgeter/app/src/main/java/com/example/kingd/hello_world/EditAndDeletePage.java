package com.example.kingd.hello_world;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditAndDeletePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditAndDeletePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditAndDeletePage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Payments payment;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditAndDeletePage() {
        // Required empty public constructor
        payment = EditEvent_Dates.getPayment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout llayout = (LinearLayout) inflater.inflate(R.layout.fragment_edit_and_delete_page, container,
                false);
        DatePicker datePicker = (DatePicker)llayout.findViewById(R.id.editDatePicker);
        final Spinner categoriesSpinner = (Spinner)llayout.findViewById(R.id.spinner);
        final EditText amountAdd = (EditText)llayout.findViewById(R.id.AmountToAddText);
        final EditText notedAdd = (EditText)llayout.findViewById(R.id.AddNotesText);
        //datePicker.
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
