package com.cor.luis.administrator.pruebaandroid.vista.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.loggin.logginEvento;


/**
 * A simple {@link Fragment} subclass.
 */
public class logfragment extends Fragment {


    public logfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_logfragment, container, false);

        TextView logText = (TextView)view.findViewById(R.id.logcontent);

        logText.setText(logginEvento.obtieneLog(getActivity().getApplicationContext()));


        return view;
    }

}
