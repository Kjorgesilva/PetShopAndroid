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

import com.example.pethealth.Model.Consulta;
import com.example.pethealth.R;

import java.util.List;

public class AdapterAgendamento extends RecyclerView.Adapter<AdapterAgendamento.AgendamentoHolder> {
    private List<Consulta> list;
    private Context contexto;
    private AgendamentoOnClickListner AgendamentoOnClickListner;

    public AdapterAgendamento(Context context, List<Consulta> listarBanco, AdapterAgendamento.AgendamentoOnClickListner clickListner) {
        this.contexto = context;
        this.list = listarBanco;
        AgendamentoOnClickListner = clickListner;

    }


    public AdapterAgendamento.AgendamentoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter_agendamento, parent, false);
        AgendamentoHolder holder = new AgendamentoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterAgendamento.AgendamentoHolder holder, final int position) {
        holder.txt_nome.setText(list.get(position).getNome().toUpperCase());
        holder.txt_medico.setText(list.get(position).getMedico());
        holder.txt_data.setText(list.get(position).getData());

        if (AgendamentoOnClickListner != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    possição do card
                    AgendamentoOnClickListner.clickListenerView(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AgendamentoOnClickListner.onLongClick(holder.itemView, position);

                    return true;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        if (list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }


    }

    public interface AgendamentoOnClickListner {
        @SuppressLint("NewApi")
        void onLongClick(View view, int i);

        void clickListenerView(View view, int index);
    }


    public static class AgendamentoHolder extends RecyclerView.ViewHolder {

        View view;
        TextView txt_nome, txt_medico, txt_data;
        CardView cardView;


        public AgendamentoHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            txt_nome = view.findViewById(R.id.txt_nome);
            txt_medico = view.findViewById(R.id.txt_medico);
            txt_data = view.findViewById(R.id.txt_data);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}