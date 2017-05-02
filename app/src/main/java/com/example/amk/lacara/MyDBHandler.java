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
    public static final String COLUMN_NAME = "_Name";
    public static final String COLUMN_EMAIL = "_email";
    public static final String COLUMN_PASS = "_Password";
    public static final String COLUMN_BUDGET = "_budget";

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
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
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
        values.put(COLUMN_CAT, recipe.get_cat());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    //Adding user settings
    public void addSettings(SettingsData user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.get_name());
        values.put(COLUMN_BUDGET, user.get_budget());
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

    public ArrayList<ArrayList<String>> singleResult(String selectedDate)
    {
        String dbString = "";
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<String>();
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
                inner.add(recordSet.getString(recordSet.getColumnIndex("_id")));
                inner.add(recordSet.getString(recordSet.getColumnIndex("_recipename")));
                inner.add(recordSet.getString(recordSet.getColumnIndex("_location")));
                inner.add(recordSet.getString(recordSet.getColumnIndex("_price")));
                inner.add(recordSet.getString(recordSet.getColumnIndex("_date")));
                inner.add(recordSet.getString(recordSet.getColumnIndex("_Cat")));
            }
            list.add(inner);
            inner = new ArrayList<String>();
            recordSet.moveToNext();
        }
        db.close();
        return list;
    }


    public double getTotalSpending(String month, String year)
    {
        double totalSpending = 0;
        SQLiteDatabase db = getWritableDatabase();
        String date = month + "-__-" + year;
        //Log.d("tag", "Total money");
        String query = "SELECT " + COLUMN_PRICE + " FROM " + TABLE_RECIPES + " WHERE " + COLUMN_DATE + " like " + "\'" + date + "\'";
        //Log.d("tag", query);
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_recipename");
        while (!recordSet.isAfterLast()) {
            totalSpending += Double.parseDouble(recordSet.getString(recordSet.getColumnIndex("_price")));
            recordSet.moveToNext();
        }
        db.close();
        return totalSpending;
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

    public double getMonthCat(String month, String year, String Cat)
    {
        SQLiteDatabase db = getWritableDatabase();
        double sum = 0;
        String date = month + "-__-" + year;
        String query = "SELECT *" + " FROM " + TABLE_RECIPES + " WHERE " + COLUMN_DATE + " like " + "\'" + date + "\' AND " + COLUMN_CAT + " = \'" + Cat + "\'";
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

    public boolean updateUser(String x, String newInfo)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(x, newInfo);
        return db.update(TABLE_SETTINGS, args, COLUMN_ID + "= 1", null) > 0;
    }

    public void userExist()
    {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT *" + " FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + " = 1";
        Cursor recordSet = db.rawQuery(query, null);

        if (!recordSet.moveToFirst()) {
            ContentValues args = new ContentValues();
            args.put(COLUMN_NAME, "Name");
            args.put(COLUMN_BUDGET, 0);
            db.insert(TABLE_SETTINGS, null, args);
            Log.d("insert", "Insert completed");
        }
    }

    public ArrayList<String> getUser()
    {
        ArrayList<String> result = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT *" + " FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + " = 1";
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();

        if (recordSet.getString(recordSet.getColumnIndex("_id")) != null) {
            ContentValues args = new ContentValues();
            result.add(recordSet.getString(recordSet.getColumnIndex("_Name")));
            result.add(recordSet.getString(recordSet.getColumnIndex("_budget")));
        }
        db.close();
        return result;
    }

    public String getBudget()
    {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT *" + " FROM " + TABLE_SETTINGS + " WHERE " + COLUMN_ID + " = 1";
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        db.close();
        Log.d("budget",recordSet.getString(recordSet.getColumnIndex("_budget")));
        return recordSet.getString(recordSet.getColumnIndex("_budget"));
    }
}
