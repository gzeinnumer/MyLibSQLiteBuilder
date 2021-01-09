package com.gzeinnumer.mylibsqlitebuilder;

import android.content.Context;

import com.gzeinnumer.mylibsqlitebuilder.lib.MyLibSQLiteBuilderHelper;

import java.io.File;

public class SQLiteBuilder {

    private static final String TAG = "SQLiteBuilder";

    public static MyLibSQLiteBuilderHelper builder(Class<?> clss, Context context) {
        return new MyLibSQLiteBuilderHelper(clss, context);
    }

    protected boolean deleteDatabase(String DB_PATH, String DATABASE_NAME) {
        String path = DB_PATH + DATABASE_NAME;

        return new File(path).delete();
    }
}
