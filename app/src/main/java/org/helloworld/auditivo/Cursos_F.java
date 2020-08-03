package org.helloworld.auditivo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cursos_F extends Fragment {

    public static ImageButton imageButton, imageButton2,imageButton3,imageButton4,imageButton5,imageButton6,imageButton7, filal;
    public static String ventana="";
    ProgressBar progressBar, progressBar2, progressBar3,progressBar4,progressBar5,progressBar6,progressBar7;
    public Cursos_F() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cursos_, container, false);
        imageButton=view.findViewById(R.id.imageButton4);
        imageButton2=view.findViewById(R.id.imageButton9);
        imageButton3=view.findViewById(R.id.imageButton10);
        imageButton4=view.findViewById(R.id.imageButton11);
        imageButton5=view.findViewById(R.id.imageButton12);
        imageButton6=view.findViewById(R.id.imageButton13);
        imageButton7=view.findViewById(R.id.imageButton14);
        filal=view.findViewById(R.id.imageButton3);
        progressBar=view.findViewById(R.id.progressBar1);
        progressBar2=view.findViewById(R.id.progressBar2);
        progressBar3=view.findViewById(R.id.progressBar3);
        progressBar4=view.findViewById(R.id.progressBar4);
        progressBar5=view.findViewById(R.id.progressBar5);
        progressBar6=view.findViewById(R.id.progressBar6);
        progressBar7=view.findViewById(R.id.progressBar7);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ventana="1";
                Intent inet=new Intent(getActivity(), Ventana_Cursos_Ejer_Selec_Txt.class);
                startActivity(inet);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inet=new Intent(getActivity(), Ventana_Cursos_Ejer_Selec_Txt.class);
                startActivity(inet);
                ventana="2";
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inet=new Intent(getActivity(), Ventana_Cursos_Ejer_Selec_Txt.class);
                startActivity(inet);
                ventana="3";
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inet=new Intent(getActivity(), Ventana_Cursos_Ejer_Selec_Txt.class);
                startActivity(inet);
                ventana="4";
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inet=new Intent(getActivity(), Ventana_Cursos_Ejer_Selec_Txt.class);
                startActivity(inet);
                ventana="5";
            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inet=new Intent(getActivity(), Ventana_Cursos_Ejer_Selec_Txt.class);
                startActivity(inet);
                ventana="6";
            }
        });
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inet=new Intent(getActivity(), Ventana_Cursos_Ejer_Selec_Txt.class);
                startActivity(inet);
                ventana="7";
            }
        });
    return view;
    }

}
