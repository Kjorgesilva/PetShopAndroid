package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pethealth.Model.TipoVacinas;

public class TipoVacinaDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public void TipoVacinaDAO(Context contexto) {
        databaseHelper = new DatabaseHelper(contexto);

    }


    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(TipoVacinas cadastro) {
        ContentValues contentValues = new ContentValues();
  //        db.execSQL("CREATE TABLE tipoVacina(_id INTEGER PRIMARY KEY, descricao TEXT )");
        contentValues.put("descricao", cadastro.getDescricao());
        return getDabase().insert("tipoVacina", null, contentValues);
    }
}
