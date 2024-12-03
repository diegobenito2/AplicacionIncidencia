package com.example.aplicacionincidencias.Ubicacion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntSala;
import gestionincidencias.entidades.EntUbicacion;

public class Activity_Info_Ubicacion extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntUbicacion ubicacion;
    private TextView tvFechaInicio;
    private TextView tvFechaFin;
    private int SalaSelected;
    private int ElementoSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_ubicacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int codigoUbicacion = getIntent().getExtras().getInt("codigo");
        String salaUbicacion = getIntent().getExtras().getString("sala");

        if (codigoUbicacion > 0) {
            for (EntUbicacion u : GestionIncidencias.getArUbicaciones()) {
                if (u.getCodigoUbicacion() == codigoUbicacion) {
                    ubicacion = u;
                    break;
                }
            }
        } else if (codigoUbicacion == 0 && salaUbicacion.isEmpty()) {

            String fecha = "2024-11-29 20:32:00";
            SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date nuevaFechaInicio=null;
            try {
                nuevaFechaInicio = formatInicio.parse(fecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ubicacion = new EntUbicacion(0, 0, 0, "", nuevaFechaInicio, nuevaFechaInicio);
        }

        if (ubicacion != null) {
            EditText edCodigoUbicacion = findViewById(R.id.edCodigoUbicacion);
            edCodigoUbicacion.setText(String.valueOf(ubicacion.getCodigoUbicacion()));
            EditText edDescripcionUbicacion = findViewById(R.id.edDescripcionUbicacion);
            edDescripcionUbicacion.setText(ubicacion.getDescripcion());

////////////////////////////////////////////////////////Spinner Salas//////////////////////////////////////////////////////////////////////////////////
            Spinner spinnerSalas = findViewById(R.id.spinnerSala);
            ArrayList<String> listaSalas = new ArrayList<>();
            ArrayList<Integer> listaSalasIds = new ArrayList<>();
            for (EntSala sala : GestionIncidencias.getArSalas()) {
                listaSalas.add(sala.getNombre());
                listaSalasIds.add(sala.getCodigoSala());
            }
            ArrayAdapter<String> adapterSalas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaSalas);
            spinnerSalas.setAdapter(adapterSalas);

            // Seleccionar la sala actual de la ubicacion
            if (ubicacion.getIdSala() > 0) {
                SalaSelected = listaSalasIds.indexOf(ubicacion.getIdSala());
                if (SalaSelected != -1) {
                    spinnerSalas.setSelection(SalaSelected);
                }
            }

////////////////////////////////////////////////////////Spinner Elementos//////////////////////////////////////////////////////////////////////////////////
            Spinner spinnerElementos = findViewById(R.id.spinnerElemento);
            ArrayList<String> listaElementos = new ArrayList<>();
            ArrayList<Integer> listaElementosIds = new ArrayList<>();
            for (EntElemento elemento : GestionIncidencias.getArElementos()) {
                listaElementos.add(elemento.getNombre());
                listaElementosIds.add(elemento.getCodigoElemento());
            }
            ArrayAdapter<String> adapterElementos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaElementos);
            spinnerElementos.setAdapter(adapterElementos);

            // Seleccionar el elemento actual de la ubicacion
            if (ubicacion.getIdElemento() > 0) {
                ElementoSelected = listaElementosIds.indexOf(ubicacion.getIdElemento());
                if (ElementoSelected != -1) {
                    spinnerElementos.setSelection(ElementoSelected);
                }
            }

////////////////////////////////////////////////////////DatePicker Fecha Inicio//////////////////////////////////////////////////////////////////////////////////
            tvFechaInicio = findViewById(R.id.tvinfocodigoUbicacionFechaInicio);
            Date fechaInicio = ubicacion.getFechaInicio();

            if (fechaInicio != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvFechaInicio.setText(format.format(fechaInicio));
            } else {
                tvFechaInicio.setText("Fecha no disponible.");
            }

////////////////////////////////////////////////////////DatePicker Fecha Fin//////////////////////////////////////////////////////////////////////////////////
            tvFechaFin = findViewById(R.id.tvinfocodigoUbicacionFechaFin);
            Date fechaFin = ubicacion.getFechaFin();

            if (fechaFin != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvFechaFin.setText(format.format(fechaFin));
            } else {
                tvFechaFin.setText("Fecha no disponible.");
            }
        }

        btnVolver = findViewById(R.id.btnVolverUbicacion);
        btnGuardar = findViewById(R.id.btnGuardarUbicacion);
        btnVolver.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        tvFechaInicio.setOnClickListener(this);
        tvFechaFin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityUbicacion.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (ubicacion != null) {

////////////////////////////////////////////////////////Spinner Salas//////////////////////////////////////////////////////////////////////////////////
                Spinner spinnerSalas = findViewById(R.id.spinnerSala);
                String SalaSelected = spinnerSalas.getSelectedItem().toString();
                for (EntSala sala:GestionIncidencias.getArSalas()){
                    if (sala.getNombre().equals(SalaSelected)) {
                        ubicacion.setIdSala(sala.getCodigoSala()); // Guarda el id del elemento puesto en el spinner.
                        ubicacion.setSala(sala);
                        break;
                    }
                }

////////////////////////////////////////////////////////Spinner Elementos//////////////////////////////////////////////////////////////////////////////////
                Spinner spinnerElementos = findViewById(R.id.spinnerElemento);
                String ElementoSelected = spinnerElementos.getSelectedItem().toString();
                for (EntElemento elemento : GestionIncidencias.getArElementos()) {

                    if (elemento.getNombre().equals(ElementoSelected)) {
                        ubicacion.setIdElemento(elemento.getCodigoElemento()); // Guarda el id del elemento puesto en el spinner.
                        ubicacion.setElemento(elemento);
                        break;
                    }
                }
////////////////////////////////////////////////////////DatePicker Fecha Inicio//////////////////////////////////////////////////////////////////////////////////
                TextView tvFechaInicio = findViewById(R.id.tvinfocodigoUbicacionFechaInicio);
                String fechaInicio = tvFechaInicio.getText().toString();
                SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                try {
                    Date fechaInicioDate = formatInicio.parse(fechaInicio);
                    ubicacion.setFechaInicio(fechaInicioDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
////////////////////////////////////////////////////////DatePicker Fecha Fin//////////////////////////////////////////////////////////////////////////////////
                TextView tvFechaFin = findViewById(R.id.tvinfocodigoUbicacionFechaFin);
                String fechaFin = tvFechaFin.getText().toString();
                SimpleDateFormat formatFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                try {
                    Date fechaFinDate = formatFin.parse(fechaFin);
                    ubicacion.setFechaFin(fechaFinDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (ubicacion.getCodigoUbicacion() == 0) {
                    ubicacion.setCodigoUbicacion(GestionIncidencias.getArUbicaciones().size() + 1);
                    EditText edDescripcionUbicacion = findViewById(R.id.edDescripcionUbicacion);
                    ubicacion.setDescripcion(edDescripcionUbicacion.getText().toString());
                    GestionIncidencias.getArUbicaciones().add(GestionIncidencias.getArUbicaciones().size(), ubicacion);
                }

                Intent intentVolverUbicacion = new Intent(view.getContext(), activityUbicacion.class);
                startActivity(intentVolverUbicacion);
            }
        });
        // Listener para seleccionar fecha de inicio.
        tvFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(ubicacion.getFechaInicio());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Ubicacion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaInicio = ubicacion.getFechaInicio();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String[] fecha = format.format(fechaInicio).split(" ");
                        tvFechaInicio.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
        // Listener para seleccionar fecha de fin.
        tvFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(ubicacion.getFechaFin());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Ubicacion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaFin = ubicacion.getFechaFin();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()); // Establece nueva fecha.
                        String[] fecha = format.format(fechaFin).split(" ");
                        tvFechaFin.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
    }
}