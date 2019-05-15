package com.example.pethealth.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pethealth.Model.Agenda;
import com.example.pethealth.Model.Animal;
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
        contentValues.put("id_animal_agenda", cadastro.getAnimal().getId());
        contentValues.put("id_cliente_agenda", cadastro.getIdCliente());
        contentValues.put("dataInicio_agenda", cadastro.getDataInicio());
        contentValues.put("id_endereco_agenda", cadastro.getEndereco().getId());
        contentValues.put("dataFim_agenda", cadastro.getDataFim());
        contentValues.put("id_medico_agenda", cadastro.getMedico().getId());

        Log.e("Nome: ", "Texto: " + cadastro.getAnimal().getNome());

        return getDabase().insert("agenda", null, contentValues);
    }


    public List<Agenda> ListarBanco(int idCliente) {
        List<Agenda> listarTodosOsElementos = new ArrayList<Agenda>();
        // fazer um inner join
        String[] args = {String.valueOf(idCliente)};
        Cursor cursor = getDabase().rawQuery("SELECT con._id_agenda, con.id_animal_agenda, con.id_cliente_agenda, con.dataInicio_agenda,con.id_endereco_agenda , con.dataFim_agenda, con.id_medico_agenda ," +
                "ani.nome_animal, ani.raca_animal, ani.cor_animal, ani.dataNascimento_animal, ani.sexo_animal, ani.paisOrigem_animal, ani.observacoes_animal," +
                "ende.rua_endereco, ende.cidade_endereco, ende.bairro_endereco, ende.estado_endereco, med.nome_medico, med.telefone_medico, med.email_medico" +
                " FROM agenda con" +
                " INNER JOIN animal ani ON con.id_animal_agenda = ani._id_animal " +
                " INNER JOIN endereco ende ON con.id_endereco_agenda = ende._id_endereco " +
                " INNER JOIN medico med ON con.id_medico_agenda = med._id_medico  WHERE id_cliente_agenda = ?" +
                " ORDER BY con._id_agenda", args);
        while (cursor.moveToNext()) {
            Agenda listarCadastro = new Agenda();
            Animal listAnimal = new Animal();
            Endereco listEndereco = new Endereco();
            Medico listMedico = new Medico();

            //Animal
            listAnimal.setId(cursor.getInt(cursor.getColumnIndex("id_animal_agenda")));
            listAnimal.setNome(cursor.getString(cursor.getColumnIndex("nome_animal")));
            listAnimal.setCor(cursor.getString(cursor.getColumnIndex("cor_animal")));
            listAnimal.setDataNascimento(cursor.getString(cursor.getColumnIndex("dataNascimento_animal")));
            listAnimal.setObservacoes(cursor.getString(cursor.getColumnIndex("observacoes_animal")));
            listAnimal.setPaisOrigem(cursor.getString(cursor.getColumnIndex("paisOrigem_animal")));
            listAnimal.setRaca(cursor.getString(cursor.getColumnIndex("raca_animal")));
            listAnimal.setSexo(cursor.getString(cursor.getColumnIndex("sexo_animal")));

            Log.e("nome", "passou : " + listAnimal.getNome());
            Log.e("nome", "passou : " + listAnimal.getId());

            //Endereco
            listEndereco.setId(cursor.getInt(cursor.getColumnIndex("id_endereco_agenda")));
            listEndereco.setBairro(cursor.getString(cursor.getColumnIndex("bairro_endereco")));
            listEndereco.setCidade(cursor.getString(cursor.getColumnIndex("cidade_endereco")));
            listEndereco.setEstado(cursor.getString(cursor.getColumnIndex("estado_endereco")));
            listEndereco.setRua(cursor.getString(cursor.getColumnIndex("rua_endereco")));


            //Medico
            listMedico.setId(cursor.getInt(cursor.getColumnIndex("id_medico_agenda")));
            listMedico.setNome(cursor.getString(cursor.getColumnIndex("nome_medico")));
            listMedico.setEmail(cursor.getString(cursor.getColumnIndex("email_medico")));
            listMedico.setTelefone(cursor.getString(cursor.getColumnIndex("telefone_medico")));



            listarCadastro.setId(cursor.getInt(cursor.getColumnIndex("_id_agenda")));
            listarCadastro.setAnimal(listAnimal);
            listarCadastro.setEndereco(listEndereco);
            listarCadastro.setMedico(listMedico);
            listarCadastro.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio_agenda")));
            listarCadastro.setDataFim((cursor.getString(cursor.getColumnIndex("dataFim_agenda"))));



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

       // Log.e("passouAqui", "Delete" + ListarBanco().size());
    }


    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        Log.e("teste", "passou aqui");
        db.execSQL(String.format("DELETE FROM %s", "agenda"));
        db.execSQL("VACUUM");
    }


}
