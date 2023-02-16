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

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchstItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.OddsResponce;
import crickettv.preditionscore.cricinfo.MainActivity1;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.LayoutMatchOddsBinding;
import crickettv.preditionscore.cricinfo.retrofit.APIClient;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchoddsFragments extends Fragment {

    private LayoutMatchOddsBinding binding;
    Fragment fragment;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public DataItem convertedObject;

    public static RetrofitInterface apiInterface;
    ArrayList<MatchstItem> matchresultdata = new ArrayList<>();
    ArrayList<MatchstItem> matchresultdatasecond = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = LayoutMatchOddsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        admobsmallnative = root.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = root.findViewById(R.id.native_banner_ad_container);
        q_native_banner = root.findViewById(R.id.q_native_banner);

        apiInterface = APIClient.getcrickclient().create(RetrofitInterface.class);

        convertedObject = Utils.getResponse(getActivity());

        if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
            binding.progressbar.setVisibility(View.GONE);
            cricoddstab();
        } else {
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
        }


        if (convertedObject != null) {

            // TODO: 11-09-2020  Native Ads
            ((MainAdsActivity) getActivity()).checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            ((MainAdsActivity) getActivity()).admob_nativebanner_load();
            ((MainAdsActivity) getActivity()).admob_native_load();
        }

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new FirstInningFragment(matchresultdata);
                        break;
                    case 1:
                        fragment = new SecondInningFragment(matchresultdatasecond);
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


        /*binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new FirstInningFragment();
                        break;
                    case 1:
                        fragment = new SecondInningFragment();
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
        });*/
        return root;
    }

    public void cricoddstab() {
        HashMap jsonObject = new HashMap();
        jsonObject.put("MatchId", MainActivity1.MatchId);
        Call<OddsResponce> call1 = apiInterface.crickoddstab(jsonObject);

        call1.enqueue(new Callback<OddsResponce>() {
            @Override
            public void onResponse(Call<OddsResponce> call, Response<OddsResponce> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getMatchst() != null && response.body().getMatchst().size() > 0) {
                        for (int i = 0; i < response.body().getMatchst().size(); i++) {

                            if (response.body().getMatchst().get(i).getIsfirstinning().equalsIgnoreCase("1")) {
                                matchresultdata.add(response.body().getMatchst().get(i));
                            } else {
                                if (response.body().getMatchst().get(i).getIsfirstinning().equalsIgnoreCase("2")) {
                                    matchresultdatasecond.add(response.body().getMatchst().get(i));
                                }
                            }
                        }

                        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("1st Innings"));
                        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("2nd Innings"));
                        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_START);

                        fragment = new FirstInningFragment(matchresultdata);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.pager, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

                        binding.progressbar.setVisibility(View.GONE);

                    } else {
                        binding.progressbar.setVisibility(View.GONE);
                        binding.nodatapage.noDataLy.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(Call<OddsResponce> call, Throwable t) {
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
