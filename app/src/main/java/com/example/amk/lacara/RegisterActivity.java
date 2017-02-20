package com.example.amk.lacara;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText entEmail = (EditText) findViewById(R.id.entEmail);
        final EditText entPass = (EditText) findViewById(R.id.entPass);
        final EditText entConPass = (EditText) findViewById(R.id.entConPass);

        final Button btRegister = (Button) findViewById(R.id.Register);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = entEmail.getText().toString();
                final String password = entPass.getText().toString();

                Response.Listener<String> resListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRes = new JSONObject(response);
                            boolean success = jsonRes.getBoolean("Created!");

                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration failed!")
                                        .setNegativeButton("Please try again!", null)
                                        .create()
                                        .show();
                            }
                        }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                };

                RegisterRequest registerRequest = new RegisterRequest(email, password, resListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

            }
        });

    }
}
