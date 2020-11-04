package org.helloworld.auditivo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

public class Perfil_Usuario extends AppCompatActivity {
    TextView nombre, correo, numero;
    String nombree, correoo,numeroo;
    FloatingActionButton editar_image;
    CircleImageView circleImageView;
    String CARPETA_PRINCIPAL="HWPhoto/";
    String CARPETA_IMAGEN="imagenes", Uri_image;
    String DIRECTORIO_IMAGEN=CARPETA_PRINCIPAL+CARPETA_IMAGEN;
    String path;
    File fileimagen;
    Uri uri;
    String ExternalStorageDirectory = Environment.getExternalStorageDirectory() + File.separator;
    private static final int PERMISSION_FILE=23;
    private static final int ACCES_FILE=43;
    int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__usuario);
        nombre=findViewById(R.id.nombre_usuario);
        correo=findViewById(R.id.correo_usuario);
        circleImageView=findViewById(R.id.imagen_usuario);
        numero=findViewById(R.id.telefono_usu);
        editar_image=findViewById(R.id.editar_imagen);
        Datos_Usuario conex = new Datos_Usuario(Perfil_Usuario.this, "DBPerfill", null, 1);
        SQLiteDatabase db = conex.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Perfill", null);
if(cursor!=null){
    while (cursor.moveToNext()) {
        nombree = cursor.getString(0);
        numeroo = cursor.getString(2);
        correoo = cursor.getString(4);
        // Uri_image=cursor.getString(6);

    }
}
else{
    Toast.makeText(this, "No se puede avanzar", Toast.LENGTH_SHORT).show();
}
if(nombree!=null){
    if(nombree!=null){
        for(int x=0;x<nombree.length();x++){

            if (nombree.charAt(x)==' ') {
                contador=x;
                break;
            }

        }
    }
    else{
        Toast.makeText(this, "No existe nombre", Toast.LENGTH_SHORT).show();
    }

    if(Uri_image==null){
        Toast.makeText(this, "no hay imagen", Toast.LENGTH_SHORT).show();
    }

    nombre.setText(nombree.substring(0,contador));
    correo.setText(correoo);
    numero.setText(numeroo);
}else{
    Toast.makeText(this, "No se encuentra ningun valor", Toast.LENGTH_SHORT).show();
}

        editar_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Perfil_Usuario.this, Manifest.permission.WRITE_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Perfil_Usuario.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_FILE);
                }
                else{
                    Intent intent=new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(Intent.createChooser(intent,"Pilih gambar"),ACCES_FILE);
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ACCES_FILE && resultCode== Activity.RESULT_OK && data!=null && data.getData()!=null){
            Uri FILE_URI=data.getData();
            CropImage.activity(FILE_URI)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setActivityTitle("Editar")
                    .setFixAspectRatio(true)
                    .setCropMenuCropButtonTitle("Guardar")
                    .start(Perfil_Usuario.this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode ==Activity.RESULT_OK) {
                Uri resultUri = result.getUri();
                File myfile=new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
                boolean isCreado=myfile.exists();
                if(isCreado==false){
                    isCreado=myfile.mkdirs();

                }
                if(isCreado==true){
                    try {

                        Long consecutivo = System.currentTimeMillis() / 1000;
                        String nombre = consecutivo.toString() + ".jpeg";
                        path = Environment.getExternalStorageDirectory() + File.separator + DIRECTORIO_IMAGEN + File.separator + nombre;
                        fileimagen = new File(path);
                        Bitmap bitmap= MediaStore.Images.Media.getBitmap(Perfil_Usuario.this.getContentResolver(),resultUri);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100, new FileOutputStream(ExternalStorageDirectory+DIRECTORIO_IMAGEN+nombre));
                        File filefinal=new File(ExternalStorageDirectory+DIRECTORIO_IMAGEN+nombre);
                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "Titulo");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripción");
                        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis ());
                        values.put(MediaStore.Images.ImageColumns.BUCKET_ID, filefinal.toString().toLowerCase(Locale.getDefault()).hashCode());
                        values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, filefinal.getName().toLowerCase(Locale.getDefault()));
                        values.put("_data", filefinal.getAbsolutePath());
                        ContentResolver cr =Perfil_Usuario.this.getContentResolver();
                        cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Toast.makeText(Perfil_Usuario.this, "Se ha creado", Toast.LENGTH_SHORT).show();
                        //Guardar en la BD Local
                        SQLiteDatabase db = new Datos_Usuario(this, "DBPerfill", null, 1).getWritableDatabase();
                        db.execSQL("INSERT INTO Perfill (Imagen) VALUES ('"+resultUri.toString()+"')");
     db.close();


                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(Perfil_Usuario.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ERRORBDIMAGE: ", e.toString());
                    }
                }
               /* try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),resultUri);
bitmap.compress(Bitmap.CompressFormat.JPEG,100, new FileOutputStream(ExternalStorageDirectory+rutaCarpeta+nombre));
File filefinal=new File(ExternalStorageDirectory+rutaCarpeta+nombre);
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "Titulo");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Descripción");
                    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis ());
                    values.put(MediaStore.Images.ImageColumns.BUCKET_ID, filefinal.toString().toLowerCase(Locale.getDefault()).hashCode());
                    values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, filefinal.getName().toLowerCase(Locale.getDefault()));
                    values.put("_data", filefinal.getAbsolutePath());
                    ContentResolver cr = getActivity().getContentResolver();

                    cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


                //circleImageView.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(Perfil_Usuario.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    }
}