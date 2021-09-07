package com.example.alexcaban.cine;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class Actores extends Fragment {
    public static EditText EtIdActor, EtNombreActor, EtApellidoActor, EtEmailActor;

    private OnFragmentInteractionListener mListener;

    public Actores() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V =inflater.inflate(R.layout.fragment_actores, container, false);
        EtIdActor = (EditText)V.findViewById(R.id.EtIdActor);
        EtNombreActor = (EditText)V.findViewById(R.id.EtNombreActor);
        EtApellidoActor = (EditText)V.findViewById(R.id.EtApellidoActor);
        EtEmailActor = (EditText)V.findViewById(R.id.EtEmailActor);


        final TextInputLayout EtIdActorWrapper = (TextInputLayout) V.findViewById(R.id.EtIdActorWrapper);
        EtIdActorWrapper.setHint("Identificacion");

        final TextInputLayout EtNombreActorWrapper = (TextInputLayout) V.findViewById(R.id.EtNombreActorWrapper);
        EtNombreActorWrapper.setHint("Nombres");

        final TextInputLayout EtApellidoActorWrapper = (TextInputLayout) V.findViewById(R.id.EtApellidoActorWrapper);
        EtApellidoActorWrapper.setHint("Apellidos");

        final TextInputLayout EtEmailActorWrapper = (TextInputLayout) V.findViewById(R.id.EtEmailActorWrapper);
        EtEmailActorWrapper.setHint("Email ");


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
