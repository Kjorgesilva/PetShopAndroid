package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.pethealth.Model.Medico;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MedicoDAO (Context contexto){
        databaseHelper = new DatabaseHelper(contexto);
    }

    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }
    public long inserir(Medico cadastro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome_medico", cadastro.getNome());
        contentValues.put("telefone_medico", cadastro.getTelefone());
        contentValues.put("email_medico", cadastro.getEmail());
        contentValues.put("idUsuario_medico",cadastro.getIdUsuario());
        return getDabase().insert("medico", null, contentValues);
    }
    public List<Medico> findAllMedico() {
        List<Medico> listarTodosMedicos = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM medico ORDER BY _id_medico", null);
        while (cursor.moveToNext()) {
            Medico medico = new Medico();
            medico.setId(cursor.getInt(cursor.getColumnIndex("_id_medico")));
            medico.setNome((cursor.getString(cursor.getColumnIndex("nome_medico"))));
            medico.setTelefone((cursor.getString(cursor.getColumnIndex("telefone_medico"))));
            medico.setEmail((cursor.getString(cursor.getColumnIndex("email_medico"))));
            medico.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario_medico")));
            listarTodosMedicos.add(medico);
        }
        cursor.close();
        return listarTodosMedicos;
    }


}
