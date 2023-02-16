package crickettv.preditionscore.cricinfo.Main_Ads;

import static crickettv.preditionscore.cricinfo.Main_Ads.VideoPlayer_App.arrAdDataStart;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAdLayout;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Main_Ads.Blue_mycustomtab.Blue_LoadCustom;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;

public class SkipActivity extends MainAdsActivity {

    TextView Skipbtn;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    LinearLayout qureka2, qureka3;

    RelativeLayout Liner_Localad;
    private AdViewAdapter_Start adViewAdapter;
    RecyclerView ad_recycle_view;

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
        setContentView(R.layout.activity_skip);

        Skipbtn = findViewById(R.id.skipbtn);

        Liner_Localad = (RelativeLayout) findViewById(R.id.localad);
        Liner_Localad.setVisibility(View.GONE);

        context = SkipActivity.this;
        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        qureka2 = findViewById(R.id.qureka2);
        qureka3 = findViewById(R.id.qureka3);

        VideoPlayer_functions.iplscr_flag = true;

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {

            if (convertedObject.getCheckQurekaBtn().equalsIgnoreCase("on")) {
                qureka2.setVisibility(View.VISIBLE);
                qureka3.setVisibility(View.VISIBLE);

            } else {
                qureka2.setVisibility(View.GONE);
                qureka3.setVisibility(View.GONE);
            }

            try {

                VideoPlayer_App app = (VideoPlayer_App) getApplication();
                app.setStartAdListener(new VideoPlayer_App.StartAdListener() {
                    @Override
                    public void onStartAdError() {
                    }

                    @Override
                    public void onStartAdLoaded() {
                        showStartApps();
                    }
                });

            } catch (Exception e) {
            }

            // TODO: 11-09-2020  Native Ads
            checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            checkNativeAdsMode(admobmediumnative, native_ad_container, q_native);
            admob_nativebanner_load();
            admob_native_load();
        }

        findViewById(R.id.qurekaclick4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(SkipActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(SkipActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(SkipActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(SkipActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(SkipActivity.this, convertedObject.getQurekaUrl());
            }
        });

        findViewById(R.id.qurekaclick9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blue_LoadCustom.myCustom(SkipActivity.this, convertedObject.getQurekaUrl());
            }
        });

        Skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (convertedObject != null) {
                    AppManage.getInstance(SkipActivity.this).adintercheck(convertedObject, context, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                        @Override
                        public void onintentscreen() {
                            startActivity(new Intent(SkipActivity.this, ContinueActivity.class));
                        }
                    });

                } else {
                    startActivity(new Intent(SkipActivity.this, ContinueActivity.class));
                }
            }
        });

    }

    private void showStartApps() {

        boolean isAdVisible = false;

        Liner_Localad.setVisibility(View.VISIBLE);

        if (arrAdDataStart != null && arrAdDataStart.size() > 0) {
            isAdVisible = true;
        } else {
            isAdVisible = false;
        }

        if (isAdVisible) {

            ad_recycle_view = (RecyclerView) findViewById(R.id.ad_recycle_view);
            ad_recycle_view.setHasFixedSize(true);
            ad_recycle_view.setLayoutFrozen(true);
            GridLayoutManager llm = new GridLayoutManager(SkipActivity.this, 3);
            llm.setOrientation(GridLayoutManager.VERTICAL);
            ad_recycle_view.setLayoutManager(llm);
            adViewAdapter = new AdViewAdapter_Start(context);
            ad_recycle_view.setAdapter(adViewAdapter);

            VideoPlayer_ItemClickSupport.addTo(ad_recycle_view).setOnItemClickListener(new VideoPlayer_ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    gotoAppStore(arrAdDataStart.get(position).getAppName(),
                            arrAdDataStart.get(position).getPackagename());
                }
            });

        }
    }

    private void gotoAppStore(final String appname, final String packagename) {
        // TODO Auto-generated method stub
        // new UpdateCounter().execute(packagename);
        try {
            context.startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id="
                            + packagename)));
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id="
                            + packagename)));
        }
    }

    public class AdViewAdapter_Start extends RecyclerView.Adapter<VideoPlayer_AdViewHolderView> {
        Context context;

        public AdViewAdapter_Start(Context context) {
            this.context = context;
        }

        @Override
        public VideoPlayer_AdViewHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adview_listitem, parent, false);
            VideoPlayer_AdViewHolderView viewHolder = new VideoPlayer_AdViewHolderView(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final VideoPlayer_AdViewHolderView holder, int position) {
            try {
                Glide.with(context).load(arrAdDataStart.get(position).getAppIcon())
                        .into(holder.appicon);
                holder.appname.setText(arrAdDataStart.get(position).getAppName());
                holder.appname.setTextSize(11);
                holder.appname.setSelected(true);
            } catch (Exception e) {
            }
        }

        @Override
        public int getItemCount() {

            return arrAdDataStart.size();
        }
    }

    @Override
    public void onBackPressed() {

        if (convertedObject != null) {
            AppManage.getInstance(SkipActivity.this).adBackpressintercheck(convertedObject, context, new AppManage.onAdIntent() {
                @Override
                public void onintentscreen() {
                    startActivity(new Intent(SkipActivity.this, thankyou.class));
                }
            });
        } else {
            startActivity(new Intent(SkipActivity.this, thankyou.class));
        }
    }
}
