package com.example.amk.lacara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.util.Log;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME= "recipes.db";
    public static final String TABLE_RECIPES = "recipes";
    //public static final String DATABASE_SETTING_NAME = "settings.db";
    public static final String TABLE_SETTINGS = "settings";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPENAME = "_recipename";
    public static final String COLUMN_LOCATION = "_location";
    public static final String COLUMN_PRICE = "_price";
    public static final String COLUMN_DATE = "_date";
    public static final String COLUMN_CAT = "_Cat";
    public static final String COLUMN_NAME = " _Name ";
    public static final String COLUMN_EMAIL = " _email ";
    public static final String COLUMN_PASS = " _Password ";
    public static final String COLUMN_BUDGET = " _budget ";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String RecipeQ =  "CREATE TABLE " + TABLE_RECIPES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPENAME + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_PRICE + " DOUBLE, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_CAT + " TEXT " +

                ");";

        String settingsQuery =  "CREATE TABLE " + TABLE_SETTINGS + "(" +
                COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PASS + " TEXT, " +
                COLUMN_BUDGET + " DOUBLE " +



                ");";
        db.execSQL(RecipeQ);
        db.execSQL(settingsQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MyDBHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        onCreate(db);
    }

    //Add a new row to database iteminfo
    public void addRecipe(Data recipe)
    {
        Calendar c = Calendar.getInstance();
        //SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        //String formattedDate = df.format(c.getTime());
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPENAME, recipe.get_itemname());
        values.put(COLUMN_LOCATION, recipe.get_location());
        values.put(COLUMN_PRICE, recipe.get_price());
        values.put(COLUMN_DATE, recipe.get_date());
        values.put(COLUMN_CAT, "Cat");
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    //Adding user settings
    public void addSettings(SettingsData user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.get_name());
        values.put(COLUMN_PASS, user.get_password());
        values.put(COLUMN_BUDGET, user.get_budget());
        values.put(COLUMN_EMAIL, user.get_email());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SETTINGS, null, values);
        db.close();
    }


    public void deleteRecipe(String recipeName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPENAME +"=\"" + recipeName + "\"" );
    }

    public String databaseToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE 1";

        //Cursor point to the location in your result
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_recipename");
        //Position after the last row means the end of the results


        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_recipename")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("_recipename"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_location"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_price"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_date"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_Cat"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    public ArrayList<String> itemViewDisplay(int id)
    {
        String dbString = "";
        ArrayList<String> list = new ArrayList<String>();
        int x = 0;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + COLUMN_ID + " = " + id;
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        list.add(recordSet.getString(recordSet.getColumnIndex("_recipename")));
        list.add(recordSet.getString(recordSet.getColumnIndex("_location")));
        list.add(recordSet.getString(recordSet.getColumnIndex("_date")));
        list.add(recordSet.getString(recordSet.getColumnIndex("_price")));
        return list;
    }

    public ArrayList<String> singleResult(String selectedDate)
    {
        String dbString = "";
        ArrayList<String> list = new ArrayList<String>();
        int x = 0;
        SQLiteDatabase db = getWritableDatabase();
        Log.d("tag", selectedDate);
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + COLUMN_DATE + " = " + "\"" + selectedDate + "\"";
        Log.d("tag", query);
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_recipename");
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_recipename")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("_id"));
                dbString += " : ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_recipename"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_location"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_price"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_date"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_Cat"));
                dbString += "\n";
            }
            list.add(dbString);
            dbString = "";
            recordSet.moveToNext();
        }
        db.close();
        return list;
    }

    public boolean updateSingleDate(String x, String newInfo, int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(x, newInfo);
        return db.update(TABLE_RECIPES, args, COLUMN_ID + "=" + id, null) > 0;

    }

    public double getMonthTotal(String month, String year)
    {
        SQLiteDatabase db = getWritableDatabase();
        double sum = 0;
        String date = month + "-__-" + year;
        String query = "SELECT *" + " FROM " + TABLE_RECIPES + " WHERE " + COLUMN_DATE + " like " + "\'" + date + "\'";
        Log.d("Get Month Querty", query);
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_recipename");
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_recipename")) != null) {
                Log.d("Query result", recordSet.getString(recordSet.getColumnIndex("_price")));
                sum += Double.parseDouble(recordSet.getString(recordSet.getColumnIndex("_price")));
            }
            recordSet.moveToNext();
        }
        db.close();

        return sum;
    }

    public double getBudget() {
        double budget = 0;
        SQLiteDatabase db = getWritableDatabase();

        Log.d("tag", "Budget");
        String query = "SELECT " + COLUMN_BUDGET + " FROM " + TABLE_SETTINGS;
        Log.d("tag", query);
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_budget");
        while (!recordSet.isAfterLast()) {
            budget = Double.parseDouble(recordSet.getString(recordSet.getColumnIndex("_budget")));
            recordSet.moveToNext();
        }
        db.close();
        return budget;
    }
}
