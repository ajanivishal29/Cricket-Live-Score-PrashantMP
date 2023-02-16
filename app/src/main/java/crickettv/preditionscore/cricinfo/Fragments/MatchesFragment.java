package crickettv.preditionscore.cricinfo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import crickettv.preditionscore.cricinfo.Adapters.AdapterLiveMatch;
import crickettv.preditionscore.cricinfo.Adapters.AdapterMatchesResult;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AllMatchItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchResultResponce;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchesliveResponceItem;
import crickettv.preditionscore.cricinfo.retrofit.APIClient;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.databinding.FragmentMatchesBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesFragment extends Fragment {

    private FragmentMatchesBinding binding;

    public AdapterLiveMatch adapter;
    public AdapterMatchesResult adapter1;
    public static RetrofitInterface apiInterface;

    ArrayList<MatchesliveResponceItem> livematchdata = new ArrayList<>();
    ArrayList<AllMatchItem> matchresultdata = new ArrayList<>();
    public ScheduledExecutorService f27541h0;
    public ScheduledFuture<?> f27540g0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiInterface = APIClient.getcrickclient().create(RetrofitInterface.class);

        if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
            binding.progressbar.setVisibility(View.VISIBLE);
            mo20544v0();
            crickmatchResult();
        } else {
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
        }

        binding.recyclerMatches.setOrientation(DSVOrientation.HORIZONTAL);
        binding.recyclerMatches.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        binding.recyclerUpcommingMatches.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerUpcommingMatches.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        binding.recyclerUpcommingMatches.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));

        adapter = new AdapterLiveMatch(getActivity(), livematchdata);
        binding.recyclerMatches.setAdapter(adapter);

        return root;
    }


    public final void mo20544v0() {
        try {
            if (this.f27541h0 == null) {
                ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.f27541h0 = newSingleThreadScheduledExecutor;
                this.f27540g0 = newSingleThreadScheduledExecutor.scheduleAtFixedRate(new C6013a(), 0, 5, TimeUnit.SECONDS);
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

    public void cricklivematchResponce() {
        livematchdata.clear();
        Call<ArrayList<MatchesliveResponceItem>> call1 = apiInterface.cricklivematch();

        call1.enqueue(new Callback<ArrayList<MatchesliveResponceItem>>() {
            @Override
            public void onResponse(Call<ArrayList<MatchesliveResponceItem>> call, Response<ArrayList<MatchesliveResponceItem>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body() != null && response.body().size() > 0) {
                        livematchdata.addAll(response.body());
                        binding.progressbar.setVisibility(View.GONE);
                        if (adapter != null) {
                            adapter.updated(livematchdata);
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


    public void crickmatchResult() {
        matchresultdata.clear();
        HashMap jsonObject = new HashMap();
        jsonObject.put("start", "1");
        jsonObject.put("end", "15");
        Call<MatchResultResponce> call1 = apiInterface.crickmatchResult(jsonObject);

        call1.enqueue(new Callback<MatchResultResponce>() {
            @Override
            public void onResponse(Call<MatchResultResponce> call, Response<MatchResultResponce> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getAllMatch() != null && response.body().getAllMatch().size() > 0) {
                        matchresultdata.addAll(response.body().getAllMatch());

                        binding.progressbar.setVisibility(View.GONE);
                        adapter1 = new AdapterMatchesResult(getActivity(), matchresultdata);
                        binding.recyclerUpcommingMatches.setAdapter(adapter1);
                    }
                }

            }

            @Override
            public void onFailure(Call<MatchResultResponce> call, Throwable t) {
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
            this.f27541h0 = null;
        }
    }
}