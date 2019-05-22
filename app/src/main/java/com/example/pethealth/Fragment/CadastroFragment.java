package com.example.pethealth.Fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.animation.ImageMatrixProperty;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.AnimalDAO;
import com.example.pethealth.Dao.EnderecoDAO;
import com.example.pethealth.Dao.MedicoDAO;
import com.example.pethealth.Dao.UsuarioDAO;
import com.example.pethealth.InterfaceHelp.Mask;
import com.example.pethealth.Model.Agenda;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.Model.CadastroGeral;
import com.example.pethealth.Model.Endereco;
import com.example.pethealth.Model.Medico;
import com.example.pethealth.R;
import com.example.pethealth.WebService.AgendamentoWs;
import com.example.pethealth.WebService.Connection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {
    private TextView txt_data_inicio, txt_data_fim;
    private TextView edt_nome_dono, edt_nome_animal, edt_endereco;
    private ImageButton imgSpinner, imgEdt_nome_animal, imgEdt_nome, imgedt_endereco;
    private TextView tvSpinner;
    private Button btn_cadastrar;
    private Context contexto;
    private Spinner spinner, spinner2;

    private String horario;
    private String horario2;
    private List<Medico> listaMedico = new ArrayList<>();
    private List<Animal> listaAnimal = new ArrayList<>();
    private List<Endereco> listaEndereco = new ArrayList<>();


    private int id_animal, id_cliente, id_endereco, id_medico;
    private Animal animal;
    private Endereco endereco;
    private Medico medico;
    private UsuarioDAO dao;

    private ImageView imgViewDataInicio,imgViewDataFim;




    private DatePickerDialog datePickerDialog;


    //serve para fazer a ligação com a classe AgendamentoDAO e chama os metodos do Banco
    AgendamentoDAO db = new AgendamentoDAO(getContext());
    MedicoDAO dbMedico = new MedicoDAO(getContext());
    AnimalDAO dbAnimal = new AnimalDAO(getContext());
    EnderecoDAO dbEndereco = new EnderecoDAO(getContext());
    UsuarioDAO dbUsuario = new UsuarioDAO(getContext());


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
        dbAnimal = new AnimalDAO(getContext());
        dbEndereco = new EnderecoDAO(getContext());
        dbUsuario = new UsuarioDAO(getContext());

        dao = new UsuarioDAO(contexto);

        listarGeral(contexto, "cadastro/" + String.valueOf(dbUsuario.findAllUsuario().getIdCliente()) + "/listaCadastroGeral");


        edt_nome_animal = view.findViewById(R.id.edt_nome_animal);
        edt_nome_dono = view.findViewById(R.id.edt_nome_dono);
        txt_data_fim = view.findViewById(R.id.edt_data_fim);
        edt_endereco = view.findViewById(R.id.edt_endereco);
        txt_data_inicio = view.findViewById(R.id.edt_data);
        btn_cadastrar = view.findViewById(R.id.btn_cadastrar);
        imgSpinner = view.findViewById(R.id.imgSpinner);
        imgEdt_nome_animal = view.findViewById(R.id.imgEdt_nome_animal);
        imgEdt_nome = view.findViewById(R.id.imgEdt_nome);
        imgedt_endereco = view.findViewById(R.id.imgEdt_endereco);
        tvSpinner = view.findViewById(R.id.tvSpinner);
        spinner = view.findViewById(R.id.spinnerHoras);
        spinner2 = view.findViewById(R.id.spinnerHorasFim);

        imgViewDataInicio = view.findViewById(R.id.imgViewDataInicio);
        imgViewDataFim = view.findViewById(R.id.imgViewDataFim);



        ArrayAdapter adaptador = ArrayAdapter.createFromResource
                (contexto, R.array.horarioInicio, android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adaptador);

        final String array[] = getResources().getStringArray(R.array.horarioInicio);
        final String arrays[] = getResources().getStringArray(R.array.horarioFim);


        ArrayAdapter adaptador2 = ArrayAdapter.createFromResource
                (contexto, R.array.horarioFim, android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adaptador2);

        db = new AgendamentoDAO(getContext());

        //edt_data.addTextChangedListener(Mask.insert("##/##/####", edt_data));

        imgViewDataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day, month, year;
                Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);


                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker,  int year, int month, int day) {

                        String dia = String.valueOf(day);
                        month = month + 1;
                        String mes = String.valueOf(month);
                        if (dia.length() == 1) {
                            dia = "0" + dia;
                        }
                        if (mes.length() == 1) {
                            mes = "0" + mes;
                        }

                        String dataSelecionada = dia + "/" + mes + "/" + String.valueOf(year);
                        txt_data_inicio.setText(dataSelecionada);


                    }
                };
                datePickerDialog = new DatePickerDialog(contexto, listener, year, month, day);
                datePickerDialog.show();
            }
        });

        imgViewDataFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day, month, year;
                Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);


                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker,  int year, int month, int day) {

                        String dia = String.valueOf(day);
                        month = month + 1;
                        String mes = String.valueOf(month);
                        if (dia.length() == 1) {
                            dia = "0" + dia;
                        }
                        if (mes.length() == 1) {
                            mes = "0" + mes;
                        }

                        String dataSelecionada = dia + "/" + mes + "/" + String.valueOf(year);
                        txt_data_fim.setText(dataSelecionada);


                    }
                };
                datePickerDialog = new DatePickerDialog(contexto, listener, year, month, day);
                datePickerDialog.show();

            }
        });




        //edt_data_fim.addTextChangedListener(Mask.insert("##/##/####", edt_data_fim));


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


        edt_nome_dono.setText(dbUsuario.findAllUsuario().getNome().toUpperCase());


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
                } else if (txt_data_fim.getText().toString().isEmpty()) {
                    txt_data_fim.setError("Preencha o campo");
                } else if (edt_nome_dono.getText().toString().isEmpty()) {
                    edt_nome_dono.setError("Preencha o campo");
                } else if (edt_endereco.getText().toString().isEmpty()) {
                    edt_endereco.setError("Preencha o campo");
                } else if (txt_data_inicio.getText().toString().isEmpty()) {
                    txt_data_inicio.setError("Preencha o campo");
                } else if (tvSpinner.getText().toString().isEmpty()) {
                    tvSpinner.setError("Preencha o campo");
                } else {

                    String data = (txt_data_inicio.getText().toString() + " " + horario);
                    String dataFim = (txt_data_fim.getText().toString() + " " + horario2);






                    String status_agendamento = "P";
                    db.inserir(new Agenda(animal, dao.findAllUsuario().getIdCliente(), endereco, data, medico, dataFim,status_agendamento));

//                   List<Agenda> agenda = new ArrayList<>(db.ListarBanco());
//                   int id = agenda.get(agenda.size() - 1).getId();


                    Toast.makeText(getContext(), "Consulta Agendada", Toast.LENGTH_LONG).show();


                    cadValor(id_animal, dao.findAllUsuario().getIdCliente(), id_endereco, data, id_medico, dataFim,status_agendamento);

                    Log.e("idClienteService", "passou: " + dao.findAllUsuario().getIdCliente());
                    edt_nome_animal.setText("");
                    txt_data_fim.setText("");
                    edt_endereco.setText("");
                    txt_data_inicio.setText("");
                    tvSpinner.setText("");


                }
            }
        });


        return view;
    }

    private void cadValor(int id_animal, int id_cliente, int id_endereco, String data, int id_medico, String dataFim, String status_agendamento) {
        Map<String, String> map = new HashMap<>();

        map.put("id_animal", String.valueOf(id_animal));
        map.put("id_cliente", String.valueOf(id_cliente));
        map.put("id_endereco", String.valueOf(id_endereco));
        map.put("data", data);
        map.put("id_medico", String.valueOf(id_medico));
        map.put("dataFim", dataFim);
        map.put("status_agendamento", status_agendamento);

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
                tvSpinner.setText(listaMedico.get(i).getNome().toUpperCase());
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
                edt_endereco.setText(listaEndereco.get(i).getRua().toUpperCase());
                id_endereco = listaEndereco.get(i).getId();
                endereco = listaEndereco.get(i);
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
                edt_nome_animal.setText(listaAnimal.get(i).getNome().toUpperCase());
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

    public void listarGeral(final Context contexto, String path) {
        RequestQueue queue = Volley.newRequestQueue(contexto);
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, Connection.getUrl() + path, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                AnimalDAO dbAnimal = new AnimalDAO(contexto);
                EnderecoDAO dbEndereco = new EnderecoDAO(contexto);
                MedicoDAO dbMedico = new MedicoDAO(contexto);

                try {
                    CadastroGeral cadastroGeral = new Gson().fromJson(response.toString(), CadastroGeral.class);
                    if (cadastroGeral == null) {
                        Toast.makeText(contexto, "Lista vazia", Toast.LENGTH_LONG).show();
                    } else {


                        //animal -------------------------------------------------------------------
                        int contAnimal = 0;
                        if (dbAnimal.findAllAnimal(dao.findAllUsuario().getIdCliente()).size() == 0) {
                            for (Animal animal : cadastroGeral.getListaAnimal()) {
                                dbAnimal.inserir(animal);
                            }

                        } else {

                            for (int x = 0; x < cadastroGeral.getListaAnimal().size(); x++) {

                                for (Animal animal : dbAnimal.findAllAnimal(dao.findAllUsuario().getIdCliente())) {
                                    if (cadastroGeral.getListaAnimal().get(x).getId() != animal.getId()) {
                                        contAnimal = contAnimal + 1;
                                    }
                                }

                                if (contAnimal == dbAnimal.findAllAnimal(dao.findAllUsuario().getIdCliente()).size()) {
                                    dbAnimal.inserir(cadastroGeral.getListaAnimal().get(x));
                                }
                                contAnimal = 0;

                            }


                        }


                        //medico -------------------------------------------------------------------
                        int contMedico = 0;
                        if (dbMedico.findAllMedico().size() == 0) {

                            for (int h = 0; h < cadastroGeral.getListaMedico().size(); h++) {
                                dbMedico.inserir(cadastroGeral.getListaMedico().get(h));
                            }

                        } else {

                            for (int h = 0; h < cadastroGeral.getListaMedico().size(); h++) {

                                for (Medico medico : dbMedico.findAllMedico()) {

                                    if (medico.getId() != cadastroGeral.getListaMedico().get(h).getId()) {

                                        contMedico = contMedico + 1;
                                    }
                                }

                                if (contMedico == dbMedico.findAllMedico().size()) {
                                    dbMedico.inserir(cadastroGeral.getListaMedico().get(h));
                                }

                                contMedico = 0;

                            }

                        }

                        //endereco -----------------------------------------------------------------
                        int contEndereco = 0;
                        if (dbEndereco.findAllEndereco().size() == 0) {

                            for (int h = 0; h < cadastroGeral.getListaEndereco().size(); h++) {
                                dbEndereco.inserir(cadastroGeral.getListaEndereco().get(h));
                            }

                        } else {

                            for (int h = 0; h < cadastroGeral.getListaEndereco().size(); h++) {

                                for (Endereco endereco : dbEndereco.findAllEndereco()) {

                                    if (endereco.getId() != cadastroGeral.getListaEndereco().get(h).getId()) {

                                        contEndereco = contEndereco + 1;
                                    }
                                }

                                if (contEndereco == dbEndereco.findAllEndereco().size()) {
                                    dbEndereco.inserir(cadastroGeral.getListaEndereco().get(h));
                                }
                                contEndereco = 0;

                            }

                        }
                        //popula lista dos spinners
                        listaAnimal.clear();
                        listaAnimal.addAll(dbAnimal.findAllAnimal(dao.findAllUsuario().getIdCliente()));
                        listaMedico.clear();
                        listaMedico.addAll(dbMedico.findAllMedico());
                        listaEndereco.clear();
                        listaEndereco.addAll(dbEndereco.findAllEndereco());

//                        Toast.makeText(contexto, "tamanho Animal: "+ String.valueOf(listaAnimal.size()),Toast.LENGTH_LONG).show();
//                        Toast.makeText(contexto, "tamanho Medico: "+ String.valueOf(listaMedico.size()),Toast.LENGTH_LONG).show();
//                        Toast.makeText(contexto, "tamanho Endereco: "+ String.valueOf(listaEndereco.size()),Toast.LENGTH_LONG).show();


                    }
                } catch (IllegalStateException | JsonSyntaxException exception) {
                    Log.e("Erro", "Erro" + exception.getMessage());
                    exception.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Erro", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=UTF-8");
                return header;
            }
        };
        getRequest.setRetryPolicy(new DefaultRetryPolicy(15000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getRequest);

    }

}
