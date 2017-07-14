package com.example.maduranga.vamps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://vamps.vedicsoft.com/portal/mobile/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vamps x = new Vamps(this);
        //x.getAuthToken("login", "sandun");

        String json = "{\"action\":\"login\",\"username\":\"sandun\"}";
        String Jcontent = "{'action':'init_user_activities','email':'piyaluom12@gmail.com','name':'maduranga'}";
        PostExample example = new PostExample();

        example.postCall( BASE_URL, json, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    System.out.print(responseStr);
                    // Do what you want to do with the response.
                } else {
                    System.out.print("error");
                    // Request not successful
                }
            }
        });

        example.getCall("https://www.vamps.vedicsoft.net/frontdesk/api/guest/activities?email=tharaka@gmail.com" , new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    System.out.print(responseStr);
                    // Do what you want to do with the response.
                } else {
                    // Request not successful
                }
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("tesing");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
