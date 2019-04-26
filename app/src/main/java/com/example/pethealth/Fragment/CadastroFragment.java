package com.example.pethealth.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.AnimalDAO;
import com.example.pethealth.Dao.MedicoDAO;
import com.example.pethealth.InterfaceHelp.Mask;
import com.example.pethealth.Model.Agenda;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.Model.Medico;
import com.example.pethealth.R;
import com.example.pethealth.WebService.AgendamentoWs;
import com.example.pethealth.WebService.MedicoWS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {
    private EditText edt_nome, edt_nome_dono, edt_endereco, edt_data, edt_data_fim;
    private ImageButton imgSpinner;
    private TextView tvSpinner;
    private Button btn_cadastrar;
    private Context contexto;
    private Spinner spinner, spinner2;

    private String horario;
    private String horario2;
    private List<Medico> listaTeste = new ArrayList<>();
    //serve para fazer a ligação com a classe AgendamentoDAO e chama os metodos do Banco
    AgendamentoDAO db = new AgendamentoDAO(getContext());
    MedicoDAO dbMedico = new MedicoDAO(getContext());

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        contexto = getContext();
        dbMedico = new MedicoDAO(getContext());

        listaTeste.addAll(dbMedico.findAllMedico());
        MedicoWS.listarMedico(contexto, "medico/listamedico");


        edt_nome = view.findViewById(R.id.edt_nome);
        edt_nome_dono = view.findViewById(R.id.edt_nome_dono);
        edt_data_fim = view.findViewById(R.id.edt_data_fim);
        edt_endereco = view.findViewById(R.id.edt_endereco);
        edt_data = view.findViewById(R.id.edt_data);
        btn_cadastrar = view.findViewById(R.id.btn_cadastrar);
        imgSpinner = view.findViewById(R.id.imgSpinner);
        tvSpinner = view.findViewById(R.id.tvSpinner);
        spinner = view.findViewById(R.id.spinnerHoras);
        spinner2 = view.findViewById(R.id.spinnerHorasFim);

        ArrayAdapter adaptador = ArrayAdapter.createFromResource
                (contexto, R.array.horarioInicio, android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adaptador);

        final String array[] = getResources().getStringArray(R.array.horarioInicio);
        final String arrays[] = getResources().getStringArray(R.array.horarioFim);


        ArrayAdapter adaptador2 = ArrayAdapter.createFromResource
                (contexto, R.array.horarioFim, android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adaptador2);

        db = new AgendamentoDAO(getContext());

        edt_data.addTextChangedListener(Mask.insert("##/##/####", edt_data));
        edt_data_fim.addTextChangedListener(Mask.insert("##/##/####", edt_data_fim));

        imgSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogItens();
                // Toast.makeText(contexto,"teste imagem",Toast.LENGTH_LONG).show();
            }
        });

        tvSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogItens();
                // Toast.makeText(contexto,"teste spiner",Toast.LENGTH_LONG).show();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                horario = array[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                horario2 = arrays[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_nome.getText().toString().isEmpty()) {
                    edt_nome.setError("Preencha o campo");
                } else if (edt_data_fim.getText().toString().isEmpty()) {
                    edt_data_fim.setError("Preencha o campo");
                } else if (edt_nome_dono.getText().toString().isEmpty()) {
                    edt_nome_dono.setError("Preencha o campo");
                } else if (edt_endereco.getText().toString().isEmpty()) {
                    edt_endereco.setError("Preencha o campo");
                } else if (edt_data.getText().toString().isEmpty()) {
                    edt_data.setError("Preencha o campo");
                } else if (tvSpinner.getText().toString().isEmpty()) {
                    tvSpinner.setError("Preencha o campo");
                } else {


                    int idAnimal = Integer.parseInt(edt_nome.getText().toString());
                    int idCliente = Integer.parseInt(edt_nome_dono.getText().toString());
                    int idEndereco = Integer.parseInt(edt_endereco.getText().toString());
                    int data = Integer.parseInt(edt_data.getText().toString() + " " + horario);
                    int idMedico = Integer.parseInt(tvSpinner.getText().toString());
                    int dataFim = Integer.parseInt(edt_data_fim.getText().toString() + " " + horario2);

//                    db.inserir(new Agenda(idAnimal, idCliente, idEndereco, data, idMedico, dataFim));

                    List<Agenda> agenda = new ArrayList<>(db.ListarBanco());
                    int id = agenda.get(agenda.size() - 1).getId();


                    Toast.makeText(getContext(), "Agenda Agendada", Toast.LENGTH_LONG).show();

//                    cadValor(id, idAnimal, idCliente, idEndereco, data, idMedico, dataFim);


                    edt_nome.setText("");
                    edt_nome_dono.setText("");
                    edt_data_fim.setText("");
                    edt_endereco.setText("");
                    edt_data.setText("");
                    tvSpinner.setText("");

                }
            }
        });


        return view;
    }

    private void cadValor(int id, int idAnimal, int idCliente, int idEndereco, String data, int idMedico, String dataFim) {
        Map<String, String> map = new HashMap<>();

        map.put("id", String.valueOf(id));
        map.put("idAnimal", String.valueOf(idAnimal));
        map.put("idCliente", String.valueOf(idCliente));
        map.put("idEndereco", String.valueOf(idEndereco));
        map.put("data", data);
        map.put("idMedico", String.valueOf(idMedico));
        map.put("dataFim", dataFim);

        AgendamentoWs.agendamentoMedico(contexto, "consulta", map);

    }


    public void showDialogItens() {
        MedicoDAO dao = new MedicoDAO(contexto);
        final List<Medico> produtos = new ArrayList<>();
        produtos.addAll(dao.findAllMedico());
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Selecione um produto abaixo:");
        String[] itens = new String[produtos.size()];
        for (int x = 0; x < produtos.size(); x++) {
            itens[x] = produtos.get(x).getNome();
        }
        alert.setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvSpinner.setText(produtos.get(i).getNome());
            }
        });
        alert.show();
    }

    public void showDialogAnimal() {
        AnimalDAO dao = new AnimalDAO(contexto);
        final List<Animal> animals = new ArrayList<>();
        animals.addAll(dao.findAllAnimal());
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Selecione Animal:");
        String[] itens = new String[animals.size()];
        for (int x = 0; x < animals.size(); x++) {
            itens[x] = animals.get(x).getNome();
        }
        alert.setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvSpinner.setText(animals.get(i).getNome());
            }
        });
        alert.show();
    }


      public static CadastroFragment newInstance() {
        CadastroFragment fragment = new CadastroFragment();
        return fragment;
    }

}
