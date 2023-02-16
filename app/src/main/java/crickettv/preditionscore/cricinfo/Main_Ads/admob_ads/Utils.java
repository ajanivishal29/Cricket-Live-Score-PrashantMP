package crickettv.preditionscore.cricinfo.Main_Ads.admob_ads;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;

public class Utils {

    public static String PREFS_NAME = "shared_prefrence";

    public static void saveStringtoPrefrence(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static DataItem getResponse(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String s = sharedpreferences.getString(Constant.AdResponse, "");
        DataItem dataItem = new Gson().fromJson(s, DataItem.class);
        return dataItem;
    }
}
