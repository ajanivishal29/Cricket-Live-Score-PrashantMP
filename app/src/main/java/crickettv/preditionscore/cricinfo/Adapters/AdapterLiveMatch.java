package crickettv.preditionscore.cricinfo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import crickettv.preditionscore.cricinfo.Apiresponse.JsonClient;
import crickettv.preditionscore.cricinfo.Apiresponse.JsonRunsResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.JsondataResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchesliveResponceItem;
import crickettv.preditionscore.cricinfo.C6495a;
import crickettv.preditionscore.cricinfo.MainActivity1;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.AdapterLiveMatchesBinding;

/* renamed from: com.indvssa.cricinfo.adapter.home.AdapterUpcomingMatch */
public class AdapterLiveMatch extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Activity activity;
    public ArrayList<MatchesliveResponceItem> crickalldata1 = new ArrayList<>();

    public String f27269d = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    public DataItem convertedObject;

    String teamimageurl = C6495a.m21868a("03D530BE4FEA3E4787195A72F8477BA88E4561E20C65BA17EC918EDCEF89C6081A83AA7CE3369E462FA678C2F211BC22");
    public AdapterLiveMatch(FragmentActivity activity2, ArrayList<MatchesliveResponceItem> crickalldata) {
        activity = activity2;
        crickalldata1 = crickalldata;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        convertedObject = Utils.getResponse(activity);
        return new LivematchViewHolder(this, AdapterLiveMatchesBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        MatchesliveResponceItem matchesliveResponceItem = crickalldata1.get(i);
        LivematchViewHolder livematchViewHolder = (LivematchViewHolder) holder;

        JsonClient client = null;
        if (matchesliveResponceItem.getJsondata().length() > 0) {
            client = new Gson().fromJson(matchesliveResponceItem.getJsondata(), JsonClient.class);
        }
        livematchViewHolder.matchBinding.txtTitle.setText(matchesliveResponceItem.getTitle());
        livematchViewHolder.matchBinding.txtTime.setText(matchesliveResponceItem.getMatchtime());
        Glide.with(activity).load(teamimageurl + matchesliveResponceItem.getTeamAImage()).into(livematchViewHolder.matchBinding.leftSideTeamImg);
        Glide.with(activity).load(teamimageurl + matchesliveResponceItem.getTeamBImage()).error(R.mipmap.icon128).into(livematchViewHolder.matchBinding.rightSideTeamImg);

        livematchViewHolder.matchBinding.txtLeftSideTeam.setText(matchesliveResponceItem.getTeamA());
        livematchViewHolder.matchBinding.txtRightSideTeam.setText(matchesliveResponceItem.getTeamB());


        if (matchesliveResponceItem.getResult().trim().length() > 0) {
            livematchViewHolder.matchBinding.liveTxt.setVisibility(0);
            livematchViewHolder.matchBinding.liveTxt.setText("finished");
            livematchViewHolder.matchBinding.liveTxt.setTextColor(Color.parseColor("#F1AA33"));
            livematchViewHolder.matchBinding.rateLL.setVisibility(8);
            livematchViewHolder.matchBinding.overs.setVisibility(8);
            livematchViewHolder.matchBinding.oversb.setVisibility(8);
            livematchViewHolder.matchBinding.txtResult.setVisibility(0);
            TextView textView3 = livematchViewHolder.matchBinding.txtResult;
            textView3.setText(matchesliveResponceItem.getResult() + "");
            livematchViewHolder.matchBinding.scorea.setVisibility(8);
            livematchViewHolder.matchBinding.scoreb.setVisibility(8);
        } else {
            if (this.f27269d.trim().equals(matchesliveResponceItem.getMatchDate().trim())) {
                livematchViewHolder.matchBinding.liveTxt.setText("Upcoming");
                livematchViewHolder.matchBinding.scorea.setVisibility(8);
                livematchViewHolder.matchBinding.scoreb.setVisibility(8);
                livematchViewHolder.matchBinding.rateLL.setVisibility(8);
                livematchViewHolder.matchBinding.overs.setVisibility(8);
                livematchViewHolder.matchBinding.oversb.setVisibility(8);
                livematchViewHolder.matchBinding.txtResult.setVisibility(8);
            } else {
                livematchViewHolder.matchBinding.liveTxt.setText("Upcoming");
                livematchViewHolder.matchBinding.scorea.setVisibility(8);
                livematchViewHolder.matchBinding.scoreb.setVisibility(8);
                livematchViewHolder.matchBinding.rateLL.setVisibility(8);
                livematchViewHolder.matchBinding.overs.setVisibility(8);
                livematchViewHolder.matchBinding.oversb.setVisibility(8);
                livematchViewHolder.matchBinding.txtResult.setVisibility(8);
            }

            if (client != null && matchesliveResponceItem.getJsondata().length() > 0) {
                JsondataResponse a = client.jsondataResponse();
                JsonRunsResponse a2 = new Gson().fromJson(matchesliveResponceItem.getJsonruns(), JsonRunsResponse.class);

                if (!a.bowler.equalsIgnoreCase("0")) {
                    livematchViewHolder.matchBinding.liveTxt.setText("Live");
                    livematchViewHolder.matchBinding.rateLL.setVisibility(0);
                    livematchViewHolder.matchBinding.scorea.setVisibility(0);
                    livematchViewHolder.matchBinding.scoreb.setVisibility(0);
                    livematchViewHolder.matchBinding.txtResult.setVisibility(8);
                }
                TextView textView4 = livematchViewHolder.matchBinding.scorea;
                textView4.setText(a.wicketA + "");
                TextView textView5 = livematchViewHolder.matchBinding.scoreb;
                textView5.setText(a.wicketB + "");
                TextView textView6 = livematchViewHolder.matchBinding.overs;
                textView6.setText("Overs (" + a.oversA.trim() + ")");
                TextView textView7 = livematchViewHolder.matchBinding.oversb;
                textView7.setText(a.oversB + "");
                TextView textView8 = livematchViewHolder.matchBinding.rate;
                textView8.setText(a2.mo20595a().rateA + "");
                TextView textView9 = livematchViewHolder.matchBinding.rate2;
                textView9.setText(a2.mo20595a().rateB + "");
                TextView textView10 = livematchViewHolder.matchBinding.txtLeftSideTeam;
                textView10.setText(a.teamA + "");
                TextView textView11 = livematchViewHolder.matchBinding.txtRightSideTeam;
                textView11.setText(a.teamB + "");
                if (a.teamABanner != null) {
                    Glide.with(activity).load(teamimageurl + a.teamABanner).error(R.mipmap.icon128).into(livematchViewHolder.matchBinding.leftSideTeamImg);
                }
                if (a.teamBBanner != null) {
                    Glide.with(activity).load(teamimageurl + a.teamBBanner).error(R.mipmap.icon128).into(livematchViewHolder.matchBinding.rightSideTeamImg);
                }
                if (a2.mo20595a().rateA.equalsIgnoreCase(a.teamB)) {
                    livematchViewHolder.matchBinding.favTeamA.setVisibility(0);
                    livematchViewHolder.matchBinding.favTeamB.setVisibility(8);
                } else if (a2.mo20595a().rateA.equalsIgnoreCase(a.title)) {
                    livematchViewHolder.matchBinding.favTeamA.setVisibility(8);
                    livematchViewHolder.matchBinding.favTeamB.setVisibility(0);
                } else {
                    livematchViewHolder.matchBinding.favTeamA.setVisibility(0);
                    livematchViewHolder.matchBinding.favTeamB.setVisibility(0);
                }
                if (a2.mo20595a().rateB.equalsIgnoreCase("0")) {
                    livematchViewHolder.matchBinding.rateLL.setVisibility(8);
                    livematchViewHolder.matchBinding.favTeamA.setVisibility(8);
                    livematchViewHolder.matchBinding.favTeamB.setVisibility(8);
                }
                if (a.ns4.equals("Test")) {
                    livematchViewHolder.matchBinding.rateLL.setVisibility(8);
                } else {
                    livematchViewHolder.matchBinding.rateLL.setVisibility(0);
                }
            }
        }

        JsonClient finalClient = client;
        livematchViewHolder.matchBinding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (convertedObject != null) {
                    AppManage.getInstance(activity).adintercheck(convertedObject, activity, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                        @Override
                        public void onintentscreen() {
                            String str;
                            try {
                                Intent intent = new Intent(activity, MainActivity1.class);
                                intent.putExtra("MatchId", matchesliveResponceItem.getMatchId() + "");
                                MatchesliveResponceItem dVar = matchesliveResponceItem;
                                if (dVar.getJsondata().length() > 0) {
//                        C6029c cVar = (C6029c) new C5701i().mo20204b(dVar.mo20668a(), C6029c.class);
                                    JsonClient cVar = new Gson().fromJson(matchesliveResponceItem.getJsondata(), JsonClient.class);
                                    if (cVar != null) {
                                        JsondataResponse a = cVar.jsondataResponse();
                                        if (a != null) {
                                            if (a.title.contains("Match")) {
                                                str = a.title.substring(0, a.title.indexOf("Match"));
                                            } else if (a.title.substring(0, a.title.indexOf("|")).contains("C.RR")) {
                                                String[] split = a.title.substring(0, a.title.indexOf("|")).split("C.RR");
                                                if (split[0] != null) {
                                                    str = split[0].substring(0, split[0].length() - 1) + "";
                                                } else {
                                                    str = "";
                                                }
                                            } else {
                                                str = a.title.substring(0, a.title.indexOf("|")) + "";
                                            }
                                            intent.putExtra("Match", str);
                                            intent.putExtra("MatchType", "Live");
                                        }
                                    } else {
                                        intent.putExtra("MatchType", "Result");
                                        intent.putExtra("Match", matchesliveResponceItem.getTitle());
                                    }
                                } else {
                                    intent.putExtra("MatchType", "Result");
                                    intent.putExtra("Match", matchesliveResponceItem.getTitle());
                                }
                                intent.putExtra("MatchT", matchesliveResponceItem.getMatchType() + "");
                                activity.startActivity(intent);
                            } catch (Exception unused) {
                            }
                        }
                    });

                } else {
                    String str;
                    try {
                        Intent intent = new Intent(activity, MainActivity1.class);
                        intent.putExtra("MatchId", matchesliveResponceItem.getMatchId() + "");
                        MatchesliveResponceItem dVar = matchesliveResponceItem;
                        if (dVar.getJsondata().length() > 0) {
//                        C6029c cVar = (C6029c) new C5701i().mo20204b(dVar.mo20668a(), C6029c.class);
                            JsonClient cVar = new Gson().fromJson(matchesliveResponceItem.getJsondata(), JsonClient.class);
                            if (cVar != null) {
                                JsondataResponse a = cVar.jsondataResponse();
                                if (a != null) {
                                    if (a.title.contains("Match")) {
                                        str = a.title.substring(0, a.title.indexOf("Match"));
                                    } else if (a.title.substring(0, a.title.indexOf("|")).contains("C.RR")) {
                                        String[] split = a.title.substring(0, a.title.indexOf("|")).split("C.RR");
                                        if (split[0] != null) {
                                            str = split[0].substring(0, split[0].length() - 1) + "";
                                        } else {
                                            str = "";
                                        }
                                    } else {
                                        str = a.title.substring(0, a.title.indexOf("|")) + "";
                                    }
                                    intent.putExtra("Match", str);
                                    intent.putExtra("MatchType", "Live");
                                }
                            } else {
                                intent.putExtra("MatchType", "Result");
                                intent.putExtra("Match", matchesliveResponceItem.getTitle());
                            }
                        } else {
                            intent.putExtra("MatchType", "Result");
                            intent.putExtra("Match", matchesliveResponceItem.getTitle());
                        }
                        intent.putExtra("MatchT", matchesliveResponceItem.getMatchType() + "");
                        activity.startActivity(intent);
                    } catch (Exception unused) {
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return crickalldata1.size();
    }

    public void updated(ArrayList<MatchesliveResponceItem> livematchdata) {
        crickalldata1 = new ArrayList<>();
        this.crickalldata1.addAll(livematchdata);
        notifyDataSetChanged();
    }

    public class LivematchViewHolder extends RecyclerView.ViewHolder {
        public AdapterLiveMatchesBinding matchBinding;

        public LivematchViewHolder(@NonNull AdapterLiveMatch adapterUpcomingMatch, AdapterLiveMatchesBinding AdapterLiveMatchesBinding) {
            super(AdapterLiveMatchesBinding.getRoot());
            this.matchBinding = AdapterLiveMatchesBinding;
        }
    }
}
