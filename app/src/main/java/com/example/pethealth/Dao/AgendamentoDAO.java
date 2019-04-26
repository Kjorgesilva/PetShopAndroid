package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pethealth.Model.Agenda;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.Model.Cliente;
import com.example.pethealth.Model.Endereco;
import com.example.pethealth.Model.Medico;

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

    public long inserir(Agenda cadastro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("animal", cadastro.getAnimal().getNome());
        contentValues.put("cliente", cadastro.getCliente().getNome());
        contentValues.put("dataInicio", cadastro.getDataInicio());
        contentValues.put("endereco", cadastro.getEndereco().getRua());
        contentValues.put("dataFim", cadastro.getDataFim());
        contentValues.put("medico", cadastro.getMedico().getNome());

        return getDabase().insert("agenda", null, contentValues);
    }

    //


    public List<Agenda> ListarBanco() {
        List<Agenda> listarTodosOsElementos = new ArrayList<Agenda>();
        // fazer um inner join
        Cursor cursor = getDabase().rawQuery("SELECT con._id, con.id_animal, con.id_cliente, con.dataInicio,con.id_endereco , con.dataFim, con.id_medico ," +
                "ani.nome, ani.raca, ani.cor, ani.dataNascimento, ani.sexo, ani.paisOrigem, ani.observacoes, cli.nome, cli.rg, cli.endereco, cli.telefone, cli.email ," +
                "ende.rua, ende.cidade, ende.bairro, ende.estado, med.nome, med.telefone, med.email" +
                " FROM agenda con" +
                " INNER JOIN animal ani ON con.id_animal = ani._id " +
                " INNER JOIN cliente cli ON con.id_cliente = cli._id " +
                " INNER JOIN endereco ende ON con.id_endereco = ende._id " +
                " INNER JOIN medico med ON con.id_medico = med._id " +
                " ORDER BY con._id", null);
        while (cursor.moveToNext()) {
            Agenda listarCadastro = new Agenda();
            Animal listAnimal = new Animal();
            Cliente listCliente = new Cliente();
            Endereco listEndereco = new Endereco();
            Medico listMedico = new Medico();

            //Animal
            listAnimal.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listAnimal.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            listAnimal.setCor(cursor.getString(cursor.getColumnIndex("cor")));
            listAnimal.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento")));
            listAnimal.setObservacoes(cursor.getString(cursor.getColumnIndex("observacoes")));
            listAnimal.setPaisOrigem(cursor.getString(cursor.getColumnIndex("paisOrigem")));
            listAnimal.setRaca(cursor.getString(cursor.getColumnIndex("raca")));
            listAnimal.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));

            //Cliente
            listCliente.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listCliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            listCliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            listCliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            listCliente.setRg(cursor.getString(cursor.getColumnIndex("rg")));
            listCliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));


            //Endereco
            listEndereco.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listEndereco.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
            listEndereco.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
            listEndereco.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
            listEndereco.setRua(cursor.getString(cursor.getColumnIndex("rua")));


            //Medico
            listMedico.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listMedico.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            listMedico.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            listMedico.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));








            listarCadastro.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listarCadastro.setAnimal(listAnimal);
            listarCadastro.setCliente(listCliente);
            listarCadastro.setEndereco(listEndereco);
            listarCadastro.setMedico(listMedico);
            listarCadastro.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio")));
            listarCadastro.setDataFim((cursor.getString(cursor.getColumnIndex("dataFim"))));



            Log.e("nome", "passou : " + listarCadastro.getAnimal() + String.valueOf(listarCadastro.getId()));

            listarTodosOsElementos.add(listarCadastro);
        }

        cursor.close();

        Log.e("listar", "foi " + listarTodosOsElementos.size());
        return listarTodosOsElementos;
    }

//    public long update(Agenda agendamento) {
//
//        ContentValues values = new ContentValues();
//        values.put("nome", agendamento.getNome());
//        values.put("dataInicio", agendamento.getDataInicio());
//        values.put("endereco", agendamento.getEndereco());
//        values.put("medico", agendamento.getMedico());
//        values.put("dataFim", agendamento.getDataFim());
//
//        Log.e("pass", "update: " + agendamento.getNome());
//
//        return getDabase().update("agenda", values, "_id = ?", new String[]{String.valueOf(agendamento.getId())});
//
//    }

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
