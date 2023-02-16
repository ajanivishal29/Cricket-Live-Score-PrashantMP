package crickettv.preditionscore.cricinfo.Main_Ads.admob_ads;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.List;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Main_Ads.Blue_mycustomtab.Blue_LoadCustom;
import crickettv.preditionscore.cricinfo.R;

public class MainAdsActivity extends AppCompatActivity {

    public DataItem convertedObject;

    public static NativeAd mNativebannerAd;
    public static NativeAd mNativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        convertedObject = Utils.getResponse(MainAdsActivity.this);

    }

    public void admob_nativebanner_load() {
        if (convertedObject == null) {
            convertedObject = Utils.getResponse(MainAdsActivity.this);
        }

        MobileAds.initialize(this);
        AdLoader adLoader = new AdLoader.Builder(this, convertedObject.getAdmobNativeid())
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        mNativebannerAd = nativeAd;
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void admob_nativebanner_show(TemplateView admobsmallnative) {

        if (mNativebannerAd != null) {
            admobsmallnative.setVisibility(View.VISIBLE);
            NativeTemplateStyle styles = new
                    NativeTemplateStyle.Builder().build();
            admobsmallnative.setStyles(styles);
            admobsmallnative.setNativeAd(mNativebannerAd);
        } else {
            admob_nativebanner_load();
        }
    }

    // TODO: 11-09-2020  Admob Native Ads
    public void admob_native_banner(TemplateView admobsmallnative) {
        try {

            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, convertedObject.getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            admobsmallnative.setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            admobsmallnative.setStyles(styles);
                            admobsmallnative.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    // TODO: 08-Oct-20  FB Banner Ads
    public NativeBannerAd nativeBannerAd;

    public void loadnative_bannerad(NativeAdLayout native_banner_ad_container) {
        nativeBannerAd = new NativeBannerAd(this, convertedObject.getFbNativeBanner1());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                // Inflate Native Banner Ad into Container
                inflateAd(nativeBannerAd, native_banner_ad_container);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };
        // load the ad
        nativeBannerAd.loadAd(nativeBannerAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    public void inflateAd(NativeBannerAd nativeBannerAd, NativeAdLayout native_banner_ad_container) {
        nativeBannerAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        adView = (LinearLayout) inflater.inflate(R.layout.fb_native_banner_ad_layout, native_banner_ad_container, false);
        native_banner_ad_container.addView(adView);

        RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(getApplicationContext(), nativeBannerAd, native_banner_ad_container);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
    }

    public void admob_native_load() {
        MobileAds.initialize(this);
        AdLoader adLoader = new AdLoader.Builder(this, convertedObject.getAdmobNativeid())
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {

                        mNativeAd = nativeAd;
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void admob_native_show(TemplateView templateView) {

        if (mNativeAd != null) {
            templateView.setVisibility(View.VISIBLE);
            NativeTemplateStyle styles = new
                    NativeTemplateStyle.Builder().build();
            templateView.setStyles(styles);
            templateView.setNativeAd(mNativeAd);
        } else {
            admob_nativebanner_load();
        }
    }

    public void admob_native(TemplateView templateView) {
        try {
            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, convertedObject.getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            templateView.setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            templateView.setStyles(styles);
                            templateView.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
        }
    }

    // TODO: 08-Oct-20  Fb Native
    public LinearLayout adView;

    public com.facebook.ads.NativeAd nativeAd1;

    // TODO: 11-09-2020  Fb Native Ads
    public void Fb_loadNativeAd(NativeAdLayout native_ad_container1) {
        nativeAd1 = new com.facebook.ads.NativeAd(this, convertedObject.getFbNative1());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeAd1 == null || nativeAd1 != ad) {
                    return;
                }
                // Inflate Native Ad into Container
                inflateAd1(nativeAd1, native_ad_container1);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        nativeAd1.loadAd(
                nativeAd1.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }

    public void inflateAd1(com.facebook.ads.NativeAd nativeAd, NativeAdLayout native_ad_container1) {
        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(MainAdsActivity.this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (LinearLayout) inflater.inflate(R.layout.fb_native_ad_unit, native_ad_container1, false);
        native_ad_container1.addView(adView);

        // Add the AdChoices icon
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container1);
        AdOptionsView adChoicesView = new AdOptionsView(MainAdsActivity.this, nativeAd, native_ad_container1);
        adChoicesContainer.addView(adChoicesView, 0);

        MediaView nativeAdIcon = findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = findViewById(R.id.native_ad_call_to_action);

        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }

    public void Q_Native_banner(CardView qurekaid) {

//        q_native_banner = findViewById(R.id.q_native_banner);
        qurekaid.setVisibility(View.VISIBLE);
        qurekaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blue_LoadCustom.myCustom(MainAdsActivity.this, convertedObject.getQurekaUrl());
            }
        });
    }

    public void Q_Native(CardView qurekanativeid) {
        qurekanativeid.setVisibility(View.VISIBLE);
        qurekanativeid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blue_LoadCustom.myCustom(MainAdsActivity.this, convertedObject.getQurekaUrl());
            }
        });

    }


    public void checkNativeAdsMode(TemplateView admobmediumnative, NativeAdLayout native_ad_container, CardView q_native) {
        // TODO: 11-09-2020  Native Ads
        if (convertedObject.getCheckAdNative().equals(Constant.admob)) {
            admob_native_show(admobmediumnative);
        } else if (convertedObject.getCheckAdNative().equals(Constant.fb)) {
            Fb_loadNativeAd(native_ad_container);
        } else if (convertedObject.getCheckAdNative().equals(Constant.qureka)) {
            Q_Native(q_native);
        }

    }

    public void checkNativeBannerAdsMode(TemplateView admobsmallnative, NativeAdLayout native_banner_ad_container, CardView q_native_banner) {
        // TODO: 11-09-2020  Native Ads
        if (convertedObject.getCheckAdNativeBanner().equals(Constant.admob)) {
            admob_nativebanner_show(admobsmallnative);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.fb)) {
            loadnative_bannerad(native_banner_ad_container);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.qureka)) {
            Q_Native_banner(q_native_banner);
        }

    }

    public void checkNativeBannerAdsModestatic(TemplateView admobsmallnative, NativeAdLayout native_banner_ad_container, CardView q_native_banner) {
        // TODO: 11-09-2020  Native Ads
        if (convertedObject.getCheckAdNativeBanner().equals(Constant.admob)) {
            admob_native_banner(admobsmallnative);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.fb)) {
            loadnative_bannerad(native_banner_ad_container);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.qureka)) {
            Q_Native_banner(q_native_banner);
        }

    }

    public void clearprefrence() {
        SharedPreferencesManager.getInstance(this).backpressstoreClicks(1);
        SharedPreferencesManager.getInstance(this).storeClicks(1);
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

}