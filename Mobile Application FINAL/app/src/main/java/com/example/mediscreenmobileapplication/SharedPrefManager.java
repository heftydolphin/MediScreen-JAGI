package com.example.mediscreenmobileapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private User user;

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";

    private static final String KEY_EMAIL = "keyEmail";
    private static final String KEY_PASSWORD = "keyPassword";
    private static final String KEY_FNAME = "keyFName";
    private static final String KEY_LNAME = "keyLName";
    private static final String KEY_MEMBER_NUM = "keyMemberNum";
    private static final String KEY_POLICY_NUM = "keyPolicyNum";

    private static SharedPrefManager mInstance;
    private static Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        this.user = user;

        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_FNAME, user.getfName());
        editor.putString(KEY_LNAME, user.getlName());
        editor.putString(KEY_MEMBER_NUM, user.getMemberNumber());
        editor.putString(KEY_POLICY_NUM, user.getPolicyNumber());

        System.out.println(user.toString());

        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_FNAME, null),
                sharedPreferences.getString(KEY_LNAME, null),
                sharedPreferences.getString(KEY_MEMBER_NUM, null),
                sharedPreferences.getString(KEY_POLICY_NUM, null)
        );
    }

    //this method will logout the user
    public void logout() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        //context.startActivity(new Intent(context, MainActivity.class));
    }
}