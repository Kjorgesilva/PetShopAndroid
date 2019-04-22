package com.example.pethealth.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pethealth.Model.Animal;
import com.example.pethealth.Model.Vacinas;
import com.example.pethealth.R;
import com.example.pethealth.WebService.VacinasWs;

import java.util.List;

public class AdapterVacinas extends RecyclerView.Adapter<AdapterVacinas.VacinasHolder> {
    private List<Vacinas> list;
    private Context contexto;
    private VacinasOnclickListener vacinasOnclickListener;

    public AdapterVacinas(List<Vacinas> list, Context contexto, VacinasOnclickListener vacinasOnclickListener) {
        this.list = list;
        this.contexto = contexto;
        this.vacinasOnclickListener = vacinasOnclickListener;
    }

    public AdapterVacinas(Context context, List<Vacinas> listaTeste, VacinasOnclickListener vacinasOnclickListener) {
        this.contexto = context;
        this.list = listaTeste;
        this.vacinasOnclickListener = vacinasOnclickListener;
    }




    public AdapterVacinas.VacinasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter_vacinas, parent, false);
        VacinasHolder holder = new VacinasHolder(view);
        return holder;
    }

        @Override
    public void onBindViewHolder(@NonNull final AdapterVacinas.VacinasHolder holder, final int position) {

            holder.txt_nome_animal.setText(list.get(position).getNomeAnimal().toUpperCase());
            holder.txt_descricao_animal.setText(list.get(position).getNomeVacina());
            holder.txt_data_animal.setText(list.get(position).getDataVacina());

            if (vacinasOnclickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                    possição do card
                        vacinasOnclickListener.vacinasOnclickListener(holder.itemView, position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        vacinasOnclickListener.vacinasOnclickListener(holder.itemView, position);
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


    public interface VacinasOnclickListener {
        @SuppressLint("NewApi")
        void vacinasOnclickListener(View view, int index);
    }

    public static class VacinasHolder extends RecyclerView.ViewHolder {

        View view;
        TextView txt_nome_animal, txt_descricao_animal, txt_data_animal ;
        CardView cardViewAnimal;


        public VacinasHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            txt_nome_animal = view.findViewById(R.id.txt_nome_animal);
            txt_descricao_animal = view.findViewById(R.id.txt_descricao_animal);
            txt_data_animal = view.findViewById(R.id.txt_data_animal);
            cardViewAnimal = view.findViewById(R.id.cardViewAnimal);

        }



    }
}
