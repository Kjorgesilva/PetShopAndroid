package com.example.pethealth.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pethealth.Adapter.AdapterVacinas;
import com.example.pethealth.Dao.VacinasDAO;
import com.example.pethealth.Model.Vacinas;
import com.example.pethealth.R;
import com.example.pethealth.WebService.VacinasWs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VacinasFragment extends Fragment {

    private Context contexto;
    private RecyclerView recyclerView;
    private AdapterVacinas adapterVacinas;
    private List<Vacinas> listaTeste;
    private Vacinas vacinaTeste;
    private VacinasDAO db;
    private List<VacinasWs> vacinasWsLista = new ArrayList<>();


    public VacinasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_vacinas, container, false);
        contexto = getContext();
        db = new VacinasDAO(getContext());



        listaTeste = new ArrayList<>();
        vacinaTeste = new Vacinas();

        vacinaTeste.setId(1);
        vacinaTeste.setAviso("dasdasdad");
        vacinaTeste.setDataDaProxima("21/04/2019");
        vacinaTeste.setDataVacina("20/04/2019");
//      vacinaTeste.setDescricao("descrição");
//      vacinaTeste.setVacinaEmDia(true);

        listaTeste.add(vacinaTeste);


        recyclerView = view.findViewById(R.id.recyclerViewAnimal);
        recyclerView.setLayoutManager(new LinearLayoutManager(contexto));
        recyclerView.setAdapter(new AdapterVacinas(contexto, listaTeste, clickListner()));


        VacinasWs.listarVacinas(contexto, "vacinacao/listavacinas");


        return view;

    }


    private AdapterVacinas.VacinasOnclickListener clickListner() {

        return new AdapterVacinas.VacinasOnclickListener() {

            @Override
            public void vacinasOnclickListener(View view, final int index) {
                Toast.makeText(getContext(), "funcionando", Toast.LENGTH_LONG).show();

            }
        };
    }

    public static VacinasFragment newInstance() {
        VacinasFragment fragment = new VacinasFragment();
        return fragment;
    }

}
