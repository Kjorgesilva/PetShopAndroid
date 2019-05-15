package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.pethealth.Model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EnderecoDAO(Context contexto) {
        databaseHelper = new DatabaseHelper(contexto);
    }

    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(Endereco cadastro) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id_endereco", cadastro.getId());
        contentValues.put("rua_endereco", cadastro.getRua());
        contentValues.put("cidade_endereco", cadastro.getCidade());
        contentValues.put("bairro_endereco", cadastro.getBairro());
        contentValues.put("estado_endereco", cadastro.getEstado());
        Log.e("tamanho", "Lista Tamanho" + cadastro.getRua());


        return getDabase().insert("endereco", null, contentValues);
    }

    public List<Endereco> findAllEndereco() {
        Log.e("tamanho", "Lista Tamanho");
        List<Endereco> listarTodosEndereco = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM endereco ORDER BY _id_endereco", null);
        try {
            while (cursor.moveToNext()) {
                Endereco endereco = new Endereco();
                endereco.setId(cursor.getInt(cursor.getColumnIndex("_id_endereco")));
                endereco.setRua((cursor.getString(cursor.getColumnIndex("rua_endereco"))));
                endereco.setCidade((cursor.getString(cursor.getColumnIndex("cidade_endereco"))));
                endereco.setBairro((cursor.getString(cursor.getColumnIndex("bairro_endereco"))));
                endereco.setEstado((cursor.getString(cursor.getColumnIndex("estado_endereco"))));
                listarTodosEndereco.add(endereco);

                Log.e("tamanho", "Lista Tamanho" + listarTodosEndereco.size());

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tamanho", "catch");
        } finally {
            cursor.close();
        }


        return listarTodosEndereco;
    }

    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        db.execSQL(String.format("DELETE FROM %s", "endereco"));
        db.execSQL("VACUUM");
    }

}
