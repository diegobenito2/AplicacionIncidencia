package com.example.aplicacionincidencias.Tipo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Sala.activitySalas;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntTipo;

public class Activity_Info_Tipo extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntTipo tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_tipo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int codigoTipo = getIntent().getExtras().getInt("codigo");
        if (codigoTipo > 0) {
            for (EntTipo t : GestionIncidencias.getArTipos()) {
                if (t.getCodigoTipo() == codigoTipo) {
                    tipo = t;
                }
            }
        } else{
            tipo= new EntTipo(0,"","");
        }
        if (tipo != null) {
            EditText edCodigoTipo = findViewById(R.id.edinfoCodigoTipo);
            EditText edNombreTipo = findViewById(R.id.editNombreTipo);
            EditText edDescripcion = findViewById(R.id.editDescripcionTipo);

            edCodigoTipo.setText(String.valueOf(tipo.getCodigoTipo()));
            edNombreTipo.setText(tipo.getNombre());
            edDescripcion.setText(tipo.getDescripcion());
        }


        initComponentsVolverGuardar();
        initListenersVolverGuardar();


    }

    private void initComponentsVolverGuardar() {
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    private void initListenersVolverGuardar() {
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityTipo.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (tipo != null) {
                EditText edCodigoTipo = findViewById(R.id.edinfoCodigoTipo);
                EditText edNombreTipo = findViewById(R.id.editNombreTipo);
                EditText edDescripcion = findViewById(R.id.editDescripcionTipo);
                tipo.setCodigoTipo(Integer.parseInt(edCodigoTipo.getText().toString()));
                tipo.setNombre(String.valueOf(edNombreTipo.getText()));
                tipo.setDescripcion(String.valueOf(edDescripcion.getText()));
                if (tipo.getCodigoTipo() == 0) {
                    tipo.setCodigoTipo(GestionIncidencias.getArSalas().size() + 1);
                    GestionIncidencias.getArTipos().add(0, tipo);
                }
                Intent intentVolverTipo = new Intent(view.getContext(), activityTipo.class);
                startActivity(intentVolverTipo);
            }
        });
    }
}