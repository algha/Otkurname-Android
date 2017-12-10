package com.algha.otkurname;


import cn.jpush.android.api.JPushInterface;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class App extends Application {
	
	public static Context CONTEXT;
	public static Resources RESOURCES;
	public static Typeface TYPE_FACE;
	public static DisplayMetrics DISPLAY_METRICS;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	    JPushInterface.setDebugMode(true); 
	    JPushInterface.init(this);     		
		
		CONTEXT = this;
		RESOURCES = CONTEXT.getResources();
		TYPE_FACE = Typeface.createFromAsset(CONTEXT.getAssets(), "UKIJEkran.ttf");
		DISPLAY_METRICS = RESOURCES.getDisplayMetrics();
		
	}
	
	public static int getPixelFromSp(int size) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, App.DISPLAY_METRICS);
	}

	public static int getPixelFromDp(int size) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, App.DISPLAY_METRICS);
	}
	
	public static class Preferences {
		static SharedPreferences preferences;
		static SharedPreferences.Editor editor;

		static {
			preferences = CONTEXT.getSharedPreferences("Otkurname_Preferences", Context.MODE_PRIVATE);
			editor = preferences.edit();
		}

		public static int getInt(String flag){
			return preferences.getInt(flag, 0);
		}

		public static void setInt(String flag,int val) {
			editor.putInt(flag, val);
			editor.commit();
		}
		
		public static String getString(String flag) {
			return preferences.getString(flag, null);
		}
		
		public static void setString(String flag,String val) {
			editor.putString(flag, val);
			editor.commit();
		}
		
		public static boolean getBoolean(String flag) {
			return preferences.getBoolean(flag, false);
		}
		
		public static void setBoolean(String flag,boolean val) {
			editor.putBoolean(flag, val);
			editor.commit();
		}
	}
}
