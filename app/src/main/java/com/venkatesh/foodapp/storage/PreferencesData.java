package com.venkatesh.foodapp.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class PreferencesData {

	public static final String SHARED_PREF="credentials";
	public static final String PREF_TOKEN="auth_token";
	public static final String PREF_EMAIL="email";
	public static final String PREF_PASSWORD="password";
	public static final String PREF_ROLE="role";
	public static final String PREF_MTYPE="role2";
	public static final String PREF_NAME="name";
	public static final String PREF_IMAGE="image";
	public static final String PREF_IMAGE_SMALL="image_small";
	public static final String PREF_IMAGE_MEDIUM="image_med";
	public static final String PREF_LOGIN="login";
	public static final String PREF_NOTIFICATIONS="numnotifications";
	public static final String PREF_ACCESS_TOKEN="access_token";
	public static final String PREF_TIME_ZONEDATA="time_zone";
	public static final String PREF_PROFILE="profile";
	public static final String PREF_PROFILE3="profile3";
	public static final String PREF_PROFILE2="profile2";
	public static final String PREF_PROFILE1="profile1";
	public static final String PREF_PROFILE_COMPANY="COMPANY_CREATED";
	public static final String PREF_PROFILE_SUBSCRIPTION="profile_subscription";
	public static final String PREF_PROFILE_SUBSCRIPTION_TYPE="profile_subscription_TYPE";
	public static final String PREF_COUNTRY="countries";
	public static final String PREF_SHOW_DESC="desc";
	public static final String PREF_USER_ID="user_id";
	public static final String PREF_CHAT_MSG="new_msg";
	public static final String PREF_REFRESH_TOKEN="refresh_token";
	public static final String PREF_FACEBOOK_LOGIN="facebook_login";
	public static final String PREF_TERMS_SERVICE="terms_of_service";
	public static final String PREF_ON_OFFLINE="on_off_line";
	public static  String PREF_AUTO_SOURCE_ENABLED="auto_source";
	public static final String PREF_PROFILE_SINGLEPAGE="singlepage";


	//public static final String PREF_PROFILE="profile";
	
	
	 static final String TAG = "Splash Screen";
	    public static final String PROPERTY_REG_ID = "registration_id";
	    private static final String PROPERTY_APP_VERSION = "appVersion";
	public static final String PROVIDER ="provider" ;

	private static SharedPreferences preferences;
	private static Editor editor;


	public static synchronized void initPrefs(Context context) {
		if (preferences == null) {
			preferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
		}
	}
	public static SharedPreferences getSharedPreferences()
	{
		if(preferences!=null)
				return preferences;
		else
		throw new RuntimeException(
				"Prefs class not correctly instantiated please call Prefs.iniPrefs(context) in the Application class onCreate.");
	}
	public static String getToken(Context con)
	{
		if(preferences==null)
		preferences = con.getSharedPreferences(SHARED_PREF, 0);
		return preferences.getString(PREF_TOKEN, "");
	}
	public static void putToken(Context con,String token)
	{
			if(preferences==null)
				preferences=con.getSharedPreferences(SHARED_PREF, 0);
			editor=preferences.edit();
			editor.putString(PREF_TOKEN, token); 
			editor.apply();
	}
	
	public static String getEmail(Context con)
	{
		if(preferences==null)
		preferences=con.getSharedPreferences(SHARED_PREF, 0);
		return preferences.getString(PREF_EMAIL, null);
	}
	

	public static String getRole(Context con)
	{
		if(preferences==null)
		preferences=con.getSharedPreferences(SHARED_PREF, 0);
		return preferences.getString(PREF_ROLE, "");
	}
	public static String getMemberType(Context con)
	{
		if(preferences==null)
		preferences=con.getSharedPreferences(SHARED_PREF, 0);
		return preferences.getString(PREF_MTYPE, null);
	}
	public static String getPwd(Context con)
	{
		if(preferences==null)
		preferences=con.getSharedPreferences(SHARED_PREF, 0);
		return preferences.getString(PREF_PASSWORD, "no");
	}
	public static String getNumNotif(Context con)
	{
		if(preferences==null)
		preferences=con.getSharedPreferences(SHARED_PREF, 0);
		return preferences.getString(PREF_NOTIFICATIONS, "0");
	}
	public static  boolean getAutoSourceStatus(Context con){
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF,0);
		return preferences.getBoolean(PREF_AUTO_SOURCE_ENABLED,false);
	}
	public static void putAutoSourceStatus(Context con,boolean value){
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putBoolean(PREF_AUTO_SOURCE_ENABLED, value);
		editor.apply();
	}
	public static void putEmail(Context con,String email)
	{
		
			if(preferences==null)
				preferences=con.getSharedPreferences(SHARED_PREF, 0);
			editor=preferences.edit();
			editor.putString(PREF_EMAIL, email); 
			editor.apply();
	}

	public static boolean isLogin(Context con)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
			return preferences.getBoolean(PREF_LOGIN, false);
	}
	

	public static void putRole(Context con,String r)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putString(PREF_ROLE, r);
		editor.putString(PREF_MTYPE, r);
		editor.apply();
	}
	public static void putMeberType(Context con,String r)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putString(PREF_MTYPE, r);
		editor.apply();
	}
	public static void putPwd(Context con,String r)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putString(PREF_PASSWORD, r);
		editor.apply();
	}
	public static void putNumNotifications(Context con,String r)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putString(PREF_NOTIFICATIONS, r);
		editor.apply();
	}
	public static void putTimezone(Context con,boolean b)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putBoolean(PREF_TIME_ZONEDATA, b);
		editor.apply();
	}
	public static boolean isTimezoneCompleted(Context con)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
			return preferences.getBoolean(PREF_TIME_ZONEDATA, false);
	}
	public static void putCountry(Context con,boolean b)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putBoolean(PREF_COUNTRY, b);
		editor.apply();
	}
	public static boolean isCountryCompleted(Context con)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
			return preferences.getBoolean(PREF_COUNTRY, false);
	}
	public static void putProifleFilled(Context con,boolean b)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putBoolean(PREF_PROFILE, b);
		editor.apply();
	}
	public static void putShowDesc(Context con,boolean b)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putBoolean(PREF_SHOW_DESC, b);
		editor.apply();
	}
	public static boolean isShowDesc(Context con)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
			return preferences.getBoolean(PREF_SHOW_DESC, false);
	}
	public static boolean isProfileCompleted(Context con)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
			return preferences.getBoolean(PREF_PROFILE, false);
	}
	public static void putProifle1Filled(Context con,boolean b)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putBoolean(PREF_PROFILE1, b);
		editor.apply();
	}
	public static void putComapanyDetailsFilled(Context con,boolean b)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
		editor=preferences.edit();
		editor.putBoolean(PREF_PROFILE1, b);
		editor.apply();
	}
	public static boolean isProfile1Completed(Context con)
	{
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
			return preferences.getBoolean(PREF_PROFILE1, false);
	}
	public static String getRegistrationId(Context con) {
		if(preferences==null)
			preferences=con.getSharedPreferences(SHARED_PREF, 0);
	        String registrationId = preferences.getString(PROPERTY_REG_ID, "");
	        ////Log.i(TAG, registrationId);
	        if (registrationId.isEmpty()) {  
	            //Log.i(TAG, "Registration not found.");
	            return "";
	        }
	        // Check if app was updated; if so, it must clear the registration ID
	        // since the existing regID is not guaranteed to work with the new
	        // app version.
	        int registeredVersion = preferences.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	        int currentVersion = getAppVersion(con);
	        if (registeredVersion != currentVersion) {
	            //Log.i(TAG, "App version changed.");
	            return "";
	        }
	        return registrationId;
	    }
	 public static void storeRegistrationId(Context context, String regId) {
		 if(preferences==null)
				preferences=context.getSharedPreferences(SHARED_PREF, 0);
	        int appVersion = getAppVersion(context);
	        //Log.i(TAG, "Saving regId on app version " + appVersion);
	        Editor editor = preferences.edit();
	        editor.putString(PROPERTY_REG_ID, regId);
	        editor.putInt(PROPERTY_APP_VERSION, appVersion);
	        editor.apply();
	    }
	 private static int getAppVersion(Context context) {
	        try {
	            PackageInfo packageInfo = context.getPackageManager()
	                    .getPackageInfo(context.getPackageName(), 0);
	            return packageInfo.versionCode;
	        } catch (NameNotFoundException e) {
	            // should never happen
	            throw new RuntimeException("Could not get package name: " + e);
	        }
	    }


	public static void putInt( int val,String key) {
	        Editor editor = getSharedPreferences().edit();
	        editor.putInt(key, val);
	        editor.apply();
	}
	public static void putString( String val,String key) {
        Editor editor = getSharedPreferences().edit();
	        editor.putString(key, val);
	        editor.apply();
	}
	public static void putBool( boolean val,String key) {

		Editor editor = getSharedPreferences().edit();
		editor.putBoolean(key, val);
		editor.apply();
	}

	public static int getInt(String key)
	{

		return getSharedPreferences().getInt(key, -1);
	}
	public static String getString(String key)
	{

		return getSharedPreferences().getString(key, "");
	}
	public static boolean getBool(String key)
	{

		return getSharedPreferences().getBoolean(key, false);
	}

	public static void insertTerms(Set<String> terms)
	{
		Editor editor=getSharedPreferences().edit();
		editor.putStringSet(KEY_TERMS,terms);
		editor.apply();
	}
	public static Set<String> getTerms()
	{
		return  getSharedPreferences().getStringSet(KEY_TERMS,new HashSet<String>());
	}
	public static final String KEY_TERMS="terms";

	public static void putFlag( String val,String key) {
		Editor editor = getSharedPreferences().edit();
		editor.putString(key, val);

		editor.apply();
	}

	public static String getFlag(String key)
	{

		return getSharedPreferences().getString(key, "");
	}

}
