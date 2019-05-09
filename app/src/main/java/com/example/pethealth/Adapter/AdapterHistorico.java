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

import com.example.pethealth.Model.Agenda;
import com.example.pethealth.R;

import java.util.List;

public class AdapterHistorico extends RecyclerView.Adapter<AdapterHistorico.HistoricoHolder> {
    private List<Agenda> listarBanco;
    private Context contexto;
    private HistoricoOnClickListner HistoricoOnClickListner;


    public AdapterHistorico(Context context, List<Agenda> listarBanco) {
        this.listarBanco = listarBanco;
        this.contexto = context;
    }

    public AdapterHistorico(Context context, List<Agenda> listarBanco, AdapterHistorico.HistoricoOnClickListner clickListner) {
        this.listarBanco = listarBanco;
        this.contexto = context;
        this.HistoricoOnClickListner = clickListner;
    }

    public AdapterHistorico.HistoricoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter_historico, parent, false);
        HistoricoHolder holder = new HistoricoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoricoHolder holder, final int position) {
        holder.txt_nome.setText(listarBanco.get(position).getAnimal().getNome().toUpperCase());
        holder.txt_medico.setText(listarBanco.get(position).getMedico().getNome());
        holder.txt_data.setText(listarBanco.get(position).getDataInicio());
        holder.txt_data_fim.setText(listarBanco.get(position).getDataFim());

        if (HistoricoOnClickListner != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    possição do card
                    HistoricoOnClickListner.clickListenerView(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    HistoricoOnClickListner.onLongClick(holder.itemView, position);

                    return true;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        if (listarBanco.size() > 0) {
            return listarBanco.size();
        } else {
            return 0;
        }


    }

    public interface HistoricoOnClickListner {
        @SuppressLint("NewApi")
        void onLongClick(View view, int i);
        void clickListenerView(View view, int index);
    }


    public static class HistoricoHolder extends RecyclerView.ViewHolder {

        View view;
        TextView txt_nome, txt_medico, txt_data,txt_data_fim;
        CardView cardView;


        public HistoricoHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            txt_nome = view.findViewById(R.id.txt_nome);
            txt_medico = view.findViewById(R.id.txt_medico);
            txt_data = view.findViewById(R.id.txt_data);
            txt_data_fim = view.findViewById(R.id.txt_data_fim);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}