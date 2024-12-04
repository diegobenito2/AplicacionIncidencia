package com.example.aplicacionincidencias.Prestamo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.aplicacionincidencias.R;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntPrestamo;
import gestionincidencias.entidades.EntUsuario;

public class Activity_Info_Prestamo extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar, btnBorrar;
    private EntPrestamo prestamo;
    private TextView tvfechaInicio;
    private TextView tvfechaFin;
    private int ElementoSelected;
    private int UsuarioSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_prestamo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int idPrestamo = getIntent().getExtras().getInt("codigoPrestamo");
        if (idPrestamo > 0) {
            for (EntPrestamo e : GestionIncidencias.getArPrestamos()) {
                if (e.getCodigoPrestamo() == idPrestamo) {
                    prestamo = e;
                }
            }

        } else if (idPrestamo == 0) {
            Date nuevafechainicio = null;

            String fecha = "2024-01-01 10:30:00";
            SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                nuevafechainicio = formatInicio.parse(fecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            prestamo = new EntPrestamo(0, 1, 1, nuevafechainicio, nuevafechainicio);
        }

        if (prestamo != null) {
            TextView tvInfoCodigoPrestamo = findViewById(R.id.infotvCodigoPrestamo);
            // Al ser un nuevo tipo y el código ponerse automaticamente pues los campos se ocultan.
            if (prestamo.getCodigoPrestamo() == 0) {
                TextView tvCodigoPrestamo = findViewById(R.id.tvCodigoPrestamo);
                tvInfoCodigoPrestamo.setText(String.valueOf(GestionIncidencias.getArPrestamos().size() + 1));
                tvCodigoPrestamo.setVisibility(View.INVISIBLE);
                tvInfoCodigoPrestamo.setVisibility(View.INVISIBLE);
            }

            tvInfoCodigoPrestamo.setText(String.valueOf(prestamo.getCodigoPrestamo()));

////////////////////////////////////////////////////////Inicio Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////

            // Configurar los Spinners
            Spinner spinnerUsuarios = findViewById(R.id.spinnerUsuario);
            Spinner spinnerElementos = findViewById(R.id.spinnerElemento);

            // Configuración del Spinner de Usuarios
            ArrayList<String> listaUsuarios = new ArrayList<>();
            ArrayList<Integer> listaUsuariosIds = new ArrayList<>();
            for (EntUsuario user : GestionIncidencias.getArUsuarios()) {
                listaUsuarios.add(user.getNombre());
                listaUsuariosIds.add(user.getCodigoUsuario());
            }

            ArrayAdapter<String> adapterUsuarios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaUsuarios);
            spinnerUsuarios.setAdapter(adapterUsuarios);

            // Seleccionar el usuario actual del préstamo
            if (prestamo.getIdUsuario() > 0) {
                UsuarioSelected = listaUsuariosIds.indexOf(prestamo.getIdUsuario());
                if (UsuarioSelected != -1) {
                    spinnerUsuarios.setSelection(UsuarioSelected);
                }
            }
////////////////////////////////////////////////////////Fin Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////Inicio Spinner Elementos//////////////////////////////////////////////////////////////////////////////////
            // Configuración del Spinner de Elementos
            ArrayList<String> listaElementos = new ArrayList<>();
            ArrayList<Integer> listaElementosIds = new ArrayList<>();
            for (EntElemento elemento : GestionIncidencias.getArElementos()) {
                listaElementos.add(elemento.getNombre());
                listaElementosIds.add(elemento.getCodigoElemento());
            }

            ArrayAdapter<String> adapterElementos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaElementos);
            spinnerElementos.setAdapter(adapterElementos);

            // Seleccionar el elemento actual del préstamo
            if (prestamo.getIdElemento() > 0) {
                ElementoSelected = listaElementosIds.indexOf(prestamo.getIdElemento());
                if (ElementoSelected != -1) {
                    spinnerElementos.setSelection(ElementoSelected);
                }
            }
////////////////////////////////////////////////////////Fin Spinner Elementos//////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////Inicio DatePickers Fechas//////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////DatePicker Fecha Inicio//////////////////////////////////////////////////////////////////////////////////
            tvfechaInicio = findViewById(R.id.infotvfechaInicio);
            Date fechaInicio = prestamo.getFechaInicio();

            if (fechaInicio != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvfechaInicio.setText(format.format(fechaInicio));
            } else {
                tvfechaInicio.setText("Fecha no disponible.");
            }
////////////////////////////////////////////////////////DatePicker Fecha Fin//////////////////////////////////////////////////////////////////////////////////
            tvfechaFin = findViewById(R.id.infotvfechaFin);
            Date fechaFin = prestamo.getFechaFin();

            if (fechaFin != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvfechaFin.setText(format.format(fechaFin));
            } else {
                tvfechaFin.setText("Fecha no disponible.");
            }

        }
