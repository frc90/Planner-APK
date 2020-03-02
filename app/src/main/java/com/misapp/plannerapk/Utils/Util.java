package com.misapp.plannerapk.Utils;

import android.content.SharedPreferences;

public class Util {

    public static String getUserMailPrefs(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    public static String getUserMailPassword(SharedPreferences preferences) {
        return preferences.getString("password", "");
    }
}
