package crickettv.preditionscore.cricinfo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.ads.NativeAdLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AllplyerResponce;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.PlayerslistItem;
import crickettv.preditionscore.cricinfo.retrofit.APIClient;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.MainActivity1;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.PlayingXiFragmentBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScorecardFragments extends Fragment {

    private PlayingXiFragmentBinding binding;
    Fragment fragment;

    public static RetrofitInterface apiInterface;
    ArrayList<PlayerslistItem> firstinningdata = new ArrayList<>();
    ArrayList<PlayerslistItem> secoundinningdata = new ArrayList<>();

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;


    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;


    public DataItem convertedObject;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = PlayingXiFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiInterface = APIClient.getcrickclient().create(RetrofitInterface.class);
        if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
            binding.progressbar.setVisibility(View.VISIBLE);
            playerscorecarddata();
        } else {
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
        }


        admobsmallnative = root.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = root.findViewById(R.id.native_banner_ad_container);
        q_native_banner = root.findViewById(R.id.q_native_banner);

        admobmediumnative = root.findViewById(R.id.admobmediumnative);
        native_ad_container = root.findViewById(R.id.native_ad_container);
        q_native = root.findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(getActivity());

        if (convertedObject != null) {

            // TODO: 11-09-2020  Native Ads
            ((MainAdsActivity) getActivity()).checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            ((MainAdsActivity) getActivity()).checkNativeAdsMode(admobmediumnative, native_ad_container, q_native);
            ((MainAdsActivity) getActivity()).admob_nativebanner_load();
            ((MainAdsActivity) getActivity()).admob_native_load();
        }

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new CountryFirstFragment(firstinningdata);
                        break;
                    case 1:
                        fragment = new CountrySecoundFragment(secoundinningdata);
                        break;
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.pager, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }


    public void playerscorecarddata() {
        HashMap jsonObject = new HashMap();
        jsonObject.put("MatchId", MainActivity1.MatchId);
        Call<AllplyerResponce> call1 = apiInterface.playerscorecarddata(jsonObject);

        call1.enqueue(new Callback<AllplyerResponce>() {
            @Override
            public void onResponse(Call<AllplyerResponce> call, Response<AllplyerResponce> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getPlayerslist() != null && response.body().getPlayerslist().size() > 0) {
                        for (int i = 0; i < response.body().getPlayerslist().size(); i++) {

                            if (response.body().getPlayerslist().get(i).getTeamSide().equalsIgnoreCase("Team A")) {
                                firstinningdata.add(response.body().getPlayerslist().get(i));
                            }
                            if (response.body().getPlayerslist().get(i).getTeamSide().equalsIgnoreCase("Team B")) {
                                secoundinningdata.add(response.body().getPlayerslist().get(i));
                            }
                        }

                        binding.progressbar.setVisibility(View.GONE);
                        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(firstinningdata.get(0).getTeamName() + " (" + firstinningdata.get(0).getTeamRuns() + ") "));
                        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(secoundinningdata.get(0).getTeamName() + " (" + secoundinningdata.get(0).getTeamRuns() + ") "));
                        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                        fragment = new CountryFirstFragment(firstinningdata);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.pager, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

                    } else {

                        binding.progressbar.setVisibility(View.GONE);
                        binding.toprltv.setVisibility(View.GONE);
                        binding.nodatapage.noDataLy.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(Call<AllplyerResponce> call, Throwable t) {
                call.cancel();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
