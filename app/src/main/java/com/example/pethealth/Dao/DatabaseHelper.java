package com.example.pethealth.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //nome do banco
    private static final String NOME_BANCO = "meuBanco";
    //versao do banco
    private static final int VERSAO_BANCO = 1;

    public DatabaseHelper(Context contexto) {
        super(contexto, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }




    @Override
    public void onCreate(SQLiteDatabase db) {



        db.execSQL("CREATE TABLE cliente(_id INTEGER PRIMARY KEY, nome TEXT, rg TEXT,endereco TEXT, telefone TEXT, email TEXT, idUsuario INTEGER)");
        db.execSQL("CREATE TABLE endereco(_id INTEGER PRIMARY KEY, rua TEXT, cidade TEXT,bairro TEXT, estado TEXT)");


        db.execSQL("CREATE TABLE vacinas(_id INTEGER PRIMARY KEY, aviso TEXT , dataVacina TEXT, dataDaProxima TEXT, idAnimal INTEGER, nomeVacina TEXT, nomeAnimal TEXT, idTipoVacina INTEGER )");
        db.execSQL("CREATE TABLE animal(_id INTEGER PRIMARY KEY, nome TEXT , raca TEXT, cor TEXT, dataNascimento TEXT, sexo TEXT, paisOrigem TEXT, observacoes TEXT, idCliente INTEGER, idEspecieAnimal INTEGER )");
        db.execSQL("CREATE TABLE tipoVacina(_id INTEGER PRIMARY KEY, descricao TEXT )");
        db.execSQL("CREATE TABLE medico(_id INTEGER PRIMARY KEY, nome TEXT, telefone TEXT, email TEXT, idUsuario INTEGER )");

        db.execSQL("CREATE TABLE agenda(_id INTEGER PRIMARY KEY, id_animal INTEGER , id_cliente INTEGER, dataInicio TEXT, id_endereco INTEGER, dataFim TEXT , id_medico INTEGER," +
                "FOREIGN KEY(id_animal) REFERENCES animal(_id)," +
                "FOREIGN KEY(id_cliente) REFERENCES cliente(_id)," +
                "FOREIGN KEY(id_endereco) REFERENCES endereco(_id)," +
                "FOREIGN KEY(id_medico) REFERENCES medico(_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
