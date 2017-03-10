package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ManualEnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualenter);
    }
<<<<<<< HEAD
    private EditText item;
    private EditText location;
    private EditText price;

    public void buttonOnClick(View v) {
        Button button=(Button) v;
        //Button price=(Button) v;
        //Button location=(Button) v;
        item = (EditText) findViewById(R.id.itemName);
        location = (EditText) findViewById(R.id.locationInput);
        price = (EditText) findViewById(R.id.priceInput);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP| Gravity.LEFT, 0, 0);
        toast.makeText(ManualEnterActivity.this, item.getText(), toast.LENGTH_SHORT).show();
        toast.makeText(ManualEnterActivity.this, location.getText(), toast.LENGTH_SHORT).show();
        toast.makeText(ManualEnterActivity.this, price.getText(), toast.LENGTH_SHORT).show();
    }
}
=======
}
>>>>>>> origin/tempswipes
