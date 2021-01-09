package com.gzeinnumer.mylibsqlitebuilder.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class MyLibSQLiteBuilderHelper {
    private final String TAG = "Helper";
    private String DB_NAME;
    private int DATABASE_VERSION;
    private String DB_PATH_EXTERNAL = "";
    private String DB_PATH_BACKUP = "";
    private SQLiteDatabase myDataBase;
    private Context context;

    Class<?> aClass;

    public MyLibSQLiteBuilderHelper(Class<?> clss, Context context) {
        this.aClass = clss;
        this.context = context;
    }

    public MyLibSQLiteBuilderHelper setDatabaseName(String dbName) {
        if (!dbName.contains(".db")){
            dbName = dbName+".db";
        }
        DB_NAME = dbName;
        return this;
    }

    public MyLibSQLiteBuilderHelper setDatabaseVersion(int version) {
        DATABASE_VERSION = version;
        return this;
    }

    public MyLibSQLiteBuilderHelper backUpDatabaseToExternal(String dbPath) {
        DB_PATH_BACKUP = dbPath;
        return this;
    }

    public MyLibSQLiteBuilderHelper loadDatabaseFromExternal(String dbPath) {
        DB_PATH_EXTERNAL = dbPath;
        return this;
    }

    public SQLiteDatabase build() throws SQLException {
        MyLibSQLiteDBHelper myDB;
        if (DB_PATH_EXTERNAL.length() > 0) {
            myDB = new MyLibSQLiteDBHelper(context, DB_NAME, null, DATABASE_VERSION);
            File dbFile = new File(DB_PATH_EXTERNAL);

            if (dbFile.exists()) {
                Log.d(TAG, "DatabaseHelper: Database exist");
                this.myDataBase = SQLiteDatabase.openDatabase(DB_PATH_EXTERNAL, null, 0);
            } else {
                if (!checkDatabase()) {
                    try {
                        myDB.getReadableDatabase();
                        copyDatabase();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (DB_PATH_BACKUP.length()>0){
            myDB = new MyLibSQLiteDBHelper(context, DB_NAME, null, DATABASE_VERSION);
            this.myDataBase = myDB.getWritableDatabase();
            backup(context);
            this.myDataBase = SQLiteDatabase.openDatabase(DB_PATH_BACKUP, null, 0);
        } else {
            myDB = new MyLibSQLiteDBHelper(context, DB_NAME, null, DATABASE_VERSION);
            this.myDataBase = myDB.getWritableDatabase();
        }
        return this.myDataBase;
    }

    public boolean checkDatabase() {
        boolean statusDB = false;
        SQLiteDatabase checkDB = null;
        try {
            if (new File(DB_PATH_EXTERNAL).exists()) {
                try {
                    checkDB = SQLiteDatabase.openDatabase(DB_PATH_EXTERNAL, null, 0);
                    statusDB = true;
                } catch (Exception e) {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return statusDB;
    }

    private void copyDatabase() throws IOException {
        if (new File(DB_PATH_EXTERNAL).exists()){
            InputStream myInput = this.context.getAssets().open(DB_NAME);
            File outFile = this.context.getDatabasePath(DB_PATH_EXTERNAL);
            outFile.delete();
            OutputStream myOutput = new FileOutputStream(outFile);
            byte[] buffer = new byte[1204];
            while (true) {
                int length = myInput.read(buffer);
                if (length <= 0) {
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();

                    return;
                }
                myOutput.write(buffer, 0, length);
            }

        } else {
            Log.e(TAG, "copyDatabase: Database file doesn't exist" );
        }
    }

    private void backup(Context applicationContext) {
        try {
            String fileName = getNameFromUrl(DB_PATH_EXTERNAL);
            String dir = DB_PATH_EXTERNAL.replace(fileName,"");
            new File(dir).mkdirs();
            @SuppressLint("SdCardPath") final String inFileName = "/data/data/"+applicationContext.getPackageName()+"/databases/"+ DB_NAME;
            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            OutputStream output = new FileOutputStream(DB_PATH_EXTERNAL);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            fis.close();
        } catch (IOException e) {
            Log.e(TAG, "backup: failed backup Database");
            e.printStackTrace();
        }
    }

    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf('/'));
    }
}