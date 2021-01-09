package com.gzeinnumer.mylibsqlitebuilder.lib;

import android.content.Context;

import com.gzeinnumer.mylibsqlitebuilder.lib.MyLibSQLiteBuilderHelper;

public class SQLiteBuilder {

    public static MyLibSQLiteBuilderHelper builder(Class<?> clss, Context context) {
        return new MyLibSQLiteBuilderHelper(clss,context);
    }

}
