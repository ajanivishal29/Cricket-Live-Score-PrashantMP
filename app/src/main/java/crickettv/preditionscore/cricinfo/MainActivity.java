package crickettv.preditionscore.cricinfo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.ads.NativeAdLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Fragments.MatchesFragment;
import crickettv.preditionscore.cricinfo.Fragments.NewsFragments;
import crickettv.preditionscore.cricinfo.Fragments.ResultFragment;
import crickettv.preditionscore.cricinfo.Fragments.UpcomingFragment;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.TemplateView;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.databinding.ActivityMainBinding;

public class MainActivity extends MainAdsActivity {

    private ActivityMainBinding binding;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public DataItem convertedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       /* BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_matches, R.id.navigation_upcoming, R.id.navigation_result, R.id.navigation_news)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);*/

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {

            // TODO: 11-09-2020  Native Ads
            checkNativeBannerAdsMode(admobsmallnative, native_banner_ad_container, q_native_banner);
            admob_nativebanner_load();
            admob_native_load();
        }

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, new MatchesFragment());
        transaction.commit();

        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_matches /*2131296844*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity.this).adintercheck(convertedObject, MainActivity.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new MatchesFragment());
                                }
                            });

                        } else {
                            setOnFragmentClick(new MatchesFragment());
                        }
                        break;
                    case R.id.navigation_upcoming /*2131296846*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity.this).adintercheck(convertedObject, MainActivity.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new UpcomingFragment());
                                }
                            });

                        } else {
                            setOnFragmentClick(new UpcomingFragment());
                        }
                        break;
                    case R.id.navigation_result /*2131296847*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity.this).adintercheck(convertedObject, MainActivity.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new ResultFragment());
                                }
                            });

                        } else {
                            setOnFragmentClick(new ResultFragment());
                        }
                        break;
                    case R.id.navigation_news /*2131296849*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity.this).adintercheck(convertedObject, MainActivity.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new NewsFragments());
                                }
                            });

                        } else {
                            setOnFragmentClick(new NewsFragments());
                        }
                        break;
                    default:

                        break;
                }
                return true;
            }
        });

        binding.header.title.setText(R.string.app_name);
        binding.header.toolBack.setVisibility(View.GONE);
    }

    public void setOnFragmentClick(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        if (binding.navView.getSelectedItemId() == R.id.navigation_matches) {
            if (convertedObject != null) {
                AppManage.getInstance(MainActivity.this).adBackpressintercheck(convertedObject, MainActivity.this, new AppManage.onAdIntent() {
                    @Override
                    public void onintentscreen() {
                        finish();
                    }
                });
            } else {
                finish();
            }
        } else {
            binding.navView.setSelectedItemId(R.id.navigation_matches);
        }
    }
}