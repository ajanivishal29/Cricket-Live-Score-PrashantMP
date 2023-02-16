package crickettv.preditionscore.cricinfo.Main_Ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import static crickettv.preditionscore.cricinfo.Main_Ads.VideoPlayer_App.convertedObject;

import java.util.Date;

public class VideoPlayer_Touch_AppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    private static final String LOG_TAG = "Touch_AppOpenManager";
    public static boolean isShowingAd = false;
    public AppOpenAd appOpenAd = null;
    private Activity currentActivity;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    public long loadTime = 0;
    private final VideoPlayer_App valeTicTic;
    public static int initalize_appopenad = 0;

    FullScreenContentCallback fullScreenContentCallback;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public VideoPlayer_Touch_AppOpenManager(VideoPlayer_App MyApplication) {
        this.valeTicTic = MyApplication;
        MyApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
//        Log.e("onstart", "onStart: " + guide.get(0).check_appopenad);
        if (convertedObject != null) {
            if (convertedObject.getCheckAppopenad().equals("on")) {
                showAdIfAvailable();
            }
        }

    }

    public void fetchAd() {

        if (!isAdAvailable()) {
            loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {

                @Override
                public void onAdLoaded(AppOpenAd appOpenAd) {
//                            AppOpenManager.this.appOpenAd = ad;
                    AppOpenAd unused = VideoPlayer_Touch_AppOpenManager.this.appOpenAd = appOpenAd;
                    long unused2 = VideoPlayer_Touch_AppOpenManager.this.loadTime = new Date().getTime();
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    // Handle the error.
                    Log.e("ch65", "onAppOpenAdFailedToLoad: " + loadAdError);
                }

            };
            if (convertedObject != null) {
                AppOpenAd.load((Context) this.valeTicTic, convertedObject.getAppopenadId(), getAdRequest(), 1, this.loadCallback);
            }
        }

    }

    private boolean wasLoadTimeLessThanNHoursAgo(long j) {
        return new Date().getTime() - this.loadTime < j * 3600000;
    }

    public void showAdIfAvailable() {
        if (isShowingAd || !isAdAvailable()) {
            Log.d(LOG_TAG, "Can not show ad.");
            fetchAd();
            return;
        }
        Log.d(LOG_TAG, "Will show ad.");

        fullScreenContentCallback = new FullScreenContentCallback() {
            public void onAdDismissedFullScreenContent() {
                AppOpenAd unused = VideoPlayer_Touch_AppOpenManager.this.appOpenAd = null;
                boolean unused2 = VideoPlayer_Touch_AppOpenManager.isShowingAd = false;
                VideoPlayer_Touch_AppOpenManager.this.fetchAd();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
            }

            public void onAdShowedFullScreenContent() {
                Log.e("TAG", "onAdDismissedFullScreenContent:====> show ");
                boolean unused = VideoPlayer_Touch_AppOpenManager.isShowingAd = true;
            }
        };
        appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
        this.appOpenAd.show(this.currentActivity);
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return this.appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    public void onActivityStarted(Activity activity) {
        this.currentActivity = activity;
    }

    public void onActivityResumed(Activity activity) {
        this.currentActivity = activity;
    }

    public void onActivityPaused(Activity activity) {
        if (convertedObject != null) {
            if (VideoPlyer_Splace_Activity.initalize_appopenad == 1 && VideoPlayer_App.convertedObject.getCheckAppopenad().equals("on")) {
                showAdIfAvailable();
                VideoPlyer_Splace_Activity.initalize_appopenad++;
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        this.currentActivity = null;
    }
}
