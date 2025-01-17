package com.example.aplicacionincidencias.Bbdd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class BbddIncidencias extends SQLiteOpenHelper {

    private static final String BORRAR_TABLA_tipo = "DROP TABLE if exists tipo";
    private static final String BORRAR_TABLA_sala = "DROP TABLE if exists sala";
    private static final String BORRAR_TABLA_rol = "DROP TABLE if exists rol";
    private static final String BORRAR_TABLA_incidencia = "DROP TABLE if exists incidencia";
    private static final String BORRAR_TABLA_usuario = "DROP TABLE if exists  usuario";
    private static final String BORRAR_TABLA_elemento = "DROP TABLE if exists elemento";
    private static final String BORRAR_TABLA_prestamo = "DROP TABLE if exists prestamo";
    private static final String BORRAR_TABLA_ubicacion = "DROP TABLE if exists ubicacion";

    private static final String crear_tabla_tipo = "CREATE TABLE if not exists tipo(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT)";
    private static final String crear_tabla_sala = "CREATE TABLE if not exists sala(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT)";
    private static final String crear_tabla_rol = "CREATE TABLE if not exists rol(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT,nivel_acceso INTEGER)";
    private static final String crear_tabla_usuario = "CREATE TABLE if not exists usuario(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,correo TEXT,telefono TEXT,password TEXT,rol INTEGER)";
    private static final String crear_tabla_ubicacion = "CREATE TABLE if not exists ubicacion(codigo INTEGER PRIMARY KEY AUTOINCREMENT,idSala INTEGER,descripcion TEXT,fechaInicio TEXT,fechaFin TEXT,idElemento INTEGER)";
    private static final String crear_tabla_elemento = "CREATE TABLE if not exists elemento(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT,idTipo INTEGER)";
    private static final String crear_tabla_prestamo = "CREATE TABLE if not exists prestamo(codigo INTEGER PRIMARY KEY AUTOINCREMENT,idUsuario INTEGER,idElemento INTEGER,fechaInicio TEXT,fechaFin TEXT)";
    private static final String crear_tabla_incidencia = "CREATE TABLE if not exists incidencia(codigo INTEGER PRIMARY KEY AUTOINCREMENT,idUsuarioCreacion INTEGER,idElemento INTEGER,fechaCreacion TEXT,descripcion TEXT)";

    public static final String KEY_COL_CODIGO = "codigo";
    public static final String KEY_COL_NOMBRE = "nombre";
    public static final String KEY_COL_DESCRIPCION = "descripcion";

    //Tablas
    public static final String TABLA_TIPO = "tipo";
    public static final String TABLA_SALA = "sala";
    public static final String TABLA_ROL = "rol";
    public static final String TABLA_USUARIO = "usuario";
    public static final String TABLA_UBICACION = "ubicacion";
    public static final String TABLA_ELEMENTO = "elemento";
    public static final String TABLA_PRESTAMO = "prestamo";
    public static final String TABLA_INCIDENCIA = "incidencia";


    public BbddIncidencias(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(crear_tabla_rol);
        sqLiteDatabase.execSQL(crear_tabla_usuario);
        sqLiteDatabase.execSQL(crear_tabla_sala);
        sqLiteDatabase.execSQL(crear_tabla_ubicacion);
        sqLiteDatabase.execSQL(crear_tabla_tipo);
        sqLiteDatabase.execSQL(crear_tabla_elemento);
        sqLiteDatabase.execSQL(crear_tabla_prestamo);
        sqLiteDatabase.execSQL(crear_tabla_incidencia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(BORRAR_TABLA_tipo);
        sqLiteDatabase.execSQL(BORRAR_TABLA_sala);
        sqLiteDatabase.execSQL(BORRAR_TABLA_rol);
        sqLiteDatabase.execSQL(BORRAR_TABLA_incidencia);
        sqLiteDatabase.execSQL(BORRAR_TABLA_usuario);
        sqLiteDatabase.execSQL(BORRAR_TABLA_elemento);
        sqLiteDatabase.execSQL(BORRAR_TABLA_prestamo);
        sqLiteDatabase.execSQL(BORRAR_TABLA_ubicacion);

        onCreate(sqLiteDatabase);
    }


}
