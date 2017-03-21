/**
 * Created by LJImutan on 3/21/17.
 */

package com.example.amk.lacara;



public class Data {

    private int _id;
    private String _itemname;
    private String _location;
    private double _price;

    public Data(String name, String location, double price)
    {
        this._itemname=name;
        this._location=location;
        this._price=price;
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
}
