package com.example.amk.lacara;

/**
 * Created by AMK on 4/9/2017.
 */

public class SettingsData {

    private int _id;
    private double _budget;
    private String _name;
    private String _password;
    private int _notifications;


    public SettingsData(int id, double budget, String name, String pass, int notif)
    {
        this._budget=budget;
        this._id=id;
        this._name=name;
        this._password=pass;
        this._notifications=notif;
    }

    public double get_budget(){
        return _budget;
    }

    public String get_name(){
        return _name;
    }

    public int get_notifications()
    {
        return _notifications;
    }

}

