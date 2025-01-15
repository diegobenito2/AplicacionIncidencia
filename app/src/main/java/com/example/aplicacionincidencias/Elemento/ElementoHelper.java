package com.example.aplicacionincidencias.Elemento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;

public class ElementoHelper extends BbddIncidencias {
    public ElementoHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


}
