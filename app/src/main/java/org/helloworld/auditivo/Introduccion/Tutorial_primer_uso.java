package org.helloworld.auditivo.Introduccion;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.helloworld.auditivo.Carga_Inicio;
import org.helloworld.auditivo.Datos_Usuario;
import org.helloworld.auditivo.Formulario_Datos_Usuario;
import org.helloworld.auditivo.Inicio_Usuario_Cuenta;
import org.helloworld.auditivo.Menu_principall;
import org.helloworld.auditivo.R;


import static org.helloworld.auditivo.Clases.VariablesYDatos.Num_apartado;
import static org.helloworld.auditivo.Clases.VariablesYDatos.Num_columna;
import static org.helloworld.auditivo.Clases.VariablesYDatos.Tutorial_Primer_uso;

public class Tutorial_primer_uso extends AppCompatActivity {

    TextView txtTitulo,txtSubTitulo;
    TextView txtDescripcion;
    Button btnSiguiente, btn_saltar_tutorial;
    VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_primer_uso);

        btn_saltar_tutorial=findViewById(R.id.btn_saltar_tutorial);
        txtDescripcion=findViewById(R.id.txtDescripcion_);
        txtTitulo=findViewById(R.id.txtTitulo_);
        vv=findViewById(R.id.video_tuto);
        txtSubTitulo=findViewById(R.id.txtSubTitulo_);

        btn_saltar_tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Carga_Inicio.EntrarDirecto){
                    startActivity(new Intent(Tutorial_primer_uso.this, Menu_principall.class));
                }else {
                    startActivity(new Intent(getApplication(), Inicio_Usuario_Cuenta.class));
                }
            }
        });

        btnSiguiente=findViewById(R.id.btn_tuto_sig);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Num_columna==(Tutorial_Primer_uso[1].length-1)){
                    try {
                        Datos_Usuario conex_primera = new Datos_Usuario(getApplicationContext(), "DBUsuarios", null, 3);
                        SQLiteDatabase db_pri = conex_primera.getReadableDatabase();
                        db_pri.execSQL("UPDATE Primeravezen SET Auditivo='1' WHERE Auditivo='0'");
                        db_pri.execSQL("UPDATE Primeravezen SET Comunicativo='1' WHERE Comunicativo ='0'");
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    if(Carga_Inicio.EntrarDirecto){
                     startActivity(new Intent(Tutorial_primer_uso.this,Menu_principall.class));
                    }else {
                        startActivity(new Intent(getApplication(), Formulario_Datos_Usuario.class));
                    }
                }else {
                    Num_columna++;
                    if(Num_columna==2||Num_columna==4||Num_columna==6){
                        Num_apartado++;
                    }
                    startActivity(new Intent(getApplication(), Tutorial_primer_uso.class));
                }
            }
        });
        settearDatos();
    }

    private void settearDatos() {
        btnSiguiente.setText("Siguiente   "+(Num_columna+1)+"/9");
        txtTitulo.setText(                      Tutorial_Primer_uso [0] [Num_apartado]+"");
        txtSubTitulo.setText(                   Tutorial_Primer_uso [1] [Num_columna]+"");
        txtDescripcion.setText(                  Tutorial_Primer_uso [2] [Num_columna]+"");
        SettearVideo();
    }
    void SettearVideo(){
        try {
            String path = "android.resource://org.tensorflow.tensorflowdemo/" +
                    Integer.parseInt(Tutorial_Primer_uso [3] [Num_columna]+"");
            Uri uri = Uri.parse(path);
            vv.setVideoURI(uri);
            vv.setMediaController(new MediaController(this));
            vv.start();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
