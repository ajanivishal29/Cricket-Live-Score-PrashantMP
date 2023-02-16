package crickettv.preditionscore.cricinfo.Fragments;

import static crickettv.preditionscore.cricinfo.Adapters.PaginationListener.PAGE_START;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.facebook.ads.NativeAdLayout;

import java.util.ArrayList;
import java.util.List;

import crickettv.preditionscore.cricinfo.Adapters.Adapteroddstab;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchstItem;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.databinding.FragmentTeamBinding;

public class FirstInningFragment extends androidx.fragment.app.Fragment {

    FragmentTeamBinding binding;

    public static RetrofitInterface apiInterface;
    ArrayList<MatchstItem> matchresultdata = new ArrayList<>();

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    public DataItem convertedObject;

    public Adapteroddstab adapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 100;
    private boolean isLoading = false;
    private boolean loading = true;

    int itemCount = 0;
    boolean flag = false;

    public FirstInningFragment(ArrayList<MatchstItem> matchresultdata) {
        this.matchresultdata = matchresultdata;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentTeamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        admobmediumnative = root.findViewById(R.id.admobmediumnative);
        native_ad_container = root.findViewById(R.id.native_ad_container);
        q_native = root.findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(getActivity());

        if (convertedObject != null) {

            // TODO: 11-09-2020  Native Ads
            ((MainAdsActivity) getActivity()).checkNativeAdsMode(admobmediumnative, native_ad_container, q_native);
            ((MainAdsActivity) getActivity()).admob_nativebanner_load();
            ((MainAdsActivity) getActivity()).admob_native_load();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerTeamA.setLayoutManager(layoutManager);

        adapter = new Adapteroddstab(getActivity(), new ArrayList<>());
        binding.recyclerTeamA.setAdapter(adapter);

        Log.e("ARRAYV DATA SIZE---", String.valueOf(matchresultdata.size()));
        binding.nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    try {
                        currentPage++;
                        setdata();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    Log.e("Page---", String.valueOf(currentPage));
                }
            }
        });



        new loadVideos().execute();
        return root;

    }

    class loadVideos extends AsyncTask<String, String, List<MatchstItem>> {
        loadVideos() {
        }

        /* access modifiers changed from: protected */
        @SuppressLint("WrongConstant")
        public void onPreExecute() {
            super.onPreExecute();
            binding.progressbar.setVisibility(View.VISIBLE);

        }

        /* access modifiers changed from: protected */
        public List<MatchstItem> doInBackground(String... strArr) {
            return matchresultdata;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<MatchstItem> list) {
            super.onPostExecute(list);
//            if(matchresultdata.size()>50)
//            {
//                totalPage = matchresultdata.size() / 50;
//            }
            setdata();
        }
    }

    public void setdata() {
        final ArrayList<MatchstItem> items = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (itemCount != matchresultdata.size())
                        items.add(matchresultdata.get(itemCount));
                    else
                        break;

                    itemCount++;
                }
                /**
                 * manage progress view
                 */
                if (!flag) {
                    binding.progressbar.setVisibility(View.GONE);
                    Log.e("check987456", String.valueOf(itemCount));
                    if (currentPage != PAGE_START)
                        adapter.removeLoading();
                    adapter.addItems(items);
                    if (matchresultdata.size() == itemCount) {

                        flag = true;
                    }
                    else {
                        if (currentPage < totalPage) {
                            adapter.addLoading();
                        } else {
                            isLastPage = true;
                        }
                        isLoading = false;
                    }
                    // check weather is last page or not

                }


            }
        }, 500);

    }

}
