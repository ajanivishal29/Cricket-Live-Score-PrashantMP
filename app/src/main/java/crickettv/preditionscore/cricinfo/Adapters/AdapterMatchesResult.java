package crickettv.preditionscore.cricinfo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AllMatchItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.MainActivity1;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;

/* renamed from: com.indvssa.cricinfo.adapter.home.AdapterUpcomingMatch */
public class AdapterMatchesResult extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Activity activity;
    public ArrayList<AllMatchItem> matchresultdata1 = new ArrayList<>();
    public DataItem convertedObject;

    public AdapterMatchesResult(FragmentActivity activity2, ArrayList<AllMatchItem> matchresultdata) {
        activity = activity2;
        matchresultdata1 = matchresultdata;
        Log.e("check4878", "adaopter called");


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        convertedObject = Utils.getResponse(activity);
        return new MatchresultViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_matches_result, viewGroup, false));
    }

    public int mo1100c(int i) {
        return (i == 0 || i % 4 != 0) ? 1 : 2;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

//        if (mo1100c(i) == 1) {
            MatchresultViewHolder bVar = (MatchresultViewHolder) holder;

            AllMatchItem allMatchItem = matchresultdata1.get(i);
//            C6033f.C6034a aVar = this.f27259e.get(i);
            bVar.f27267z.setText(allMatchItem.getTitle());
            bVar.f27260A.setText(allMatchItem.getVenue());
            bVar.f27263v.setText(allMatchItem.getMatchtime());
            bVar.f27265x.setText(allMatchItem.getTeamA());
            bVar.f27266y.setText(allMatchItem.getTeamB());
            bVar.f27264w.setText(allMatchItem.getResult());

            Glide.with(activity).load(allMatchItem.getImageUrl() + allMatchItem.getTeamAImage()).error(R.mipmap.icon128).into(bVar.f27261t);
            Glide.with(activity).load(allMatchItem.getImageUrl() + allMatchItem.getTeamBImage()).error(R.mipmap.icon128).into(bVar.f27262u);

            bVar.rlmain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (convertedObject != null) {
                        AppManage.getInstance(activity).adintercheck(convertedObject, activity, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                            @Override
                            public void onintentscreen() {
                                Intent intent = new Intent(activity, MainActivity1.class);
                                intent.putExtra("MatchId", allMatchItem.getMatchId() + "");
                                intent.putExtra("MatchType", "Result");
                                intent.putExtra("MatchT", allMatchItem.getMatchtype());
                                intent.putExtra("Match", allMatchItem.getTitle() + "");
                                activity.startActivity(intent);
                            }
                        });

                    } else {
                        Intent intent = new Intent(activity, MainActivity1.class);
                        intent.putExtra("MatchId", allMatchItem.getMatchId() + "");
                        intent.putExtra("MatchType", "Result");
                        intent.putExtra("MatchT", allMatchItem.getMatchtype());
                        intent.putExtra("Match", allMatchItem.getTitle() + "");
                        activity.startActivity(intent);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return matchresultdata1.size();
    }

    public class MatchresultViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: A */
        public TextView f27260A;

        /* renamed from: t */
        public ImageView f27261t;

        /* renamed from: u */
        public ImageView f27262u;

        /* renamed from: v */
        public TextView f27263v;

        /* renamed from: w */
        public TextView f27264w;

        /* renamed from: x */
        public TextView f27265x;

        /* renamed from: y */
        public TextView f27266y;

        /* renamed from: z */
        public TextView f27267z;
        RelativeLayout rlmain;

        public MatchresultViewHolder(View view) {
            super(view);

            this.f27267z = (TextView) view.findViewById(R.id.txtTitle);
            this.f27264w = (TextView) view.findViewById(R.id.txtResult);
            this.f27260A = (TextView) view.findViewById(R.id.txtVenue);
            this.f27263v = (TextView) view.findViewById(R.id.txtMatchTime);
            this.f27265x = (TextView) view.findViewById(R.id.txtLeftSideTeam);
            this.f27266y = (TextView) view.findViewById(R.id.txtRightSideTeam);
            this.f27261t = (ImageView) view.findViewById(R.id.leftSideTeamImg);
            this.f27262u = (ImageView) view.findViewById(R.id.rightSideTeamImg);
            this.rlmain = view.findViewById(R.id.card_view);
        }
    }
}
