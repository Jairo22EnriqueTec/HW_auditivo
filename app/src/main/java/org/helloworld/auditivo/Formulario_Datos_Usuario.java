package org.helloworld.auditivo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.helloworld.auditivo.R;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import at.markushi.ui.CircleButton;


public class Formulario_Datos_Usuario extends AppCompatActivity {
    EditText edit1, edit22, edit3;
    TextView edit2;
    //FloatingActionButton fl;
   CircleButton fl;
    Calendar cal;
    int dia, mes, año;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
ProgressDialog progressDialog;
    String l;
    String ll;
    String numero;
    String frase;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_datos_usuario);
        edit22=findViewById(R.id.editText3);
        edit3=findViewById(R.id.editText5);
        edit1=findViewById(R.id.editText);
        edit2=findViewById(R.id.editText2);
        fl=findViewById(R.id.floatt2);
        cal=Calendar.getInstance();
        dia=cal.get(Calendar.DAY_OF_MONTH);
        mes=cal.get(Calendar.MONTH);
        año=cal.get(Calendar.YEAR);
        requestQueue= Volley.newRequestQueue(this);
fl.setOnClickListener(new View.OnClickListener() {
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        DatePickerDialog datePickerDialog=new DatePickerDialog(Formulario_Datos_Usuario.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
edit2.setText(i2+"/"+(i1+1)+"/"+i);
            }
        },año,mes,dia);
        datePickerDialog.show();
    }
});
    }
        public void inicio (View view) {
            try{
             l = edit1.getText().toString();
             ll = edit2.getText().toString();
             numero=edit22.getText().toString();
             frase=edit3.getText().toString();
            if (l.length() > 0 && ll.length() > 0 && numero.length()>0 && frase.length()>0) {
                try {

                    String dele="DELETE FROM Usuario";

                    SQLiteDatabase db = new Datos_Usuario(this, "DBUsuario", null, 2).getWritableDatabase();
                    db.execSQL(dele);
                    db.execSQL("INSERT INTO Usuario (Discapacidad, Nombre, Nacimiento, Numero, Frase)VALUES('audicomu','" + l + "','" + ll + "','"+numero+"','"+frase+"')");
                    //Subir al servidor
cargarweb();



                        startActivity(new Intent(Formulario_Datos_Usuario.this, Inicio_Comunicativo.class));
                } catch (Exception e) {
                    Toast.makeText(this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Rellena todos los Espacios", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
                Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            }
    }

    private void cargarweb() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        String url="https://helloworldapp.000webhostapp.com/Registro.php?";
        url.replace(" ","%20");
        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    Toast.makeText(Formulario_Datos_Usuario.this, "Se ha Publicado", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Formulario_Datos_Usuario.this, "No se ha podido Conectar"+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("ERROR",error.toString());
                progressDialog.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Map<String,String>parametros=new HashMap<>();

                Map<String,String>parametros=new HashMap<>();
                parametros.put("nombre",l);
                parametros.put("fecha_nacimiento",ll);
                parametros.put("telefono",numero);
                parametros.put("frase",frase);
                return parametros;

            }
        };
        requestQueue.add(stringRequest);

    }


   /* @Override
    public void onErrorResponse(VolleyError error) {
progressDialog.hide();
        Toast.makeText(this, "No se ha podido registrar"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
        progressDialog.hide();
    }*/
}
