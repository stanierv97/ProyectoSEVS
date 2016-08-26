package com.facci.proyectosevs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import  android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    ////////// tabla
    public static final String DB_NOMBRE = "CNE_SEVS";
    public static final String TABLA_NOMBRE = "VOTANTES_SEVS";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "NOMBRE";
    public static final String Col_3 = "APELLIDO";
    public static final String Col_4 = "RECINTO_ELECTORAL";
    public static final String Col_5 = "AÑO_DE_NACIMIENTO";


    //construtor
    public DBhelper (Context context){///////, String name, SQLiteDatabase.CursorFactory factory, int version
        super(context, DB_NOMBRE, null,1); //la version cambirara cada que se haga un cambio
        SQLiteDatabase db = this.getWritableDatabase();

    }
    /// metodos propios de SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT, %s TEXT, %s INTEGER)",TABLA_NOMBRE, Col_2, Col_3, Col_4, Col_5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLA_NOMBRE));
        onCreate(db);

    }
    /////////////////

    public  boolean Insertar (String Nombre, String Apellido,String RecintoElectoral,int anoNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,Nombre);
        contentValues.put(Col_3,Apellido);
        contentValues.put(Col_4,RecintoElectoral);
        contentValues.put(Col_5,anoNacimiento);
        long Resultado = db.insert(TABLA_NOMBRE,null,contentValues);

        if (Resultado == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor VerTodos () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return res;
    }

    public boolean ModificarRegistro (String Id,String Nombre,String Apellido,String RecintoElectoral,int AnoNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,Nombre);
        contentValues.put(Col_3,Apellido);
        contentValues.put(Col_4,RecintoElectoral);
        contentValues.put(Col_5,AnoNacimiento);
        db.update(TABLA_NOMBRE,contentValues,"Id = ?",new String[]{Id});
        return true;
    }

    public Integer Eliminar (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"Id = ?",new String[]{Id});
    }


}
