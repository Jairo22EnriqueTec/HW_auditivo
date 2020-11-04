package org.helloworld.auditivo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jj on 12/06/18.
 */

public class Datos_Usuario extends SQLiteOpenHelper {
    String tabla="CREATE TABLE Usuario(Nombre TEXT, Nacimiento TEXT,Discapacidad TEXT,Numero TEXT,Frase TEXT)";

    String Palabras_rapidas="CREATE TABLE PalabrasR (Frase TEXT)";
    String Primera_vez_en="CREATE TABLE Primeravezen (Auditivo TEXT,Comunicativo TEXT,Visual TEXT)";
    //String tabla_usuario2="CREATE TABLE Perfil (Nombre TEXT, Nacimiento TEXT,Numero TEXT,Frase TEXT,Correo TEXT,Password TEXT,TEXT Imagen)";
    String tabla_usuario3="CREATE TABLE Perfill (Nombre TEXT, Nacimiento TEXT,Numero TEXT,Frase TEXT,Correo TEXT,Password TEXT, Imagen TEXT)";

    public Datos_Usuario(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
        db.execSQL(Palabras_rapidas);
        db.execSQL(Primera_vez_en);
        db.execSQL(tabla_usuario3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Palabras_rapidas);
    }
}
