package org.helloworld.auditivo;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.helloworld.auditivo.R;
import org.helloworld.auditivo.Adaptadores.Adaptador_Titulos;
import org.helloworld.auditivo.Adaptadores.TitulosA;

import java.util.ArrayList;
import java.util.Locale;

public class Teclado_senas_voz extends AppCompatActivity {
    public static android.support.v4.app.Fragment f=null;
    ArrayList<TitulosA>datos;
    //ArrayList<String>datos2;
    RecyclerView recyclerView, recyclerView2;
    ImageButton hablar,borrar;
    public static String TEMA="";
    int result;
    public static TextView textView;
    public TextToSpeech toSpeech;
    String frase="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_senas_voz);
        try {
            toSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
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
        hablar=findViewById(R.id.hablar);

        textView=findViewById(R.id.texttt);
        recyclerView=findViewById(R.id.recycleropciones);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        datos=new ArrayList<>();
        llenarrecycler();
        borrar=findViewById(R.id.borrar);
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
        Adaptador_Titulos adaptador_titulos=new Adaptador_Titulos(datos);

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
    }

    private void mostrar() {
        f = new Ventana_Teclado_Voz_Senas();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmm,f).commit();
    }

    private void llenarrecycler() {
        datos.add(new TitulosA("Casa",R.drawable.fondo1, R.drawable.cassa));
        datos.add(new TitulosA("Hospital",R.drawable.fondo2,R.drawable.hospitall));
        datos.add(new TitulosA("Escuela",R.drawable.fondo3,R.drawable.escuelaa));

    }
}


