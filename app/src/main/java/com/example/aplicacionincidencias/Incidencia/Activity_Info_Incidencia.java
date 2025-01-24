package com.example.aplicacionincidencias.Incidencia;

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
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.aplicacionincidencias.Elemento.ElementoHelper;
import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Usuario.UsuarioHelper;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntIncidencia;
import gestionincidencias.entidades.EntUsuario;


public class Activity_Info_Incidencia extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar, btnBorrar;
    private EntIncidencia incidencia;
    private TextView tvFechaCreacion;
    private int ElementoSelected;
    private int UsuarioSelected;
    private IncidenciaHelper ih = new IncidenciaHelper(this, "bbddIncidencias", null, 1);
    private ElementoHelper eh = new ElementoHelper(this, "bbddIncidencias", null, 1);
    private UsuarioHelper uh = new UsuarioHelper(this, "bbddIncidencias", null, 1);
    private ArrayList<EntElemento> arElementos = eh.obtenerElementos();
    private ArrayList<EntUsuario> arUsuarios = uh.obtenerUsuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_incidencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int codigoIncidencia = getIntent().getExtras().getInt("codigoIncidencia");
        String descripcionIncidencia = getIntent().getExtras().getString("descripcionIncidencia");
        if (codigoIncidencia > 0) {
            incidencia = ih.obtenerIncidencia(codigoIncidencia);
        } else if (codigoIncidencia == 0 && descripcionIncidencia.isEmpty()) {
            Date nuevafechacreacion = null;

            String fecha = "2024-01-01 10:30:00";
            SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                nuevafechacreacion = formatInicio.parse(fecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            incidencia = new EntIncidencia(0, "", 0, nuevafechacreacion, 0);
        }
        if (incidencia != null) {
            TextView tvInfoCodigoIncidencia = findViewById(R.id.tvinfoCodigoIncidencia);

            EditText edDescripcionIncidencia = findViewById(R.id.edDescripcionIncidencia);
            tvInfoCodigoIncidencia.setText(String.valueOf(incidencia.getCodigoIncidencia()));
            edDescripcionIncidencia.setText(incidencia.getDescripcion());
            if (incidencia.getCodigoIncidencia() == 0) {
                TextView tvCodigoIncidenia = findViewById(R.id.tvCodigoIncidencia);
                tvCodigoIncidenia.setVisibility(View.INVISIBLE);
                tvInfoCodigoIncidencia.setVisibility(View.INVISIBLE);
            }
////////////////////////////////////////////////////////Inicio Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////

            // Configurar los Spinners
            Spinner spinnerUsuarios = findViewById(R.id.spinnerUsuario);
            Spinner spinnerElementos = findViewById(R.id.spinnerElemento);

            // Configuración del Spinner de Usuarios

            ArrayList<String> listaUsuarios = new ArrayList<>();
            ArrayList<Integer> listaUsuariosIds = new ArrayList<>();
            for (EntUsuario user : arUsuarios) {
                listaUsuarios.add(user.getNombre());
                listaUsuariosIds.add(user.getCodigoUsuario());
            }

            ArrayAdapter<String> adapterUsuarios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaUsuarios);
            spinnerUsuarios.setAdapter(adapterUsuarios);

            // Seleccionar el usuario actual de la incidencia
            if (incidencia.getIdUsuarioCreacion() > 0) {
                UsuarioSelected = listaUsuariosIds.indexOf(incidencia.getIdUsuarioCreacion());
                if (UsuarioSelected != -1) {
                    spinnerUsuarios.setSelection(UsuarioSelected);
                }
            }
////////////////////////////////////////////////////////Fin Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////Inicio Spinner Elementos//////////////////////////////////////////////////////////////////////////////////
            // Configuración del Spinner de Elementos

            ArrayList<String> listaElementos = new ArrayList<>();
            ArrayList<Integer> listaElementosIds = new ArrayList<>();
            for (EntElemento elemento : arElementos) {
                listaElementos.add(elemento.getNombre());
                listaElementosIds.add(elemento.getCodigoElemento());
            }

            ArrayAdapter<String> adapterElementos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaElementos);
            spinnerElementos.setAdapter(adapterElementos);

            // Seleccionar el elemento actual de la incidencia
            if (incidencia.getIdElemento() > 0) {
                ElementoSelected = listaElementosIds.indexOf(incidencia.getIdElemento());
                if (ElementoSelected != -1) {
                    spinnerElementos.setSelection(ElementoSelected);
                }
            }
