package org.helloworld.auditivo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Inicio_Usuario_Cuenta extends AppCompatActivity {
EditText correo, password;
Button iniciar, registrar;
String correoo,contra;
String response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__usuario__cuenta);
        correo=findViewById(R.id.correo_usu);
        password=findViewById(R.id.contrasena_usu);
        registrar=findViewById(R.id.registrar);
        iniciar=findViewById(R.id.ingresar);

        correoo=correo.getText().toString();
        contra=password.getText().toString();
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscarperfil(correoo, contra);
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

    private void Buscarperfil( String correo_perfil, String password_perfil){
        ClaseConection claseConection=new ClaseConection();
        try {
             response=claseConection.execute("https://helloworldapp.000webhostapp.com/Verificacion_Cuenta.php?correo="+correoo+"&passwordd="+contra+"").get();
            if (response!=null){
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                //Registrar en BD Local
                JSONArray jsonArray=new JSONArray(response);
                JSONObject jsonObject=jsonArray.getJSONObject(0);
               String Nombre=jsonObject.getString("nombre");
               String fecha_nacimiento=jsonObject.getString("fecha_nacimiento");
               String telefono=jsonObject.getString("telefono");
               String frase=jsonObject.getString("frase");
               String correo=jsonObject.getString("correo");
               String passwordd=jsonObject.getString("passwordd");
                SQLiteDatabase db = new Datos_Usuario(this, "DBPerfil", null, 1).getWritableDatabase();
                db.execSQL("INSERT INTO Perfil (Nombre, Nacimiento, Numero, Frase,Correo,Password)VALUES("+Nombre+"','"+fecha_nacimiento+"','"+telefono+"','"+frase+"','"+correo+"','"+passwordd+"')");
                //--------------------
                Intent intent=new Intent(Inicio_Usuario_Cuenta.this, Menu_principall.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(this, "Correo o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();

        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}