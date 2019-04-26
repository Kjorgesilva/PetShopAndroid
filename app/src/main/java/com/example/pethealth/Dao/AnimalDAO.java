package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pethealth.Model.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public AnimalDAO(Context contexto) {
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

    public List<Animal> findAllAnimal() {
        List<Animal> listarTodosMedicos = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM animal ORDER BY _id", null);
        while (cursor.moveToNext()) {
            Animal animal = new Animal();

//            private Cliente cliente;
//            private EspecieAnimal idEspecieAnimal;

            animal.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            animal.setNome((cursor.getString(cursor.getColumnIndex("nome"))));
            animal.setRaca(cursor.getString(cursor.getColumnIndex("raca")));
            animal.setCor(cursor.getString(cursor.getColumnIndex("cor")));
            animal.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento")));
            animal.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
            animal.setPaisOrigem(cursor.getString(cursor.getColumnIndex("paisOrigem")));
            animal.setObservacoes(cursor.getString(cursor.getColumnIndex("observacoes")));


            listarTodosMedicos.add(animal);
        }
        cursor.close();
        return listarTodosMedicos;
    }

}
