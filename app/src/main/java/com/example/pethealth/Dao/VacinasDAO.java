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
        contentValues.put("aviso", cadastro.getAviso());
        contentValues.put("dataVacina", cadastro.getDataVacina());
        contentValues.put("dataDaProxima", cadastro.getDataDaProxima());
        contentValues.put("idAnimal", cadastro.getIdAnimal().getId());
        contentValues.put("idTipoVacina", cadastro.getIdTipoVacina().getId());

        return getDabase().insert("vacinas", null, contentValues);
    }


    public List<Vacinas> findAllVacinas() {
        List<Vacinas> listatodasVacinas = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery(

                "SELECT vac.id, vac.aviso, vac.dataVacina, vac.dataDaProxima,vac.idAnimal, vac.idTipoVacina"+
                        "a.id, a.nome, t.id, t.descricao "+
                        "FROM vacina vac" +
                        " INNER JOIN animal a ON vac.idAnimal = a.id " +
                        " INNER JOIN tipoVacina t ON vac.idTipoVacina = t.id " +
                        " ORDER BY _id", null);

        while (cursor.moveToNext()) {
            Vacinas listaVacina = new Vacinas();

            Animal animal = new Animal();

            TipoVacinas tipoVacinas = new TipoVacinas();
            listaVacina.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listaVacina.setAviso((cursor.getString(cursor.getColumnIndex("aviso"))));
            listaVacina.setDataVacina((cursor.getString(cursor.getColumnIndex("dataVacina"))));
            listaVacina.setDataDaProxima((cursor.getString(cursor.getColumnIndex("dataDaProxima"))));
            listaVacina.setIdAnimal(animal);
            listaVacina.setIdTipoVacina(tipoVacinas);


            listatodasVacinas.add(listaVacina);


        }

        return listatodasVacinas;
    }


}
