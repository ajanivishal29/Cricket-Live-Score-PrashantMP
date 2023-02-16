package crickettv.preditionscore.cricinfo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAdLayout;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.databinding.ActivityNewsDetailBinding;

public class NewsDetailView extends MainAdsActivity {

    ActivityNewsDetailBinding binding;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;
    public DataItem convertedObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        binding.header.title.setText("News Detail");

        convertedObject = Utils.getResponse(NewsDetailView.this);

        if (convertedObject != null) {

            // TODO: 11-09-2020  Native Ads
            checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            checkNativeAdsMode(admobmediumnative, native_ad_container, q_native);
            admob_nativebanner_load();
            admob_native_load();
        }

        binding.header.toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        String newstitle = getIntent().getStringExtra("newsTitle");
        String newsCon = getIntent().getStringExtra("newsDesc");
        String newsimage = getIntent().getStringExtra("newsImage");

        binding.newtitle.setText(newstitle);
        binding.newDetails.setText(newsCon);
        Glide.with(getApplicationContext()).load(newsimage).into(binding.imageNews);
    }

    @Override
    public void onBackPressed() {
        if (convertedObject != null) {
            AppManage.getInstance(NewsDetailView.this).adBackpressintercheck(convertedObject, NewsDetailView.this, new AppManage.onAdIntent() {
                @Override
                public void onintentscreen() {
                    finish();
                }
            });
        } else {
            finish();
        }
    }
}
