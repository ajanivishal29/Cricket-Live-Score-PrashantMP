package crickettv.preditionscore.cricinfo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.NewsListItem;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.NewsDetailView;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.AdapterNewsBinding;

/* renamed from: com.indvssa.cricinfo.adapter.home.AdapterUpcomingMatch */
public class AdapterNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Activity activity;
    public ArrayList<NewsListItem> matchresultdata1 = new ArrayList<>();
    public DataItem convertedObject;

    public AdapterNews(FragmentActivity activity2, ArrayList<NewsListItem> matchresultdata) {
        activity = activity2;
        matchresultdata1 = matchresultdata;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        convertedObject = Utils.getResponse(activity);
        return new OddsdetauilViewHolder(this, AdapterNewsBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        NewsListItem newsListItem = matchresultdata1.get(i);
        OddsdetauilViewHolder oddsdetauilViewHolder = (OddsdetauilViewHolder) holder;
        oddsdetauilViewHolder.oddsBinding.newsHeading.setText(newsListItem.getTitle());
        oddsdetauilViewHolder.oddsBinding.newsText.setText(newsListItem.getDescription());
        Glide.with(activity).load(newsListItem.getURLToImage()).error(R.mipmap.icon128).into(oddsdetauilViewHolder.oddsBinding.newsImage);

        oddsdetauilViewHolder.oddsBinding.lnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (convertedObject != null) {
                    AppManage.getInstance(activity).adintercheck(convertedObject, activity, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                        @Override
                        public void onintentscreen() {
                            Intent intent = new Intent(activity, NewsDetailView.class);
                            intent.putExtra("newsTitle", newsListItem.getTitle());
                            intent.putExtra("newsDesc", newsListItem.getContent());
                            intent.putExtra("newsImage", newsListItem.getURLToImage());
                            activity.startActivity(intent);
                        }
                    });

                } else {
                    Intent intent = new Intent(activity, NewsDetailView.class);
                    intent.putExtra("newsTitle", newsListItem.getTitle());
                    intent.putExtra("newsDesc", newsListItem.getContent());
                    intent.putExtra("newsImage", newsListItem.getURLToImage());
                    activity.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return matchresultdata1 == null ? 0 : matchresultdata1.size();
    }

    public class OddsdetauilViewHolder extends RecyclerView.ViewHolder {

        public AdapterNewsBinding oddsBinding;

        public OddsdetauilViewHolder(@NonNull AdapterNews adapteroddstab, AdapterNewsBinding adapterNewsBinding) {
            super(adapterNewsBinding.getRoot());
            oddsBinding = adapterNewsBinding;
        }
    }
}
