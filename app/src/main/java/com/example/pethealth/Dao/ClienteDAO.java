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
        contentValues.put("_id", cadastro.getId());
        contentValues.put("nome", cadastro.getNome());
        contentValues.put("rg",cadastro.getRg());
        contentValues.put("endereco", cadastro.getEndereco());
        contentValues.put("telefone", cadastro.getTelefone());
        contentValues.put("email", cadastro.getEmail());
        contentValues.put("idUsuario", cadastro.getIdUsuario());
        Log.e("teste","teste"+ cadastro.getNome());



        return getDabase().insert("cliente", null, contentValues);

    }

    public List<Cliente> findAllCliente() {
        List<Cliente> listarTodosCleinte = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM cliente ORDER BY _id", null);
        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente();

            cliente.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cliente.setRg(cursor.getString(cursor.getColumnIndex("rg")));
            cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            cliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            cliente.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario")));

            listarTodosCleinte.add(cliente);
        }
        cursor.close();
        return listarTodosCleinte;
    }





    }
