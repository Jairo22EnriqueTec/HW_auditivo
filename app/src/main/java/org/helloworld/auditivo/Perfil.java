package org.helloworld.auditivo;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil extends Fragment {
TextView nombre, correo, numero;
String nombree, correoo,numeroo,nombre_corto;
    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_perfil, container, false);
        nombre=view.findViewById(R.id.NombreUsuario);
        correo=view.findViewById(R.id.correo_usuario);
        numero=view.findViewById(R.id.telefono_usu);
        Datos_Usuario conex = new Datos_Usuario(getActivity(), "DBUsuario", null, 2);
        SQLiteDatabase db = conex.getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT Numero,Correo,Password FROM Perfil", null);
try{
    while (cursor.moveToNext()) {
        nombree=cursor.getString(0);
        correoo=cursor.getString(1);
        numeroo=cursor.getString(2);

    }
}catch (Exception e){

}

for(int x=0;x<=nombree.length();x++){
    nombre_corto=nombre_corto+nombree.substring(0,x);
    if (nombre_corto.equals(nombre_corto+" ")) {
        break;
    }
}
nombre.setText(nombre_corto);
correo.setText(correoo);
numero.setText(numeroo);
        return view;
    }

}
