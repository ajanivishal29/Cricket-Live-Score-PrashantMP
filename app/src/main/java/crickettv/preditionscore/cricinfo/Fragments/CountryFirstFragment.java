package crickettv.preditionscore.cricinfo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Adapters.Adapterallplyer;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.PlayerslistItem;
import crickettv.preditionscore.cricinfo.databinding.FragmentCountryBinding;

public class CountryFirstFragment extends androidx.fragment.app.Fragment {

    FragmentCountryBinding binding;

    ArrayList<PlayerslistItem> firstinningdata = new ArrayList<>();
    ArrayList<PlayerslistItem> secoundinningdata = new ArrayList<>();

    ArrayList<PlayerslistItem> allplayeradata = new ArrayList<>();

    public Adapterallplyer adapter, adpter1;

    public CountryFirstFragment(ArrayList<PlayerslistItem> firstinningdata) {
        this.allplayeradata = firstinningdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCountryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        this.binding.recyclerinningfirst.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        this.binding.recyclerinningsecound.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));


        for (int i = 0; i < allplayeradata.size(); i++) {
            if (allplayeradata.get(i).getInning() == 1) {
                firstinningdata.add(allplayeradata.get(i));
            }
            if (allplayeradata.get(i).getInning() == 2) {
                secoundinningdata.add(allplayeradata.get(i));
            }
        }
        if (firstinningdata.size() <= 0) {
            binding.topr.setVisibility(View.GONE);
        } else {
            adapter = new Adapterallplyer(getActivity(), firstinningdata);
            binding.recyclerinningfirst.setAdapter(adapter);
        }

        if (secoundinningdata.size() <= 0) {
            binding.twotop.setVisibility(View.GONE);
        } else {
            adpter1 = new Adapterallplyer(getActivity(), secoundinningdata);
            binding.recyclerinningsecound.setAdapter(adpter1);

        }

        binding.expande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.recyclerinningfirst.getVisibility() == View.VISIBLE) {
                    binding.recyclerinningfirst.setVisibility(View.GONE);
                    binding.expande.setRotation(180);
                    if (firstinningdata.size() <= 0) {
                        binding.nodatapage1.noDataLy.setVisibility(View.GONE);
                    }
                } else {
                    binding.recyclerinningfirst.setVisibility(View.VISIBLE);
                    if (firstinningdata.size() <= 0) {
                        binding.nodatapage1.noDataLy.setVisibility(View.VISIBLE);
                    }
                    binding.expande.setRotation(0);
                }
            }
        });

        binding.expande1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.recyclerinningsecound.getVisibility() == View.VISIBLE) {
                    binding.recyclerinningsecound.setVisibility(View.GONE);
                    if (secoundinningdata.size() <= 0) {
                        binding.nodatapage2.noDataLy.setVisibility(View.GONE);
                    }
                    binding.expande1.setRotation(180);
                } else {
                    binding.recyclerinningsecound.setVisibility(View.VISIBLE);
                    if (secoundinningdata.size() <= 0) {
                        binding.nodatapage2.noDataLy.setVisibility(View.VISIBLE);
                    }
                    binding.expande1.setRotation(0);
                }
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
