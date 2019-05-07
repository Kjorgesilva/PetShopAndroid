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


public class AdapterRespostaRelatorio extends RecyclerView.Adapter<AdapterRespostaRelatorio.RespostaRelatorioHolder> {
    private List<RespostaRelatorio> list;
    private Context contexto;
    private RespostaRelatorioOnclickListener respostaRelatorioOnclickListener;

    public AdapterRespostaRelatorio(Context contexto, List<RespostaRelatorio> lista, RespostaRelatorioOnclickListener clickListner) {
        this.contexto = contexto;
        this.list = lista;
        this.respostaRelatorioOnclickListener = clickListner;
    }

    public RespostaRelatorioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter_relatorio_resposta, parent, false);
        RespostaRelatorioHolder holder = new RespostaRelatorioHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RespostaRelatorioHolder holder, final int position) {

        holder.txt_animal_nome.setText(list.get(position).getResposta());

        if (respostaRelatorioOnclickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    respostaRelatorioOnclickListener.animalOnclickListener(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    respostaRelatorioOnclickListener.animalOnclickListener(holder.itemView, position);
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


    public interface RespostaRelatorioOnclickListener {
        @SuppressLint("NewApi")
        void animalOnclickListener(View view, int index);
    }

    public static class RespostaRelatorioHolder extends RecyclerView.ViewHolder {
        View view;
        CardView cardView;
        TextView txt_animal_nome, txt_nome_medico;


        public RespostaRelatorioHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            cardView = view.findViewById(R.id.cardViewAnimalRelarotio);
            txt_animal_nome = view.findViewById(R.id.txt_animal_nome);
            txt_nome_medico = view.findViewById(R.id.txt_nome_medico);
        }
    }
}
