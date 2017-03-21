package com.example.amk.lacara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME= "recipes.db";
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPENAME = "_recipename";
    public static final String COLUMN_LOCATION = "_location";
    public static final String COLUMN_PRICE = "_price";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + TABLE_RECIPES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPENAME + " TEXT " +
                COLUMN_LOCATION + " TEXT " +
                COLUMN_PRICE + " DOUBLE " +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    //Add a new row to database
    public void addRecipe(Data recipe)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPENAME, recipe.get_itemname());
        //values.put(COLUMN_LOCATION, recipe.get_location());
        //values.put(COLUMN_PRICE, recipe.get_price());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
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
        //Cursor recordSet1 = db.rawQuery(query, null);
        //Cursor recordSet2 = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_recipename");
        //recordSet1.moveToFirst();
        //recordSet1.getColumnIndex("_location");
        //recordSet2.moveToFirst();
        //recordSet2.getColumnIndex("_price");
        //Position after the last row means the end of the results

        //Log.d("myTag", "This is my message " + recordSet.getColumnIndex("_recipename"));
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_recipename")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("_recipename"));
                dbString += "\n";
            }
            recordSet.moveToNext();
           // recordSet1.moveToNext();
            //recordSet2.moveToNext();
        }
        db.close();
        return dbString;
    }
}
