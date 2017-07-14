package com.example.maduranga.vamps;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maduranga on 7/12/17.
 */

public class Vamps {
    String VENDOR = "vendor";
    String VA_DEVICE_MAC = "deviceMAC";

    private static String authToken;
    private static String vampsBaseURL = "https://vamps.vedicsoft.com";

    private Context mContext;

    public Vamps(Context context) {
        mContext = context;
    }

    public static void setAuthToken(String token) {
        authToken = token;
    }

    //api for getAuth Token input: user name and password relavent tenant output: return auth key
    public void getAuthToken(final String username, final String password) {
        String service_url = "http://192.168.1.6/portal/mobile/index.php";
        RequestQueue requestQueue = NetworkQueueSingleton.getInstance(mContext).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, service_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.print(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.print(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", username);
                params.put("username", password);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    //api for check mac user exist or not input: divice mac output: true or false
    public boolean isMACUserExists(String devicemac) {
        String service_url = vampsBaseURL + "/policy/api/subscribers/bymac/" + devicemac;
        RequestQueue requestQueue = NetworkQueueSingleton.getInstance(mContext).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, service_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.print(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        requestQueue.add(stringRequest);
        return false;
    }
    //api for check user exist or not input: user name output: true or false
    public boolean isUserExists(String username)
    {
        String service_url = vampsBaseURL + "/policy/api/subscribers/accounts/name/"+ username;
        RequestQueue requestQueue = NetworkQueueSingleton.getInstance(mContext).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, service_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.print(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        requestQueue.add(stringRequest);
        return false;
    }
}
