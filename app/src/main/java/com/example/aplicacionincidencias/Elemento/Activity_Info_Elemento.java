package com.example.aplicacionincidencias.Elemento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

import java.util.ArrayList;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntTipo;

public class Activity_Info_Elemento extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar,btnBorrar;
    private EntElemento elemento;
    private int TipoSelected;

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
        } else if (codigoElemento == 0 && nombreElemento.isBlank()) {
            elemento = new EntElemento(0, "", "", 0);
        }
        if (elemento != null) {
            TextView tvInfoCodigoElemento = findViewById(R.id.tvinfoCodigoElemento);
            EditText edNombreElemento = findViewById(R.id.edNombreElemento);
            EditText edDescripcion = findViewById(R.id.edDescripcionElemento);

////////////////////////////////////////////////////////Inicio Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////

            // Configurar los Spinners
            Spinner spinnerTipos = findViewById(R.id.spinnerTipoElemento);


            // Configuración del Spinner de Usuarios
            ArrayList<String> listaTipos = new ArrayList<>();
            ArrayList<Integer> listaTiposIds = new ArrayList<>();
            for (EntTipo type : GestionIncidencias.getArTipos()) {
                listaTipos.add(type.getNombre());
                listaTiposIds.add(type.getCodigoTipo());
            }

            ArrayAdapter<String> adapterTipos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaTipos);
            spinnerTipos.setAdapter(adapterTipos);

            // Seleccionar el usuario actual del préstamo
            if (elemento.getIdTipo() > 0) {
                TipoSelected = listaTiposIds.indexOf(elemento.getIdTipo());
                if (TipoSelected != -1) {
                    spinnerTipos.setSelection(TipoSelected);
                }
            }
////////////////////////////////////////////////////////Fin Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////
            if (codigoElemento == 0 && nombreElemento.isBlank()) {
                TextView tvCodigoElemento = findViewById(R.id.tvCodigoElemento);
                tvInfoCodigoElemento.setVisibility(View.INVISIBLE);
                tvCodigoElemento.setVisibility(View.INVISIBLE);
            }
            tvInfoCodigoElemento.setText(String.valueOf(elemento.getCodigoElemento()));
            edNombreElemento.setText(elemento.getNombre());
            edDescripcion.setText(elemento.getDescripcion());

        }


        btnVolver = findViewById(R.id.btnVolverElemento);
        btnGuardar = findViewById(R.id.btnGuardarElemento);
        btnBorrar = findViewById(R.id.btnBorrarElemento);
        btnBorrar.setOnClickListener((View.OnClickListener) this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);


    }


    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityElemento.class);
            startActivity(intent);
        });
        btnBorrar.setOnClickListener(v -> {
            GestionIncidencias.getArElementos().remove(elemento);
            Intent intentVolverElemento = new Intent(view.getContext(), activityElemento.class);
            startActivity(intentVolverElemento);
        });
        btnGuardar.setOnClickListener(v -> {
            if (elemento != null) {
                EditText edNombreElemento = findViewById(R.id.edNombreElemento);
                EditText edDescripcion = findViewById(R.id.edDescripcionElemento);
                Spinner spinnerTipos = findViewById(R.id.spinnerTipoElemento);
                String TipoSelected = spinnerTipos.getSelectedItem().toString(); //Guardas el nombre del Tipo seleccionado
                for (EntTipo type : GestionIncidencias.getArTipos()) { //Recorres la lista de Tipos.
                    if (elemento.getNombre().equals(TipoSelected)) { //Cuando el nombre es igual al nombre de Tipo seleccionado.
                        elemento.setIdTipo(elemento.getIdTipo()); //Cambia el código de Tipo del Elemento al del Tipo seleccionado.
                        elemento.setTipoElemento(type); //Cambia el objeto Tipo del Elemento por el nuevo Tipo.
                    }
                }
                if (elemento.getCodigoElemento() != 0) {
                    elemento.setCodigoElemento(elemento.getCodigoElemento());
                    elemento.setNombre(String.valueOf(edNombreElemento.getText()));
                    elemento.setDescripcion(String.valueOf(edDescripcion.getText()));

                } else if (elemento.getCodigoElemento() == 0 && elemento.getNombre().isEmpty()) {
                    elemento.setCodigoElemento(GestionIncidencias.getArElementos().size() + 1);
                    elemento.setNombre(String.valueOf(edNombreElemento.getText()));
                    elemento.setDescripcion(String.valueOf(edDescripcion.getText()));
                    GestionIncidencias.getArElementos().add(GestionIncidencias.getArElementos().size(), elemento);
                }
                Intent intentVolverSalas = new Intent(view.getContext(), activityElemento.class);
                startActivity(intentVolverSalas);
            }
        });
    }
}