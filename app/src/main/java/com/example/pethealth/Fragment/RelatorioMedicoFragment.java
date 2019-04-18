package com.example.pethealth.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pethealth.Activity.RelatorioMedicoActivity;
import com.example.pethealth.Adapter.AdapterAnimal;
import com.example.pethealth.Adapter.AdapterVacinas;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelatorioMedicoFragment extends Fragment {

    private Context contexto;
    private RecyclerView recyclerView;
    private AdapterAnimal adapterAnimal;
    private List<Animal> listateste;
    private Animal animal;

private Button btnRelatorioMedico;
    public RelatorioMedicoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_relatorio_medico, container, false);
        contexto = getContext();

        listateste = new ArrayList<>();
        animal = new Animal();

        animal.setId(1);
        animal.setNome("totó");




        listateste.add(animal);



        recyclerView = view.findViewById(R.id.recyclerViewRelatorio);
        recyclerView.setLayoutManager(new LinearLayoutManager(contexto));
        recyclerView.setAdapter(new AdapterAnimal(contexto, listateste,clickListner()));


//        btnRelatorioMedico.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), RelatorioMedicoActivity.class);
//                startActivity(intent);
//            }
//        });


        return view;

    }

    public  AdapterAnimal.AnimalOnclickListener clickListner(){
        return  new AdapterAnimal.AnimalOnclickListener() {
            @Override
            public void animalOnclickListener(View view, int index) {
                Intent intent = new Intent(contexto,RelatorioMedicoActivity.class);
                startActivity(intent);
            }
        };
    }


    public static RelatorioMedicoFragment newInstance() {
        RelatorioMedicoFragment fragment = new RelatorioMedicoFragment();
        return fragment;
    }

}
