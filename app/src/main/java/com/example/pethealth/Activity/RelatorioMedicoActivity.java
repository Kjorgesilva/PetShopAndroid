package com.example.pethealth.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.RespostaRelatorioDAO;
import com.example.pethealth.InterfaceHelp.InterfaceHelp;
import com.example.pethealth.Model.RespostaRelatorio;
import com.example.pethealth.R;
import com.example.pethealth.WebService.RespostaRelatorioWs;

import java.util.ArrayList;
import java.util.List;

public class RelatorioMedicoActivity extends AppCompatActivity {
    private Context context = this;
    private List<RespostaRelatorio> listaResposta = new ArrayList<>();
    private RespostaRelatorioDAO dao = new RespostaRelatorioDAO(context);
    private TextView txt_hidratação, txt_pelagem, txt_frequenciaCardiaca, txt_sistemaRespiratorio, txt_cavidadeOral, txt_temperatura, txt_condutorAuditivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_medico);

        dao = new RespostaRelatorioDAO(context);

        listaResposta.addAll(dao.ListarBanco());
        RespostaRelatorioWs.listarRespostaRelatorio(context, "respostaRelatorio/listaRespostaRelatorio");



        txt_hidratação = findViewById(R.id.txt_hidratação);
        txt_pelagem = findViewById(R.id.txt_pelagem);
        txt_frequenciaCardiaca = findViewById(R.id.txt_frequenciaCardiaca);
        txt_sistemaRespiratorio = findViewById(R.id.txt_sistemaRespiratorio);
        txt_cavidadeOral = findViewById(R.id.txt_cavidadeOral);
        txt_temperatura = findViewById(R.id.txt_temperatura);
        txt_condutorAuditivo = findViewById(R.id.txt_condutorAuditivo);

        if (listaResposta.isEmpty()){
         Toast.makeText(context,"Lista vazia",Toast.LENGTH_LONG).show();
        }else {

            txt_hidratação.setText(listaResposta.get(0).getResposta());
            txt_pelagem.setText(listaResposta.get(1).getResposta());
            txt_frequenciaCardiaca.setText(listaResposta.get(2).getResposta());
            txt_sistemaRespiratorio.setText(listaResposta.get(3).getResposta());
            txt_cavidadeOral.setText(listaResposta.get(4).getResposta());
            txt_temperatura.setText(listaResposta.get(5).getResposta());
            txt_condutorAuditivo.setText(listaResposta.get(6).getResposta());
        }
    }

}
