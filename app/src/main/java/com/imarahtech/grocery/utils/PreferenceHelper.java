package com.imarahtech.grocery.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {


    Context context;

    public PreferenceHelper(Context context) {
        this.context = context;
    }

    public void putString(String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constants.PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).apply();
    }


    public String getString(String key) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constants.PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public void putInt(String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constants.PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putInt(key, value).apply();
    }


    public int getInt(String key) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constants.PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constants.PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).apply();
    }


    public boolean getBoolean(String key) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constants.PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }
}
