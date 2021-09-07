package com.example.alexcaban.cine;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Categorias extends Fragment {
    public static EditText EtDescripcionCategoria;


    private OnFragmentInteractionListener mListener;

    public Categorias() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_categorias, container, false);
        EtDescripcionCategoria = (EditText)V.findViewById(R.id.EtDescripcionCategoria);
        TextInputLayout EtDescripcionCategoriaWrapper = (TextInputLayout) V.findViewById(R.id.EtDescripcionCategoriaWrapper);
        EtDescripcionCategoriaWrapper.setHint("Descripcion");



        return V;



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
