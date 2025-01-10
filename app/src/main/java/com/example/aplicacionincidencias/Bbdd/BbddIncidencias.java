package com.example.aplicacionincidencias.Bbdd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class BbddIncidencias extends SQLiteOpenHelper {
    private static final String BORRAR_TABLA_tipo = "DROP TABLE tipo";
    private static final String BORRAR_TABLA_sala = "DROP TABLE sala";
    private static final String BORRAR_TABLA_rol = "DROP TABLE rol";
    private static final String BORRAR_TABLA_incidencia = "DROP TABLE incidencia";
    private static final String BORRAR_TABLA_usuario = "DROP TABLE usuario";
    private static final String BORRAR_TABLA_elemento = "DROP TABLE elemento";
    private static final String BORRAR_TABLA_prestamo = "DROP TABLE prestamo";
    private static final String BORRAR_TABLA_ubicacion = "DROP TABLE ubicacion";

    private static final String crear_tabla_tipo = "CREATE TABLE tipo(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT)";
    private static final String crear_tabla_sala = "CREATE TABLE sala(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT)";
    private static final String crear_tabla_rol = "CREATE TABLE rol(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT,nivel_acceso INTEGER)";
    private static final String crear_tabla_usuario = "CREATE TABLE usuario(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,correo TEXT,telefono TEXT,password TEXT,rol INTEGER)";
    private static final String crear_tabla_ubicacion = "CREATE TABLE ubicacion(codigo INTEGER PRIMARY KEY AUTOINCREMENT,idSala INTEGER,descripcion TEXT,fechaInicio TEXT,fechaFin TEXT,idElemento INTEGER)";
    private static final String crear_tabla_elemento = "CREATE TABLE elemento(codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,descripcion TEXT,idTipo INTEGER)";
    private static final String crear_tabla_prestamo = "CREATE TABLE prestamo(codigo INTEGER PRIMARY KEY AUTOINCREMENT,idUsuario INTEGER,idElemento INTEGER,fechaInicio TEXT,fechaFin TEXT)";
    private static final String crear_tabla_incidencia = "CREATE TABLE incidencia(codigo INTEGER PRIMARY KEY AUTOINCREMENT,idUsuarioCreacion INTEGER,idElemento INTEGER,fechaCreacion TEXT,descripcion TEXT)";

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
