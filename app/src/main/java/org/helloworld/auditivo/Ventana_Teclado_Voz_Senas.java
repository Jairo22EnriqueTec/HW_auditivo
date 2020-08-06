package org.helloworld.auditivo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.helloworld.auditivo.R;
import org.helloworld.auditivo.Adaptadores.Adaptador_subtitulos;
import org.helloworld.auditivo.Clases.VariablesYDatos;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ventana_Teclado_Voz_Senas extends Fragment {
    //ArrayList<TitulosA>datos;
    public static RecyclerView recyclerViewww, recyclerView2;
    public static ArrayList<String>dats;
    public static ArrayList<Adaptador_Senas_Quiz>lists;
    public static ImageView imagen;
    public static Adaptador_subtitulos adaptador_subtitulos;
    public static  LinearLayoutManager linearLayoutManager;
    public static String subtitulo="Pronombres";
    public static String texto="";

    public static Elemento_Quiz_Sena senas;
    public Ventana_Teclado_Voz_Senas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ventana_teclado_voz_senas, container, false);
        recyclerView2=view.findViewById(R.id.recyclerViewtitulos);
        dats=new ArrayList<>();
        adaptador_subtitulos=new Adaptador_subtitulos(dats);
        linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(linearLayoutManager);
        imagen=view.findViewById(R.id.imggg);
//------------------
        recyclerViewww=view.findViewById(R.id.recycleropcionesss);
        lists=new ArrayList<>();
        senas=new Elemento_Quiz_Sena(lists);
        recyclerViewww.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adaptador_subtitulos.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtitulo=dats.get(recyclerView2.getChildAdapterPosition(v)).toString();
                llenarrecycler();
            }
        });
        senas.setOnClickListener2(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto+=lists.get(recyclerViewww.getChildAdapterPosition(v)).getText()+" ";
                Pictogramas.textView.setText(""+texto);
            }
        });
        return view;
    }

    public static void llenarrecycler() {
        lists.clear();
        recyclerViewww.setAdapter(senas);
        //Casa
        if (subtitulo.equalsIgnoreCase("Pronombres")){
            for (int x = 0; x< VariablesYDatos.imgpronombres.length; x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgpronombres[x],""+VariablesYDatos.txtpronombres[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Cuerpo")){
            for(int x=0;x<VariablesYDatos.imgcuerpo.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgcuerpo[x],""+VariablesYDatos.txtcuerpo[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Casa")){
            for (int x=0;x<VariablesYDatos.imagcasa.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imagcasa[x],""+VariablesYDatos.txtcasa[x]));
            }
        }
        if (subtitulo.equalsIgnoreCase("Objetos")){
            for (int x=0;x<VariablesYDatos.imgobjetos.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgobjetos[x],""+VariablesYDatos.txtobjetos[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Partes del Cuerpo")){
            for (int x=0;x<VariablesYDatos.imgcuerpo.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgcuerpo[x],""+VariablesYDatos.txtcuerpo[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Comida")){
            for (int x=0;x<VariablesYDatos.imgcomida.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgcomida[x],""+VariablesYDatos.txtcomida[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Colores")){
            for (int x=0;x<VariablesYDatos.imgcolores.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgcolores[x],""+VariablesYDatos.txtcolores[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Expresiones")){
            for (int x=0;x<VariablesYDatos.imgexpresi.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgexpresi[x],""+VariablesYDatos.txtexpresi[x]));
            }
        }
        //0--------------------
        //Hospital;
        if(subtitulo.equalsIgnoreCase("Salud")){
            for (int x=0;x<VariablesYDatos.imgHospital.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgHospital[x],""+VariablesYDatos.txtHospital[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Transporte")){
            for (int x=0;x<VariablesYDatos.imgtransporte.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgtransporte[x],""+VariablesYDatos.txttransporte[x]));
            }
        }
        //-----------------
        //Escuelaaa
        if(subtitulo.equalsIgnoreCase("Escuela")){
            for (int x=0;x<VariablesYDatos.imgescuela.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgescuela[x],""+VariablesYDatos.txtescuela[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Numeros")){
            for (int x=0;x<VariablesYDatos.imgnumeros.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgnumeros[x],""+VariablesYDatos.txtnumeros[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Tiempos")){
            for (int x=0;x<VariablesYDatos.imgtiempos.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgtiempos[x],""+VariablesYDatos.txttiempo[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Dias")){
            for (int x=0;x<VariablesYDatos.imgdias.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imgdias[x],""+VariablesYDatos.textdias[x]));
            }
        }
        if(subtitulo.equalsIgnoreCase("Animales")){
            for (int x=0;x<VariablesYDatos.imganimales.length;x++){
                lists.add(new Adaptador_Senas_Quiz(VariablesYDatos.imganimales[x],""+VariablesYDatos.txtanimales[x]));
            }
        }
    }

    public static void desaparecerimagen() {
        imagen.setVisibility(View.GONE);
    }
    public static void recyclerSubTCasa(){
        recyclerView2.setAdapter(adaptador_subtitulos);
        for(int i=0;i<VariablesYDatos.TemasCasa.length;i++){
            dats.add(VariablesYDatos.TemasCasa[i]);
        }
    }
    public static void recyclerSubTHospital(){
        recyclerView2.setAdapter(adaptador_subtitulos);
        for(int i=0;i<VariablesYDatos.TemasHospital.length;i++){
            dats.add(VariablesYDatos.TemasHospital[i]);
        }
    }
    public static void recyclerSubTEscuela(){
        recyclerView2.setAdapter(adaptador_subtitulos);
        for(int i=0;i<VariablesYDatos.TemasEscuela.length;i++){
            dats.add(VariablesYDatos.TemasEscuela[i]);
        }
    }
    public static void limpiarrecycler(){
        recyclerView2.setAdapter(adaptador_subtitulos);
        dats.clear();

    }

}

