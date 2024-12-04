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
import gestionincidencias.entidades.EntSala;
import gestionincidencias.entidades.EntTipo;

public class Activity_Info_Tipo extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar,btnBorrar;
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
        String nombreTipo = getIntent().getExtras().getString("nombre");

        if (codigoTipo > 0) {
            for (EntTipo t : GestionIncidencias.getArTipos()) {
                if (t.getCodigoTipo() == codigoTipo) {
                    tipo = t;
                }
            }
        } else if (codigoTipo == 0 && nombreTipo.isEmpty()) {
            tipo = new EntTipo(0, "", "");
        }
        if (tipo != null) {
            TextView edCodigoTipo = findViewById(R.id.tvinfoCodigoTipo);
            EditText edNombreTipo = findViewById(R.id.editNombreTipo);
            EditText edDescripcion = findViewById(R.id.editDescripcionTipo);

            edCodigoTipo.setText(String.valueOf(tipo.getCodigoTipo()));
            edNombreTipo.setText(tipo.getNombre());
            edDescripcion.setText(tipo.getDescripcion());

        // Al ser un nuevo tipo y el código ponerse automaticamente pues los campos se ocultan.
            if (tipo.getCodigoTipo() == 0 && tipo.getNombre().isEmpty()) {
                TextView tvCodigoTipo = findViewById(R.id.tvCodigoTipo);
                tvCodigoTipo.setVisibility(View.INVISIBLE);
                edCodigoTipo.setVisibility(View.INVISIBLE);
            }
        }

        btnVolver = findViewById(R.id.btnVolverTipo);
        btnGuardar = findViewById(R.id.btnGuardarTipo);
        btnBorrar = findViewById(R.id.btnBorrarTipo);
        btnBorrar.setOnClickListener((View.OnClickListener)this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);

    }


    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityTipo.class);
            startActivity(intent);
        });
        btnBorrar.setOnClickListener(v -> {
            GestionIncidencias.getArTipos().remove(tipo);
            Intent intentVolverTipo = new Intent(view.getContext(), activityTipo.class);
            startActivity(intentVolverTipo);
        });
        btnGuardar.setOnClickListener(v -> {
            if (tipo != null) {
                TextView edCodigoTipo = findViewById(R.id.tvinfoCodigoTipo);
                EditText edNombreTipo = findViewById(R.id.editNombreTipo);
                EditText edDescripcion = findViewById(R.id.editDescripcionTipo);
                if (tipo.getCodigoTipo() != 0) {
                    tipo.setCodigoTipo(Integer.parseInt(edCodigoTipo.getText().toString()));
                    tipo.setNombre(edNombreTipo.getText().toString());
                    tipo.setDescripcion(edDescripcion.getText().toString());
                } else if (tipo.getCodigoTipo() == 0 && tipo.getNombre().isEmpty()) {
                    tipo.setCodigoTipo(GestionIncidencias.getArTipos().size() + 1);
                    tipo.setNombre(edNombreTipo.getText().toString());
                    tipo.setDescripcion(edDescripcion.getText().toString());
                    GestionIncidencias.getArTipos().add(GestionIncidencias.getArTipos().size(), tipo);
                } else if (tipo.getCodigoTipo() == 0) {
                    tipo.setCodigoTipo(Integer.parseInt(edCodigoTipo.getText().toString()));
                    tipo.setNombre(edNombreTipo.getText().toString());
                    tipo.setDescripcion(edDescripcion.getText().toString());

                }
                Intent intentVolverTipo = new Intent(view.getContext(), activityTipo.class);
                startActivity(intentVolverTipo);
            }
        });
    }
}