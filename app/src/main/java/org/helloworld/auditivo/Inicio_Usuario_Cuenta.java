package org.helloworld.auditivo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Inicio_Usuario_Cuenta extends AppCompatActivity {
EditText correo, password;
Button iniciar, registrar;
String correoo,contra;
String response;
    String Nombre;
    String fecha_nacimiento;
    String telefono;
    String frase;
    String correos;
    String passwordd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__usuario__cuenta);
        correo=findViewById(R.id.correo_usu);
        password=findViewById(R.id.contrasena_usu);
        registrar=findViewById(R.id.registrar);
        iniciar=findViewById(R.id.ingresar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Buscarperfil();
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Inicio_Usuario_Cuenta.this, Formulario_Datos_Usuario.class);
                startActivity(intent);
            }
        });
    }

    private void Buscarperfil(){
        ClaseConection claseConection=new ClaseConection();
        try {
            correoo=correo.getText().toString();
            contra=password.getText().toString();
             response=claseConection.execute("https://helloworldapp.000webhostapp.com/bdApp/Verificacion_Cuenta.php?correo="+correoo+"&passwordd="+contra+"").get();
          //  Toast.makeText(this, ""+correoo+contra, Toast.LENGTH_SHORT).show();
                //Registrar en BD Local
            JSONObject Jasonobject = new JSONObject(response);
            JSONArray Jarray = Jasonobject.getJSONArray("Datos_Usuario");
            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject jsonObjectt = Jarray.getJSONObject(i);
                 Nombre=jsonObjectt.getString("nombre");
                 fecha_nacimiento=jsonObjectt.getString("fecha_nacimiento");
                 telefono=jsonObjectt.getString("telefono");
                 frase=jsonObjectt.getString("frase");
                 correos=jsonObjectt.getString("correo");
                 passwordd=jsonObjectt.getString("passwordd");


            }
            Log.e("Variable", Nombre);
             /*JSONArray jsonArray=new JSONArray(response);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
               String Nombre=jsonObject.getString("nombre");
               String fecha_nacimiento=jsonObject.getString("fecha_nacimiento");
               String telefono=jsonObject.getString("telefono");
               String frase=jsonObject.getString("frase");
               String correo=jsonObject.getString("correo");
               String passwordd=jsonObject.getString("passwordd");
               Log.e("Variable", Nombre);*/
               SQLiteDatabase db = new Datos_Usuario(this, "DBPerfill", null, 1).getWritableDatabase();
                db.execSQL("INSERT INTO Perfill (Nombre, Nacimiento, Numero, Frase,Correo,Password)VALUES('"+Nombre+"','"+fecha_nacimiento+"','"+telefono+"','"+frase+"','"+correos+"','"+passwordd+"')");
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                //--------------------
                Intent intent=new Intent(Inicio_Usuario_Cuenta.this, Menu_principall.class);
                startActivity(intent);



        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("ERROR1: ",e.getMessage());

        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("ERROR2: ",e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();

            Log.e("ERROR3: ",e.getMessage());
        }
    }

}