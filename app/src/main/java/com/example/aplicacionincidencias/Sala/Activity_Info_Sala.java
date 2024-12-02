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
        String nombreSala = getIntent().getExtras().getString("nombre");

        if (codigoSala >= 0) {
            for (EntSala s : GestionIncidencias.getArSalas()) {
                if (s.getCodigoSala() == codigoSala) {
                    sala = s;
                }
            }
        } else if (codigoSala == 0 && nombreSala.isEmpty()) {
            sala = new EntSala(0, "", "");
        } else if (codigoSala == 0) {
            for (EntSala s : GestionIncidencias.getArSalas()) {
                if (s.getCodigoSala() == codigoSala) {
                    sala = s;
                }
            }
        }
        if (sala != null) {
            EditText edCodigoSala = findViewById(R.id.edinfoCodigoSala);
            EditText edNombreSala = findViewById(R.id.editNombreSala);
            EditText edDescripcion = findViewById(R.id.editDescripcionSala);

            edCodigoSala.setText(String.valueOf(sala.getCodigoSala()));
            edNombreSala.setText(sala.getNombre());
            edDescripcion.setText(sala.getDescripcion());
            // Al ser un nuevo tipo y el código ponerse automaticamente pues los campos se ocultan.
            if (sala.getCodigoSala() == 0 && sala.getNombre().isEmpty()) {
                TextView tvCodigoSala = findViewById(R.id.tvCodigoSala);
                tvCodigoSala.setVisibility(View.INVISIBLE);
                edCodigoSala.setVisibility(View.INVISIBLE);
            }
        }

        btnVolver = findViewById(R.id.btnVolverSala);
        btnGuardar = findViewById(R.id.btnGuardarSala);
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
                TextView tvCodigoSala=findViewById(R.id.tvCodigoSala);
                EditText edCodigoSala = findViewById(R.id.edinfoCodigoSala);
                EditText edNombreSala = findViewById(R.id.editNombreSala);
                EditText edDescripcion = findViewById(R.id.editDescripcionSala);
                if (sala.getCodigoSala() != 0) {
                    sala.setCodigoSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    sala.setNombre(edNombreSala.getText().toString());
                    sala.setDescripcion(edDescripcion.getText().toString());
                } else if (sala.getCodigoSala() == 0 && sala.getNombre().isEmpty()) {
                    tvCodigoSala.setVisibility(View.INVISIBLE);
                    edCodigoSala.setVisibility(View.INVISIBLE);
                    sala.setCodigoSala(GestionIncidencias.getArSalas().size()+1);
                    sala.setNombre(edNombreSala.getText().toString());
                    sala.setDescripcion(edDescripcion.getText().toString());
                    GestionIncidencias.getArSalas().add(GestionIncidencias.getArSalas().size(), sala);
                } else if (sala.getCodigoSala() == 0) {
                    sala.setCodigoSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    sala.setNombre(edNombreSala.getText().toString());
                    sala.setDescripcion(edDescripcion.getText().toString());
                }
                Intent intentVolverSalas = new Intent(view.getContext(), activitySalas.class);
                startActivity(intentVolverSalas);
            }
        });
    }
}