////////////////////////////////////////////////////////Fin DatePickers Fechas//////////////////////////////////////////////////////////////////////////////////
        btnVolver = findViewById(R.id.btnVolverPrestamo);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar = findViewById(R.id.btnGuardarPrestamo);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
        btnBorrar = findViewById(R.id.btnBorrarPrestamo);
        btnBorrar.setOnClickListener((View.OnClickListener)this);
        tvfechaInicio.setOnClickListener(this);
        tvfechaFin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityPrestamo.class);
            startActivity(intent);
        });
        btnBorrar.setOnClickListener(v -> {
            GestionIncidencias.getArPrestamos().remove(prestamo);
            Intent intentVolverPrestamos = new Intent(view.getContext(), ActivityPrestamo.class);
            startActivity(intentVolverPrestamos);
        });
        btnGuardar.setOnClickListener(v -> {
            if (prestamo != null) {

                ////////////////////////////////////////////////////////Spinner Usuarios//////////////////////////////////////////////////////////////////////////////////
                Spinner spinnerUsuarios = findViewById(R.id.spinnerUsuario);
                String UsuarioSelected = spinnerUsuarios.getSelectedItem().toString(); //Guardas el nombre del usuario seleccionado
                for (EntUsuario usuario : GestionIncidencias.getArUsuarios()) { //Recorres la lista de usuarios.
                    if (usuario.getNombre().equals(UsuarioSelected)) { //Cuando el nombre es igual al nombre de usuario seleccionado.
                        prestamo.setIdUsuario(usuario.getCodigoUsuario()); //Cambia el código de usuario del prestamo al del usuario seleccionado.
                        prestamo.setUsuario(usuario); //Cambia el objeto Usuario del prestamo por el nuevo usuario.
                    }
                }
                ////////////////////////////////////////////////////////Spinner Elementos//////////////////////////////////////////////////////////////////////////////////
                Spinner spinnerElementos = findViewById(R.id.spinnerElemento);
                String ElementoSelected = spinnerElementos.getSelectedItem().toString();//Guardas el nombre del elemento seleccionado
                for (EntElemento elemento : GestionIncidencias.getArElementos()) {//Recorres la lista de elementos.

                    if (elemento.getNombre().equals(ElementoSelected)) {//Cuando el nombre es igual al nombre del elemento seleccionado.
                        prestamo.setIdElemento(elemento.getCodigoElemento());  //Cambia el código de elemento del prestamo al del elemento seleccionado.
                        prestamo.setElemento(elemento);//Cambia el objeto Elemento del prestamo por el nuevo elemento.
                        break;
                    }
                }


////////////////////////////////////////////////////////DatePicker Fecha Inicio//////////////////////////////////////////////////////////////////////////////////
                TextView tvFechaInicio = findViewById(R.id.infotvfechaInicio);
                String FechaInicio = tvFechaInicio.getText().toString();
                SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                try {
                    Date fechaInicio = formatInicio.parse(FechaInicio);
                    prestamo.setFechaInicio(fechaInicio);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
////////////////////////////////////////////////////////DatePicker Fecha Fin//////////////////////////////////////////////////////////////////////////////////
                TextView tvFechaFin = findViewById(R.id.infotvfechaFin);
                String fechaFin = tvFechaFin.getText().toString();
                SimpleDateFormat formatFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                try {
                    Date fechaFinal = formatFin.parse(fechaFin);
                    prestamo.setFechaFin(fechaFinal);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Si es un nuevo prestamo que le ponga el codigo automatico y que lo agrege al final de la lista.
                if (prestamo.getCodigoPrestamo() == 0) {
                    prestamo.setCodigoPrestamo(GestionIncidencias.getArPrestamos().size() + 1);
                    GestionIncidencias.getArPrestamos().add(GestionIncidencias.getArPrestamos().size(), prestamo);
                }
                Intent intentVolverPrestamos = new Intent(view.getContext(), ActivityPrestamo.class);
                startActivity(intentVolverPrestamos);
            }
        });
////////////////////////////////////////////////////////DatePicker Fecha Inicio//////////////////////////////////////////////////////////////////////////////////
        tvfechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicializar el calendario con la fecha de inicio del préstamo
                Calendar c = Calendar.getInstance();
                c.setTime(prestamo.getFechaInicio());

                // Crear el DatePickerDialog
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Prestamo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        // Buscar el TextView donde se guardará la nueva fecha
                        TextView guardaFechaInicio = findViewById(R.id.infotvfechaInicio);

                        // Obtener la fecha de inicio original como Date
                        Date fechaInicio = prestamo.getFechaInicio();

                        // Crear un SimpleDateFormat para formatear la fecha y hora
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                        // Formatear la fecha original a un string y dividirla en fecha y hora
                        String[] fecha = format.format(fechaInicio).split(" ");

                        // Actualizar el TextView con la nueva fecha y la hora original
                        guardaFechaInicio.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                // Mostrar el dialogo
                dialogo.show();
            }
        });

////////////////////////////////////////////////////////DatePicker Fecha Fin//////////////////////////////////////////////////////////////////////////////////
        TextView fechaFin = findViewById(R.id.infotvfechaFin);
        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicializar el calendario con la fecha de fin del préstamo
                Calendar c = Calendar.getInstance();
                c.setTime(prestamo.getFechaFin());

                // Crear el DatePickerDialog
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Prestamo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        // Buscar el TextView donde se guardará la nueva fecha
                        TextView guardaFechaFin = findViewById(R.id.infotvfechaFin);

                        // Obtener la fecha de fin original como Date
                        Date fechaFin = (Date) prestamo.getFechaFin();

                        // Crear un SimpleDateFormat para formatear la fecha y hora
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                        // Formatear la fecha original a un string y dividirla en fecha y hora
                        String[] fecha = format.format(fechaFin).split(" ");

                        // Actualizar el TextView con la nueva fecha y la hora original
                        guardaFechaFin.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);

//                        // Aquí guardamos la fecha seleccionada en la variable de fechaFin, si es necesario:
//                        // Prestamo.setFechaFin(new Date(...)) o algo similar si necesitas actualizar el objeto.
//                        Date datefechaFin = Date.valueOf(String.valueOf(guardaFechaFin));
//                        guardaFechaFin.setText((CharSequence) datefechaFin);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                // Mostrar el dialogo
                dialogo.show();
            }
        });

    }
}