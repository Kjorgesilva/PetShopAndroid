package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pethealth.Model.Animal;

public class AnimalDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public void AnimalDAO(Context contexto) {
        databaseHelper = new DatabaseHelper(contexto);

    }


    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(Animal cadastro) {
        ContentValues contentValues = new ContentValues();
        //id , nome, raca, cor , dataNascimento, sexo , paisOrigem, observacoes , idCliente, idEspecieAnimal
        // FALTA CRIAR O IDCLIENTE E O IDESPECIELANIMAL
        contentValues.put("nome", cadastro.getNome());
        contentValues.put("raca", cadastro.getNome());
        contentValues.put("cor", cadastro.getNome());
        contentValues.put("dataNascimento", cadastro.getNome());
        contentValues.put("paisOrigem", cadastro.getNome());
        contentValues.put("observacoes", cadastro.getNome());
        return getDabase().insert("animal", null, contentValues);
    }

}
