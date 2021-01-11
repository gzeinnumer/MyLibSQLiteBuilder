package com.gzeinnumer.mylibsqlitebuilder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.gzeinnumer.mylibsqlitebuilder.struct.SQLiteDatabaseEntity;
import com.gzeinnumer.mylibsqlitebuilder.table.Table1;
import com.gzeinnumer.mylibsqlitebuilder.table.Table2;
import com.gzeinnumer.mylibsqlitebuilder.table.Table3;

@SQLiteDatabaseEntity(entities = {
        Table1.class,
        Table2.class,
        Table3.class
})
public class DBInstance extends SQLiteBuilder {
    private static final String TAG = "DBInstance_";

    private static SQLiteDatabase db;
    public static String DB_PATH_EXTERNAL = Environment.getExternalStorageDirectory().toString() + "/MyLibSQLiteExternal/MyLibSQLiteSimple.db";
    public static String DB_PATH_BC = Environment.getExternalStorageDirectory().toString() + "/MyLibSQLiteBC/MyLibSQLiteSimple.db";
    public static String DB_NAME = "MyLibSQLiteSimple.db";

    public static SQLiteDatabase getDataBase(Context context) {
        db = SQLiteBuilder.builder(DBInstance.class, context)
                .setDatabaseName(DB_NAME)
                .setDatabaseVersion(1)
//                .backUpDatabaseToExternal(DB_PATH_BC)
//                .loadDatabaseFromExternal(DB_PATH_EXTERNAL)
                .build();
        return db;
    }

    public boolean delete() {
        return deleteDatabase(DB_PATH_EXTERNAL);
    }

    public boolean deleteRootDb(Context context) {
        return deleteDatabaseOnRoot(context, DB_NAME);
    }

    public boolean backUp(Context context) {
        String BACK_UP_TO = Environment.getExternalStorageDirectory().toString() + "/MyLibSQLiteExternalBackUp";
        return backUpDatabase(context, BACK_UP_TO, DB_NAME);
    }

    public boolean isDBExist(){
        return isDatabaseExists(DB_PATH_EXTERNAL);
    }

    public boolean isDBExistOnRoot(Context context){
        return isDatabaseExistOnRoot(context, DB_NAME);
    }
}
