package crickettv.preditionscore.cricinfo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import crickettv.preditionscore.cricinfo.databinding.FragmentCommonViewpagerBinding;

public class MatchStatesFragment extends androidx.fragment.app.Fragment {

    FragmentCommonViewpagerBinding binding;

    String websrtring;
    public MatchStatesFragment(String stat1descr) {
        websrtring = stat1descr;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCommonViewpagerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


            if (websrtring != null) {
                this.websrtring = "<!DOCTYPE html><head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> <html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=windows-1250\"><meta name=\"spanish press\" content=\"spain, spanish newspaper, news,economy,politics,sports\"><title></title></head><body id=\"body\">"+this.websrtring+ "</body></html>";
                this.binding.webViewStats.setWebViewClient(new WebViewClient());
                this.binding.webViewStats.clearCache(true);
                this.binding.webViewStats.clearHistory();
                this.binding.webViewStats.getSettings().setJavaScriptEnabled(true);
                this.binding.webViewStats.setHorizontalScrollBarEnabled(false);
                this.binding.webViewStats.loadData(this.websrtring, "text/html", "UTF-8");
            }

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
