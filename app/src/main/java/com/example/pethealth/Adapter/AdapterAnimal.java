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

import com.example.pethealth.Model.Animal;
import com.example.pethealth.R;

import java.util.List;


public class AdapterAnimal extends RecyclerView.Adapter<AdapterAnimal.RelatorioAnimalHolder> {
    private List<Animal> list;
    private Context contexto;
    private AnimalOnclickListener animalOnclickListener;

    public AdapterAnimal(Context contexto, List<Animal> listateste, AnimalOnclickListener clickListner) {
        this.contexto = contexto;
        this.list = listateste;
        this.animalOnclickListener = clickListner;
    }

    public AdapterAnimal.RelatorioAnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter_animal, parent, false);
        RelatorioAnimalHolder holder = new RelatorioAnimalHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterAnimal.RelatorioAnimalHolder holder, final int position) {

        holder.txt_animal_nome.setText(list.get(position).getNome().toUpperCase());
        //holder.txt_nome_medico.setText(list.get(position).getMedico());

        if (animalOnclickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    possição do card
                    animalOnclickListener.animalOnclickListener(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    animalOnclickListener.animalOnclickListener(holder.itemView, position);
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


    public interface AnimalOnclickListener {
        @SuppressLint("NewApi")
        void animalOnclickListener(View view, int index);
    }

    public static class RelatorioAnimalHolder extends RecyclerView.ViewHolder {
        View view;
        CardView cardView;
        TextView txt_animal_nome, txt_nome_medico;


        public RelatorioAnimalHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            cardView = view.findViewById(R.id.cardViewAnimalRelarotio);
            txt_animal_nome = view.findViewById(R.id.txt_animal_nome);
            txt_nome_medico = view.findViewById(R.id.txt_nome_medico);
        }
    }
}
