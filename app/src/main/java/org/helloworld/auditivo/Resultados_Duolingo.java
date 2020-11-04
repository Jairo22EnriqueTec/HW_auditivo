package org.helloworld.auditivo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Resultados_Duolingo extends AppCompatActivity {

    public static int total,aciertos;
    ImageView img;
    Button btnRegresar,btnIntentarlo;
    TextView txtRes,txtRestxt;
    boolean update=false;
    String PPa[],PAc[];

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados__duolingo);
        img=findViewById(R.id.IMGResultado);
        txtRes=findViewById(R.id.txtResultado);
        txtRestxt=findViewById(R.id.txtResultadotxt);
        btnRegresar=findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ventana_Cursos_Ejer_Selec_Txt.aciertos=0;
                Ventana_Cursos_Ejer_Selec_Sena.aciertos2=0;
                startActivity(new Intent(getApplicationContext(), Menu_principall.class));
            }
        });
        setAciertos();
        total=getIntent().getIntExtra("total",9);
        Settear();
    }


    Cursor cursor;
    private void setAciertos(){
        aciertos= Ventana_Cursos_Ejer_Selec_Sena.aciertos2;
    }
    private void Settear(){
        txtRes.setText(aciertos+"/"+total);
        int imagen=0;
        String txt="";
        if(total==aciertos) {
            imagen = (R.drawable.resultadotodas);
            txt="Perfecto, has acertado el 100% de las respuestas.\nAprendes muy Rapido";
        }else if(aciertos>(total/2)){
            imagen=(R.drawable.resultadocasitodas);
            txt="Casi, has acertado la mayoria de las respuestas.\nPuedes hacerlo mejor.";
        }
        else if(aciertos==(total/2)){
            imagen=(R.drawable.resultadomitad);
            txt="Puedes mejorar, has acertado la mitad de las respuestas.\nEstudia mas.";
        }else if(aciertos==0){
            imagen=(R.drawable.resultadocasininguna);
            txt = "¡Muy mal!, no has acertado ninguna de las respuestas.\n¡A repasar todo!";
        }else{
            imagen = (R.drawable.resultadocasininguna);
            txt = "¡Estudia!, has acertado menos de la mitad de las respuestas.\nNecesitas repasar más la lección.";
        }
        img.setImageResource(imagen);
        txtRestxt.setText(""+txt);
    }
}