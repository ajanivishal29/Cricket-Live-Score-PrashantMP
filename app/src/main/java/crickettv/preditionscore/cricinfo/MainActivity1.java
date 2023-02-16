package crickettv.preditionscore.cricinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.DataItem;
import crickettv.preditionscore.cricinfo.Fragments.LivelineFragments;
import crickettv.preditionscore.cricinfo.Fragments.MatchoddsFragments;
import crickettv.preditionscore.cricinfo.Fragments.ScorecardFragments;
import crickettv.preditionscore.cricinfo.Fragments.StatsFragments;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.AppManage;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.MainAdsActivity;
import crickettv.preditionscore.cricinfo.Main_Ads.admob_ads.Utils;
import crickettv.preditionscore.cricinfo.databinding.ActivityMain1Binding;

public class MainActivity1 extends MainAdsActivity {

    private ActivityMain1Binding binding;

    public static String MatchId;
    public static String Match;
    public static String MatchType;
    public static String MatchT;
    public DataItem convertedObject;
    public String str;
    public Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MatchId = getIntent().getStringExtra("MatchId");
        Match = getIntent().getStringExtra("Match");
        MatchType = getIntent().getStringExtra("MatchType");
        MatchT = getIntent().getStringExtra("MatchT");

        convertedObject = Utils.getResponse(this);

        binding.header.title.setText(Match);
        binding.header.headerView.setVisibility(View.GONE);
        binding.header.toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Log.e("check4532", MainActivity1.MatchId);

        if (this.MatchType.equals("Live")) {
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, new LivelineFragments());
            transaction.commit();


        } else {
            binding.bottomNavigation.getMenu().removeItem(R.id.navigation_home);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, new ScorecardFragments());
            transaction.commit();

        }


        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home /*2131296844*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity1.this).adintercheck(convertedObject, MainActivity1.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new LivelineFragments());
                                }
                            });

                        } else {
                            setOnFragmentClick(new LivelineFragments());
                        }
                        break;
                    case R.id.navigation_odds /*2131296846*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity1.this).adintercheck(convertedObject, MainActivity1.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new MatchoddsFragments());
                                }
                            });

                        } else {
                            setOnFragmentClick(new MatchoddsFragments());
                        }
                        break;
                    case R.id.navigation_playing11 /*2131296847*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity1.this).adintercheck(convertedObject, MainActivity1.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new ScorecardFragments());
                                }
                            });

                        } else {
                            setOnFragmentClick(new ScorecardFragments());
                        }
                        break;
                    case R.id.navigation_stats /*2131296849*/:
                        if (convertedObject != null) {
                            AppManage.getInstance(MainActivity1.this).adintercheck(convertedObject, MainActivity1.this, convertedObject.getFbinter2(), new AppManage.onAdIntent() {
                                @Override
                                public void onintentscreen() {
                                    setOnFragmentClick(new StatsFragments());
                                }
                            });

                        } else {
                            setOnFragmentClick(new StatsFragments());
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void setOnFragmentClick(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

    }

    @Override
    public void onBackPressed() {
            if (convertedObject != null) {
                AppManage.getInstance(MainActivity1.this).adBackpressintercheck(convertedObject, MainActivity1.this, new AppManage.onAdIntent() {
                    @Override
                    public void onintentscreen() {
                        finish();
                    }
                });
            } else {
                finish();
            }
    }
}