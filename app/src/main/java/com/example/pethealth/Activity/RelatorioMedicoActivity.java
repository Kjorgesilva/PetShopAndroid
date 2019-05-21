package com.example.pethealth.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pethealth.Dao.RespostaRelatorioDAO;
import com.example.pethealth.Model.RespostaRelatorio;
import com.example.pethealth.R;

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

        int idRelatorio = (int) getIntent().getSerializableExtra("id_relatorio");
        listaResposta.addAll(dao.findByIdAgenga(idRelatorio));
        //RespostaRelatorioWs.listarRespostaRelatorio(context, "respostaRelatorio/listaRespostaRelatorio");


        txt_hidratação = findViewById(R.id.txt_hidratação);
        txt_pelagem = findViewById(R.id.txt_pelagem);
        txt_frequenciaCardiaca = findViewById(R.id.txt_frequenciaCardiaca);
        txt_sistemaRespiratorio = findViewById(R.id.txt_sistemaRespiratorio);
        txt_cavidadeOral = findViewById(R.id.txt_cavidadeOral);
        txt_temperatura = findViewById(R.id.txt_temperatura);
        txt_condutorAuditivo = findViewById(R.id.txt_condutorAuditivo);

        if (listaResposta.isEmpty()) {
            Toast.makeText(context, "Lista vazia", Toast.LENGTH_LONG).show();
        } else {

            if (listaResposta.get(0).getResposta().equals("0")) {
                txt_hidratação.setText("Normal");
            }else {
                txt_hidratação.setText("Alterado");
            }

            if (listaResposta.get(1).getResposta().equals("0")) {
                txt_pelagem.setText("Normal");
            }else {
                txt_pelagem.setText("Alterado");
            }

            if (listaResposta.get(2).getResposta().equals("0")) {
                txt_frequenciaCardiaca.setText("Normal");

            } else {
                txt_frequenciaCardiaca.setText("Alterado");
            }


            if (listaResposta.get(3).getResposta().equals("0")) {
                txt_sistemaRespiratorio.setText("Normal");

            } else {
                txt_sistemaRespiratorio.setText("Alterado");
            }

            if (listaResposta.get(4).getResposta().equals("0")) {
                txt_cavidadeOral.setText("Normal");

            } else {
                txt_cavidadeOral.setText("Alterado");

            }


            if (listaResposta.get(5).getResposta().equals("0")) {
                txt_condutorAuditivo.setText("Normal");
            } else {
                txt_condutorAuditivo.setText("Alterado");
            }

            if (listaResposta.get(6).getResposta().equals("0")) {
                txt_temperatura.setText("Normal");
            } else {
                txt_temperatura.setText("Alterado");

            }



        }
    }

}
