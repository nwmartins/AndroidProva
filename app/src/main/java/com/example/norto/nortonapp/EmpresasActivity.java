package com.example.norto.nortonapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.norto.nortonapp.model.Empresa;

import java.util.ArrayList;
import java.util.List;

public class EmpresasActivity extends AppCompatActivity {

    private Button btCancelarEmpresa;
    private ListView lvEmpresas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();
        loadEvents();

    }

    private void loadEvents() {
        fillListView();

        lvEmpresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent output = new Intent();
                output.putExtra("EMPRESASEL", ((Empresa) lvEmpresas.getItemAtPosition(position))); //PASSA A EMPRESA SEL
                setResult(RESULT_OK, output);
                finish();
            }
        });

        btCancelarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }

    private void fillListView() {
        List<Empresa> empresaList = new ArrayList<>();

        empresaList.add(new Empresa(1, "Sigha Sistenas"));
        empresaList.add(new Empresa(2, "Fag"));
        empresaList.add(new Empresa(3, "Prati"));
        empresaList.add(new Empresa(4, "Maxicon"));

        ArrayAdapter<Empresa> empresaArrayAdapter = new ArrayAdapter<>(
                EmpresasActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                empresaList
        );
        lvEmpresas.setAdapter(empresaArrayAdapter);
    }

    private void loadComponents() {
        btCancelarEmpresa = findViewById(R.id.btCancelarEmpresa);
        lvEmpresas = findViewById(R.id.lvEmpresas);
    }

}
