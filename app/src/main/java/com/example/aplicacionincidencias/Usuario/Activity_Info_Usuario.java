package com.example.aplicacionincidencias.Usuario;

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
import com.example.aplicacionincidencias.Rol.RolHelper;

import java.util.ArrayList;

import gestionincidencias.entidades.EntRol;
import gestionincidencias.entidades.EntUsuario;

public class Activity_Info_Usuario extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar, btnBorrar;
    private EntUsuario usuario;
    private int RolSelected;
    private UsuarioHelper uh = new UsuarioHelper(this, "bbddIncidencias", null, 1);
    private RolHelper rh = new RolHelper(this, "bbddIncidencias", null, 1);


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

        if (codigoUsuario > 0) {
            usuario= uh.obtenerUsuario(codigoUsuario);
        } else if (codigoUsuario == 0 && nombreUsuario.isBlank()) {
            usuario = new EntUsuario(codigoUsuario, nombreUsuario, "", "", "", 0);
        }
        if (usuario != null) {
            TextView tvInfoCodigoUsuario = findViewById(R.id.tvInfoIdCodigoUsuario);
            EditText edNombreUsuario = findViewById(R.id.edNombreUsuario);
            EditText edCorreoUsuario = findViewById(R.id.edcorreoUsuario);
            EditText edTelefonoUsuario = findViewById(R.id.edtelefonoUsuario);
            EditText edContraseñaUsuario = findViewById(R.id.edpasswordUsuario);
            // Configurar los Spinners
            Spinner spinnerRoles = findViewById(R.id.spinnerrolUsuario);


            // Configuración del Spinner de Usuarios
            ArrayList<EntRol> arRoles = rh.obtenerRoles();
            ArrayList<String> listaRoles = new ArrayList<>();
            ArrayList<Integer> listaRolesIds = new ArrayList<>();
            for (EntRol rol : arRoles) {
                listaRoles.add(rol.getNombre());
                listaRolesIds.add(rol.getCodigo());
            }

            ArrayAdapter<String> adapterRoles = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaRoles);
            spinnerRoles.setAdapter(adapterRoles);

            // Seleccionar el usuario actual del préstamo
            if (usuario.getRol() > 0) {
                RolSelected = listaRolesIds.indexOf(usuario.getRol());
                if (RolSelected != -1) {
                    spinnerRoles.setSelection(RolSelected);
                }
            }

            if (usuario.getCodigoUsuario() == 0 && usuario.getNombre().isBlank()) {
                TextView tvCodigoUsuario = findViewById(R.id.IdCodigoUsuario);
                tvCodigoUsuario.setVisibility(View.INVISIBLE);
                tvInfoCodigoUsuario.setVisibility(View.INVISIBLE);
            }
            tvInfoCodigoUsuario.setText(String.valueOf(usuario.getCodigoUsuario()));
            edNombreUsuario.setText(usuario.getNombre());
            edCorreoUsuario.setText(usuario.getCorreo());
            edTelefonoUsuario.setText(usuario.getTelefono());
            edContraseñaUsuario.setText(usuario.getPassword());

        }

        btnVolver = findViewById(R.id.btnVolverUsuario);
        btnGuardar = findViewById(R.id.btnGuardarUsuario);
        btnBorrar = findViewById(R.id.btnBorrarUsuario);
        btnBorrar.setOnClickListener(this);
        btnVolver.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityUsuario.class);
            startActivity(intent);
        });
        btnBorrar.setOnClickListener(v -> {
            uh.borrarUsuario(usuario.getCodigoUsuario());
            Toast.makeText(getApplicationContext(), "Usuario Borrado Correctamente", Toast.LENGTH_SHORT).show();
            Intent intentVolverUsuario = new Intent(view.getContext(), ActivityUsuario.class);
            startActivity(intentVolverUsuario);
        });
        btnGuardar.setOnClickListener(v -> {
            if (usuario != null) {
                EditText edNombreUsuario = findViewById(R.id.edNombreUsuario);
                EditText edCorreoUsuario = findViewById(R.id.edcorreoUsuario);
                EditText edTelefonoUsuario = findViewById(R.id.edtelefonoUsuario);
                EditText edContraseñaUsuario = findViewById(R.id.edpasswordUsuario);

                Spinner spinnerRol = findViewById(R.id.spinnerrolUsuario);
                String RolSelected = spinnerRol.getSelectedItem().toString(); //Guardas el nombre del Tipo seleccionado
                EntRol rolSelected = rh.obtenerNombreRol(RolSelected);
                usuario.setEntRol(rolSelected);
                usuario.setRol(rolSelected.getCodigo());

                usuario.setNombre(edNombreUsuario.getText().toString());
                usuario.setCorreo(edCorreoUsuario.getText().toString());
                usuario.setTelefono(edTelefonoUsuario.getText().toString());
                usuario.setPassword(edContraseñaUsuario.getText().toString());

                if (usuario.getCodigoUsuario() == 0 && usuario.getNombre().isEmpty()) {


                    uh.crearUsuario(usuario);
                    Toast.makeText(getApplicationContext(), "Usuario Añadido Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    uh.actualizarUsuario(usuario);
                    Toast.makeText(getApplicationContext(), "Usuario Guardado Correctamente", Toast.LENGTH_SHORT).show();
                }
                Intent intentVolverUsuario = new Intent(view.getContext(), ActivityUsuario.class);
                startActivity(intentVolverUsuario);
            }
        });
    }
}
























