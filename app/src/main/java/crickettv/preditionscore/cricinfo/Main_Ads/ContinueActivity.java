package crickettv.preditionscore.cricinfo.Main_Ads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.facebook.ads.NativeAdLayout;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.MainActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.Blue_mycustomtab.Blue_LoadCustom;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;

public class ContinueActivity extends MainAdsActivity {

    TextView Continuebtn;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    LinearLayout qureka1, qureka2, qureka3;


    public void onResume() {
        VideoPlayer_functions.callAutoQureka(this, convertedObject);
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        VideoPlayer_functions.destroyThread();
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);

        Continuebtn = findViewById(R.id.continuebtn);

        context = ContinueActivity.this;
        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        qureka1 = findViewById(R.id.qureka1);
        qureka2 = findViewById(R.id.qureka2);
        qureka3 = findViewById(R.id.qureka3);

        VideoPlayer_functions.iplscr_flag = true;

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {

            if (convertedObject.getCheckQurekaBtn().equalsIgnoreCase("on")) {
                qureka1.setVisibility(View.VISIBLE);
                qureka2.setVisibility(View.VISIBLE);
                qureka3.setVisibility(View.VISIBLE);

            } else {
                qureka1.setVisibility(View.GONE);
                qureka2.setVisibility(View.GONE);
                qureka3.setVisibility(View.GONE);
            }

            // TODO: 11-09-2020  Native Ads
            checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            checkNativeAdsMode(admobmediumnative, native_ad_container, q_native);
            admob_nativebanner_load();
            admob_native_load();
        }

        findViewById(R.id.qurekaclick1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(ContinueActivity.this, convertedObject.getQurekaUrl());
            }
        });

        Continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (convertedObject != null) {
                    AppManage.getInstance(ContinueActivity.this).adintercheck(convertedObject, context, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                        @Override
                        public void onintentscreen() {
                            startActivity(new Intent(ContinueActivity.this, MainActivity.class));
                        }
                    });

                } else {
                    startActivity(new Intent(ContinueActivity.this, MainActivity.class));
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (convertedObject != null) {
            AppManage.getInstance(ContinueActivity.this).adBackpressintercheck(convertedObject, context, new AppManage.onAdIntent() {
                @Override
                public void onintentscreen() {
                    startActivity(new Intent(ContinueActivity.this, SkipActivity.class));
                }
            });
        } else {
            startActivity(new Intent(ContinueActivity.this, SkipActivity.class));
        }
    }
}
