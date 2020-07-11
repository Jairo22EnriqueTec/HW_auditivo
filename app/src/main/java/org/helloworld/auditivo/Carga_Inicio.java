package org.helloworld.auditivo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.helloworld.auditivo.R;
import org.helloworld.auditivo.Introduccion.Bienvenida;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


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
    public final static int PERMISOS=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga__inicio);
        validarpermisos();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String dele="DELETE FROM Usuario";
                Datos_Usuario conex = new Datos_Usuario(getApplicationContext(), "DBUsuario", null, 2);
                SQLiteDatabase db = conex.getReadableDatabase();

                 db.execSQL(dele);
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

    private void validarpermisos() {
    if(!(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)){

        if(checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED && checkSelfPermission(SEND_SMS)==PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(CALL_PHONE)==PackageManager.PERMISSION_GRANTED && checkSelfPermission(ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(RECORD_AUDIO)==PackageManager.PERMISSION_GRANTED && checkSelfPermission(READ_CONTACTS)==PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED && checkSelfPermission(WRITE_CONTACTS)==PackageManager.PERMISSION_GRANTED){

        }else{
            if(shouldShowRequestPermissionRationale(CAMERA)||shouldShowRequestPermissionRationale(SEND_SMS)
                    ||shouldShowRequestPermissionRationale(CALL_PHONE)||shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)
                    ||shouldShowRequestPermissionRationale(RECORD_AUDIO) ||shouldShowRequestPermissionRationale(READ_CONTACTS)
                    ||shouldShowRequestPermissionRationale(WRITE_CONTACTS)||shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)){

                Recomendacion_permisos();

            }        }    }    }

    private void Recomendacion_permisos() {
        AlertDialog.Builder ventana= new AlertDialog.Builder(this);
        ventana.setTitle("Permisos desactivados");
        ventana.setMessage("Los permisos son necesarios para el correcto funcionamiento de la app.");
        ventana.setPositiveButton("Conceder", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{CAMERA, RECORD_AUDIO, CALL_PHONE, ACCESS_FINE_LOCATION, WRITE_CONTACTS, WRITE_EXTERNAL_STORAGE, SEND_SMS, READ_CONTACTS},100);

            }
        });
        ventana.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100) {
            if(!(grantResults.length==8 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED
                    && grantResults[2]==PackageManager.PERMISSION_GRANTED && grantResults[5]==PackageManager.PERMISSION_GRANTED
                    && grantResults[3]==PackageManager.PERMISSION_GRANTED && grantResults[6]==PackageManager.PERMISSION_GRANTED
                    && grantResults[4]==PackageManager.PERMISSION_GRANTED && grantResults[7]==PackageManager.PERMISSION_GRANTED)){

            }

        }
    }
}