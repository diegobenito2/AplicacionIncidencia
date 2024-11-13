package com.example.aplicacionincidencias.Sala;

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

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntSala;

public class Activity_Info_Sala extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntSala sala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_sala);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int codigoSala = getIntent().getExtras().getInt("codigo");
        if (codigoSala > 0) {
            for (EntSala s : GestionIncidencias.getArSalas()) {
                if (s.getCodigoSala() == codigoSala) {
                    sala = s;
                }
            }
        } else {
            sala = new EntSala(0, "", "");
        }
        if (sala != null) {
            TextView tvCodigoSala = findViewById(R.id.tvinfoCodigoSala);
            EditText edNombreSala = findViewById(R.id.editNombreSala);
            EditText edDescripcion = findViewById(R.id.editDescripcionSala);

            tvCodigoSala.setText(String.valueOf(sala.getCodigoSala()));
            edNombreSala.setText(sala.getNombre());
            edDescripcion.setText(sala.getDescripcion());
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
            Intent intent = new Intent(this, activitySalas.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (sala != null) {
                TextView tvCodigoSala = findViewById(R.id.tvinfoCodigoSala);
                EditText edNombreSala = findViewById(R.id.editNombreSala);
                EditText edDescripcion = findViewById(R.id.editDescripcionSala);
                sala.setCodigoSala(tvCodigoSala.getId());
                sala.setNombre(String.valueOf(edNombreSala.getText()));
                sala.setDescripcion(String.valueOf(edDescripcion.getText()));
                if (sala.getCodigoSala() == 0) {
                    sala.setCodigoSala(GestionIncidencias.getArSalas().size() + 1);
                    GestionIncidencias.getArSalas().add(0, sala);
                }
                Intent intentVolverSalas = new Intent(view.getContext(), activitySalas.class);
                startActivity(intentVolverSalas);
            }
        });
    }
}