package com.example.aplicacionincidencias.Ubicacion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.aplicacionincidencias.R;

import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntUbicacion;

public class Activity_Info_Ubicacion extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntUbicacion ubicacion;
    private TextView tvFechaInicio;
    private TextView tvFechaFin;

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
        String descripcionUbicacion = getIntent().getExtras().getString("descripcion");

        if (codigoUbicacion > 0) {
            for (EntUbicacion u : GestionIncidencias.getArUbicaciones()) {
                if (u.getCodigoUbicacion() == codigoUbicacion) {
                    ubicacion = u;
                }
            }
        } else if (codigoUbicacion == 0 && descripcionUbicacion.isEmpty()) {

            ubicacion = new EntUbicacion(0, 0, 0, "0", null, null);
        } else if (codigoUbicacion == 0) {
            for (EntUbicacion u : GestionIncidencias.getArUbicaciones()) {
                if (u.getCodigoUbicacion() == codigoUbicacion) {
                    ubicacion = u;
                }
            }
        }
        if (ubicacion != null) {
            EditText edCodigoUbicacion = findViewById(R.id.edinfoCodigoElemento);
            EditText edCodigoSala = findViewById(R.id.edNombreElemento);
            EditText edCodigoElemento = findViewById(R.id.edDescripcionElemento);
            tvFechaInicio = findViewById(R.id.tvcodigoUbicacionFechaInicio);
            tvFechaFin = findViewById(R.id.tvcodigoUbicacionFechaFin);

            edCodigoUbicacion.setText(String.valueOf(ubicacion.getCodigoUbicacion()));
            edCodigoSala.setText(ubicacion.getDescripcion());
            edCodigoElemento.setText(ubicacion.getDescripcion());
            Date fechaInicio = ubicacion.getFechaInicio();

            if (fechaInicio != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvFechaInicio.setText(format.format(fechaInicio));
            } else {
                tvFechaInicio.setText("Fecha no disponible.");
            }

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
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
        tvFechaInicio.setOnClickListener((View.OnClickListener) this);
        tvFechaFin.setOnClickListener((View.OnClickListener) this);

    }


    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityUbicacion.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (ubicacion != null) {
                EditText edCodigoUbicacion = findViewById(R.id.edinfoCodigoElemento);
                EditText edCodigoSala = findViewById(R.id.edNombreElemento);
                EditText edCodigoElemento = findViewById(R.id.edDescripcionElemento);

                // Obtiene y formatea las fechas.
                tvFechaInicio = findViewById(R.id.infotvfechaInicio);
                String FechaInicio = tvFechaInicio.getText().toString();
                SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                tvFechaFin = findViewById(R.id.infotvfechaFin);
                String fechaFin = tvFechaFin.getText().toString();
                SimpleDateFormat formatFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                if (ubicacion.getCodigoUbicacion() != 0) {
                    // Actualiza los valores de la ubicación.
                    ubicacion.setCodigoUbicacion(Integer.parseInt(edCodigoUbicacion.getText().toString()));
                    ubicacion.setIdSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    ubicacion.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    try {
                        Date fechaInicio = formatInicio.parse(FechaInicio);
                        ubicacion.setFechaInicio(fechaInicio);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        Date fechaFinal = formatFin.parse(fechaFin);
                        ubicacion.setFechaFin(fechaFinal);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (ubicacion.getCodigoUbicacion() == 0 && ubicacion.getDescripcion().isEmpty()) {
                    // Actualiza los valores de la ubicación al agregar una nueva.
                    ubicacion.setCodigoUbicacion(Integer.parseInt(edCodigoUbicacion.getText().toString()));
                    ubicacion.setIdSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    ubicacion.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    try {
                        Date fechaInicio = formatInicio.parse(FechaInicio);
                        ubicacion.setFechaInicio(fechaInicio);
                    } catch (ParseException e) {
                        e.printStackTrace();    // Manejo de error si el formato falla.
                    }

                    try {
                        Date fechaFinal = formatFin.parse(fechaFin);
                        ubicacion.setFechaFin(fechaFinal);
                    } catch (ParseException e) {
                        e.printStackTrace();    // Manejo de error si el formato falla.
                    }
                    GestionIncidencias.getArUbicaciones().add(GestionIncidencias.getArUbicaciones().size(), ubicacion);
                } else if (ubicacion.getCodigoUbicacion() == 0) {
                    // Actualiza los valores de la ubicación.
                    ubicacion.setCodigoUbicacion(Integer.parseInt(edCodigoUbicacion.getText().toString()));
                    ubicacion.setIdSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    ubicacion.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    try {
                        Date fechaInicio = formatInicio.parse(FechaInicio);
                        ubicacion.setFechaInicio(fechaInicio);
                    } catch (ParseException e) {
                        e.printStackTrace();// Manejo de error si el formato falla.
                    }

                    try {
                        Date fechaFinal = formatFin.parse(fechaFin);
                        ubicacion.setFechaFin(fechaFinal);
                    } catch (ParseException e) {
                        e.printStackTrace();// Manejo de error si el formato falla.
                    }
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