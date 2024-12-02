package com.example.aplicacionincidencias.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import gestionincidencias.entidades.EntUsuario;

public class Activity_Info_Usuario extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntUsuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int codigoUsuario = getIntent().getExtras().getInt("codigo");
        String nombreUsuario = getIntent().getExtras().getString("nombre");

        if (codigoUsuario >= 0) {
            for (EntUsuario u : GestionIncidencias.getArUsuarios()) {
                if (u.getCodigoUsuario() == codigoUsuario) {
                    usuario = u;
                }
            }
        } else if (codigoUsuario == 0 && nombreUsuario.isBlank()) {
            usuario = new EntUsuario(codigoUsuario, nombreUsuario, "", "", "", "");
        }
        if (usuario != null) {
            EditText edCodigoUsuario = findViewById(R.id.edIdCodigoUsuario);
            EditText edNombreUsuario = findViewById(R.id.edNombreUsuario);
            EditText edCorreoUsuario = findViewById(R.id.edcorreoUsuario);
            EditText edTelefonoUsuario = findViewById(R.id.edtelefonoUsuario);
            EditText edContraseñaUsuario = findViewById(R.id.edpasswordUsuario);
            EditText edRolUsuario = findViewById(R.id.edrolUsuario);

            if (usuario.getCodigoUsuario() == 0 && usuario.getNombre().equals("")) {
                TextView tvCodigoUsuario=findViewById(R.id.IdCodigoUsuario);
                tvCodigoUsuario.setVisibility(View.INVISIBLE);
                edCodigoUsuario.setVisibility(View.INVISIBLE);
            }
            edCodigoUsuario.setText(String.valueOf(usuario.getCodigoUsuario()));
            edNombreUsuario.setText(usuario.getNombre());
            edCorreoUsuario.setText(usuario.getCorreo());
            edTelefonoUsuario.setText(usuario.getTelefono());
            edContraseñaUsuario.setText(usuario.getPassword());
            edRolUsuario.setText(usuario.getRol());
        }

        btnVolver = findViewById(R.id.btnVolverUsuario);
        btnGuardar = findViewById(R.id.btnGuardarUsuario);

        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityUsuario.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (usuario != null) {
                EditText edCodigoUsuario = findViewById(R.id.edIdCodigoUsuario);
                EditText edNombreUsuario = findViewById(R.id.edNombreUsuario);
                EditText edCorreoUsuario = findViewById(R.id.edcorreoUsuario);
                EditText edTelefonoUsuario = findViewById(R.id.edtelefonoUsuario);
                EditText edContraseñaUsuario = findViewById(R.id.edpasswordUsuario);
                EditText edRolUsuario = findViewById(R.id.edrolUsuario);
                if (usuario.getCodigoUsuario() != 0) {
                    usuario.setCodigoUsuario(Integer.parseInt(edCodigoUsuario.getText().toString()));
                    usuario.setNombre(edNombreUsuario.getText().toString());
                    usuario.setCorreo(edCorreoUsuario.getText().toString());
                    usuario.setTelefono(edTelefonoUsuario.getText().toString());
                    usuario.setPassword(edContraseñaUsuario.getText().toString());
                    usuario.setRol(edRolUsuario.getText().toString());
                } else if (usuario.getCodigoUsuario() == 0 && usuario.getNombre().isEmpty()) {
                    usuario.setCodigoUsuario(GestionIncidencias.getArUsuarios().size() + 1);
                    usuario.setNombre(edNombreUsuario.getText().toString());
                    usuario.setCorreo(edCorreoUsuario.getText().toString());
                    usuario.setTelefono(edTelefonoUsuario.getText().toString());
                    usuario.setPassword(edContraseñaUsuario.getText().toString());
                    usuario.setRol(edRolUsuario.getText().toString());
                    GestionIncidencias.getArUsuarios().add(GestionIncidencias.getArUsuarios().size(), usuario);
                } else if (usuario.getCodigoUsuario() == 0) {
                    usuario.setCodigoUsuario(Integer.parseInt(edCodigoUsuario.getText().toString()));
                    usuario.setNombre(edNombreUsuario.getText().toString());
                    usuario.setCorreo(edCorreoUsuario.getText().toString());
                    usuario.setTelefono(edTelefonoUsuario.getText().toString());
                    usuario.setPassword(edContraseñaUsuario.getText().toString());
                    usuario.setRol(edRolUsuario.getText().toString());
                }
                Intent intentVolverUsuario = new Intent(view.getContext(), ActivityUsuario.class);
                startActivity(intentVolverUsuario);
            }
        });
    }
}
























