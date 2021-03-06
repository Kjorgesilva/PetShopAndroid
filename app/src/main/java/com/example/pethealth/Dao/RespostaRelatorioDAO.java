package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pethealth.Model.RespostaRelatorio;

import java.util.ArrayList;
import java.util.List;

public class RespostaRelatorioDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public RespostaRelatorioDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(RespostaRelatorio respostaRelatorio){
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id_relatorio", respostaRelatorio.getId());
        contentValues.put("id_agenda_relatorio", respostaRelatorio.getIdAgenda());
        contentValues.put("resposta_relatorio",respostaRelatorio.getResposta());
        contentValues.put("id_pergunta_relatorio", respostaRelatorio.getRelatoriosPergunta());
        contentValues.put("id_cliente_relatorio",respostaRelatorio.getIdCliente());
        contentValues.put("id_medico_relatorio",respostaRelatorio.getRelatoriosMedcio());
        contentValues.put("id_animal_relatorio",respostaRelatorio.getRelatoriosAnimal());


        Log.e("listar", "inserio: " + contentValues.size());


        return getDabase().insert("respostaRelatorio", null, contentValues);
    }

    public List<RespostaRelatorio> ListarBanco(int idCliente) {
        List<RespostaRelatorio> listarTodosOsElementos = new ArrayList<RespostaRelatorio>();
        // fazer um inner join
//        Cursor cursor = getDabase().rawQuery("SELECT con._id_relatorio , con.id_animal_relatorio, con.id_cliente_relatorio, con.id_medico_relatorio,con.id_pergunta_relatorio,resposta_relatorio," +
//                " ani.nome_animal, ani.raca_animal, ani.cor_animal, ani.dataNascimento_animal, ani.sexo_animal, ani.paisOrigem_animal, ani.observacoes_animal,cli.nome_cliente, cli.rg_cliente, cli.endereco_cliente, cli.telefone_cliente, cli.email_cliente ," +
//                " per.descricao_relatorio, med.nome_medico, med.telefone_medico, med.email_medico" +
//                " FROM respostaRelatorio con" +
//                " INNER JOIN animal ani ON con.id_animal_relatorio = ani._id_animal " +
//                " INNER JOIN cliente cli ON con.id_cliente_relatorio = cli._id_cliente " +
//                " INNER JOIN perguntaRelatorio per ON con.id_pergunta_relatorio = per._id_pergunta " +
//                " INNER JOIN medico med ON con.id_medico_relatorio = med._id_medico " +
//                " ORDER BY con._id_relatorio", null);

        String args[] = {String.valueOf(idCliente)};
        Cursor cursor = getDabase().rawQuery("SELECT * FROM respostaRelatorio" +
                " WHERE id_cliente_relatorio = ? ORDER BY _id_relatorio", args);
        while (cursor.moveToNext()) {
            RespostaRelatorio listarCadastro = new RespostaRelatorio();

//            Animal listAnimal = new Animal();
//            Cliente listCliente = new Cliente();
//            Medico listMedico = new Medico();
//            PerguntaRelatorio listaPergunta = new PerguntaRelatorio();
//
//            //Animal
//            listAnimal.setId(cursor.getInt(cursor.getColumnIndex("id_animal_relatorio")));
//            listAnimal.setNome(cursor.getString(cursor.getColumnIndex("nome_animal")));
//            listAnimal.setCor(cursor.getString(cursor.getColumnIndex("cor_animal")));
//            listAnimal.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento_animal")));
//            listAnimal.setObservacoes(cursor.getString(cursor.getColumnIndex("observacoes_animal")));
//            listAnimal.setPaisOrigem(cursor.getString(cursor.getColumnIndex("paisOrigem_animal")));
//            listAnimal.setRaca(cursor.getString(cursor.getColumnIndex("raca_animal")));
//            listAnimal.setSexo(cursor.getString(cursor.getColumnIndex("sexo_animal")));
//
//
//            //Cliente
//            listCliente.setId(cursor.getInt(cursor.getColumnIndex("id_cliente_relatorio")));
//            listCliente.setNome(cursor.getString(cursor.getColumnIndex("nome_cliente")));
//            listCliente.setEmail(cursor.getString(cursor.getColumnIndex("email_cliente")));
//            listCliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco_cliente")));
//            listCliente.setRg(cursor.getString(cursor.getColumnIndex("rg_cliente")));
//            listCliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone_cliente")));
//
//
//            //Medico
//            listMedico.setId(cursor.getInt(cursor.getColumnIndex("id_medico_relatorio")));
//            listMedico.setNome(cursor.getString(cursor.getColumnIndex("nome_medico")));
//            listMedico.setEmail(cursor.getString(cursor.getColumnIndex("email_medico")));
//            listMedico.setTelefone(cursor.getString(cursor.getColumnIndex("telefone_medico")));
//
//
//            //Pergunta Relatorio
//            listaPergunta.setId(cursor.getInt(cursor.getColumnIndex("_id_pergunta")));
//            listaPergunta.setDescricao(cursor.getString(cursor.getColumnIndex("descricao_relatorio")));


            listarCadastro.setIdAgenda(cursor.getInt(cursor.getColumnIndex("id_agenda_relatorio")));
            listarCadastro.setId(cursor.getInt(cursor.getColumnIndex("_id_relatorio")));
            listarCadastro.setRelatoriosAnimal(cursor.getString(cursor.getColumnIndex("id_animal_relatorio")));
            listarCadastro.setIdCliente(cursor.getInt(cursor.getColumnIndex("id_cliente_relatorio")));
            listarCadastro.setRelatoriosMedcio(cursor.getString(cursor.getColumnIndex("id_medico_relatorio")));
            listarCadastro.setRelatoriosPergunta(cursor.getString(cursor.getColumnIndex("id_pergunta_relatorio")));
            listarCadastro.setResposta((cursor.getString(cursor.getColumnIndex("resposta_relatorio"))));


            listarTodosOsElementos.add(listarCadastro);
//            Log.e("tamanhoDaListaRelatorio", "tamanho : " + listarTodosOsElementos.size());

//            for (int i = 0 ; i <listarTodosOsElementos.size();i++) {
//                Log.e("tamanhoDaListaRelatorio", "idAgenda : " + listarTodosOsElementos.get(i).getResposta());
//            }
        }

        cursor.close();

        return listarTodosOsElementos;
    }


    public List<RespostaRelatorio> findByIdAgenga(int idAgenda) {
        List<RespostaRelatorio> listarTodosOsElementos = new ArrayList<RespostaRelatorio>();
        // fazer um inner join
//        Cursor cursor = getDabase().rawQuery("SELECT con._id_relatorio , con.id_animal_relatorio, con.id_cliente_relatorio, con.id_medico_relatorio,con.id_pergunta_relatorio,resposta_relatorio," +
//                " ani.nome_animal, ani.raca_animal, ani.cor_animal, ani.dataNascimento_animal, ani.sexo_animal, ani.paisOrigem_animal, ani.observacoes_animal,cli.nome_cliente, cli.rg_cliente, cli.endereco_cliente, cli.telefone_cliente, cli.email_cliente ," +
//                " per.descricao_relatorio, med.nome_medico, med.telefone_medico, med.email_medico" +
//                " FROM respostaRelatorio con" +
//                " INNER JOIN animal ani ON con.id_animal_relatorio = ani._id_animal " +
//                " INNER JOIN cliente cli ON con.id_cliente_relatorio = cli._id_cliente " +
//                " INNER JOIN perguntaRelatorio per ON con.id_pergunta_relatorio = per._id_pergunta " +
//                " INNER JOIN medico med ON con.id_medico_relatorio = med._id_medico " +
//                " ORDER BY con._id_relatorio", null);

        String args[] = {String.valueOf(idAgenda)};
        Cursor cursor = getDabase().rawQuery("SELECT * FROM respostaRelatorio" +
                " WHERE id_agenda_relatorio = ? ORDER BY _id_relatorio", args);
        while (cursor.moveToNext()) {
            RespostaRelatorio listarCadastro = new RespostaRelatorio();

//            Animal listAnimal = new Animal();
//            Cliente listCliente = new Cliente();
//            Medico listMedico = new Medico();
//            PerguntaRelatorio listaPergunta = new PerguntaRelatorio();
//
//            //Animal
//            listAnimal.setId(cursor.getInt(cursor.getColumnIndex("id_animal_relatorio")));
//            listAnimal.setNome(cursor.getString(cursor.getColumnIndex("nome_animal")));
//            listAnimal.setCor(cursor.getString(cursor.getColumnIndex("cor_animal")));
//            listAnimal.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento_animal")));
//            listAnimal.setObservacoes(cursor.getString(cursor.getColumnIndex("observacoes_animal")));
//            listAnimal.setPaisOrigem(cursor.getString(cursor.getColumnIndex("paisOrigem_animal")));
//            listAnimal.setRaca(cursor.getString(cursor.getColumnIndex("raca_animal")));
//            listAnimal.setSexo(cursor.getString(cursor.getColumnIndex("sexo_animal")));
//
//
//            //Cliente
//            listCliente.setId(cursor.getInt(cursor.getColumnIndex("id_cliente_relatorio")));
//            listCliente.setNome(cursor.getString(cursor.getColumnIndex("nome_cliente")));
//            listCliente.setEmail(cursor.getString(cursor.getColumnIndex("email_cliente")));
//            listCliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco_cliente")));
//            listCliente.setRg(cursor.getString(cursor.getColumnIndex("rg_cliente")));
//            listCliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone_cliente")));
//
//
//            //Medico
//            listMedico.setId(cursor.getInt(cursor.getColumnIndex("id_medico_relatorio")));
//            listMedico.setNome(cursor.getString(cursor.getColumnIndex("nome_medico")));
//            listMedico.setEmail(cursor.getString(cursor.getColumnIndex("email_medico")));
//            listMedico.setTelefone(cursor.getString(cursor.getColumnIndex("telefone_medico")));
//
//
//            //Pergunta Relatorio
//            listaPergunta.setId(cursor.getInt(cursor.getColumnIndex("_id_pergunta")));
//            listaPergunta.setDescricao(cursor.getString(cursor.getColumnIndex("descricao_relatorio")));


            listarCadastro.setIdAgenda(cursor.getInt(cursor.getColumnIndex("id_agenda_relatorio")));
            listarCadastro.setId(cursor.getInt(cursor.getColumnIndex("_id_relatorio")));
            listarCadastro.setRelatoriosAnimal(cursor.getString(cursor.getColumnIndex("id_animal_relatorio")));
            listarCadastro.setIdCliente(cursor.getInt(cursor.getColumnIndex("id_cliente_relatorio")));
            listarCadastro.setRelatoriosMedcio(cursor.getString(cursor.getColumnIndex("id_medico_relatorio")));
            listarCadastro.setRelatoriosPergunta(cursor.getString(cursor.getColumnIndex("id_pergunta_relatorio")));
            listarCadastro.setResposta((cursor.getString(cursor.getColumnIndex("resposta_relatorio"))));


            listarTodosOsElementos.add(listarCadastro);
//            Log.e("tamanhoDaListaRelatorio", "tamanho : " + listarTodosOsElementos.size());

//            for (int i = 0 ; i <listarTodosOsElementos.size();i++) {
//                Log.e("tamanhoDaListaRelatorio", "idAgenda : " + listarTodosOsElementos.get(i).getResposta());
//            }
        }

        cursor.close();

        return listarTodosOsElementos;
    }


    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        db.execSQL(String.format("DELETE FROM %s", "respostaRelatorio"));
        db.execSQL("VACUUM");
    }

}
