package crickettv.preditionscore.cricinfo.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Adapters.AdapterNews;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.NewsListItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.NewsdataResponce;
import crickettv.preditionscore.cricinfo.retrofit.RetrofitInterface;
import crickettv.preditionscore.cricinfo.C6495a;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.databinding.FragmentNewsBinding;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragments extends Fragment {

    private FragmentNewsBinding binding;
    ArrayList<NewsListItem> newsdatalist = new ArrayList<>();
    public AdapterNews adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerUpcoming.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));


        if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
            binding.progressbar.setVisibility(View.VISIBLE);
            Newsdata();
        } else {
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
        }


        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (((MainAdsActivity) getActivity()).isInternetConnection() == true) {
                    newsdatalist = new ArrayList<>();
                    Newsdata();
                } else {
                    binding.swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_LONG).show();
                }

            }
        });
        return root;
    }

    public void Newsdata() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(C6495a.m21868a("77D37A1DCBC9FF089761B3FFA6C978D291777186834B9DA85103050C4DD40F3723BA21570E1F679FAF79FE9F577072C4"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.e("TAg",C6495a.m21868a("77D37A1DCBC9FF089761B3FFA6C978D291777186834B9DA85103050C4DD40F3723BA21570E1F679FAF79FE9F577072C4"));
        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Call<NewsdataResponce> call1 = service.Newsdata();

        call1.enqueue(new Callback<NewsdataResponce>() {
            @Override
            public void onResponse(Call<NewsdataResponce> call, Response<NewsdataResponce> response) {

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getNewsList() != null && response.body().getNewsList().size() > 0) {

                        newsdatalist.addAll(response.body().getNewsList());
                        binding.progressbar.setVisibility(View.GONE);
                        binding.swipeRefreshLayout.setRefreshing(false);
                        adapter = new AdapterNews(getActivity(), newsdatalist);
                        binding.recyclerUpcoming.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsdataResponce> call, Throwable t) {
                call.cancel();

            }
        });
    }

    public void Newsdata1() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer KAv3FUFSiwXWIzp5Fk90147JqhqtCFJ9Yt45kqMLJc1SCizqehEHcwAEUPOWYxlZ6RpeHS2nFnXtVQ9w").build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://zimoos.com/api/zimoos/app")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Call<NewsdataResponce> call1 = service.Newsdata();

        call1.enqueue(new Callback<NewsdataResponce>() {
            @Override
            public void onResponse(Call<NewsdataResponce> call, Response<NewsdataResponce> response) {

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getNewsList() != null && response.body().getNewsList().size() > 0) {

                        newsdatalist.addAll(response.body().getNewsList());
                        binding.progressbar.setVisibility(View.GONE);
                        binding.swipeRefreshLayout.setRefreshing(false);
                        adapter = new AdapterNews(getActivity(), newsdatalist);
                        binding.recyclerUpcoming.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsdataResponce> call, Throwable t) {
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
