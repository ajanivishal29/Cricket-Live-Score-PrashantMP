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
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchStatesResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchstStateItem;
import crickettv.preditionscore.cricinfo.retrofit.APIClient;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.MainActivity1;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.FragmentMatchStatsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsFragments extends Fragment {

    private FragmentMatchStatsBinding binding;
    Fragment fragment;

    ArrayList<MatchstStateItem> matchstStateItems = new ArrayList<>();
    public static RetrofitInterface apiInterface;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    public DataItem convertedObject;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiInterface = APIClient.getcrickclient().create(RetrofitInterface.class);
        binding.slidingTabs.setTabGravity(TabLayout.GRAVITY_FILL);

        admobsmallnative = root.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = root.findViewById(R.id.native_banner_ad_container);
        q_native_banner = root.findViewById(R.id.q_native_banner);

        admobmediumnative = root.findViewById(R.id.admobmediumnative);
        native_ad_container = root.findViewById(R.id.native_ad_container);
        q_native = root.findViewById(R.id.q_native);
        if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
            binding.progressbar.setVisibility(View.VISIBLE);
            Matchstatdata();
        } else {
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
        }

        convertedObject = Utils.getResponse(getActivity());

        if (convertedObject != null) {

            // TODO: 11-09-2020  Native Ads
            ((MainAdsActivity) getActivity()).checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            ((MainAdsActivity) getActivity()).checkNativeAdsMode(admobmediumnative, native_ad_container, q_native);
            ((MainAdsActivity) getActivity()).admob_nativebanner_load();
            ((MainAdsActivity) getActivity()).admob_native_load();
        }

        binding.slidingTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new MatchStatesFragment(matchstStateItems.get(0).getStat1descr());
                        break;
                    case 1:
                        fragment = new MatchStatesFragment(matchstStateItems.get(0).getStat2descr());
                        break;
                    case 2:
                        fragment = new MatchStatesFragment(matchstStateItems.get(0).getStat3descr());
                        break;
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.viewPager, fragment);
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

    public void Matchstatdata() {
        HashMap jsonObject = new HashMap();
        jsonObject.put("MatchId", MainActivity1.MatchId);
        Call<MatchStatesResponse> call1 = apiInterface.Matchstatdata(jsonObject);

        call1.enqueue(new Callback<MatchStatesResponse>() {
            @Override
            public void onResponse(Call<MatchStatesResponse> call, Response<MatchStatesResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getMatchstState() != null && response.body().getMatchstState().size() > 0) {
                        matchstStateItems.addAll(response.body().getMatchstState());
                        if (response.body().getMatchstState().get(0).getStat1name().length() > 0) {
                            binding.slidingTabs.addTab(binding.slidingTabs.newTab().setText(response.body().getMatchstState().get(0).getStat1name()));
                            binding.progressbar.setVisibility(View.GONE);
                            fragment = new MatchStatesFragment(matchstStateItems.get(0).getStat1descr());
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.viewPager, fragment);
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.commit();

                        }

                        if (response.body().getMatchstState().get(0).getStat2name().length() > 0) {
                            binding.slidingTabs.addTab(binding.slidingTabs.newTab().setText(response.body().getMatchstState().get(0).getStat2name()));

                        }

                        if (response.body().getMatchstState().get(0).getStat3name().length() > 0) {
                            binding.slidingTabs.addTab(binding.slidingTabs.newTab().setText(response.body().getMatchstState().get(0).getStat3name()));

                        }


                    } else {
                        binding.progressbar.setVisibility(View.GONE);
                        binding.llnodata.noDataLy.setVisibility(View.VISIBLE);
                        binding.mainLy.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<MatchStatesResponse> call, Throwable t) {
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
