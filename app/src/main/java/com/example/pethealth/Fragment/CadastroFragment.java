package com.example.pethealth.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.pethealth.Dao.ClienteDAO;
import com.example.pethealth.Dao.EnderecoDAO;
import com.example.pethealth.Dao.MedicoDAO;
import com.example.pethealth.InterfaceHelp.Mask;
import com.example.pethealth.Model.Agenda;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.Model.Cliente;
import com.example.pethealth.Model.Endereco;
import com.example.pethealth.Model.Medico;
import com.example.pethealth.R;
import com.example.pethealth.WebService.AgendamentoWs;
import com.example.pethealth.WebService.AnimalWs;
import com.example.pethealth.WebService.ClienteWs;
import com.example.pethealth.WebService.EnderecoWs;
import com.example.pethealth.WebService.MedicoWS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {
    private EditText edt_data, edt_data_fim;
    private TextView edt_nome_dono, edt_nome_animal, edt_endereco;
    private ImageButton imgSpinner,imgEdt_nome_animal,imgEdt_nome, imgedt_endereco;
    private TextView tvSpinner;
    private Button btn_cadastrar;
    private Context contexto;
    private Spinner spinner, spinner2;

    private String horario;
    private String horario2;
    private List<Medico> listaMedico = new ArrayList<>();
    private List<Animal> listaAnimal = new ArrayList<>();
    private List<Cliente> listaCliente = new ArrayList<>();
    private List<Endereco> listaEndereco = new ArrayList<>();


    private int id_animal,id_cliente,id_endereco,id_medico;
    private Animal animal;
    private Cliente cliente;
    private Endereco endereco;
    private Medico medico;


    //serve para fazer a ligação com a classe AgendamentoDAO e chama os metodos do Banco
    AgendamentoDAO db = new AgendamentoDAO(getContext());
    MedicoDAO dbMedico = new MedicoDAO(getContext());
    AnimalDAO dbAnimal = new AnimalDAO(getContext());
    ClienteDAO dbCliente = new ClienteDAO(getContext());
    EnderecoDAO dbEndereco = new EnderecoDAO(getContext());


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

        dbAnimal = new AnimalDAO(getContext());
        listaAnimal.addAll(dbAnimal.findAllAnimal());
        AnimalWs.listarAnimal(contexto, "animal/listaAnimal");

        dbMedico = new MedicoDAO(getContext());
        listaMedico.addAll(dbMedico.findAllMedico());
        MedicoWS.listarMedico(contexto, "medico/listaMedico");


        dbCliente = new ClienteDAO(getContext());
        listaCliente.addAll(dbCliente.findAllCliente());
        ClienteWs.listarCliente(contexto,"cliente/listaCliente");


        dbEndereco = new EnderecoDAO(getContext());
        listaEndereco.addAll(dbEndereco.findAllEndereco());
        EnderecoWs.listarEndereco(contexto,"endereco/listaEndereco");

        edt_nome_animal = view.findViewById(R.id.edt_nome_animal);
        edt_nome_dono = view.findViewById(R.id.edt_nome_dono);
        edt_data_fim = view.findViewById(R.id.edt_data_fim);
        edt_endereco = view.findViewById(R.id.edt_endereco);
        edt_data = view.findViewById(R.id.edt_data);
        btn_cadastrar = view.findViewById(R.id.btn_cadastrar);
        imgSpinner = view.findViewById(R.id.imgSpinner);
        imgEdt_nome_animal = view.findViewById(R.id.imgEdt_nome_animal);
        imgEdt_nome = view.findViewById(R.id.imgEdt_nome);
        imgedt_endereco = view.findViewById(R.id.imgEdt_endereco);
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


        edt_endereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEndereco();
            }
        });

        imgedt_endereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEndereco();
            }
        });

        edt_nome_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAnimal();
            }
        });

        imgEdt_nome_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAnimal();
            }
        });

        imgEdt_nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCliente();
            }
        });

        edt_nome_dono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCliente();
            }
        });

        imgSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialogMedico();

            }
        });

        tvSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMedico();

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
                if (edt_nome_animal.getText().toString().isEmpty()) {
                    edt_nome_animal.setError("Preencha o campo");
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



                    String data = (edt_data.getText().toString() + " " + horario);
                    String dataFim = (edt_data_fim.getText().toString() + " " + horario2);

                    db.inserir(new Agenda(animal, cliente, endereco, data, medico, dataFim));

                    List<Agenda> agenda = new ArrayList<>(db.ListarBanco());
                    int id = agenda.get(agenda.size() - 1).getId();


                    Toast.makeText(getContext(), "Agenda Agendada", Toast.LENGTH_LONG).show();

                   cadValor( id, id_animal, id_cliente, id_endereco, data, id_medico, dataFim);


                    edt_nome_animal.setText("");
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

    private void cadValor(int id,int id_animal, int id_cliente, int id_endereco, String data, int id_medico, String dataFim) {
        Map<String, String> map = new HashMap<>();

        map.put("id",String.valueOf(id));
        map.put("id_animal", String.valueOf(id_animal));
        map.put("id_cliente", String.valueOf(id_cliente));
        map.put("id_endereco", String.valueOf(id_endereco));
        map.put("data", data);
        map.put("id_medico", String.valueOf(id_medico));
        map.put("dataFim", dataFim);

        AgendamentoWs.agendamentoMedico(contexto, "consulta", map);

    }


    public void showDialogMedico() {
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Selecione o Medico:");
        String[] itens = new String[listaMedico.size()];
        for (int x = 0; x < listaMedico.size(); x++) {
            itens[x] = listaMedico.get(x).getNome();
        }
        alert.setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvSpinner.setText(listaMedico.get(i).getNome());
                id_medico = listaMedico.get(i).getId();
                medico = listaMedico.get(i);
            }
        });
        alert.show();
    }

    public void showDialogEndereco() {
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Selecione Endereço:");
        String[] itens = new String[listaEndereco.size()];
        for (int x = 0; x < listaEndereco.size(); x++) {
            itens[x] = listaEndereco.get(x).getRua();
        }
        alert.setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edt_endereco.setText(listaEndereco.get(i).getRua());
                id_endereco = listaEndereco.get(i).getId();
                endereco = listaEndereco.get(i);
            }
        });


        alert.show();
    }
    public void showDialogCliente() {
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Selecione Cliente:");
        String[] itens = new String[listaCliente.size()];
        for (int l = 0; l < listaCliente.size(); l++) {
            itens[l] = listaCliente.get(l).getNome();
        }
        alert.setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edt_nome_dono.setText(listaCliente.get(i).getNome());
                id_cliente = listaCliente.get(i).getId();
                cliente =listaCliente.get(i);
            }
        });
        alert.show();
    }

    public void showDialogAnimal() {
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Selecione Animal:");
        String[] itens = new String[listaAnimal.size()];
        for (int l = 0; l < listaAnimal.size(); l++) {
            itens[l] = listaAnimal.get(l).getNome();
        }
        alert.setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edt_nome_animal.setText(listaAnimal.get(i).getNome());
                id_animal = listaAnimal.get(i).getId();
                animal = listaAnimal.get(i);
            }
        });
        alert.show();
    }



      public static CadastroFragment newInstance() {
        CadastroFragment fragment = new CadastroFragment();
        return fragment;
    }

}
