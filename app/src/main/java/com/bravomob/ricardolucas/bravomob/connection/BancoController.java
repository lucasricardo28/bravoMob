package com.bravomob.ricardolucas.bravomob.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bravomob.ricardolucas.bravomob.model.SinalGps;

import java.util.ArrayList;
import java.util.List;

public class BancoController {
    private SQLiteDatabase db;
    private DataBase dataBase;
    Context context;

    public BancoController(Context context){
        dataBase = new DataBase(context);
        this.context = context;
    }

    public String inserirPosition(double longi, double lati, String name, String address, String time){
        ContentValues valores;
        long resultado;

        db = dataBase.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DataBase.LONGITUDE, longi);
        valores.put(DataBase.LATITUDE, lati);
        valores.put(DataBase.NAME, name);
        valores.put(DataBase.ADDRESS, address);
        valores.put(DataBase.TIME, time);

        resultado = db.insert(DataBase.TABLE_REFRIGERATOR_GPS, null, valores);
        db.close();

        if (resultado ==-1){
            return "Erro ao inserir registro";
        }
        else{
            return "Registro Inserido com sucesso";
        }

    }

    public List<SinalGps> getPots(){
        List<SinalGps> listPots = new ArrayList<>();

        Cursor cursor;
        String[] campos =  {dataBase.ID ,dataBase.LATITUDE , dataBase.LONGITUDE, dataBase.NAME, dataBase.ADDRESS, dataBase.TIME };
        db = dataBase.getReadableDatabase();
        cursor = db.query(dataBase.TABLE_REFRIGERATOR_GPS, campos, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            SinalGps sinalGps = new SinalGps();

            sinalGps.setId(cursor.getInt(cursor.getColumnIndex("id")));
            sinalGps.setLongitude(cursor.getDouble(cursor.getColumnIndex("long")));
            sinalGps.setLatitude(cursor.getDouble(cursor.getColumnIndex("lat")));
            sinalGps.setName(cursor.getString(cursor.getColumnIndex("name")));
            sinalGps.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            sinalGps.setTime(cursor.getString(cursor.getColumnIndex("time")));

            listPots.add(sinalGps);
        }
        cursor.close();

        return listPots;
    }

    public SinalGps getPotsItem(String itemId){
        SinalGps sinalGps = new SinalGps();

        Cursor cursor;
        String[] campos =  {dataBase.ID ,dataBase.LATITUDE , dataBase.LONGITUDE, dataBase.NAME, dataBase.ADDRESS, dataBase.TIME };
        db = dataBase.getReadableDatabase();
        cursor = db.query(dataBase.TABLE_REFRIGERATOR_GPS, campos, null, null, null, null, null, null);

        while (cursor.moveToNext()){


            sinalGps.setId(cursor.getInt(cursor.getColumnIndex("id")));
            sinalGps.setLongitude(cursor.getDouble(cursor.getColumnIndex("long")));
            sinalGps.setLatitude(cursor.getDouble(cursor.getColumnIndex("lat")));
            sinalGps.setName(cursor.getString(cursor.getColumnIndex("name")));
            sinalGps.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            sinalGps.setTime(cursor.getString(cursor.getColumnIndex("time")));

        }
        cursor.close();

        return sinalGps;
    }
}
