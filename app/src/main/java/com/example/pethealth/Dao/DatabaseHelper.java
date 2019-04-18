package com.example.pethealth.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //nome do banco
    private static final String NOME_BANCO = "meuBanco";
    //versao do banco
    private static final int VERSAO_BANCO = 1;

    public DatabaseHelper(Context contexto){
        super(contexto,NOME_BANCO, null,VERSAO_BANCO);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE agenda(_id INTEGER PRIMARY KEY, nome TEXT , telefone TEXT, endereco TEXT, data TEXT,medico TEXT )");
        db.execSQL("CREATE TABLE vacinas(_id INTEGER PRIMARY KEY, aviso TEXT , dataVacina TEXT, dataDaProxima TEXT, idAnimal INTEGER, nomeVacina TEXT, nomeAnimal TEXT, idTipoVacina INTEGER )");
        db.execSQL("CREATE TABLE animal(_id INTEGER PRIMARY KEY, nome TEXT , raca TEXT, cor TEXT, dataNascimento TEXT, sexo TEXT, paisOrigem TEXT, observacoes TEXT, idCliente INTEGER, idEspecieAnimal INTEGER )");
        db.execSQL("CREATE TABLE tipoVacina(_id INTEGER PRIMARY KEY, descricao TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }


}
