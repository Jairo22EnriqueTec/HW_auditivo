package org.helloworld.auditivo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Calendar;
import at.markushi.ui.CircleButton;



public class Formulario_Datos_Usuario extends AppCompatActivity {
    EditText NombreUsuario, TelefonoUsuario, correo, contrasena, apellidos;
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
    String ll, correo_r, contra_r;
    String numero;
    String frase;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_datos_usuario);
        TelefonoUsuario = findViewById(R.id.TelefonoUsuario);
        NombreUsuario = findViewById(R.id.NombreUsuario);
        EditCalendario = findViewById(R.id.EditCalendario);
        BtnCalendar = findViewById(R.id.BtnCalendar);
        correo = findViewById(R.id.correo);
        apellidos = findViewById(R.id.apellido);
        contrasena = findViewById(R.id.contrasena);
        cal = Calendar.getInstance();
        button = findViewById(R.id.button);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH);
        año = cal.get(Calendar.YEAR);
        requestQueue = Volley.newRequestQueue(this);
        BtnCalendar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Formulario_Datos_Usuario.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        EditCalendario.setText(i2 + "/" + (i1 + 1) + "/" + i);
                    }
                }, año, mes, dia);
                datePickerDialog.show();
            }
        });

    }

    public void inicio(View view) {
        try {
            l = NombreUsuario.getText().toString()+" "+ apellidos.getText().toString();
            ll = EditCalendario.getText().toString();
            numero = TelefonoUsuario.getText().toString();
            frase = "NA";
            correo_r = correo.getText().toString();
            contra_r = contrasena.getText().toString();
            if (l.length() > 0 && ll.length() > 0 && numero.length() > 0) {
                try {

                    // String dele="DELETE FROM Usuario";

                    SQLiteDatabase db = new Datos_Usuario(this, "DBPerfill", null, 1).getWritableDatabase();
                    //db.execSQL(dele);
                    db.execSQL("INSERT INTO Perfill (Nombre, Nacimiento, Numero, Frase, Correo, Password) VALUES ('" + l + "','" + ll + "','" + numero + "','" + frase + "','" + correo_r + "','" + contra_r + "')");

                    //Subir al servidor
//cargarweb();
                    ClaseConection claseConection = new ClaseConection();
                    String response = claseConection.execute("https://helloworldapp.000webhostapp.com/bdApp/Registro.php?nombre=" + l + "&fecha_nacimiento=" + ll + "&telefono=" + numero + "&frase=" + frase + "&correo=" + correo_r + "&passwordd=" + contra_r + "").get();
                    if (response != null) {
                        Toast.makeText(this, "Se ha registrado con Exito", Toast.LENGTH_SHORT).show();


                    }


                    startActivity(new Intent(Formulario_Datos_Usuario.this, Menu_principall.class));
                } catch (Exception e) {
                    Toast.makeText(this, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Rellena todos los Espacios", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    private void cargarweb() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        String url = "https://helloworldapp.000webhostapp.com/Registro.php?";
        url.replace(" ", "%20");

    }
}
