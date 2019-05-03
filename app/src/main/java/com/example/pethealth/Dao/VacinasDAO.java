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

        contentValues.put("_id_vacinas", cadastro.getId());
        contentValues.put("aviso_vacinas", cadastro.getAviso());
        contentValues.put("dataVacina_vacinas", cadastro.getDataVacina());
        contentValues.put("dataDaProxima_vacinas", cadastro.getDataDaProxima());
        contentValues.put("idAnimal_vacinas", cadastro.getIdAnimal());
        contentValues.put("idTipoVacina_vacinas", cadastro.getIdTipoVacina());
        contentValues.put("nomeAnimal_vacinas", cadastro.getNomeAnimal());
        contentValues.put("nomeVacina_vacinas", cadastro.getNomeVacina());


        return getDabase().insert("vacinas", null, contentValues);
    }


    public List<Vacinas> findAllVacinas() {
        List<Vacinas> listatodasVacinas = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM vacinas ORDER BY _id_vacinas", null);

        while (cursor.moveToNext()) {
            Vacinas listaVacina = new Vacinas();


            listaVacina.setId(cursor.getInt(cursor.getColumnIndex("_id_vacinas")));
            listaVacina.setAviso((cursor.getString(cursor.getColumnIndex("aviso_vacinas"))));
            listaVacina.setDataVacina((cursor.getString(cursor.getColumnIndex("dataVacina_vacinas"))));
            listaVacina.setDataDaProxima((cursor.getString(cursor.getColumnIndex("dataDaProxima_vacinas"))));
            listaVacina.setIdAnimal(cursor.getInt(cursor.getColumnIndex("idAnimal_vacinas")));
            listaVacina.setIdTipoVacina(cursor.getInt(cursor.getColumnIndex("idTipoVacina_vacinas")));
            listaVacina.setNomeAnimal((cursor.getString(cursor.getColumnIndex("nomeAnimal_vacinas"))));
            listaVacina.setNomeVacina((cursor.getString(cursor.getColumnIndex("nomeVacina_vacinas"))));


            listatodasVacinas.add(listaVacina);


        }

        cursor.close();

        return listatodasVacinas;
    }


}
