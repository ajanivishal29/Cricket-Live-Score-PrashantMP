package crickettv.preditionscore.cricinfo.retrofit;


import java.util.ArrayList;
import java.util.Map;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AdListResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AllplyerResponce;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.CrickapiResponce;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.LocaladsResponce;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchResultResponce;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchStatesResponse;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchesliveResponceItem;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.NewsdataResponce;
import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.OddsResponce;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface RetrofitInterface {

    @POST("adservice/get_Prashant_MP.php")
    @FormUrlEncoded
    Call<AdListResponse> getadsdetail(@Field("packagename") String packagename);

    @POST("localadservice/updatedownloadcount.php")
    @FormUrlEncoded
    Call<Object> updatecounter(@Field("packagename") String packagename);

    @POST("localadservice/get_ShanTech_LocalAds.php")
    @FormUrlEncoded
    Call<LocaladsResponce> localads(@Field("packagename") String packagename);

    @GET("upcomingMatches")
    Call<CrickapiResponce> cricupcoming();

    @GET("LiveLine")
    Call<ArrayList<MatchesliveResponceItem>> cricklivematch();

    @POST("MatchResults")
    Call<MatchResultResponce> crickmatchResult(@Body Map<String,String> jsonObject);

    @POST("LiveLine_Match")
    Call<ArrayList<MatchesliveResponceItem>> cricketlivematchdetail(@Body Map<String,String> jsonObject);

    @POST("MatchOdds")
    Call<OddsResponce> crickoddstab(@Body Map<String,String> jsonObject);

    @POST("GetAllPlayers")
    Call<AllplyerResponce> playerscorecarddata(@Body Map<String,String> jsonObject);

    @POST("MatchStats")
    Call<MatchStatesResponse> Matchstatdata(@Body Map<String,String> jsonObject);

    @GET("SportsNews")
    Call<NewsdataResponce> Newsdata();

}
