package com.example.aplicacionincidencias.Elemento;

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
import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntSala;

public class Activity_Info_Elemento extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntElemento elemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_elemento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int codigoElemento = getIntent().getExtras().getInt("codigo");
        String nombreElemento = getIntent().getExtras().getString("nombre");

        if (codigoElemento > 0) {
            for (EntElemento e : GestionIncidencias.getArElementos()) {
                if (e.getCodigoElemento() == codigoElemento) {
                    elemento = e;
                }
            }
        } else if (codigoElemento == 0 && nombreElemento.isEmpty()) {
            elemento = new EntElemento(0, "", "", 0);
        } else if (codigoElemento == 0) {
            for (EntElemento e : GestionIncidencias.getArElementos()) {
                if (e.getCodigoElemento() == codigoElemento) {
                    elemento = e;
                }
            }
        }
        if (elemento != null) {
            TextView tvCodigoElemento = findViewById(R.id.tvinfoCodigoElemento);
            EditText edNombreElemento = findViewById(R.id.edNombreElemento);
            EditText edDescripcion = findViewById(R.id.edDescripcionElemento);
            EditText edTipo = findViewById(R.id.editcodigoTipoElemento);

            tvCodigoElemento.setText(String.valueOf(elemento.getCodigoElemento()));
            edNombreElemento.setText(elemento.getNombre());
            edDescripcion.setText(elemento.getDescripcion());
            edTipo.setText(String.valueOf(elemento.getIdTipo()));
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
            Intent intent = new Intent(this, activityElemento.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (elemento != null) {
                TextView tvCodigoElemento = findViewById(R.id.tvinfoCodigoElemento);
                EditText edNombreElemento = findViewById(R.id.edNombreElemento);
                EditText edDescripcion = findViewById(R.id.edDescripcionElemento);
                EditText edTipo = findViewById(R.id.editcodigoTipoElemento);
                if (elemento.getCodigoElemento()!= 0) {
                    tvCodigoElemento.setText(String.valueOf(elemento.getCodigoElemento()));
                    edNombreElemento.setText(elemento.getNombre());
                    edDescripcion.setText(elemento.getDescripcion());
                    edTipo.setText(String.valueOf(elemento.getIdTipo()));
                } else if (elemento.getCodigoElemento() == 0 && elemento.getNombre().isEmpty()) {
                    tvCodigoElemento.setText(String.valueOf(elemento.getCodigoElemento()));
                    edNombreElemento.setText(elemento.getNombre());
                    edDescripcion.setText(elemento.getDescripcion());
                    edTipo.setText(String.valueOf(elemento.getIdTipo()));
                    GestionIncidencias.getArElementos().add(GestionIncidencias.getArElementos().size(), elemento);
                } else if (elemento.getCodigoElemento() == 0) {
                    tvCodigoElemento.setText(String.valueOf(elemento.getCodigoElemento()));
                    edNombreElemento.setText(elemento.getNombre());
                    edDescripcion.setText(elemento.getDescripcion());
                    edTipo.setText(String.valueOf(elemento.getIdTipo()));
                }
                Intent intentVolverSalas = new Intent(view.getContext(), activitySalas.class);
                startActivity(intentVolverSalas);
            }
        });
    }
}