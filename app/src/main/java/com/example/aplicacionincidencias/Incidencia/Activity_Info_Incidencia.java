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

public class Activity_Info_Incidencia extends menutrespuntos implements View.OnClickListener{
    private Button btnVolver, btnGuardar;

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
        Intent infoIncidencia = this.getIntent();

        Bundle bnd = infoIncidencia.getExtras();

        int codigoIncidencia = bnd.getInt("codigoIncidencia");
        String descripcionIncidencia = bnd.getString("descripcionIncidencia");
        int codigoElemento = bnd.getInt("codigoElemento");
        int codigoUsuarioCreacion = bnd.getInt("codigoUsuarioCreacion");
        String fechaCreacion = bnd.getString("fechaCreacion");

        EditText edCodigoIncidencia = findViewById(R.id.edinfoCodigoIncidencia);
        EditText edDescripcionIncidencia = findViewById(R.id.edDescripcionIncidencia);
        EditText edCodigoElemento = findViewById(R.id.edInfoCodigoElemento);
        EditText edCodigoUsuarioCreacion = findViewById(R.id.edInfoCodigoUsuarioCreacion);
        EditText edFechaCreacion = findViewById(R.id.edInfoFechaCreacion);

        edCodigoIncidencia.setText(String.valueOf(codigoIncidencia));
        edDescripcionIncidencia.setText(descripcionIncidencia);
        edCodigoElemento.setText(String.valueOf(codigoElemento));
        edCodigoUsuarioCreacion.setText(String.valueOf(codigoUsuarioCreacion));
        edFechaCreacion.setText(String.valueOf(fechaCreacion));
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

        });
    }
}