package crickettv.preditionscore.cricinfo.Main_Ads;

import static crickettv.preditionscore.cricinfo.Main_Ads.VideoPlyer_Splace_Activity.apiInterface;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.multidex.MultiDex;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.LocaladsResponce;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoPlayer_App extends Application {
    private int success;
    public static ArrayList<DataItem> arrAdDataStart = new ArrayList<>();
    private StartAdListener startAdListener;
    public static VideoPlayer_Touch_AppOpenManager valeAppOpenManager;
    public static DataItem convertedObject;

    private SharedPreferences iplscr_sharedPRefrences;
    public static Context context;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences preferences;

    private static VideoPlayer_App instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();

        convertedObject = Utils.getResponse(VideoPlayer_App.this);

        MobileAds.initialize((Context) this, (OnInitializationCompleteListener) new OnInitializationCompleteListener() {
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        valeAppOpenManager = new VideoPlayer_Touch_AppOpenManager(this);

        if (AudienceNetworkAds.isInitialized(this)) {
            return;
        }
        AudienceNetworkAds.initialize(this);
    }

    private void initApplication() {
        instance = this;
    }

    public static VideoPlayer_App getInstance() {
        return instance;
    }

    public SharedPreferences getPrefrences() {
        return this.iplscr_sharedPRefrences;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void FatchStartApps() {
        arrAdDataStart.clear();
        Call<LocaladsResponce> call1 = apiInterface.localads("crickettv.preditionscore.cricinfo");

        call1.enqueue(new Callback<LocaladsResponce>() {
            @Override
            public void onResponse(Call<LocaladsResponce> call, Response<LocaladsResponce> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getData() != null && response.body().getData().size() > 0) {
                        arrAdDataStart.addAll(response.body().getData());
                        if (startAdListener != null)
                            startAdListener.onStartAdLoaded();
                    }
                }

            }

            @Override
            public void onFailure(Call<LocaladsResponce> call, Throwable t) {
                call.cancel();
                if (startAdListener != null)
                    startAdListener.onStartAdError();
            }
        });
    }

    public interface StartAdListener {

        public void onStartAdError();

        public void onStartAdLoaded();
    }

    public void setStartAdListener(StartAdListener listener) {
        this.startAdListener = listener;
        FatchStartApps();

    }

}
