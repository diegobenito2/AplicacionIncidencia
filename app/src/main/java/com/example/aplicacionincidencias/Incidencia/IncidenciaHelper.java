package com.example.aplicacionincidencias.Incidencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;
import com.example.aplicacionincidencias.Usuario.UsuarioHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import gestionincidencias.entidades.EntIncidencia;


public class IncidenciaHelper extends BbddIncidencias {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public IncidenciaHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public long crearIncidencia(EntIncidencia incidencia) {
        SQLiteDatabase db = getWritableDatabase();
        long incidenciaID = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            if (incidencia.getCodigoIncidencia() > 0) {
                values.put(KEY_COL_CODIGO, incidencia.getCodigoIncidencia());
            }
            values.put(KEY_COL_DESCRIPCION, incidencia.getDescripcion());
            values.put("idElemento", incidencia.getIdElemento());
            values.put("idUsuarioCreacion", incidencia.getIdUsuarioCreacion());
            values.put("fechaCreacion", formatter.format(incidencia.getFechaCreacion()));


            incidenciaID = db.insertOrThrow(TABLA_INCIDENCIA, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(IncidenciaHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return incidenciaID;
    }

    public long actualizarIncidencia(EntIncidencia incidencia) {
        SQLiteDatabase db = getWritableDatabase();
        long incidenciaID = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            if (incidencia.getCodigoIncidencia() > 0) {
                values.put(KEY_COL_CODIGO, incidencia.getCodigoIncidencia());
            }
            values.put(KEY_COL_DESCRIPCION, incidencia.getDescripcion());
            values.put("idElemento", incidencia.getIdElemento());
            values.put("idUsuarioCreacion", incidencia.getIdUsuarioCreacion());
            if (incidencia.getFechaCreacion() != null) {
                values.put("fechaCreacion", formatter.format(incidencia.getFechaCreacion()));
            }else{
                values.put("fechaCreacion", formatter.format(new Date(System.currentTimeMillis())));
            }


            int rows = db.update(TABLA_INCIDENCIA, values, "codigo = ?",
                    new String[]{String.valueOf(incidencia.getCodigoIncidencia())});

            if (rows > 0) {
                db.setTransactionSuccessful();
                incidenciaID = incidencia.getCodigoIncidencia();
            }

        } catch (Exception e) {
            Log.d(UsuarioHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return incidenciaID;
    }

    public EntIncidencia obtenerIncidencia(int idIncidencia) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_INCIDENCIA+" where "+ KEY_COL_CODIGO + "=" + idIncidencia, null);

        EntIncidencia incidencia = null;

        if (cursor.moveToFirst()) {
            do {

                int codigo = cursor.getInt(0);
                int idUsuarioCreacion = cursor.getInt(1);
                int idElemento = cursor.getInt(2);
                String fechaCreacionstr = cursor.getString(3);
                String descripcion = cursor.getString(4);
                Date fechaCreacion = null;

                if (fechaCreacionstr != null && !fechaCreacionstr.equals("")) {
                    try {
                        fechaCreacion = formatter.parse(fechaCreacionstr);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }

                 incidencia = new EntIncidencia(codigo, descripcion, idElemento, fechaCreacion, idUsuarioCreacion);

            } while (cursor.moveToNext());
        }
        return incidencia;
    }

    public ArrayList<EntIncidencia> obtenerIncidencias() {
        ArrayList<EntIncidencia> incidencias = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM incidencia", null);

        if (cursor.moveToFirst()) {
            do {

                int codigo = cursor.getInt(0);
                int idUsuarioCreacion = cursor.getInt(1);
                int idElemento = cursor.getInt(2);
                String fechaCreacionstr = cursor.getString(3);
                String descripcion = cursor.getString(4);
                Date fechaCreacion = null;

                if (fechaCreacion != null && fechaCreacion.equals("")) {
                    try {
                        fechaCreacion = formatter.parse(fechaCreacionstr);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }

                EntIncidencia incidencia = new EntIncidencia(codigo, descripcion, idElemento, fechaCreacion, idUsuarioCreacion);
                incidencias.add(incidencia);
            } while (cursor.moveToNext());
        }
        return incidencias;
    }

    public int borrarIncidencia(int codigo) {
        SQLiteDatabase db = getWritableDatabase();
        int borrados = 0;

        db.beginTransaction();

        try {
            borrados = db.delete(TABLA_INCIDENCIA, "codigo = ?", new String[]{String.valueOf(codigo)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(UsuarioHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return borrados;
    }
     
}

