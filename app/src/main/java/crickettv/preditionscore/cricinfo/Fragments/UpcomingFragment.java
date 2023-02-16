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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Adapters.AdapterUpcomingMatch;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AllMatchItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.CrickapiResponce;
import crickettv.preditionscore.cricinfo.retrofit.APIClient;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.databinding.FragmentUpcomingBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingFragment extends Fragment {

    private FragmentUpcomingBinding binding;

    public AdapterUpcomingMatch adapter;
    public RetrofitInterface apiInterface;
    public ArrayList<AllMatchItem> crickalldata = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiInterface = APIClient.getcrickclient().create(RetrofitInterface.class);
        if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
            binding.progressbar.setVisibility(View.VISIBLE);
            crickResponce();
        } else {
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
        }


        this.binding.recyclerViewHome.setItemAnimator(new DefaultItemAnimator());
        this.binding.recyclerViewHome.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        this.binding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
                    crickResponce();
                } else {
                    binding.swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
                }

            }
        });

        return root;
    }


    public void crickResponce() {
        crickalldata.clear();
        Call<CrickapiResponce> call1 = apiInterface.cricupcoming();

        call1.enqueue(new Callback<CrickapiResponce>() {
            @Override
            public void onResponse(Call<CrickapiResponce> call, Response<CrickapiResponce> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getAllMatch() != null && response.body().getAllMatch().size() > 0) {
                        crickalldata.addAll(response.body().getAllMatch());

                        binding.progressbar.setVisibility(View.GONE);
                        binding.swipeRefreshLayout.setRefreshing(false);
                        adapter = new AdapterUpcomingMatch(getActivity(), crickalldata);
                        binding.recyclerViewHome.setAdapter(adapter);

                    }
                }

            }

            @Override
            public void onFailure(Call<CrickapiResponce> call, Throwable t) {
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