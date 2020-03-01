package org.tensorflow.demo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.tensorflow.demo.Introduccion.Bienvenida;


/*
COMENTARIOS.
-------------------------------------------------------------------------
Esta actividad revisará si se cuenta con la información en la base de datos local e iniciará la app en la pantalla de inicio
Tanto la auditiva como la comunicativa van a la misma, así que se guardará en la base de datos como:
"audicomu"
Se trabajará todo en minúsculas para evitar errores
Ese valor deberá ser el guardado tanto en el servidor como la base local.
 */
public class Carga_Inicio extends AppCompatActivity {
    public final int duracion = 2000;
    public static boolean EntrarDirecto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga__inicio);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String dele="DELETE FROM Usuario";
                Datos_Usuario conex = new Datos_Usuario(getApplicationContext(), "DBUsuario", null, 2);
                SQLiteDatabase db = conex.getReadableDatabase();

                 //db.execSQL(dele);
                final Cursor cursor = db.rawQuery("SELECT Discapacidad FROM Usuario", null);
                boolean sino=true;
                while (cursor.moveToNext()) {

                    if (cursor.getString(0).equals("audicomu")) {
                        Intent inte = new Intent(Carga_Inicio.this, Inicio_Comunicativo.class);
                        EntrarDirecto=true;
                        startActivity(inte);
                        finish();
                    }
                    sino=false;
                }

if(sino) {
    Intent inte = new Intent(Carga_Inicio.this, Bienvenida.class);
    startActivity(inte);
    finish();
}
            }
        }, duracion);
    }

}