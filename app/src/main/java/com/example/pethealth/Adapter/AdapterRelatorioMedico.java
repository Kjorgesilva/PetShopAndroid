package com.example.pethealth.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pethealth.Model.RespostaRelatorio;
import com.example.pethealth.R;

import java.util.List;

public class AdapterRelatorioMedico extends RecyclerView.Adapter<AdapterRelatorioMedico.HistoricoHolder> {

    private List<RespostaRelatorio> respostaRelatorio;
    private Context contexto;
    private RelatorioOnClick RelatorioOnClick;


//    public AdapterRelatorioMedico(Context context, List<RespostaRelatorio> respostaRelatorio) {
//        this.respostaRelatorio = respostaRelatorio;
//        this.contexto = context;
//    }

    public AdapterRelatorioMedico(Context context, List<RespostaRelatorio> respostaRelatorio, RelatorioOnClick clickListner) {
        this.respostaRelatorio = respostaRelatorio;
        this.contexto = context;
        this.RelatorioOnClick = clickListner;
    }

    public AdapterRelatorioMedico.HistoricoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter_relatorio_medico, parent, false);
        HistoricoHolder holder = new HistoricoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoricoHolder holder, final int position) {
        holder.tvConsulta.setText(String.valueOf(respostaRelatorio.get(position).getIdAgenda()));
        holder.tvAnimal.setText(respostaRelatorio.get(position).getRelatoriosAnimal());
        holder.tvMedico.setText(respostaRelatorio.get(position).getRelatoriosMedcio());
        holder.tvDataConsulta.setText("15/05/2019");

        if (RelatorioOnClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    possição do card
                    RelatorioOnClick.clickListenerView(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    RelatorioOnClick.onLongClick(holder.itemView, position);

                    return true;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        if (respostaRelatorio.size() > 0 ) {
            return respostaRelatorio.size();
        } else {
            return 0;
        }


    }

    public interface RelatorioOnClick {
        @SuppressLint("NewApi")
        void onLongClick(View view, int i);
        void clickListenerView(View view, int index);
    }


    public static class HistoricoHolder extends RecyclerView.ViewHolder {

        View view;
        TextView tvConsulta, tvAnimal, tvMedico, tvDataConsulta;
        CardView cardView;


        public HistoricoHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvConsulta = view.findViewById(R.id.tv_id_consulta);
            tvAnimal = view.findViewById(R.id.tv_animal);
            tvMedico = view.findViewById(R.id.tv_medico);
            tvDataConsulta = view.findViewById(R.id.tv_data_consulta);

            //cardView = view.findViewById(R.id.cardView);
        }
    }
}