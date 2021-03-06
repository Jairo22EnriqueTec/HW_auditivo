package org.helloworld.auditivo;


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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil extends Fragment {
TextView nombre, correo, numero;
String nombree, correoo,numeroo;
char nombre_corto;

FloatingActionButton editar_image;
CircleImageView circleImageView;
    String CARPETA_PRINCIPAL="misImagenesAPP/";
    String CARPETA_IMAGEN="imagenes";
    String DIRECTORIO_IMAGEN=CARPETA_PRINCIPAL+CARPETA_IMAGEN;
    String path;
    File fileimagen;
    String ExternalStorageDirectory = Environment.getExternalStorageDirectory() + File.separator;
    private static final int PERMISSION_FILE=23;
    private static final int ACCES_FILE=43;
    int contador=0;
    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_perfil, container, false);
        nombre=view.findViewById(R.id.nombre_usuario);
        correo=view.findViewById(R.id.correo_usuario);
        circleImageView=view.findViewById(R.id.imagen_usuario);
        numero=view.findViewById(R.id.telefono_usu);
        editar_image=view.findViewById(R.id.editar_imagen);
        editar_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_FILE);
                }
                else{
                    Intent intent=new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(Intent.createChooser(intent,"Pilih gambar"),ACCES_FILE);
                }
            }
        });
        Datos_Usuario conex = new Datos_Usuario(getActivity(), "DBPerfil", null, 1);
        SQLiteDatabase db = conex.getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM Perfil", null);
try{
    while (cursor.moveToNext()) {
        nombree=cursor.getString(0);
        numeroo=cursor.getString(2);
        correoo=cursor.getString(4);


    }
}catch (Exception e){
    Toast.makeText(getActivity(), "El documento no existe", Toast.LENGTH_SHORT).show();
}

for(int x=0;x<nombree.length();x++){

    if (nombree.charAt(x)==' ') {
contador=x;
break;
    }

}
        nombre.setText(nombree.substring(0,contador));
correo.setText(correoo);
numero.setText(numeroo);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ACCES_FILE && resultCode== Activity.RESULT_OK && data!=null && data.getData()!=null){
            Uri FILE_URI=data.getData();
            CropImage.activity(FILE_URI)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setActivityTitle("Editar")
                    .setFixAspectRatio(true)
                    .setCropMenuCropButtonTitle("Guardar")
                    .start(getActivity());
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
                        Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),resultUri);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100, new FileOutputStream(ExternalStorageDirectory+DIRECTORIO_IMAGEN+nombre));
                        File filefinal=new File(ExternalStorageDirectory+DIRECTORIO_IMAGEN+nombre);
                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "Titulo");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripción");
                        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis ());
                        values.put(MediaStore.Images.ImageColumns.BUCKET_ID, filefinal.toString().toLowerCase(Locale.getDefault()).hashCode());
                        values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, filefinal.getName().toLowerCase(Locale.getDefault()));
                        values.put("_data", filefinal.getAbsolutePath());
                        ContentResolver cr = getActivity().getContentResolver();

                        cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Toast.makeText(getActivity(), "Se ha creado", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
