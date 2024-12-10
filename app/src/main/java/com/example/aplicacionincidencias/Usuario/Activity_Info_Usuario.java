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

import java.util.ArrayList;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntRol;
import gestionincidencias.entidades.EntTipo;
import gestionincidencias.entidades.EntUsuario;

public class Activity_Info_Usuario extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar, btnBorrar;
    private EntUsuario usuario;
    private int RolSelected;

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
            for (EntUsuario u : GestionIncidencias.getArUsuarios()) {
                if (u.getCodigoUsuario() == codigoUsuario) {
                    usuario = u;
                }
            }
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
            ArrayList<String> listaRoles = new ArrayList<>();
            ArrayList<Integer> listaRolesIds = new ArrayList<>();
            for (EntRol rol : GestionIncidencias.getArRoles()) {
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
        btnBorrar.setOnClickListener((View.OnClickListener) this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityUsuario.class);
            startActivity(intent);
        });
        btnBorrar.setOnClickListener(v -> {
            GestionIncidencias.getArUsuarios().remove(usuario);
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
                for (EntRol rol : GestionIncidencias.getArRoles()) { //Recorres la lista de Tipos.
                    if (usuario.getEntRol().getNombre().equals(RolSelected)) { //Cuando el nombre es igual al nombre de Tipo seleccionado.
                        usuario.setRol(rol.getCodigo()); //Cambia el código de Tipo del Elemento al del Tipo seleccionado.
                        usuario.setEntRol(rol); //Cambia el objeto Tipo del Elemento por el nuevo Tipo.
                        break;
                    }
                }

                usuario.setNombre(edNombreUsuario.getText().toString());
                usuario.setCorreo(edCorreoUsuario.getText().toString());
                usuario.setTelefono(edTelefonoUsuario.getText().toString());
                usuario.setPassword(edContraseñaUsuario.getText().toString());

                if (usuario.getCodigoUsuario() == 0 && usuario.getNombre().isEmpty()) {
                    usuario.setCodigoUsuario(GestionIncidencias.getArUsuarios().size() + 1);

                    GestionIncidencias.getArUsuarios().add(GestionIncidencias.getArUsuarios().size(), usuario);
                    Toast.makeText(getApplicationContext(), "Usuario Añadido Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario Guardado Correctamente", Toast.LENGTH_SHORT).show();
                }
                Intent intentVolverUsuario = new Intent(view.getContext(), ActivityUsuario.class);
                startActivity(intentVolverUsuario);
            }
        });
    }
}
























