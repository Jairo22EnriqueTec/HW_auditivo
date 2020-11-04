package org.helloworld.auditivo.Introduccion;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.helloworld.auditivo.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

//cuaderno y libreta pagina resumenv
public class Bienvenida extends AppCompatActivity {

    Button btn_entendido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        btn_entendido=findViewById(R.id.btn_entendido_bienvenida);
        requestPermissions(new String[]{CAMERA, RECORD_AUDIO, CALL_PHONE, ACCESS_FINE_LOCATION, WRITE_CONTACTS, WRITE_EXTERNAL_STORAGE, SEND_SMS, READ_CONTACTS},100);
        btn_entendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tutorial_primer_uso.class));
            }
        });
    }
}