////////////////////////////////////////////////////////Fin Spinner Elementos//////////////////////////////////////////////////////////////////////////////////
            tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);
            Date fechaCreacion = incidencia.getFechaCreacion();

            if (fechaCreacion != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvFechaCreacion.setText(format.format(fechaCreacion));
            } else {
                tvFechaCreacion.setText("Fecha no disponible.");
            }


        }
        btnVolver = findViewById(R.id.btnVolverIncidencia);
        btnGuardar = findViewById(R.id.btnGuardarIncidencia);
        btnBorrar = findViewById(R.id.btnBorrarIncidencia);
        btnBorrar.setOnClickListener((View.OnClickListener) this);
        tvFechaCreacion.setOnClickListener((View.OnClickListener) this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityIncidencia.class);
            startActivity(intent);
        });
        btnBorrar.setOnClickListener(v -> {
            ih.borrarIncidencia(incidencia.getCodigoIncidencia());
            Toast.makeText(getApplicationContext(), "Incidencia Borrada Correctamente", Toast.LENGTH_SHORT).show();
            Intent intentVolverIncidencias = new Intent(view.getContext(), activityIncidencia.class);
            startActivity(intentVolverIncidencias);
        });
        btnGuardar.setOnClickListener(v -> {
            if (incidencia != null) {
                TextView tvCodigoIncidencia = findViewById(R.id.tvinfoCodigoIncidencia);
                EditText edDescripcionIncidencia = findViewById(R.id.edDescripcionIncidencia);
                tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);
                ////Guardar los nombres de los spinners///////////////////////
                ////////////////////////////////////////////////////////Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////
                Spinner spinnerUsuarios = findViewById(R.id.spinnerUsuario);
                String UsuarioSelected = spinnerUsuarios.getSelectedItem().toString(); //Guardas el nombre del usuario seleccionado
                for (EntUsuario usuario : arUsuarios) { //Recorres la lista de usuarios.
                    if (usuario.getNombre().equals(UsuarioSelected)) { //Cuando el nombre es igual al nombre de usuario seleccionado.
                        incidencia.setIdUsuarioCreacion(usuario.getCodigoUsuario()); //Cambia el código de usuario de la incidencia al del usuario seleccionado.
                        incidencia.setUsuarioCreacion(usuario); //Cambia el objeto Usuario de la incidencia por el nuevo usuario.
                    }
                }
                ////////////////////////////////////////////////////////Spinner Elementos//////////////////////////////////////////////////////////////////////////////////
                Spinner spinnerElementos = findViewById(R.id.spinnerElemento);
                String ElementoSelected = spinnerElementos.getSelectedItem().toString();//Guardas el nombre del elemento seleccionado
                for (EntElemento elemento : arElementos) {//Recorres la lista de elementos.

                    if (elemento.getNombre().equals(ElementoSelected)) {//Cuando el nombre es igual al nombre del elemento seleccionado.
                        incidencia.setIdElemento(elemento.getCodigoElemento());  //Cambia el código de elemento de la incidencia al del elemento seleccionado.
                        incidencia.setElemento(elemento);//Cambia el objeto Elemento de la incidencia por el nuevo elemento.
                        break;
                    }
                }


                if (incidencia.getCodigoIncidencia() != 0) {
                    incidencia.setCodigoIncidencia(Integer.parseInt(tvCodigoIncidencia.getText().toString()));
                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());

                    tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);
                    String FechaCreacion = tvFechaCreacion.getText().toString();
                    SimpleDateFormat formatCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    try {
                        Date fechaCreacion = formatCreacion.parse(FechaCreacion);
                        incidencia.setFechaCreacion(fechaCreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (incidencia.getCodigoIncidencia() == 0) {

                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());
                    tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);
                    String FechaCreacion = tvFechaCreacion.getText().toString();
                    SimpleDateFormat formatCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    try {
                        Date fechaCreacion = formatCreacion.parse(FechaCreacion);
                        incidencia.setFechaCreacion(fechaCreacion);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ih.crearIncidencia(incidencia);
                    Toast.makeText(getApplicationContext(), "Incidencia Añadida Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    ih.actualizarIncidencia(incidencia);
                    Toast.makeText(getApplicationContext(), "Incidencia Guardada Correctamente", Toast.LENGTH_SHORT).show();
                }
                Intent intentVolverIncidencias = new Intent(view.getContext(), activityIncidencia.class);
                startActivity(intentVolverIncidencias);
            }
        });
        tvFechaCreacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(incidencia.getFechaCreacion());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Incidencia.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaCreacion = incidencia.getFechaCreacion();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String[] fecha = format.format(fechaCreacion).split(" ");
                        tvFechaCreacion.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
    }
}