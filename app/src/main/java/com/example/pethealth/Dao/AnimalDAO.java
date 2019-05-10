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

        contentValues.put("nome_animal", cadastro.getNome());
        contentValues.put("raca_animal", cadastro.getRaca());
        contentValues.put("cor_animal", cadastro.getCor());
        contentValues.put("dataNascimento_animal", cadastro.getDataNascimento());
        contentValues.put("paisOrigem_animal", cadastro.getPaisOrigem());
        contentValues.put("observacoes_animal", cadastro.getObservacoes());
        contentValues.put("idCliente_animal", cadastro.getIdCliente());
        contentValues.put("idEspecieAnimal_animal", cadastro.getIdEspecieAnimal());

        return getDabase().insert("animal", null, contentValues);
    }

    public List<Animal> findAllAnimal() {
        List<Animal> listarTodosAnimal = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM animal ORDER BY _id_animal", null);
        while (cursor.moveToNext()) {
            Animal animal = new Animal();

            animal.setId(cursor.getInt(cursor.getColumnIndex("_id_animal")));
            animal.setNome((cursor.getString(cursor.getColumnIndex("nome_animal"))));
            animal.setRaca(cursor.getString(cursor.getColumnIndex("raca_animal")));
            animal.setCor(cursor.getString(cursor.getColumnIndex("cor_animal")));
            animal.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento_animal")));
            animal.setSexo(cursor.getString(cursor.getColumnIndex("sexo_animal")));
            animal.setPaisOrigem(cursor.getString(cursor.getColumnIndex("paisOrigem_animal")));
            animal.setObservacoes(cursor.getString(cursor.getColumnIndex("observacoes_animal")));
            animal.setIdCliente(cursor.getInt(cursor.getColumnIndex("idCliente_animal")));
            animal.setIdEspecieAnimal(cursor.getInt(cursor.getColumnIndex("idEspecieAnimal_animal")));


            listarTodosAnimal.add(animal);
        }
        cursor.close();
        return listarTodosAnimal;
    }

    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        db.execSQL(String.format("DELETE FROM %s", "animal"));
        db.execSQL("VACUUM");
    }


}
