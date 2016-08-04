package com.example.mgkan.hackathon_lost_pets.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.mgkan.hackathon_lost_pets.Model.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by erikrudie on 7/23/16.
 */

/*
All SQL queries are made from this class
 */

//    REMINDER: NICKNAMES ARE STORED W/O APOSTRASCES OR QUOTATION MARKS

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String DATABASE_NAME = "LOST_PETS_DB";
    private static final int DATABASE_VERSION = 1;

    private static DBHelper DB;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public static DBHelper getInstance(Context context) {
        if (DB == null) {
            DB = new DBHelper(context);
        }
        return DB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      // TODO: do... stuff... here?
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTables();
        this.onCreate(db);
    }

    public void createDbIfNotExists() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SC.CREATE_TABLE);
    }

    public void dropAllTables() {
        SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + SC.TABLE_PETS);
    }



    // This method returns the character ID of the inserted row after inserting character details
    public void insertPetIntoDb(Pet pet) {
        String id = pet.getAnimalId();
        String type = pet.getAnimalType();
        String date = pet.getDate();
        String dateType = pet.getDateType();
        String color = pet.getColor();
        String image = pet.getImage();
        String city = pet.getCity();
        String name = pet.getName();
        String gender = pet.getAnimalGender();
        String breed = pet.getAnimalBreed();
        String link = pet.getLink();
        int zip = pet.getZip();
        String address = pet.getAddress();
        String memo = pet.getMemo();
        String location = pet.getCurrentLocation();

        String sql = "INSERT INTO " + SC.TABLE_PETS + " (" + SC.ID + ", " + SC.TYPE + ", " + SC.DATE + ", "
                + SC.DATE_TYPE + ", " + SC.COLOR + ", " + SC.IMAGE + ", " + SC.CITY + ", " + SC.NAME + ", "
                + SC.GENDER + ", " + SC.BREED + ", " + SC.LINK + ", " + SC.ZIP + ", " + SC.ADDRESS + ", "
                + SC.MEMO + ", " + SC.LOCATION + ") VALUES ('" + id + "', '" + type + "', '"
                + date + "', '" + dateType + "', '" + color + "', '"  + image + "', '" + city + "', '"
                + name + "', '" + gender + "', '" + breed + "', '" + link + "', '" + zip + "', '"
                + address + "', '" + memo + "', '" + location + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }


    public String cleanTextForDb(String string) {
        string = string.replace("'", "''");
        string = string.replace("\"", "\\\"");
        return string;
    }


}