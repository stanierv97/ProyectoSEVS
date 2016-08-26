package com.facci.proyectosevs;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.facci.proyectosevs.DBhelper;

public class MainActivitySEVS extends AppCompatActivity  {

    DBhelper dbSQLITE;
    EditText Nombre;
    EditText Apellido;
    EditText Recinto;
    EditText Nacimiento;
    EditText ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_sevs);

        dbSQLITE = new DBhelper(this);
    }
    public void InsetarBtn (View v) {
        Nombre = (EditText) findViewById(R.id.txtNombre);
        Apellido = (EditText) findViewById(R.id.txtApellido);
        Recinto = (EditText) findViewById(R.id.txtRecintoElectoral);
        Nacimiento = (EditText) findViewById(R.id.txtanoNacimiento);

        boolean Insertadatos = dbSQLITE.Insertar(Nombre.getText().toString(),Apellido.getText().toString(),Recinto.getText().toString(),Integer.parseInt(Nacimiento.getText().toString()));

        if (Insertadatos) {
            Toast.makeText(MainActivitySEVS.this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(MainActivitySEVS.this,"Datos no Ingresados ocurrio un error",Toast.LENGTH_SHORT).show();}
    }

    public void VerTodos (View v) {
        Cursor res = dbSQLITE.VerTodos();

        if (res.getCount() == 0) {
            Mensaje("Error","No se encontraron registros");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año de Nacimiento : "+res.getInt(4)+"\n\n");
        }
        Mensaje("Registros",buffer.toString());
    }

    private void Mensaje (String titulo, String Mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }

    public void Modificar (View v) {
        Nombre = (EditText) findViewById(R.id.txtNombre);
        Apellido = (EditText) findViewById(R.id.txtApellido);
        Recinto = (EditText) findViewById(R.id.txtRecintoElectoral);
        Nacimiento = (EditText) findViewById(R.id.txtanoNacimiento);
        ID = (EditText) findViewById(R.id.txtID);


        boolean ActualizandoDatos = dbSQLITE.ModificarRegistro(ID.getText().toString(),Nombre.getText().toString(),Apellido.getText().toString(),Recinto.getText().toString(),Integer.parseInt(Nacimiento.getText().toString()));
        if(ActualizandoDatos)
            Toast.makeText(MainActivitySEVS.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivitySEVS.this,"Lo sentimos ocurrió un error",Toast.LENGTH_SHORT).show();

    }

    public void Eliminar (View v) {
        ID = (EditText) findViewById(R.id.txtID);

        Integer registrosEliminados = dbSQLITE.Eliminar(ID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivitySEVS.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivitySEVS.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }
    }



}