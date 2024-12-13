package com.example.aplicacionincidencias.Rol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntRol;

public class Activity_Info_Rol extends AppCompatActivity implements View.OnClickListener {
    private EntRol rol;
    private Button btnVolver, btnGuardar, btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_rol);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int codigoRol = getIntent().getExtras().getInt("codigo");
        String nombreRol = getIntent().getExtras().getString("nombre");

        if (codigoRol > 0) {
            for (EntRol r : GestionIncidencias.getArRoles()) {
                if (r.getCodigo() == codigoRol) {
                    rol = r;
                }
            }
        } else if (codigoRol == 0 && nombreRol.isEmpty()) {
            rol = new EntRol(0, "", "", 0);
        }
        if (rol != null) {
            TextView tvInfoCodigoRol = findViewById(R.id.tvinfoCodigoRol);
            EditText edNombreRol = findViewById(R.id.edNombreRol);
            EditText edDescripcion = findViewById(R.id.edDescripcionRol);
            EditText edNivelAcceso = findViewById(R.id.edNivelAccesoRol);
            tvInfoCodigoRol.setText(String.valueOf(rol.getCodigo()));
            edNombreRol.setText(rol.getNombre());
            edDescripcion.setText(rol.getDescripcion());
            edNivelAcceso.setText(String.valueOf(rol.getNivel_acceso()));
            if (codigoRol == 0 && nombreRol.isEmpty()) {
                TextView tvCodigoRol = findViewById(R.id.tvCodigoRol);
                tvInfoCodigoRol.setVisibility(View.INVISIBLE);
                tvCodigoRol.setVisibility(View.INVISIBLE);
            }

        }
        btnVolver = findViewById(R.id.btnVolverRol);
        btnGuardar = findViewById(R.id.btnGuardarRol);
        btnBorrar = findViewById(R.id.btnBorrarRol);
        btnBorrar.setOnClickListener((View.OnClickListener) this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_Rol.class);
            startActivity(intent);
        });
        btnBorrar.setOnClickListener(v -> {
            GestionIncidencias.getArRoles().remove(rol);
            Toast.makeText(getApplicationContext(), "Rol Borrado Correctamente", Toast.LENGTH_SHORT).show();
            Intent intentVolverRol = new Intent(view.getContext(), Activity_Rol.class);
            startActivity(intentVolverRol);
        });
        btnGuardar.setOnClickListener(v -> {
            if (rol != null) {
                EditText edNombreRol = findViewById(R.id.edNombreRol);
                EditText edDescripcion = findViewById(R.id.edDescripcionRol);
                EditText edNivelAcceso = findViewById(R.id.edNivelAccesoRol);

                rol.setNombre(edNombreRol.getText().toString());
                rol.setDescripcion(edDescripcion.getText().toString());
                rol.setNivel_acceso(Integer.parseInt(edNivelAcceso.getText().toString()));

                if (rol.getCodigo() == 0) {
                    rol.setCodigo(GestionIncidencias.getArRoles().size() + 1);
                    GestionIncidencias.getArRoles().add(GestionIncidencias.getArRoles().size(), rol);
                    Toast.makeText(getApplicationContext(), "Rol Añadido Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Rol Guardado Correctamente", Toast.LENGTH_SHORT).show();
                }
                Intent intentVolverRol = new Intent(view.getContext(), Activity_Rol.class);
                startActivity(intentVolverRol);
            }
        });
    }
}