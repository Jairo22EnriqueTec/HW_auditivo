package org.helloworld.auditivo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.helloworld.auditivo.Model.Usuario;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.helloworld.auditivo.R;
import org.helloworld.auditivo.Utils.PersonaService;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import at.markushi.ui.CircleButton;
import retrofit2.Call;
import retrofit2.Callback;


public class Formulario_Datos_Usuario extends AppCompatActivity {
    EditText NombreUsuario, TelefonoUsuario;
    TextView EditCalendario;
    //FloatingActionButton fl;
   CircleButton BtnCalendar;
   Button button;
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
    PersonaService personaService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_datos_usuario);
        TelefonoUsuario=findViewById(R.id.TelefonoUsuario);
        NombreUsuario=findViewById(R.id.NombreUsuario);
        EditCalendario=findViewById(R.id.EditCalendario);
        BtnCalendar=findViewById(R.id.BtnCalendar);
        cal=Calendar.getInstance();
        button=findViewById(R.id.button);
        dia=cal.get(Calendar.DAY_OF_MONTH);
        mes=cal.get(Calendar.MONTH);
        año=cal.get(Calendar.YEAR);
        requestQueue= Volley.newRequestQueue(this);
        BtnCalendar.setOnClickListener(new View.OnClickListener() {
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        DatePickerDialog datePickerDialog=new DatePickerDialog(Formulario_Datos_Usuario.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
EditCalendario.setText(i2+"/"+(i1+1)+"/"+i);
            }
        },año,mes,dia);
        datePickerDialog.show();
    }
});

    }
        public void inicio (View view) {
            try{
             l = NombreUsuario.getText().toString();
             ll = EditCalendario.getText().toString();
             numero=TelefonoUsuario.getText().toString();
             frase="";
            if (l.length() > 0 && ll.length() > 0 && numero.length()>0) {
                try {

                    String dele="DELETE FROM Usuario";

                    SQLiteDatabase db = new Datos_Usuario(this, "DBUsuario", null, 2).getWritableDatabase();
                    db.execSQL(dele);
                    db.execSQL("INSERT INTO Usuario (Discapacidad, Nombre, Nacimiento, Numero, Frase)VALUES('audicomu','" + l + "','" + ll + "','"+numero+"','"+frase+"')");
                    //Subir al servidor
//cargarweb();
                    ClaseConection claseConection=new ClaseConection();
                   String response= claseConection.execute("https://helloworldapp.000webhostapp.com/Registro.php?nombre="+l+"&fecha_nacimiento="+ll+"&telefono="+numero+"&frase="+frase+"").get();
                    progressDialog=new ProgressDialog(this);
                    progressDialog.setMessage("Cargando...");
                    progressDialog.show();
if(response!=null){
    progressDialog.hide();
    Toast.makeText(this, "Se ha registrado con exito", Toast.LENGTH_SHORT).show();


}



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





       /* Call<List<Usuario>>call= personaService.getUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, retrofit2.Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Formulario_Datos_Usuario.this, "Se ha registrado", Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
Log.i("ERROR",t.getMessage());
                progressDialog.hide();
            }
        });*/



       /* stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
        };*/
        //requestQueue.add(stringRequest);

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
