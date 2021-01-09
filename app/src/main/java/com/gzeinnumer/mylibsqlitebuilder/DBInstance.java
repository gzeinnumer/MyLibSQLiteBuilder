package com.gzeinnumer.mylibsqlitebuilder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.gzeinnumer.mylibsqlitebuilder.lib.SQLiteBuilder;

public class DBInstance{
    private static final String TAG = "DBInstance_";

    private static SQLiteDatabase db;
    public static String DB_PATH_EXTERNAL = Environment.getExternalStorageDirectory().toString() + "/MyLibSQLiteExternal/MyLibSQLiteSimple.db";
    public static String DB_PATH_BC = Environment.getExternalStorageDirectory().toString() + "/MyLibSQLiteBC/MyLibSQLiteSimple.db";
    public static String DATABASE_NAME = "MyLibSQLiteSimple.db";

    public static SQLiteDatabase getDataBase(Context context) {
//        if (db == null){
        db = SQLiteBuilder.builder(DBInstance.class,context)
                .setDatabaseName(DATABASE_NAME)
                .setDatabaseVersion(1)
//                .backUpDatabaseToExternal(DB_PATH_BC)
//                .loadDatabaseFromExternal(DB_PATH_EXTERNAL)
                .build();
//        }
        return db;
    }
}
