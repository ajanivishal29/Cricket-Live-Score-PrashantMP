package crickettv.preditionscore.cricinfo.Fragments;

import static crickettv.preditionscore.cricinfo.Adapters.PaginationListener.PAGE_START;
import static crickettv.preditionscore.cricinfo.Fragments.MatchesFragment.apiInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import crickettv.preditionscore.cricinfo.Adapters.AdapterMatchestabResult;
import crickettv.preditionscore.cricinfo.Adapters.PaginationListener;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AllMatchItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchResultResponce;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.databinding.FragmentResultBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultFragment extends Fragment {

    private FragmentResultBinding binding;

    ArrayList<AllMatchItem> matchresultdata = new ArrayList<>();

    public AdapterMatchestabResult adapter1;

    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 100;
    private boolean isLoading = false;
    int itemCount = 0;
    int start = 1;
    int end = 10;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
            binding.progressbar.setVisibility(View.VISIBLE);
            crickmatchResult();
        } else {
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
        }


        binding.recyclerMatches.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerMatches.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerMatches.setLayoutManager(layoutManager);

        adapter1 = new AdapterMatchestabResult(getActivity());
        binding.recyclerMatches.setAdapter(adapter1);

        binding.recyclerMatches.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                start = start + 10;
                end = end + 10;
                Log.e("startValue", String.valueOf(start));
                Log.e("endtValue", String.valueOf(end));
                crickmatchResult();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        return root;
    }

    public void crickmatchResult() {
        HashMap jsonObject = new HashMap();
        jsonObject.put("start", start);
        jsonObject.put("end", end);
        Call<MatchResultResponce> call1 = apiInterface.crickmatchResult(jsonObject);

        call1.enqueue(new Callback<MatchResultResponce>() {
            @Override
            public void onResponse(Call<MatchResultResponce> call, Response<MatchResultResponce> response) {
                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getAllMatch() != null && response.body().getAllMatch().size() > 0) {
                        matchresultdata.clear();
                        matchresultdata.addAll(response.body().getAllMatch());
                        binding.progressbar.setVisibility(View.GONE);

                        if (currentPage != PAGE_START) adapter1.removeLoading();
                        adapter1.addItems(matchresultdata);
                        // check weather is last page or not
                        if (currentPage < totalPage) {
                            adapter1.addLoading();
                        } else {
                            isLastPage = true;
                        }
                        isLoading = false;

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
        binding = null;
    }
}