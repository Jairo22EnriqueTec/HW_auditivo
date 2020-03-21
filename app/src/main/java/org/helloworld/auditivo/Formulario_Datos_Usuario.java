package org.helloworld.auditivo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.helloworld.auditivo.R;

import java.util.Calendar;

import at.markushi.ui.CircleButton;


public class Formulario_Datos_Usuario extends AppCompatActivity {
    EditText edit1, edit22, edit3;
    TextView edit2;
    //FloatingActionButton fl;
   CircleButton fl;
    Calendar cal;
    int dia, mes, año;
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
            String l = edit1.getText().toString();
            String ll = edit2.getText().toString();
            String numero=edit22.getText().toString();
            String frase=edit3.getText().toString();
            if (l.length() > 0 && ll.length() > 0 && numero.length()>0 && frase.length()>0) {
                try {

                    String dele="DELETE FROM Usuario";

                    SQLiteDatabase db = new Datos_Usuario(this, "DBUsuario", null, 2).getWritableDatabase();
                    db.execSQL(dele);
                    db.execSQL("INSERT INTO Usuario (Discapacidad, Nombre, Nacimiento, Numero, Frase)VALUES('audicomu','" + l + "','" + ll + "','"+numero+"','"+frase+"')");
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


}
