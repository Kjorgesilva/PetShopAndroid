package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pethealth.Model.Animal;
import com.example.pethealth.Model.TipoVacinas;
import com.example.pethealth.Model.Vacinas;

import java.util.ArrayList;
import java.util.List;

public class VacinasDAO {


    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    public VacinasDAO(Context contexto) {
        databaseHelper = new DatabaseHelper(contexto);
    }

    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }


    public long inserir(Vacinas cadastro) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id", cadastro.getId());
        contentValues.put("aviso", cadastro.getAviso());
        contentValues.put("dataVacina", cadastro.getDataVacina());
        contentValues.put("dataDaProxima", cadastro.getDataDaProxima());
        contentValues.put("idAnimal", cadastro.getIdAnimal());
        contentValues.put("idTipoVacina", cadastro.getIdTipoVacina());
        contentValues.put("nomeAnimal", cadastro.getNomeAnimal());
        contentValues.put("nomeVacina", cadastro.getNomeVacina());


        return getDabase().insert("vacinas", null, contentValues);
    }


    public List<Vacinas> findAllVacinas() {
        List<Vacinas> listatodasVacinas = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM vacinas ORDER BY _id", null);

        while (cursor.moveToNext()) {
            Vacinas listaVacina = new Vacinas();


            listaVacina.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listaVacina.setAviso((cursor.getString(cursor.getColumnIndex("aviso"))));
            listaVacina.setDataVacina((cursor.getString(cursor.getColumnIndex("dataVacina"))));
            listaVacina.setDataDaProxima((cursor.getString(cursor.getColumnIndex("dataDaProxima"))));
            listaVacina.setIdAnimal(cursor.getInt(cursor.getColumnIndex("idAnimal")));
            listaVacina.setIdTipoVacina(cursor.getInt(cursor.getColumnIndex("idTipoVacina")));
            listaVacina.setNomeAnimal((cursor.getString(cursor.getColumnIndex("nomeAnimal"))));
            listaVacina.setNomeVacina((cursor.getString(cursor.getColumnIndex("nomeVacina"))));


            listatodasVacinas.add(listaVacina);


        }

        cursor.close();

        return listatodasVacinas;
    }


}
