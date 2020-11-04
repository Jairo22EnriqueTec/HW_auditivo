package org.helloworld.auditivo;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.helloworld.auditivo.Adaptadores.Adaptador_Titulos;
import org.helloworld.auditivo.Adaptadores.TitulosA;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pictogramas extends Fragment {
    public static Fragment f=null;
    ArrayList<TitulosA> datos;
    //ArrayList<String>datos2;
    RecyclerView recyclerView, recyclerView2;
    public static String TEMA="";
    int result;
    public static TextView textView;
    public TextToSpeech toSpeech;
    String frase="";
    FloatingActionButton hablar, borrar;

    public Pictogramas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view=inflater.inflate(R.layout.fragment_pictogramas, container, false);
        try {
            toSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        result = toSpeech.setLanguage(Locale.getDefault());
                    } else {

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        hablar=view.findViewById(R.id.hablar);

        textView=view.findViewById(R.id.texttt);
        recyclerView=view.findViewById(R.id.recycleropciones);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        datos=new ArrayList<>();
        llenarrecycler();
        hablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frase=textView.getText().toString();
                toSpeech.speak(frase,TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        Adaptador_Titulos adaptador_titulos=new Adaptador_Titulos(datos);
        borrar=view.findViewById(R.id.borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ventana_Teclado_Voz_Senas.texto="";
                frase="";
                textView.setText("");
            }
        });
        hablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frase=textView.getText().toString();
                toSpeech.speak(frase,TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        recyclerView.setAdapter(adaptador_titulos);

        adaptador_titulos.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ventana_Teclado_Voz_Senas.limpiarrecycler();
                TEMA=datos.get(recyclerView.getChildAdapterPosition(v)).getTituloo();

                if(TEMA.equalsIgnoreCase("Casa")){
                    Ventana_Teclado_Voz_Senas.desaparecerimagen();
                    Ventana_Teclado_Voz_Senas.recyclerSubTCasa();
                    Ventana_Teclado_Voz_Senas.llenarrecycler();

                }
                if (TEMA.equalsIgnoreCase("Hospital")){
                    Ventana_Teclado_Voz_Senas.desaparecerimagen();
                    Ventana_Teclado_Voz_Senas.recyclerSubTHospital();
                    Ventana_Teclado_Voz_Senas.llenarrecycler();
                }
                if (TEMA.equalsIgnoreCase("Escuela")){
                    Ventana_Teclado_Voz_Senas.desaparecerimagen();
                    Ventana_Teclado_Voz_Senas.recyclerSubTEscuela();
                    Ventana_Teclado_Voz_Senas.llenarrecycler();
                }
            }
        });
        mostrar();
    return view;
    }
    private void mostrar() {
        f = new Ventana_Teclado_Voz_Senas();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmm,f).commit();
    }

    private void llenarrecycler() {
        datos.add(new TitulosA("Casa",R.drawable.fondo1, R.drawable.cassa));
        datos.add(new TitulosA("Hospital",R.drawable.fondo2,R.drawable.hospitall));
        datos.add(new TitulosA("Escuela",R.drawable.fondo3,R.drawable.escuelaa));

    }
}
