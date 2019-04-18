package com.example.norto.nortonapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.norto.nortonapp.model.Atendimento;
import com.example.norto.nortonapp.model.Empresa;
import com.example.norto.nortonapp.model.TipoAtendimento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AtendimentoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private EditText etAssunto, etContato, etTelefone, etEmail;
    private TextView tvData, tvEmpresa;
    private Button btSalvar, btCancelar, btEmpresa;
    private Spinner spTpAtendimento;
    private ListView lvAtendimentos;

    private final int EMPRESA = 1;

    private int day, month, year, tipoData, indexEdicao;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;

    private List<Atendimento> atendimentoList = new ArrayList<>();
    private Empresa empresa = new Empresa();
    private Atendimento atendimento = new Atendimento();
    private Boolean isEdicao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();
        loadEvents();

    }

    private void loadComponents() {
        etAssunto = findViewById(R.id.etAssunto);
        etContato = findViewById(R.id.etContato);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        tvEmpresa = findViewById(R.id.tvEmpresa);
        tvData = findViewById(R.id.tvData);
        btSalvar = findViewById(R.id.btSalvar);
        btCancelar = findViewById(R.id.btCancelar);
        btEmpresa = findViewById(R.id.btEmpresa);
        spTpAtendimento = findViewById(R.id.spTpAtendimento);
        lvAtendimentos = findViewById(R.id.lvAtendimentos);

        fillSpinner();

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(AtendimentoActivity.this, this, year, month, day);
    }

    private void fillSpinner() {

        List<TipoAtendimento> tpAtendimentoList = new ArrayList<>();
        tpAtendimentoList.add(new TipoAtendimento(1, "Correções"));
        tpAtendimentoList.add(new TipoAtendimento(2, "Melhorias"));
        tpAtendimentoList.add(new TipoAtendimento(3, "Validações"));

        ArrayAdapter<TipoAtendimento> AdapterTpAtendimento = new ArrayAdapter<>(
                AtendimentoActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                tpAtendimentoList
        );
        spTpAtendimento.setAdapter(AdapterTpAtendimento);
    }

    private void loadEvents() {
        btEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AtendimentoActivity.this, EmpresasActivity.class);
                startActivityForResult(intent, EMPRESA);
            }
        });

        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        refreshLvAtendimento();

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atendimento = new Atendimento();
                atendimento.setId(1);
                atendimento.setAssunto(etAssunto.getText().toString().trim());
                atendimento.setContato(etContato.getText().toString().trim());
                atendimento.setTelefone(etTelefone.getText().toString().trim());
                atendimento.setEmail(etEmail.getText().toString().trim());
                //atendimento.setData((Date) (day, month, year);
                atendimento.setEmpresa(empresa);
                atendimento.setTpAtendimento((TipoAtendimento) spTpAtendimento.getSelectedItem());
                if (isEdicao){
                    atendimentoList.set(indexEdicao, atendimento);
                    isEdicao = false;
                    btSalvar.setText(R.string.salvar);
                } else {
                    atendimentoList.add(atendimento);
                }
                clearFields();
                refreshLvAtendimento();
            }

        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
                if (isEdicao) {
                    isEdicao = false;
                    btSalvar.setText(R.string.salvar);
                }
            }
        });

        lvAtendimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                atendimento = (Atendimento) lvAtendimentos.getItemAtPosition(position);
                loadFields();
                isEdicao = true;
                indexEdicao = position;
                btSalvar.setText(R.string.atualizar);
            }
        });
    }

    private void loadFields() {
        etAssunto.setText(atendimento.getAssunto());
        etContato.setText(atendimento.getContato());
        etTelefone.setText(atendimento.getTelefone());
        etEmail.setText(atendimento.getEmail());
        //tvData.setText(atendimento.getData());
        tvEmpresa.setText(atendimento.getEmpresa().toString());
        //spTpAtendimento.

    }

    private void clearFields() {
        etAssunto.setText("");
        etContato.setText("");
        etTelefone.setText("");
        etEmail.setText("");
        tvData.setText("");
        tvEmpresa.setText(R.string.empresa);
        fillSpinner();
    }

    private void refreshLvAtendimento() {
        ArrayAdapter<Atendimento> atendimentoArrayAdapter = new ArrayAdapter<>(
                AtendimentoActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                atendimentoList
        );
        lvAtendimentos.setAdapter(atendimentoArrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_CANCELED:
                break;
            case RESULT_OK:
                empresa = (Empresa) data.getExtras().get("EMPRESASEL");
                tvEmpresa.setText(empresa.toString());
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_atendimento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        tvData.setText(day + "/" + month + 1 + "/" + year);
    }
}
