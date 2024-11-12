package com.example.aplicacionincidencias.MenuPrincipal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionincidencias.Elemento.activityElemento;
import com.example.aplicacionincidencias.Incidencia.activityIncidencia;
import com.example.aplicacionincidencias.Prestamo.ActivityPrestamo;
import com.example.aplicacionincidencias.Prestamo.Activity_Info_Prestamo;
import com.example.aplicacionincidencias.Prestamo.AdaptadorPrestamo;
import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Sala.activitySalas;
import com.example.aplicacionincidencias.Tipo.activityTipo;
import com.example.aplicacionincidencias.Ubicacion.activityUbicacion;
import com.example.aplicacionincidencias.Usuario.ActivityUsuario;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntPrestamo;

public class menutrespuntos extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menupuntos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemMenuSalas) {
            Intent intentSalas = new Intent(this, activitySalas.class);
            startActivity(intentSalas);
            return true;
        } else if (item.getItemId() == R.id.itemMenuElemento) {
            Intent intentElemento = new Intent(this, activityElemento.class);
            startActivity(intentElemento);
            return true;
        } else if (item.getItemId() == R.id.itemMenuIncidencia) {
            Intent intentIncidencia = new Intent(this, activityIncidencia.class);
            startActivity(intentIncidencia);
            return true;
        } else if (item.getItemId() == R.id.itemMenuPrestamo) {
            Intent intentPrestamo = new Intent(this, ActivityPrestamo.class);
            startActivity(intentPrestamo);
            return true;
        } else if (item.getItemId() == R.id.itemMenuTipo) {
            Intent intentTipo = new Intent(this, activityTipo.class);
            startActivity(intentTipo);
            return true;
        } else if (item.getItemId() == R.id.itemMenuUbicacion) {
            Intent intentUbicacion = new Intent(this, activityUbicacion.class);
            startActivity(intentUbicacion);
            return true;
        } else if (item.getItemId() == R.id.itemMenuUsuario) {
            Intent intentUsuario = new Intent(this, ActivityUsuario.class);
            startActivity(intentUsuario);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}
