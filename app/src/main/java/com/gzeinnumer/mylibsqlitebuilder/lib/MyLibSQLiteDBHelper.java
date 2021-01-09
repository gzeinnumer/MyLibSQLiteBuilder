package com.gzeinnumer.mylibsqlitebuilder.lib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class MyLibSQLiteDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private List<String> listTable = new ArrayList<>();

    public MyLibSQLiteDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        listTable = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < listTable.size(); i++) {
            String CREATE_TABLE = listTable.get(i);
            db.execSQL(CREATE_TABLE);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 0; i < listTable.size(); i++) {
            String query = listTable.get(0);
            query=query.replaceAll("\\s+"," ");
            String[] parts = query.split(" ");
            //index 2 is table name
            db.execSQL("DROP TABLE IF EXISTS "+parts[2]);
        }
        onCreate(db);
    }
}
