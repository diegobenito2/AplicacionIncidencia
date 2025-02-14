package com.example.aplicacionincidencias.Elemento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Tipo.TipoHelper;

import java.util.ArrayList;

import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntTipo;

public class Activity_Info_Elemento extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar, btnBorrar;
    private EntElemento elemento;
    private int TipoSelected;
    private ElementoHelper eh = new ElementoHelper(this, "bbddIncidencias", null, 1);
    private TipoHelper th = new TipoHelper(this, "bbddIncidencias", null, 1);

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
            elemento = eh.obtenerElemento(codigoElemento);
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


            ArrayList<EntTipo> arTipos = th.obtenerTipos();

            // Configuración del Spinner de Usuarios
            ArrayList<String> listaTipos = new ArrayList<>();
            ArrayList<Integer> listaTiposIds = new ArrayList<>();
            for (EntTipo type : arTipos) {
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
        if (view.getId() == R.id.btnVolverElemento) {

            Intent intent = new Intent(this, activityElemento.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btnVolverElemento) {
            int borrado = eh.borrarElemento(elemento.getCodigoElemento());
            if (borrado == 1) {
                Toast.makeText(getApplicationContext(), "Elemento Borrado Correctamente", Toast.LENGTH_SHORT).show();
            }
            Intent intentVolverElemento = new Intent(view.getContext(), activityElemento.class);
            startActivity(intentVolverElemento);
        } else if (view.getId() == R.id.btnGuardarElemento) {

            if (elemento != null) {
                EditText edNombreElemento = findViewById(R.id.edNombreElemento);
                EditText edDescripcion = findViewById(R.id.edDescripcionElemento);
                Spinner spinnerTipos = findViewById(R.id.spinnerTipoElemento);
                String TipoSelected = spinnerTipos.getSelectedItem().toString(); //Guardas el nombre del Tipo seleccionado
                EntTipo tipo = th.obtenerNombreTipo(TipoSelected);
                elemento.setNombre(edNombreElemento.getText().toString());
                elemento.setDescripcion(edDescripcion.getText().toString());
                elemento.setTipoElemento(tipo);
                elemento.setIdTipo(tipo.getCodigoTipo());

                if (elemento.getCodigoElemento() == 0) {
                    eh.crearElemento(elemento);
                    Toast.makeText(getApplicationContext(), "Elemento Añadido Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    eh.actualizarElemento(elemento);
                    Toast.makeText(getApplicationContext(), "Elemento Guardado Correctamente", Toast.LENGTH_SHORT).show();
                }
                Intent intentVolverSalas = new Intent(view.getContext(), activityElemento.class);
                startActivity(intentVolverSalas);
            }
        }


    }
}