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


    public SettingsData(int id, double budget, String name, String password, int notif) {
        this._budget=budget;
        this._id=id;
        this._name=name;
        this._password=password;
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

    public String get_password(){
        return _password;
    }

    public void set_id(int _id) {
        this._id=_id;
    }
    public void set_budget(double _budget){
        this._budget=_budget;

    }
    public void set_name(String _name){
        this._name=_name;
    }
    public void set_password(String _password){
        this._password=_password;
    }
    public void set_notifications(int _notifications) {
        this._notifications=_notifications;
    }

}

