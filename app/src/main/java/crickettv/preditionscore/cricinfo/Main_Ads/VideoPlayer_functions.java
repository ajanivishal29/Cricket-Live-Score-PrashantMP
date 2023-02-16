package crickettv.preditionscore.cricinfo.Main_Ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;


public class VideoPlayer_functions {
    public static boolean iplscr_flag = true;
    private static Handler iplscr_mHandler;
    private static Runnable iplscr_runnable;

    @SuppressLint("WrongConstant")
    public static void autoQureka(Context context) {
        context.startActivity(new Intent(context, VideoPlayer_AutoQurekaActivity.class).addFlags(268435456));
    }

    public static void callAutoQureka(final Context context, DataItem convertedObject) {
        iplscr_mHandler = new Handler();
        if (convertedObject != null && convertedObject.getCheckQurekaInterval().equals("on")) {
            Handler handler = iplscr_mHandler;
            Runnable r2 = new Runnable() {
                public void run() {
                    if (VideoPlayer_functions.iplscr_flag) {
                        VideoPlayer_functions.iplscr_flag = false;
                        VideoPlayer_functions.autoQureka(context);
                    }
                }
            };
            iplscr_runnable = r2;
            handler.postDelayed(r2, Long.parseLong(convertedObject.getQurekaInterval()));
        }
    }

    public static void destroyThread() {
        try {
            iplscr_mHandler.removeCallbacks(iplscr_runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}