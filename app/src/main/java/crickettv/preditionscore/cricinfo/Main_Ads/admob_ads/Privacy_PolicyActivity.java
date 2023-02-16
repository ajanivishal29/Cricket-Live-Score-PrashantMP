package crickettv.preditionscore.cricinfo.Main_Ads.admob_ads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Main_Ads.SkipActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.VideoPlayer_functions;
import crickettv.preditionscore.cricinfo.R;

public class Privacy_PolicyActivity extends MainAdsActivity {
    ImageView iv_close;
    TextView tvAccept;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public AppOpenAd appOpenAd = null;
    FullScreenContentCallback fullScreenContentCallback;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    private Dialog adprogress;

    public void onResume() {
        VideoPlayer_functions.callAutoQureka(this, convertedObject);
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        VideoPlayer_functions.destroyThread();
        super.onPause();
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
                    adprogress.dismiss();
                }

                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("LOG_TAG", adError.getMessage());
                    adprogress.dismiss();

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
                appOpenAd.show(Privacy_PolicyActivity.this);
                appOpenAd.setFullScreenContentCallback(Privacy_PolicyActivity.this.fullScreenContentCallback);
                AppOpenAd unused = Privacy_PolicyActivity.this.appOpenAd = appOpenAd;

            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

                adprogress.dismiss();
                Log.e("TAG", "onAdFailedToLoad: ===>" + loadAdError.getMessage());
            }
        };
        AppOpenAd.load((Context) this, convertedObject.getAppopenadId(), getAdRequest(), 1, this.loadCallback);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_privacypolicy);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.tvAccept = (TextView) findViewById(R.id.tvText);

        context = Privacy_PolicyActivity.this;
        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        VideoPlayer_functions.iplscr_flag = true;

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {

            /*if (convertedObject.getAppopenInter_On_Off().equals("on")) {
                //todo Ad Loading Dialog
                adprogress = new Dialog(Privacy_PolicyActivity.this, R.style.Custom);
                adprogress.requestWindowFeature(1);
                adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                adprogress.setCancelable(false);
                adprogress.setContentView(R.layout.ads_loading_dialog_inter);
                adprogress.show();
                fetchAd();
            }*/

            // TODO: 11-09-2020  Native Ads
            checkNativeBannerAdsModestatic(admobsmallnative, native_banner_ad_container, q_native_banner);
            admob_nativebanner_load();
            admob_native_load();

        }

        this.iv_close.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {

                if (convertedObject != null) {
                    AppManage.getInstance(Privacy_PolicyActivity.this).adBackpressintercheck(convertedObject, context, new AppManage.onAdIntent() {
                        @Override
                        public void onintentscreen() {
                            onBackPressed();
                        }
                    });
                } else {
                    onBackPressed();
                }
            }
        });
        this.tvAccept.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                if (convertedObject != null) {
                    AppManage.getInstance(Privacy_PolicyActivity.this).adintercheck(convertedObject, context, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                        @Override
                        public void onintentscreen() {
                            startActivity(new Intent(Privacy_PolicyActivity.this, SkipActivity.class));
                        }
                    });

                } else {
                    startActivity(new Intent(Privacy_PolicyActivity.this, SkipActivity.class));
                }
            }
        });

    }

    public void onBackPressed() {
        finishAffinity();
    }
}
