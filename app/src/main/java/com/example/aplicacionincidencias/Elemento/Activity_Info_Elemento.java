package com.example.aplicacionincidencias.Elemento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.aplicacionincidencias.R;
import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntElemento;

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
            EditText tvCodigoElemento = findViewById(R.id.edinfoCodigoElemento);
            EditText edNombreElemento = findViewById(R.id.edNombreElemento);
            EditText edDescripcion = findViewById(R.id.edDescripcionElemento);
            EditText edTipo = findViewById(R.id.editcodigoTipoElemento);

            tvCodigoElemento.setText(String.valueOf(elemento.getCodigoElemento()));
            edNombreElemento.setText(elemento.getNombre());
            edDescripcion.setText(elemento.getDescripcion());
            edTipo.setText(String.valueOf(elemento.getIdTipo()));
        }


        btnVolver = findViewById(R.id.btnVolverElemento);
        btnGuardar = findViewById(R.id.btnGuardarElemento);

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
                EditText edCodigoElemento = findViewById(R.id.edinfoCodigoElemento);
                EditText edNombreElemento = findViewById(R.id.edNombreElemento);
                EditText edDescripcion = findViewById(R.id.edDescripcionElemento);
                EditText edTipo = findViewById(R.id.editcodigoTipoElemento);
                if (elemento.getCodigoElemento()!= 0) {
                    elemento.setCodigoElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    elemento.setNombre(String.valueOf(edNombreElemento.getText()));
                    elemento.setDescripcion(String.valueOf(edDescripcion.getText()));
                    elemento.setIdTipo(Integer.parseInt(edTipo.getText().toString()));
                } else if (elemento.getCodigoElemento() == 0 && elemento.getNombre().isEmpty()) {
                    elemento.setCodigoElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    elemento.setNombre(String.valueOf(edNombreElemento.getText()));
                    elemento.setDescripcion(String.valueOf(edDescripcion.getText()));
                    elemento.setIdTipo(Integer.parseInt(edTipo.getText().toString()));
                    GestionIncidencias.getArElementos().add(GestionIncidencias.getArElementos().size(), elemento);
                } else if (elemento.getCodigoElemento() == 0) {
                    elemento.setCodigoElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    elemento.setNombre(String.valueOf(edNombreElemento.getText()));
                    elemento.setDescripcion(String.valueOf(edDescripcion.getText()));
                    elemento.setIdTipo(Integer.parseInt(edTipo.getText().toString()));
                }
                Intent intentVolverSalas = new Intent(view.getContext(), activityElemento.class);
                startActivity(intentVolverSalas);
            }
        });
    }
}