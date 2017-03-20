package com.example.amk.lacara;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.camera2.params.StreamConfigurationMap;


/**
 * Created by AMK on 3/19/2017.
 */

public abstract class AddPrefUtility {

    private SharedPreferences sharedPreferences;

    public static boolean addItem(Activity activity, String addNewItem){
        //Getting previous list to check

        String itemList = getStringFromPref(activity,null,"items");
        // Appending new Item to list
        if(itemList!=null){
            itemList = itemList+","+addNewItem;
        }
        else{
            itemList = addNewItem;
        }
        // Save to the Shared Preferences file
        return putStringInPref(activity,itemList,"items");
    }

    //Other Functions to complete actions
    // Retrieves items from file
    public static String[] getItemList(Activity activity){
        String itemList = getStringFromPref(activity,null,"items");
        return convertStringToArray(itemList);
    }
    //This pushes the new string into the SharedPref
    private static boolean putStringInPref(Activity activity,String item, String key){
        SharedPreferences sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, item);
        editor.commit();
        return true;
    }
    //Retrieves the string from sharedPref
    private static String getStringFromPref(Activity activity, String defaultValue, String key){
        SharedPreferences sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, defaultValue);
        return temp;
    }

    //Converts the string into the Array to save
    //Splits by , like csv
    private static String[] convertStringToArray(String str){
        String[] itemArray = str.split(",");
        return itemArray;
    }



}
