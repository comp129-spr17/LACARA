package com.example.amk.lacara;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText entEmailLogin = (EditText) findViewById(R.id.entEmailLogin);
        final EditText entPassLogin = (EditText) findViewById(R.id.entPassLogin);
        final Button btLogin = (Button) findViewById(R.id.btLogin);
        final Button btReg = (Button) findViewById(R.id.btReg);

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(regIntent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String useremail = entEmailLogin.getText().toString();
                final String password = entPassLogin.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonLogin = new JSONObject(response);
                            boolean success = jsonLogin.getBoolean("success");

                            if (success) {
                                String useremail = jsonLogin.getString("useremail");
                                String password = jsonLogin.getString("password");

                                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                                intent.putExtra("useremail", useremail);

                                MainActivity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Invalid Email or Password!")
                                        .setNegativeButton("Please try again!", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(useremail, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}
