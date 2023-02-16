package crickettv.preditionscore.cricinfo.Main_Ads;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.FacebookSdk;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.gson.Gson;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AdListResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.retrofit.APIClient;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Constant;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Privacy_PolicyActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoPlyer_Splace_Activity extends MainAdsActivity {

    private Context mContext;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private int success;
    private Timer tm;
    boolean isTimerStarted;

    public AppOpenAd appOpenAd = null;
    FullScreenContentCallback fullScreenContentCallback;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    public static final String ACTION_CLOSE = "ACTION_CLOSE";
    private FirstReceiver firstReceiver;

    private InterstitialAd mInterstitialAd;
    private com.facebook.ads.InterstitialAd interstitialAd;

    public static RetrofitInterface apiInterface;

    public static String TAG = "msg";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public DataItem convertedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        apiInterface = APIClient.getClient().create(RetrofitInterface.class);

        mContext = this;
        activity = this;
        isTimerStarted = false;

        sharedPreferences = activity.getSharedPreferences("MyPref", 0);
        SharedPreferences sharedPreferences2 = getSharedPreferences("memory", 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();


        // TODO: 07-02-2022 prefrence clear
        clearprefrence();


        if (isInternetConnection() == true) {
            try {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    if (checkPermission()) {
                        fatchHeliNative();
                        starttimer();
                    } else {
                        requestPermission();
                    }
                }
            } catch (Exception e) {
            }
        } else {
            Toast.makeText(mContext, "Please Connect Internet", Toast.LENGTH_LONG).show();
        }


        try {
            IntentFilter filter = new IntentFilter(ACTION_CLOSE);
            firstReceiver = new FirstReceiver();
            registerReceiver(firstReceiver, filter);

            String INSTALL_PREF = "install_pref_vd";
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            if (!sharedPreferences.getBoolean(INSTALL_PREF, false)) {
                updatecounter();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(INSTALL_PREF, true);
                editor.apply();
            }

        } catch (Exception e) {
        }
    }

    public void starttimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isTimerStarted = true;
                Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }
        };
        tm = new Timer();
        tm.schedule(task, 2500);
    }

    public static int initalize_appopenad = 0;

    private void fatchHeliNative() {
        Call<AdListResponse> call1 = apiInterface.getadsdetail("crickettv.preditionscore.cricinfo");

        call1.enqueue(new Callback<AdListResponse>() {
            @Override
            public void onResponse(Call<AdListResponse> call, Response<AdListResponse> response) {
//                Toast.makeText(footballscore_Splace_Activity.this, response.message(), Toast.LENGTH_LONG).show();
//                Toast.makeText(mContext, "Success", Toast.LENGTH_LONG).show();

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getData() != null && response.body().getData().size() > 0) {

                        DataItem item = response.body().getData().get(0);

                        Gson gson = new Gson();
                        String favData = gson.toJson(item);

                        Utils.saveStringtoPrefrence(VideoPlyer_Splace_Activity.this, Constant.AdResponse, favData);

                        convertedObject = Utils.getResponse(VideoPlyer_Splace_Activity.this);

                        if (convertedObject != null) {
                            if (convertedObject.getFacebookAppid() != null && !convertedObject.getFacebookAppid().isEmpty() &&
                                    convertedObject.getFacebookClientToken() != null && !convertedObject.getFacebookClientToken().isEmpty()) {
                                FacebookSdk.setApplicationId(convertedObject.getFacebookAppid());
                                FacebookSdk.setClientToken(convertedObject.getFacebookClientToken());
                            }
                        }

                        if (convertedObject.getCheckAdSplash().equals("admob")) {
                            if (tm != null) {
                                tm.cancel();
                                tm.purge();
                            }
                            if (!isTimerStarted)
                                Admob_callSplashAd();
                        } else if (convertedObject.getCheckAdSplash().equals("fb")) {
                            if (tm != null) {
                                tm.cancel();
                                tm.purge();
                            }
                            if (!isTimerStarted)
                                fb_callSplashAd();
                        } else if (convertedObject.getCheckAdSplash().equals("appopen")) {
                            if (tm != null) {
                                tm.cancel();
                                tm.purge();
                            }
                            if (!isTimerStarted)
                                fetchAd();
                        } else if (convertedObject.getCheckAdSplash().equals("off")) {
                            Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AdListResponse> call, Throwable t) {
                call.cancel();

//                Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
                Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }
        });
    }

    public void updatecounter() {
        Call<Object> call1 = apiInterface.updatecounter("crickettv.preditionscore.cricinfo");

        call1.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
//                Toast.makeText(VideoPlyer_Splace_Activity.this, response.message(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful() && response.body() != null) {

                    String data = new Gson().toJson(response.body());

                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        success = jsonObject.getInt("success");
                        Toast.makeText(getApplicationContext(), "updatecounter = " + success, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                call.cancel();
            }
        });
    }

    class FirstReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("FirstReceiver", "FirstReceiver");
            if (intent.getAction().equals(ACTION_CLOSE)) {
                VideoPlyer_Splace_Activity.this.finish();
            }
        }
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return this.appOpenAd != null;
    }

    public void fetchAd() {
        if (!isAdAvailable()) {
            fullScreenContentCallback = new FullScreenContentCallback() {
                public void onAdDismissedFullScreenContent() {
                    Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }

                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("LOG_TAG", adError.getMessage());

//                    Toast.makeText(VideoPlyer_Splace_Activity.this, adError.getMessage(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }

                public void onAdShowedFullScreenContent() {
                    Log.e("TAG", "onAdDismissedFullScreenContent:====> show ");
                }
            };
            getAdsLoad();
        }
    }

    private void getAdsLoad() {
        this.loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            public void onAdLoaded(final AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                appOpenAd.show(VideoPlyer_Splace_Activity.this);
                appOpenAd.setFullScreenContentCallback(VideoPlyer_Splace_Activity.this.fullScreenContentCallback);
                AppOpenAd unused = VideoPlyer_Splace_Activity.this.appOpenAd = appOpenAd;

            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("LOG_TAG", loadAdError.getMessage());
//                Toast.makeText(VideoPlyer_Splace_Activity.this, loadAdError.getMessage(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
                Log.e("TAG", "onAdFailedToLoad: ===>" + loadAdError.getMessage());
            }
        };
        AppOpenAd.load((Context) this, convertedObject.getAppopenadId(), getAdRequest(), 1, this.loadCallback);
    }


    private void Admob_callSplashAd() {
        try {

            InterstitialAd.load(this, convertedObject.getAdmobSplashInterid(), new AdRequest.Builder().build(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(VideoPlyer_Splace_Activity.this);
                            }

                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when fullscreen content is dismissed.
                                    Log.d("TAG", "The ad was dismissed.");
                                    Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(i);
                                    finish();
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when fullscreen content failed to show.
                                    Log.d("TAG", "The ad failed to show.");
                                    Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(i);
                                    finish();
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when fullscreen content is shown.
                                    // Make sure to set your reference to null so you don't
                                    // show it a second time.
                                    mInterstitialAd = null;
                                    Log.d("TAG", "The ad was shown.");
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                            mInterstitialAd = null;
                            Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(i);
                            finish();
                        }
                    });
        } catch (
                Exception e) {
        }

    }

    private void fb_callSplashAd() {

        interstitialAd = new com.facebook.ads.InterstitialAd(this, convertedObject.getFbinter1());
        // Set a listener to get notified on changes or when the user interact with the ad.
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                //======code here===========
                Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {

                Log.e("check5214", "Interstitial ad failed to load: " + adError.getErrorMessage());

                Intent i = new Intent(VideoPlyer_Splace_Activity.this, Privacy_PolicyActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED || result1 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    if (isInternetConnection() == true) {
                        fatchHeliNative();
                        starttimer();
                    } else {
                        Toast.makeText(mContext, "Please Connect Internet", Toast.LENGTH_LONG).show();
                    }

                } else {
                    finish();
                }
                break;
        }
    }

    public boolean isInternetConnection() {


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else
            return false;
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 5000;

    @Override
    protected void onResume() {
        super.onResume();
        isTimerStarted = false;
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                if ((isInternetConnection() == true)) {
                    try {
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                            if (checkPermission()) {
                                fatchHeliNative();
                                starttimer();
                            } else {
                                requestPermission();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, delay);
    }
}