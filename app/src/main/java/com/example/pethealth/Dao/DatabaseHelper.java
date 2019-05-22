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



        db.execSQL("CREATE TABLE endereco(_id_endereco INTEGER PRIMARY KEY, rua_endereco TEXT, cidade_endereco TEXT,bairro_endereco TEXT, estado_endereco TEXT)");
        db.execSQL("CREATE TABLE usuario(_id_usuario INTEGER PRIMARY KEY, login_usuario TEXT, senha_usuario TEXT,nome_usuario TEXT, email_usuario TEXT,idPerfil_usuario INTEGER,idCliente_usuario INTEGER)");



        db.execSQL("CREATE TABLE vacinas(_id_vacinas INTEGER PRIMARY KEY, aviso_vacinas TEXT , dataVacina_vacinas TEXT, dataDaProxima_vacinas TEXT, idAnimal_vacinas INTEGER, nomeVacina_vacinas TEXT, nomeAnimal_vacinas TEXT, idTipoVacina_vacinas INTEGER )");
        db.execSQL("CREATE TABLE animal(_id_animal INTEGER PRIMARY KEY, nome_animal TEXT , raca_animal TEXT, cor_animal TEXT, dataNascimento_animal TEXT, sexo_animal TEXT, paisOrigem_animal TEXT, observacoes_animal TEXT, idCliente_animal INTEGER, idEspecieAnimal_animal INTEGER )");
        db.execSQL("CREATE TABLE tipoVacina(_id_tipoVacina INTEGER PRIMARY KEY, descricao_tipoVacina TEXT )");
        db.execSQL("CREATE TABLE medico(_id_medico INTEGER PRIMARY KEY, nome_medico TEXT, telefone_medico TEXT, email_medico TEXT, idUsuario_medico INTEGER )");

        db.execSQL("CREATE TABLE agenda(_id_agenda INTEGER PRIMARY KEY, id_animal_agenda INTEGER , id_cliente_agenda INTEGER, dataInicio_agenda TEXT, id_endereco_agenda INTEGER, dataFim_agenda TEXT , id_medico_agenda INTEGER, status_agendamento TEXT," +
                "FOREIGN KEY(id_animal_agenda) REFERENCES animal(_id_animal)," +
                "FOREIGN KEY(id_endereco_agenda) REFERENCES endereco(_id_endereco)," +
                "FOREIGN KEY(id_medico_agenda) REFERENCES medico(_id_medico))");

        db.execSQL("CREATE TABLE perguntaRelatorio(_id_pergunta INTEGER PRIMARY KEY, descricao_relatorio TEXT)");


        db.execSQL("CREATE TABLE respostaRelatorio(_id_relatorio INTEGER PRIMARY KEY, id_agenda_relatorio INTEGER, resposta_relatorio TEXT, id_pergunta_relatorio INTEGER, id_cliente_relatorio INTEGER, id_medico_relatorio INTEGER, id_animal_relatorio INTEGER)");





//        db.execSQL("CREATE TABLE respostaRelatorio(_id_relatorio INTEGER PRIMARY KEY, resposta_relatorio TEXT , id_pergunta_relatorio INTEGER, id_cliente_relatorio INTEGER, id_medico_relatorio INTEGER, id_animal_relatorio INTEGER," +
//                "FOREIGN KEY(id_pergunta_relatorio) REFERENCES perguntaRelatorio(_id_pergunta)," +
//                "FOREIGN KEY(id_cliente_relatorio) REFERENCES cliente(_id_cliente)," +
//                "FOREIGN KEY(id_medico_relatorio) REFERENCES medico(_id_medico)," +
//                "FOREIGN KEY(id_animal_relatorio) REFERENCES animal(_id_animal))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
