package com.example.alexcaban.cine;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import com.example.alexcaban.cine.Adaptadores.CarteleraAdapter;
import com.example.alexcaban.cine.Modelos.CarteleraADT;

import static com.example.alexcaban.cine.Peliculas.interfazpeliculas;
public class Cartelera extends Fragment implements OnItemClickListener{
    public static ListView lvCartelera;
    public static CarteleraAdapter objCartelera;
    private OnFragmentInteractionListener mListener;
    public static CarteleraADT peliculaSelecciona;

    public Cartelera() {
        // Required empty public constructor
    }


    public static Cartelera newInstance(String param1, String param2) {
        Cartelera fragment = new Cartelera();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cartelera, container, false);
        lvCartelera = (ListView) v.findViewById(R.id.lvCatelera);

        objCartelera = new CarteleraAdapter(Cine.interfaz,Cine.interfaz.arrayCartelera);
        lvCartelera.setAdapter(objCartelera);
        lvCartelera.setOnItemClickListener(this);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        interfazpeliculas.peliculaSelecciona = Cine.interfaz.arrayCartelera.get(position);
        Cine.interfaz.tabHost.setCurrentTabByTag("tab4");

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
