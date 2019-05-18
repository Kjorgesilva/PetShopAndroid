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

    public long inserir(Animal animal) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id_animal",  animal.getId());
        contentValues.put("nome_animal", animal.getNome());
        contentValues.put("raca_animal", animal.getRaca());
        contentValues.put("cor_animal", animal.getCor());
        contentValues.put("dataNascimento_animal", animal.getDataNascimento());
        contentValues.put("paisOrigem_animal", animal.getPaisOrigem());
        contentValues.put("observacoes_animal", animal.getObservacoes());
        contentValues.put("idCliente_animal", animal.getIdCliente());
        contentValues.put("idEspecieAnimal_animal", animal.getIdEspecieAnimal());

        return getDabase().insert("animal", null, contentValues);
    }

    public List<Animal> findAllAnimal(int idCliente) {
        List<Animal> listarTodosAnimal = new ArrayList<>();
        String[] args = {String.valueOf(idCliente)};
        Cursor cursor = getDabase().rawQuery("SELECT * FROM animal " +
                "WHERE idCliente_animal = ? order by _id_animal", args);
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
