package crickettv.preditionscore.cricinfo.Main_Ads;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Main_Ads.Blue_mycustomtab.Blue_LoadCustom;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;


public class VideoPlayer_AutoQurekaActivity extends MainAdsActivity {

    ImageView iv_main;
    RelativeLayout top;

    public DataItem convertedObject;


    public InterstitialAd mInterstitialAd;

    public AppOpenAd appOpenAd = null;
    FullScreenContentCallback fullScreenContentCallback;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_qureka);

        ImageView iv_main = null;
        iv_main = findViewById(R.id.iv_main);
        top = findViewById(R.id.top);

        convertedObject = Utils.getResponse(VideoPlayer_AutoQurekaActivity.this);

        if (convertedObject != null) {
            if (convertedObject.getCheckInterval().equalsIgnoreCase("qureka")) {
                top.setVisibility(View.VISIBLE);
            } else if (convertedObject.getCheckInterval().equalsIgnoreCase("admob")) {
                admob_inter();
            } else if (convertedObject.getCheckInterval().equalsIgnoreCase("appopen")) {
                fetchAd();
            }
        }

        if (convertedObject != null) {
            try {
                Glide.with(getApplicationContext())
                        .load(convertedObject.getQurekaImageUrl())
                        .into(iv_main);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ((ImageView) findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VideoPlayer_functions.iplscr_flag = true;
                VideoPlayer_AutoQurekaActivity.this.finish();
            }
        });

        ((ImageView) findViewById(R.id.iv_main)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    Blue_LoadCustom.myCustom(VideoPlayer_AutoQurekaActivity.this, convertedObject.getQurekaUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void onBackPressed() {
        super.onBackPressed();
        VideoPlayer_functions.iplscr_flag = true;
        finish();
    }

    public void admob_inter() {
        InterstitialAd.load(this, convertedObject.getAdmobInterid(), new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(VideoPlayer_AutoQurekaActivity.this);
                        }

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                VideoPlayer_functions.iplscr_flag = true;
                                finish();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                                VideoPlayer_functions.iplscr_flag = true;
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
                        VideoPlayer_functions.iplscr_flag = true;
                        finish();
                    }
                });
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
                    VideoPlayer_functions.iplscr_flag = true;
                    VideoPlayer_AutoQurekaActivity.this.finish();
                }

                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("LOG_TAG", adError.getMessage());
                    VideoPlayer_functions.iplscr_flag = true;
                    VideoPlayer_AutoQurekaActivity.this.finish();
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
                appOpenAd.show(VideoPlayer_AutoQurekaActivity.this);
                appOpenAd.setFullScreenContentCallback(VideoPlayer_AutoQurekaActivity.this.fullScreenContentCallback);
                AppOpenAd unused = VideoPlayer_AutoQurekaActivity.this.appOpenAd = appOpenAd;

            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                VideoPlayer_functions.iplscr_flag = true;
                VideoPlayer_AutoQurekaActivity.this.finish();
                Log.e("TAG", "onAdFailedToLoad: ===>" + loadAdError.getMessage());
            }
        };
        AppOpenAd.load((Context) this, convertedObject.getAppopenadId(), getAdRequest(), 1, this.loadCallback);
    }


}
