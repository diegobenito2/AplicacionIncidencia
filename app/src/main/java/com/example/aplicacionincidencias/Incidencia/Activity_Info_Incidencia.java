package com.example.aplicacionincidencias.Incidencia;

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

import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Sala.activitySalas;

import java.sql.Date;
import java.time.LocalDate;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntIncidencia;
import gestionincidencias.entidades.EntSala;

public class Activity_Info_Incidencia extends menutrespuntos implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntIncidencia incidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_incidencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int codigoIncidencia = getIntent().getExtras().getInt("codigoIncidencia");
        String descripcionIncidencia = getIntent().getExtras().getString("descripcionIncidencia");
        if (codigoIncidencia > 0) {
            for (EntIncidencia i : GestionIncidencias.getArIncidencias()) {
                if (i.getCodigoIncidencia() == codigoIncidencia) {
                    incidencia = i;
                }
            }
        } else if (codigoIncidencia == 0 && descripcionIncidencia.isEmpty()) {
            incidencia = new EntIncidencia(0, "", 0, null, 0);
        } else if (codigoIncidencia == 0) {
            for (EntIncidencia i : GestionIncidencias.getArIncidencias()) {
                if (i.getCodigoIncidencia() == codigoIncidencia) {
                    incidencia = i;
                }
            }
        }
        if (incidencia != null) {

            EditText edCodigoIncidencia = findViewById(R.id.edinfoCodigoIncidencia);
            EditText edDescripcionIncidencia = findViewById(R.id.edDescripcionIncidencia);
            EditText edCodigoElemento = findViewById(R.id.edInfoCodigoElemento);
            EditText edCodigoUsuarioCreacion = findViewById(R.id.edInfoCodigoUsuarioCreacion);
            TextView tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);

            edCodigoIncidencia.setText(String.valueOf(incidencia.getCodigoIncidencia()));
            edDescripcionIncidencia.setText(incidencia.getDescripcion());
            edCodigoElemento.setText(String.valueOf(incidencia.getIdElemento()));
            edCodigoUsuarioCreacion.setText(String.valueOf(incidencia.getIdUsuarioCreacion()));
            tvFechaCreacion.setText(String.valueOf(incidencia.getFechaCreacion()));

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
            Intent intent = new Intent(this, activityIncidencia.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (incidencia != null) {
                EditText edCodigoIncidencia = findViewById(R.id.edinfoCodigoIncidencia);
                EditText edDescripcionIncidencia = findViewById(R.id.edDescripcionIncidencia);
                EditText edCodigoElemento = findViewById(R.id.edInfoCodigoElemento);
                EditText edCodigoUsuarioCreacion = findViewById(R.id.edInfoCodigoUsuarioCreacion);
                TextView tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);
                if (incidencia.getCodigoIncidencia() != 0) {
                    incidencia.setCodigoIncidencia(Integer.parseInt(edCodigoIncidencia.getText().toString()));
                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());
                    incidencia.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    incidencia.setIdUsuarioCreacion(Integer.parseInt(edCodigoUsuarioCreacion.getText().toString()));
                    incidencia.setFechaCreacion(Date.valueOf(tvFechaCreacion.getText().toString()));
                } else if (incidencia.getCodigoIncidencia() == 0 && incidencia.getDescripcion().isEmpty()) {
                    incidencia.setCodigoIncidencia(Integer.parseInt(edCodigoIncidencia.getText().toString()));
                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());
                    incidencia.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    incidencia.setIdUsuarioCreacion(Integer.parseInt(edCodigoUsuarioCreacion.getText().toString()));
                    incidencia.setFechaCreacion(Date.valueOf(tvFechaCreacion.getText().toString()));
                    GestionIncidencias.getArIncidencias().add(GestionIncidencias.getArIncidencias().size(), incidencia);
                } else if (incidencia.getCodigoIncidencia() == 0) {
                    incidencia.setCodigoIncidencia(Integer.parseInt(edCodigoIncidencia.getText().toString()));
                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());
                    incidencia.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    incidencia.setIdUsuarioCreacion(Integer.parseInt(edCodigoUsuarioCreacion.getText().toString()));
                    incidencia.setFechaCreacion(Date.valueOf(tvFechaCreacion.getText().toString()));
                }
                Intent intentVolverIncidencias = new Intent(view.getContext(), activityIncidencia.class);
                startActivity(intentVolverIncidencias);
            }
        });
    }
}