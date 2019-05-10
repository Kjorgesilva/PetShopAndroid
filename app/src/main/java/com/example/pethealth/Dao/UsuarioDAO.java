package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pethealth.Model.Endereco;
import com.example.pethealth.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public UsuarioDAO(Context contexto) {
        databaseHelper = new DatabaseHelper(contexto);
    }

    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(Usuario cadastro) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("login_usuario", cadastro.getLogin());
        contentValues.put("senha_usuario", cadastro.getSenha());
        contentValues.put("nome_usuario", cadastro.getNome());
        contentValues.put("email_usuario", cadastro.getEmail());
        contentValues.put("idPerfil_usuario", cadastro.getIdPerfil());
        contentValues.put("idCliente_usuario", cadastro.getIdCliente());


        Log.e("tamanho", "Lista Tamanho");


        return getDabase().insert("usuario", null, contentValues);
    }

    public Usuario findAllUsuario() {
        Log.e("tamanho", "Lista Tamanho");
        Usuario usuario = new Usuario();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM usuario ORDER BY _id_usuario", null);
        try {
            while (cursor.moveToNext()) {
                usuario.setId(cursor.getInt(cursor.getColumnIndex("_id_usuario")));
                usuario.setLogin((cursor.getString(cursor.getColumnIndex("login_usuario"))));
                usuario.setSenha((cursor.getString(cursor.getColumnIndex("senha_usuario"))));
                usuario.setNome((cursor.getString(cursor.getColumnIndex("nome_usuario"))));
                usuario.setEmail((cursor.getString(cursor.getColumnIndex("email_usuario"))));
                usuario.setIdPerfil((cursor.getInt(cursor.getColumnIndex("idPerfil_usuario"))));
                usuario.setIdCliente((cursor.getInt(cursor.getColumnIndex("idCliente_usuario"))));

                Log.e("tamanho", "Lista Tamanho");

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tamanho", "catch");
        } finally {
            cursor.close();
        }


        return usuario;
    }


    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        db.execSQL(String.format("DELETE FROM %s", "usuario"));
        db.execSQL("VACUUM");
    }

}
