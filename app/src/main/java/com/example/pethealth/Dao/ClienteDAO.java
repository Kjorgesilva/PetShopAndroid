package com.example.pethealth.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pethealth.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO  {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;



    public ClienteDAO (Context contexto){
        databaseHelper = new DatabaseHelper(contexto);
    }

    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }


    public long inserir(Cliente cadastro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome_cliente", cadastro.getNome());
        contentValues.put("rg_cliente",cadastro.getRg());
        contentValues.put("endereco_cliente", cadastro.getEndereco());
        contentValues.put("telefone_cliente", cadastro.getTelefone());
        contentValues.put("email_cliente", cadastro.getEmail());
        contentValues.put("idUsuario_cliente", cadastro.getIdUsuario());
        Log.e("teste","teste"+ cadastro.getNome());



        return getDabase().insert("cliente", null, contentValues);

    }

    public List<Cliente> findAllCliente() {
        List<Cliente> listarTodosCleinte = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM cliente ORDER BY _id_cliente", null);
        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente();

            cliente.setId(cursor.getInt(cursor.getColumnIndex("_id_cliente")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            cliente.setRg(cursor.getString(cursor.getColumnIndex("rg_cliente")));
            cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco_cliente")));
            cliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone_cliente")));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex("email_cliente")));
            cliente.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario_cliente")));

            listarTodosCleinte.add(cliente);
        }
        cursor.close();
        return listarTodosCleinte;
    }





    }
