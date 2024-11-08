package com.example.aplicacionincidencias.MenuPrincipal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.Elemento.activityElemento;
import com.example.aplicacionincidencias.Incidencia.activityIncidencia;
import com.example.aplicacionincidencias.Prestamo.ActivityPrestamo;
import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Sala.activitySalas;
import com.example.aplicacionincidencias.Tipo.activityTipo;
import com.example.aplicacionincidencias.Ubicacion.activityUbicacion;
import com.example.aplicacionincidencias.Usuario.ActivityUsuario;

public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {
    private Button ButtonMenuSala;
    private Button ButtonMenuTipo;
    private Button ButtonMenuPrestamo;
    private Button ButtonMenuUbicacion;
    private Button ButtonMenuUsuario;
    private Button ButtonMenuElemento;
    private Button ButtonMenuIncidencia;

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
    }

    private void initComponents() {
        ButtonMenuSala = findViewById(R.id.ButtonMenuSala);
        ButtonMenuTipo = findViewById(R.id.ButtonMenuTipo);
        ButtonMenuPrestamo = findViewById(R.id.ButtonMenuPrestamo);
        ButtonMenuUbicacion = findViewById(R.id.ButtonMenuUbicacion);
        ButtonMenuUsuario = findViewById(R.id.ButtonMenuUsuario);
        ButtonMenuElemento = findViewById(R.id.ButtonMenuElemento);
        ButtonMenuIncidencia = findViewById(R.id.ButtonMenuIncidencia);

    }

    public void initListeners() {
        ButtonMenuSala.setOnClickListener((View.OnClickListener) this);
        ButtonMenuTipo.setOnClickListener((View.OnClickListener) this);
        ButtonMenuPrestamo.setOnClickListener((View.OnClickListener) this);
        ButtonMenuUbicacion.setOnClickListener((View.OnClickListener) this);
        ButtonMenuUsuario.setOnClickListener((View.OnClickListener) this);
        ButtonMenuElemento.setOnClickListener((View.OnClickListener) this);
        ButtonMenuIncidencia.setOnClickListener((View.OnClickListener) this);
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

    }
}