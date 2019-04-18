package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pethealth.Model.Consulta;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    public AgendamentoDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }


    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(Consulta cadastro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cadastro.getNome());
        contentValues.put("telefone", cadastro.getTelefone());
        contentValues.put("endereco", cadastro.getEndereco());
        contentValues.put("data", cadastro.getData());
        contentValues.put("medico", cadastro.getMedico());

        return getDabase().insert("agenda", null, contentValues);
    }

    //
    public List<Consulta> ListarBanco() {
        List<Consulta> listarTodosOsElementos = new ArrayList<Consulta>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM agenda ORDER BY _id", null);
        while (cursor.moveToNext()) {
            Consulta listarCadastro = new Consulta();
            listarCadastro.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listarCadastro.setNome((cursor.getString(cursor.getColumnIndex("nome"))));
            listarCadastro.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            listarCadastro.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            listarCadastro.setMedico((cursor.getString(cursor.getColumnIndex("medico"))));
            listarCadastro.setData((cursor.getString(cursor.getColumnIndex("data"))));


            Log.e("nome", "passou : " + listarCadastro.getNome() + String.valueOf(listarCadastro.getId()));

            listarTodosOsElementos.add(listarCadastro);
        }

        cursor.close();

        Log.e("listar", "foi " + listarTodosOsElementos.size());
        return listarTodosOsElementos;
    }

    public long update(Consulta agendamento) {

        ContentValues values = new ContentValues();
        values.put("nome", agendamento.getNome());
        values.put("telefone", agendamento.getTelefone());
        values.put("endereco", agendamento.getEndereco());
        values.put("medico", agendamento.getMedico());
        values.put("data", agendamento.getData());

        Log.e("pass", "update: " + agendamento.getNome());

        return getDabase().update("agenda", values, "_id = ?", new String[]{String.valueOf(agendamento.getId())});

    }

    public void delete(Integer id) {
        getDabase().delete("agenda", "_id = ?", new String[]{String.valueOf(id)});

        Log.e("passouAqui", "Delete" + ListarBanco().size());
    }


    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        Log.e("teste", "passou aqui");
        db.execSQL(String.format("DELETE FROM %s", "agenda"));
        db.execSQL("VACUUM");
    }


}
