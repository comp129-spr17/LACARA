/**
 * Created by LJImutan on 3/21/17.
 */

package com.example.amk.lacara;



public class Data {

    private int _id;
    private String _itemname;
    private String _location;
    private double _price;
    private String _date;
    private String _cat;

    public Data(String name, String location, double price, String date, String cat)
    {
        this._itemname=name;
        this._location=location;
        this._price=price;
        this._date=date;
        this._cat=cat;
    }

    public int get_id() {
        return _id;
    }

    public String get_itemname() {
        return _itemname;
    }

    public String get_location() {
        return _location;
    }

    public double get_price() {
        return _price;
    }

    public String get_date() { return _date; }

    public String get_cat() { return _cat; }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_itemname(String _itemname) {
        this._itemname = _itemname;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public void set_date(String _date) { this._date = _date; }

    public void set_cat(String _cat) { this._cat = _cat; }
}
