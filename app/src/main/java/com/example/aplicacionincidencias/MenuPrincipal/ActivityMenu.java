package com.example.aplicacionincidencias.MenuPrincipal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.Elemento.activityElemento;
import com.example.aplicacionincidencias.Incidencia.activityIncidencia;
import com.example.aplicacionincidencias.Prestamo.ActivityPrestamo;
import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Rol.Activity_Rol;
import com.example.aplicacionincidencias.Sala.activitySalas;
import com.example.aplicacionincidencias.Tipo.activityTipo;
import com.example.aplicacionincidencias.Ubicacion.activityUbicacion;
import com.example.aplicacionincidencias.Usuario.ActivityUsuario;

public class ActivityMenu extends menutrespuntos implements View.OnClickListener {
    private Button ButtonMenuSala;
    private Button ButtonMenuTipo;
    private Button ButtonMenuPrestamo;
    private Button ButtonMenuUbicacion;
    private Button ButtonMenuUsuario;
    private Button ButtonMenuElemento;
    private Button ButtonMenuIncidencia;
    private Button ButtonMenuRol;
    private boolean revisionInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponents();
        initListeners();
        String ultima = getUltimaActividad(getSharedPreferences("datos", MODE_PRIVATE));

        if (!revisionInicial && ultima != null && !ultima.isEmpty()) {
            if (ultima.equals(activitySalas.class.toString())) {
                Intent intentSala = new Intent(this, activitySalas.class);
                startActivity(intentSala);
            } else if (ultima.equals(activityElemento.class.toString())) {
                Intent intentElemento= new Intent(this,activityElemento.class);
                startActivity(intentElemento);
            } else if (ultima.equals(activityIncidencia.class.toString())) {
                Intent intentIncidencias = new Intent(this,activityIncidencia.class);
                startActivity(intentIncidencias);
            } else if (ultima.equals(activityTipo.class.toString())) {
                Intent intentTipo= new Intent(this,activityTipo.class);
                startActivity(intentTipo);
            } else if (ultima.equals(activityUbicacion.class.toString())) {
                Intent intentUbicacion = new Intent(this,activityUbicacion.class);
                startActivity(intentUbicacion);
            } else if (ultima.equals(Activity_Rol.class.toString())) {
                Intent intentRol = new Intent(this, Activity_Rol.class);
                startActivity(intentRol);
            } else if (ultima.equals(ActivityPrestamo.class.toString())) {
                Intent intentPrestamo=new Intent(this,ActivityPrestamo.class);
                startActivity(intentPrestamo);
            } else if (ultima.equals(ActivityUsuario.class.toString())) {
                Intent intentUsuario = new Intent(this,ActivityUsuario.class);
                startActivity(intentUsuario);
            }

            revisionInicial = true;
        }
        guardaActividad(getSharedPreferences("datos", MODE_PRIVATE), "");
    }

    private void initComponents() {
        ButtonMenuSala = findViewById(R.id.ButtonMenuSala);
        ButtonMenuTipo = findViewById(R.id.ButtonMenuTipo);
        ButtonMenuPrestamo = findViewById(R.id.ButtonMenuPrestamo);
        ButtonMenuUbicacion = findViewById(R.id.ButtonMenuUbicacion);
        ButtonMenuUsuario = findViewById(R.id.ButtonMenuUsuario);
        ButtonMenuElemento = findViewById(R.id.ButtonMenuElemento);
        ButtonMenuIncidencia = findViewById(R.id.ButtonMenuIncidencia);
        ButtonMenuRol = findViewById(R.id.ButtonMenuRol);

    }

    public void initListeners() {
        ButtonMenuSala.setOnClickListener((View.OnClickListener) this);
        ButtonMenuTipo.setOnClickListener((View.OnClickListener) this);
        ButtonMenuPrestamo.setOnClickListener((View.OnClickListener) this);
        ButtonMenuUbicacion.setOnClickListener((View.OnClickListener) this);
        ButtonMenuUsuario.setOnClickListener((View.OnClickListener) this);
        ButtonMenuElemento.setOnClickListener((View.OnClickListener) this);
        ButtonMenuIncidencia.setOnClickListener((View.OnClickListener) this);
        ButtonMenuRol.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        ButtonMenuSala.setOnClickListener(v -> {
            Intent intent = new Intent(this, activitySalas.class);
            startActivity(intent);
        });
        ButtonMenuTipo.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityTipo.class);
            startActivity(intent);
        });
        ButtonMenuPrestamo.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityPrestamo.class);
            startActivity(intent);
        });
        ButtonMenuUbicacion.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityUbicacion.class);
            startActivity(intent);
        });
        ButtonMenuUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityUsuario.class);
            startActivity(intent);
        });
        ButtonMenuElemento.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityElemento.class);
            startActivity(intent);
        });
        ButtonMenuIncidencia.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityIncidencia.class);
            startActivity(intent);
        });
        ButtonMenuRol.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_Rol.class);
            startActivity(intent);
        });


    }
}