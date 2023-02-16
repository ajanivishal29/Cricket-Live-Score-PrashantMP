package crickettv.preditionscore.cricinfo.Main_Ads.admob_ads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.R;

public class AppManage {
    static Context activity;
    private static AppManage mInstance;
    static MyCallback myCallback;

    static onAdIntent adIntent;

    private Dialog adprogress;


    private int adcount;
    private int adcountbackpress;

    public interface MyCallback {
        void OnCall();
    }

    public AppManage(Context context) {
        activity = context;
    }

    public static AppManage getInstance(Context context) {
        activity = context;
        if (mInstance == null) {
            mInstance = new AppManage(context);
        }
        return mInstance;
    }

    public void addialogshow(Context activity) {
        //todo Ad Loading Dialog
        adprogress = new Dialog(activity, R.style.Custom);
        adprogress.requestWindowFeature(1);
        adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        adprogress.setCancelable(false);
        adprogress.setContentView(R.layout.ads_loading_dialog_inter);
        adprogress.show();
    }

    public void addialoghide(Context activity) {
        adprogress.dismiss();
    }

    InterstitialAd mInterstitialAd;

    public void show_Interstitial(Activity activity2, String admobinterid, MyCallback myCallback2) {

        myCallback = myCallback2;

        InterstitialAd.load(activity2, admobinterid, new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity2);
                        }

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                if (AppManage.myCallback != null) {
                                    AppManage.myCallback.OnCall();
                                    AppManage.myCallback = null;
                                }
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                                if (AppManage.myCallback != null) {
                                    AppManage.myCallback.OnCall();
                                    AppManage.myCallback = null;
                                }
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
                        if (AppManage.myCallback != null) {
                            AppManage.myCallback.OnCall();
                            AppManage.myCallback = null;
                        }
                    }
                });

    }


    com.facebook.ads.InterstitialAd interstitialAd;

    public void show_FB_Interstitial(Activity activity2, String fbinter2, MyCallback myCallback2) {

        myCallback = myCallback2;

        interstitialAd = new com.facebook.ads.InterstitialAd(activity2, fbinter2);
        // Set a listener to get notified on changes or when the user interact with the ad.
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                //======code here===========
                if (AppManage.myCallback != null) {
                    AppManage.myCallback.OnCall();
                    AppManage.myCallback = null;
                }
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {

                Log.e("check5214", "Interstitial ad failed to load: " + adError.getErrorMessage());

                if (AppManage.myCallback != null) {
                    AppManage.myCallback.OnCall();
                    AppManage.myCallback = null;
                }
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

    public AppOpenAd appOpenAd = null;
    FullScreenContentCallback fullScreenContentCallback;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return this.appOpenAd != null;
    }

    public void show_Appopen_Interstitial(Activity activity2, String appopeninterid, MyCallback myCallback2) {
        myCallback = myCallback2;

        if (!isAdAvailable()) {
            fullScreenContentCallback = new FullScreenContentCallback() {
                public void onAdDismissedFullScreenContent() {
                    if (AppManage.myCallback != null) {
                        AppManage.myCallback.OnCall();
                        AppManage.myCallback = null;
                    }
                }

                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    Log.e("LOG_TAG", adError.getMessage());
                    if (AppManage.myCallback != null) {
                        AppManage.myCallback.OnCall();
                        AppManage.myCallback = null;
                    }
                }

                public void onAdShowedFullScreenContent() {
                    Log.e("TAG", "onAdDismissedFullScreenContent:====> show ");
                }
            };
            getAdsLoad(activity2, appopeninterid, myCallback2);
        }
    }

    private void getAdsLoad(Activity activity2, String appopeninterid, MyCallback myCallback2) {
        myCallback = myCallback2;

        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            public void onAdLoaded(AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                appOpenAd.show(activity2);
                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                AppOpenAd unused = appOpenAd = appOpenAd;

            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (AppManage.myCallback != null) {
                    AppManage.myCallback.OnCall();
                    AppManage.myCallback = null;
                }
                Log.e("TAG", "onAdFailedToLoad: ===>" + loadAdError.getMessage());
            }
        };
        AppOpenAd.load(activity2, appopeninterid, getAdRequest(), 1, this.loadCallback);
        Log.e("check541", appopeninterid);
    }

    // TODO: 07-02-2022 Jay Modi Ad Mode check

    public interface onAdIntent {
        public void onintentscreen();
    }

    public void adintercheck(DataItem convertedObject, Activity activity, String fbinter2, onAdIntent adIntent) {
        AppManage.adIntent = adIntent;

        if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.admob)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {

                addialogshow(activity);
                show_Interstitial(activity, convertedObject.getAdmobInterid(), new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).storeClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
                adIntentCallback();

            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.fb)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                addialogshow(activity);
                show_FB_Interstitial(activity, fbinter2, new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).storeClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
                adIntentCallback();

            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.appopen)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                addialogshow(activity);
                show_Appopen_Interstitial(activity, convertedObject.getAppopenadId(), new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).storeClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
                adIntentCallback();

            }
        } else {
            adIntentCallback();

        }
    }

    public void adBackpressintercheck(DataItem convertedObject, Activity activity, onAdIntent adIntent) {
        AppManage.adIntent = adIntent;

        if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.admob)) {
            adcountbackpress = SharedPreferencesManager.getInstance(activity).getbackpressNumberOfClicks();
            if (adcountbackpress == convertedObject.getBackpressadcount()) {

                addialogshow(activity);
                show_Interstitial(activity, convertedObject.getAdmobInterid(), new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcountbackpress = adcountbackpress + 1;
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(adcountbackpress);
                adIntentCallback();

            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.fb)) {
            adcountbackpress = SharedPreferencesManager.getInstance(activity).getbackpressNumberOfClicks();
            if (adcountbackpress == convertedObject.getBackpressadcount()) {
                addialogshow(activity);
                show_FB_Interstitial(activity, convertedObject.getFbinter2(), new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcountbackpress = adcountbackpress + 1;
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(adcountbackpress);
                adIntentCallback();

            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.appopen)) {
            adcountbackpress = SharedPreferencesManager.getInstance(activity).getbackpressNumberOfClicks();
            if (adcountbackpress == convertedObject.getBackpressadcount()) {
                addialogshow(activity);
                show_Appopen_Interstitial(activity, convertedObject.getAppopenadId(), new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcountbackpress = adcountbackpress + 1;
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(adcountbackpress);
                adIntentCallback();

            }
        } else {
            adIntentCallback();

        }
    }


    public void adIntentCallback() {
        if (AppManage.adIntent != null) {
            AppManage.adIntent.onintentscreen();
            AppManage.adIntent = null;
        }
    }
}
