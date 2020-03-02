package com.misapp.plannerapk.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.misapp.plannerapk.Activities.LoginActivity;
import com.misapp.plannerapk.Activities.MainActivity;
import com.misapp.plannerapk.R;
import com.misapp.plannerapk.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentLogin = new Intent(SplashActivity.this, LoginActivity.class);
                Intent intentMain = new Intent(SplashActivity.this, MainActivity.class);


                // verificar que no este vacio el sharedpreferences
                if (!TextUtils.isEmpty(Util.getUserMailPrefs(preferences)) && !TextUtils.isEmpty(Util.getUserMailPassword(preferences))) {
                    startActivity(intentMain);
                } else {
                    startActivity(intentLogin);
                }
                finish();
            }
        }, 2000);
    }
}
