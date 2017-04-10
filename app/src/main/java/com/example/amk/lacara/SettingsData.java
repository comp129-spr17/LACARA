package com.example.amk.lacara;

/**
 * Created by AMK on 4/9/2017.
 */

public class SettingsData {

    private int _id;
    private int _budget;
    private String _Name;
    private String _Password;
    private int _Notifications;


    public SettingsData(int id, int budget, String name, String pass, int notif)
    {
        this._budget=budget;
        this._id=id;
        this._Name=name;
        this._Password=pass;
        this._Notifications=notif;


    }

}

