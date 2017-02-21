package com.example.amk.lacara;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AMK on 2/20/2017.
 */

public class LoginRequest extends StringRequest {

    private static final String regReqUrl = "http://suivre.hostoi.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String useremail, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, regReqUrl, listener, null);
        params = new HashMap<>();
        params.put("useremail", useremail);
        params.put("password", password);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
