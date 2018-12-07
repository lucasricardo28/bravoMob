package com.bravomob.ricardolucas.bravomob.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "bravo.db";
    private static final int VERSION = 1;

    public static final String TABLE_REFRIGERATOR_GPS = "locais";

    //GPS DATA
    public static final String ID = "id";
    public static final String LONGITUDE = "long";
    public static final String LATITUDE = "lat";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String TIME = "time";

    private static final String CREATE_TABLE_GPS = "CREATE TABLE "+ TABLE_REFRIGERATOR_GPS +"("
            + ID + " integer primary key autoincrement, "
            + LONGITUDE + " real DEFAULT NULL,"
            + LATITUDE + " real DEFAULT NULL,"
            + NAME + " text DEFAULT NULL,"
            + ADDRESS + " text DEFAULT NULL,"
            + TIME + " text DEFAULT NULL"
            +")";

    public DataBase(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFRIGERATOR_GPS);
        onCreate(db);
    }
}
