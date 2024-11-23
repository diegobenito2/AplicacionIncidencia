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

import java.text.SimpleDateFormat;

import com.example.aplicacionincidencias.R;

import java.sql.Date;
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
            tvFechaInicio.setText(String.valueOf(ubicacion.getFechaInicio()));
            tvFechaFin.setText(String.valueOf(ubicacion.getFechaFin()));
        }


        initComponentsVolverGuardar();
        initListenersVolverGuardar();


    }

    private void initComponentsVolverGuardar() {
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    private void initListenersVolverGuardar() {
        tvFechaInicio.setOnClickListener((View.OnClickListener) this);
        tvFechaFin.setOnClickListener((View.OnClickListener) this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
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
                TextView tvFechaInicio = findViewById(R.id.tvcodigoUbicacionFechaInicio);
                TextView tvFechaFin = findViewById(R.id.tvcodigoUbicacionFechaFin);
                if (ubicacion.getCodigoUbicacion() != 0) {
                    ubicacion.setCodigoUbicacion(Integer.parseInt(edCodigoUbicacion.getText().toString()));
                    ubicacion.setIdSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    ubicacion.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    ubicacion.setFechaInicio(Date.valueOf(tvFechaInicio.getText().toString()));
                    ubicacion.setFechaFin(Date.valueOf(tvFechaFin.getText().toString()));
                } else if (ubicacion.getCodigoUbicacion() == 0 && ubicacion.getDescripcion().isEmpty()) {
                    ubicacion.setCodigoUbicacion(Integer.parseInt(edCodigoUbicacion.getText().toString()));
                    ubicacion.setIdSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    ubicacion.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    ubicacion.setFechaInicio(Date.valueOf(tvFechaInicio.getText().toString()));
                    ubicacion.setFechaFin(Date.valueOf(tvFechaFin.getText().toString()));
                    GestionIncidencias.getArUbicaciones().add(GestionIncidencias.getArUbicaciones().size(), ubicacion);
                } else if (ubicacion.getCodigoUbicacion() == 0) {
                    ubicacion.setCodigoUbicacion(Integer.parseInt(edCodigoUbicacion.getText().toString()));
                    ubicacion.setIdSala(Integer.parseInt(edCodigoSala.getText().toString()));
                    ubicacion.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    ubicacion.setFechaInicio(Date.valueOf(tvFechaInicio.getText().toString()));
                    ubicacion.setFechaFin(Date.valueOf(tvFechaFin.getText().toString()));
                }
                Intent intentVolverUbicacion = new Intent(view.getContext(), activityUbicacion.class);
                startActivity(intentVolverUbicacion);
            }
        });
        tvFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(ubicacion.getFechaInicio());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Ubicacion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaInicio = (Date) ubicacion.getFechaInicio();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String[] fecha = format.format(fechaInicio).split(" ");
                        tvFechaInicio.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
        tvFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(ubicacion.getFechaFin());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Ubicacion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaFin = (Date) ubicacion.getFechaFin();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String[] fecha = format.format(fechaFin).split(" ");
                        tvFechaFin.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
    }
}