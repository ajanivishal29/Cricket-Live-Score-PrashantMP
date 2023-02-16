package crickettv.preditionscore.cricinfo.Fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAdLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import crickettv.preditionscore.cricinfo.Apiresponse.JsonClient;
import crickettv.preditionscore.cricinfo.Apiresponse.JsonRuns;
import crickettv.preditionscore.cricinfo.Apiresponse.JsonRunsResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.JsondataResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchesliveResponceItem;
import crickettv.preditionscore.cricinfo.retrofit.APIClient;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.C6495a;
import crickettv.preditionscore.cricinfo.MainActivity1;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.FragmentLiveLineFeverBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivelineFragments extends Fragment {

    private FragmentLiveLineFeverBinding binding;

    public static RetrofitInterface apiInterface;
    ArrayList<MatchesliveResponceItem> livematchdata = new ArrayList<>();
    private Animation f27463n0;
    private boolean f27478v0 = false;
    private boolean f27429Q0;
    public ScheduledExecutorService f27541h0;
    public ScheduledFuture<?> f27540g0;

    String teamimageurl = C6495a.m21868a("03D530BE4FEA3E4787195A72F8477BA88E4561E20C65BA17EC918EDCEF89C6081A83AA7CE3369E462FA678C2F211BC22");

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;
    public DataItem convertedObject;

    public TextToSpeech v;
    public String lastOvers="";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLiveLineFeverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        apiInterface = APIClient.getcrickclient().create(RetrofitInterface.class);

        admobsmallnative = root.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = root.findViewById(R.id.native_banner_ad_container);
        q_native_banner = root.findViewById(R.id.q_native_banner);

        admobmediumnative = root.findViewById(R.id.admobmediumnative);
        native_ad_container = root.findViewById(R.id.native_ad_container);
        q_native = root.findViewById(R.id.q_native);

        AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        this.f27463n0 = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);

        convertedObject = Utils.getResponse(getActivity());

        if (convertedObject != null) {

            // TODO: 11-09-2020  Native Ads
            ((MainAdsActivity) getActivity()).checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            ((MainAdsActivity) getActivity()).checkNativeAdsMode(admobmediumnative, native_ad_container, q_native);
            ((MainAdsActivity) getActivity()).admob_nativebanner_load();
            ((MainAdsActivity) getActivity()).admob_native_load();
        }
        binding.Speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.Speaker.isChecked()) {
                    f27478v0 = true;
                } else {
                    f27478v0 = false;
                }
            }
        });

        if(MainActivity1.MatchType.equalsIgnoreCase("Live"))
        {

            if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
                binding.progressbar.setVisibility(View.VISIBLE);
                mo20544v0();
            } else {
                Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
            }
        } else {
            if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
                binding.progressbar.setVisibility(View.VISIBLE);
              cricklivematchResponce();
            } else {
                Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
            }
        }

        this.v = new TextToSpeech(getActivity(), new b());

        return root;
    }

    public final void mo20544v0() {
        try {
            if (this.f27541h0 == null) {
                ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.f27541h0 = newSingleThreadScheduledExecutor;
                this.f27540g0 = newSingleThreadScheduledExecutor.scheduleAtFixedRate(new C6013a(), 0, 1, TimeUnit.SECONDS);
            }
        } catch (Exception unused) {
        }
    }


    public class C6013a implements Runnable {
        public C6013a() {
        }

        public void run() {

            try {
                cricklivematchResponce();
            } catch (Exception unused) {
            }
        }
    }



    public void mo20529z0(JsondataResponse aVar, JsonRuns jsonRuns) {

        try {
            Log.e("Balls--",aVar.last6Balls+"---"+aVar.wicketA);
            try {
                Glide.with(getActivity()).load(teamimageurl + aVar.teamABanner).into(binding.imgTeamA);
                Glide.with(getActivity()).load(teamimageurl + aVar.teamBBanner).into(binding.imgTeamB);
            } catch (Exception e) {
                e.printStackTrace();
            }
            binding.textView99.setText(aVar.score);

            binding.teama.setText(aVar.teamA);
            binding.teamb.setText(aVar.teamB);
            binding.score.setText(aVar.wicketA);
            binding.overs.setText(aVar.oversA);

            binding.scoreb.setText(aVar.wicketB);
            binding.session.setText(jsonRuns.sessionA);
            binding.sessionb.setText(jsonRuns.sessionB);
            binding.sessionover.setText(jsonRuns.sessionOver);

            binding.TestTeamA.setText(aVar.testTeamA);
            binding.TestTeamB.setText(aVar.testTeamB);

            if (jsonRuns.rateA.isEmpty() && jsonRuns.rateB.isEmpty()) {
                binding.llODI.setVisibility(View.GONE);
            }
            binding.rateA.setText(jsonRuns.rateA);
            binding.rateB.setText(jsonRuns.rateB);

            binding.sessionrunx.setText(jsonRuns.runxa);
            binding.sessionballx.setText(jsonRuns.runxb);

            if (aVar.testTeamARate1.equalsIgnoreCase("0") &&
                    aVar.testTeamARate2.equalsIgnoreCase("0") &&
                    aVar.testTeamBRate1.equalsIgnoreCase("0") &&
                    aVar.testTeamBRate2.equalsIgnoreCase("0") &&
                    aVar.testdrawRate1.equalsIgnoreCase("0") &&
                    aVar.testdrawRate2.equalsIgnoreCase("0")) {

                binding.llTest.setVisibility(View.GONE);
            }


            binding.TestTeamARate1.setText(aVar.testTeamARate1);
            binding.TestTeamARate2.setText(aVar.testTeamARate2);
            binding.TestTeamBRate1.setText(aVar.testTeamBRate1);
            binding.TestTeamBRate2.setText(aVar.testTeamBRate2);
            binding.drawRateA.setText(aVar.testdrawRate1);
            binding.drawRateB.setText(aVar.testdrawRate2);

            binding.fav.setText(jsonRuns.fav);


            try {
                String[] batsman = aVar.batsman.split("\\|");
                binding.strikerbatsman.setText(batsman[0]);
                if (batsman.length > 1)
                    binding.batsman.setText(batsman[1]);

            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                String[] odds = aVar.oversB.split(",");
                String[] rateodds = odds[1].split("\\|");
                binding.nonstrikerruns.setText(odds[0]);
                binding.strikerruns.setText(rateodds[0]);

                binding.nonstrikerballs.setText(rateodds[1]);
                binding.strikerballs.setText(odds[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }

//        CC Women 22nd ODI MATCH - India W Vs Bangladesh W- C.RR: 1.21, R.RR: 4.86

            try {
                if (aVar.title.contains("R.RR")) {
                    String result;
                    String[] strings = aVar.title.split("\\|");
                    String[] subs = strings[0].split("R.RR");
                    result = subs[1].replace(",", "");

                    binding.RRR.setText("R.R.R" + result);

                    String[] crrvalue = subs[0].split("C.RR");
                    result = crrvalue[1].replace(",", "");

                    binding.ccRR.setText("C.R.R" + result);

                } else {
                    String[] strings = aVar.title.split("\\|");
                    String[] subs = strings[0].split("C.RR");
                    binding.ccRR.setText("C.R.R" + subs[1]);
                    binding.RRR.setText("R.R.R" + ":0.0");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                binding.srStriker.setText(getStrikerate(binding.strikerruns.getText().toString(), binding.strikerballs.getText().toString()));
                binding.srOther.setText(getStrikerate(binding.nonstrikerruns.getText().toString(), binding.nonstrikerballs.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }


            binding.striker4s.setText(aVar.s4);
            binding.nonstriker4s.setText(aVar.ns4);
            binding.strikerSix.setText(aVar.s6);
            binding.nonstrikerSix.setText(aVar.ns6);

            binding.bowlerstatus.setText(aVar.bowler);

            binding.summary.setText(jsonRuns.summary);


            if (aVar.last6Balls != null || !TextUtils.isEmpty(aVar.last6Balls)) {
                String[] split = aVar.last6Balls.split("-");
                if (split.length > 0) {
                    binding.textFirstballScore.setText(split[0].toLowerCase());
                    if (split.length > 1) {
                        binding.textSecondballScore.setText(split[1].toLowerCase());
                    }
                    if (split.length > 2) {
                        binding.textThirdballScore.setText(split[2].toLowerCase());
                    }
                    if (split.length > 3) {
                        binding.textFourthballScore.setText(split[3].toLowerCase());
                    }
                    if (split.length > 4) {
                        binding.textFifthballScore.setText(split[4].toLowerCase());
                    }
                    if (split.length > 5) {
                        binding.textSixthballScore.setText(split[5].toLowerCase());
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void speechText(String score)
    {
        String str;
        Animation animation = this.f27463n0;
        if (animation != null) {
            // animation.setInterpolator(new C6046b(0.1d, 15.0d));
            // binding.textView99.startAnimation(this.f27463n0);
            String o = score;
            try {
                if (this.f27478v0) {
                    if (o.equalsIgnoreCase("4-4-4")) {
                        str = "4 run";
                    } else {
                        str = o.equalsIgnoreCase("6-6-6") ? "6 run" : o;
                    }
                    TextToSpeech textToSpeech = v;
                    if (!o.equalsIgnoreCase("0") && !o.equalsIgnoreCase("1") && !o.equalsIgnoreCase("2") && !o.equalsIgnoreCase("3") && !o.equalsIgnoreCase("4") && !o.equalsIgnoreCase("5") && !o.equalsIgnoreCase("6")) {
                        textToSpeech.speak(str + "", 0, (HashMap) null);
                    }
                    textToSpeech.speak(str + " run", 0, (HashMap) null);
                }
            } catch (Exception unused) {
            }
        } else if (this.f27463n0 != null) {
            // binding.textView99.clearAnimation();
        }
    }

    public class b implements TextToSpeech.OnInitListener {
        public b() {
        }

        public void onInit(int i) {
            if (i != -1) {
                v.setLanguage(Locale.US);

            } else {
                Log.w("215", "Could not open TTS Engine (onInit status=" + i
                        + "). Ignoring text to speech");
                v = null;
            }
        }
    }

    public String getStrikerate(String s, String toString) {
        int striruns = Integer.parseInt(s);
        int striballs = Integer.parseInt(toString);
        double SR = ((double) striruns / striballs * 100);
        if (Double.isNaN(SR)) {
            SR = 0.0;
        }
        return String.format("%.1f", SR);

    }

    public class C6046b implements Interpolator {
        public C6046b(double d, double d2) {
        }

        public float getInterpolation(float f) {
            double d = (double) (-f);
            Double.isNaN(d);
            double d2 = (double) f;
            Double.isNaN(d2);
            Double.isNaN(d);
            Double.isNaN(d);
            Double.isNaN(d2);
            Double.isNaN(d2);
            return (float) ((Math.cos(15.0d * d2) * Math.pow(2.718281828459045d, d / 0.1d) * -1.0d) + 1.0d);
        }
    }

    public void cricklivematchResponce() {


        livematchdata.clear();
        HashMap jsonObject = new HashMap();
        jsonObject.put("MatchId", MainActivity1.MatchId);
        Call<ArrayList<MatchesliveResponceItem>> call1 = apiInterface.cricketlivematchdetail(jsonObject);

        call1.enqueue(new Callback<ArrayList<MatchesliveResponceItem>>() {
            @Override
            public void onResponse(Call<ArrayList<MatchesliveResponceItem>> call, Response<ArrayList<MatchesliveResponceItem>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body() != null && response.body().size() > 0) {
                        livematchdata.addAll(response.body());
                        binding.progressbar.setVisibility(View.GONE);

                        JsonClient client = null;
                        if (livematchdata.get(0).getJsondata().length() > 0) {
                            client = new Gson().fromJson(livematchdata.get(0).getJsondata(), JsonClient.class);

                            JsonRunsResponse a2 = new Gson().fromJson(livematchdata.get(0).getJsonruns(), JsonRunsResponse.class);

                            if(lastOvers.isEmpty())
                            {
                                lastOvers=client.jsondataResponse().oversA;
                                speechText(client.jsondataResponse().score);
                            }
                            else {
                                if(!lastOvers.equalsIgnoreCase(client.jsondataResponse().oversA))
                                {
                                    lastOvers=client.jsondataResponse().oversA;
                                    speechText(client.jsondataResponse().score);
                                    Log.e("Score Over---",client.jsondataResponse().score);

                                }
                                else {
                                    if(lastOvers.equalsIgnoreCase(client.jsondataResponse().oversA))
                                    {
                                        if(!binding.textView99.getText().toString().equalsIgnoreCase(client.jsondataResponse().score))
                                        {
                                            Log.e("Score Over---",client.jsondataResponse().score);
                                            speechText(client.jsondataResponse().score);
                                        }
                                    }
                                }
                            }

                            mo20529z0(client.jsondataResponse(), a2.mo20595a());

                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MatchesliveResponceItem>> call, Throwable t) {
                call.cancel();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.f27541h0 != null) {
            this.f27540g0.cancel(true);
            this.f27541h0.shutdownNow();
            this.f27540g0 = null;
        }
        TextToSpeech textToSpeech = this.v;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
    }
}
