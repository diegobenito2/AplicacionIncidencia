package com.example.aplicacionincidencias.Tipo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;
import com.example.aplicacionincidencias.Sala.SalaHelper;
import java.util.ArrayList;
import gestionincidencias.entidades.EntTipo;

public class TipoHelper extends BbddIncidencias {
    public TipoHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public long crearTipo(EntTipo tipo) {
        SQLiteDatabase db = getWritableDatabase();
        long tipoId = -1;
        db.beginTransaction(); //Inicia la transacción y garantiza que no se acceden a los datos mientras se realiza la transaccion(una transacción es un conjunto de consultas.)
        try {
            ContentValues values = new ContentValues();
            if (tipo.getCodigoTipo() > 0) {
                values.put(KEY_COL_CODIGO, tipo.getCodigoTipo());
            }
            values.put(KEY_COL_NOMBRE, tipo.getNombre());
            values.put(KEY_COL_DESCRIPCION, tipo.getDescripcion());
            tipoId = db.insertOrThrow(TABLA_TIPO, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(SalaHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }

        return tipoId;
    }

    public long actualizarTipo(EntTipo tipo) {
        SQLiteDatabase db = getWritableDatabase();
        long tipoId = -1;
        if (tipo.getCodigoTipo() > 0) {
            db.beginTransaction(); //Inicia la transacción y garantiza que no se acceden a los datos mientras se realiza la transaccion(una transacción es un conjunto de consultas.)
            try {
                ContentValues values = new ContentValues();
                values.put(KEY_COL_CODIGO, tipo.getCodigoTipo() > 0 ? tipo.getCodigoTipo() : null);
                values.put(KEY_COL_NOMBRE, tipo.getNombre());
                values.put(KEY_COL_DESCRIPCION, tipo.getDescripcion());
                int rows = db.update(TABLA_TIPO, values, KEY_COL_CODIGO + "= ?", new String[]{String.valueOf(tipo.getCodigoTipo())});
                if (rows > 0) {
                    tipoId = tipo.getCodigoTipo();
                }
            } catch (Exception e) {
                Log.d(SalaHelper.class.getName(), e.getMessage());
            } finally {
                db.endTransaction();
            }

        }
        return tipoId;
    }

    public ArrayList<EntTipo> obtenerTipos() {
        ArrayList<EntTipo> tipos = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from tipo", null);
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                EntTipo tipo = new EntTipo(codigo, nombre, descripcion);
                tipos.add(tipo);
            } while (cursor.moveToNext());
        }
        return tipos;
    }
}
