package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "soulmateuser";

    public static final String KEY_ID = "ID";
    public static final String Premium_code = "code";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_PREMIUM = "false";
    private static final String IS_DARK = "nottrue";
    private static final String IS_LIKE = "no";
    private static final String IS_Password = "none";
    private static final String Change_pass = "none1";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL="email";
    public static final String USER_CITY="city";
    public static final String USER_GENDER="GEN";
    public static final String USER_maxheight="maxheight";
    public static final String USER_minheight="minheight";
    public static final String USER_minage="minage";
    public static final String USER_maxage="maxage";
    public static final String USER_maritialstatus="maritialstatus";
    public static final String USER_religion="religion";
    public static final String USER_mothertongue="mothertongue";
    public static final String USER_cast="cast";
    public static final String USER_education="education";
    public static final String USER_occupation="occupation";
    public static final String USER_minincome="minincome";
    public static final String USER_maxincome="maxincome";
    public static final String Image="Image";
    public static final String Profileid="Profileid";
    public static final String useremail="useremail";
    public static final String usercity="usercity";
    public static final String Gender="gender";
    public static final String Name="Name";
    public static final String UserPassword="Password";

    // Constructor
    public SessionManagement(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email,String id) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        // commit changes
        editor.commit();
    }

    public  void removePremium(){
        editor.putBoolean(IS_PREMIUM, false);
        editor.commit();
        Intent i = new Intent(_context, BottomNavigationActivity.class);
        _context.startActivity(i);
    }

    public void createpremiumcode(String code) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putBoolean(IS_PREMIUM, true);
        editor.putString(Premium_code, code);
        editor.commit();
    }
    public void createpassword(String code) {
        // Storing login value as TRUE
        editor.putBoolean(IS_Password, true);
        editor.putString(UserPassword, code);
        editor.commit();
    }

    public void changepassword(String code) {
        // Storing login value as TRUE
        editor.putBoolean(IS_Password, true);
        editor.remove(UserPassword);
        editor.putString(UserPassword, code);
        editor.commit();
    }

    public void SETDARKMODE() {
        // Storing login value as TRUE
        editor.putBoolean(IS_DARK, true);
        editor.commit();
    }
    public void changepassnewtime() {
        // Storing login value as TRUE
        editor.putBoolean(Change_pass, true);
        editor.commit();
    }
    public void changepassnewtimeremove() {
        // Storing login value as TRUE
        editor.putBoolean(Change_pass, false);
        editor.commit();
    }


    public void REMOVEDARKMODE() {
        // Storing login value as TRUE
        editor.putBoolean(IS_DARK, false);
        editor.commit();
    }
    public void RemovePassword() {
        // Storing login value as TRUE
        editor.putBoolean(IS_Password, false);
        editor.commit();
    }

    public void SETLIKEMODE() {
        // Storing login value as TRUE
        editor.putBoolean(IS_LIKE, true);
        editor.commit();
    }
    public void REMOVELIKEMODE() {
        // Storing login value as TRUE
        editor.putBoolean(IS_LIKE, false);
        editor.commit();
    }


    public void fulluserdata(String profileid,String emaild,String image,String city,String gender,String name) {
        // Storing login value as TRUE

        editor.putString(Profileid, profileid);
        editor.putString(useremail, emaild);
        editor.putString(Image, image);
        editor.putString(usercity, city);
        editor.putString(Gender,gender);
        editor.putString(Name,name);
        editor.commit();
    }
    public void userdata(String city) {
        // Storing login value as TRUE
        editor.putString(USER_CITY, city);
        // commit changes
        editor.commit();
    }
    public void userdataSelected(String name) {
        // Storing login value as TRUE
        editor.putString(USER_GENDER, name);
        // commit changes
        editor.commit();

    }
    public void matchalgorithm(String minheight,String maxheight,String maxage,String minage,String maritialstatus,String cast,String religion,String mothertongue,String education,String Occupation,String minincome,String maxincome) {
        // Storing login value as TRUE
        editor.putString(USER_minheight, minheight);
        editor.putString(USER_maxheight, maxheight);
        editor.putString(USER_maxage, maxage);
        editor.putString(USER_minage, minage);
        editor.putString(USER_maritialstatus, maritialstatus);
        editor.putString(USER_cast, cast);
        editor.putString(USER_mothertongue, mothertongue);
        editor.putString(USER_education, education);
        editor.putString(USER_occupation, Occupation);
        editor.putString(USER_minincome, minincome);
        editor.putString(USER_maxincome, maxincome);
        editor.putString(USER_religion, religion);
        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(USER_CITY,pref.getString(USER_CITY,null));
        // user email id
        user.put(USER_GENDER, pref.getString(USER_GENDER, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(USER_maxincome, pref.getString(USER_maxincome, "0"));
        user.put(USER_minincome, pref.getString(USER_minincome, "0"));
        user.put(USER_occupation, pref.getString(USER_occupation, "NULL"));
        user.put(USER_education, pref.getString(USER_education, "NULL"));
        user.put(USER_mothertongue, pref.getString(USER_mothertongue, "NULL"));
        user.put(USER_cast, pref.getString(USER_cast, "NULL"));
        user.put(USER_maritialstatus, pref.getString(USER_maritialstatus, "NULL"));
        user.put(USER_minage, pref.getString(USER_minage, "0"));
        user.put(USER_maxage, pref.getString(USER_maxage, "65"));
        user.put(USER_minheight, pref.getString(USER_minheight, "NULL"));
        user.put(USER_maxheight, pref.getString(USER_maxheight, "NULL"));
        user.put(USER_religion, pref.getString(USER_religion, "NULL"));
        user.put(Premium_code, pref.getString(Premium_code, "NULL"));
        user.put(Profileid, pref.getString(Profileid, "NULL"));
        user.put(Image, pref.getString(Image, "NULL"));
        user.put(useremail, pref.getString(useremail, "NULL"));
        user.put(usercity, pref.getString(usercity, "NULL"));
        user.put(Gender, pref.getString(Gender, "NULL"));
        user.put(Name, pref.getString(Name, "NULL"));
        user.put(UserPassword, pref.getString(UserPassword, "NULL"));
         // return user
        return user;
    }

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(i);
        }
    }


    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, login.class);
        // Closing all the Activities
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean haspassword(){ return pref.getBoolean(IS_Password, false); }

    public boolean isdark() { return pref.getBoolean(IS_DARK,false);}

    public boolean isPremium()
    {
        return pref.getBoolean(IS_PREMIUM,false);
    }

    public boolean isLike()
    {
        return pref.getBoolean(IS_LIKE,false);
    }

    public boolean isnewpass() { return pref.getBoolean(Change_pass,false); }

}
