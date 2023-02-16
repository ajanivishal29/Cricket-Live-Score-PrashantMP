package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

public class MatchesliveResponceItem{

    @SerializedName("venue")
    private String venue;

    @SerializedName("jsondata")
    private String jsondata;

    @SerializedName("TeamAImage")
    private String teamAImage;

    @SerializedName("jsonruns")
    private String jsonruns;

    @SerializedName("Title")
    private String title;

    @SerializedName("isfinished")
    private int isfinished;

    @SerializedName("TeamB")
    private String teamB;

    @SerializedName("Matchtime")
    private String matchtime;

    @SerializedName("ispriority")
    private int ispriority;

    @SerializedName("TeamA")
    private String teamA;

    @SerializedName("ImgeURL")
    private String imgeURL;

    @SerializedName("seriesid")
    private int seriesid;

    @SerializedName("Result")
    private String result;

    @SerializedName("MatchDate")
    private String matchDate;

    @SerializedName("MatchType")
    private String matchType;

    @SerializedName("MatchId")
    private int matchId;

    @SerializedName("Appversion")
    private Object appversion;

    @SerializedName("TeamBImage")
    private String teamBImage;

    public void setVenue(String venue){
        this.venue = venue;
    }

    public String getVenue(){
        return venue;
    }

    public void setJsondata(String jsondata){
        this.jsondata = jsondata;
    }

    public String getJsondata(){
        return jsondata;
    }

    public void setTeamAImage(String teamAImage){
        this.teamAImage = teamAImage;
    }

    public String getTeamAImage(){
        return teamAImage;
    }

    public void setJsonruns(String jsonruns){
        this.jsonruns = jsonruns;
    }

    public String getJsonruns(){
        return jsonruns;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setIsfinished(int isfinished){
        this.isfinished = isfinished;
    }

    public int getIsfinished(){
        return isfinished;
    }

    public void setTeamB(String teamB){
        this.teamB = teamB;
    }

    public String getTeamB(){
        return teamB;
    }

    public void setMatchtime(String matchtime){
        this.matchtime = matchtime;
    }

    public String getMatchtime(){
        return matchtime;
    }

    public void setIspriority(int ispriority){
        this.ispriority = ispriority;
    }

    public int getIspriority(){
        return ispriority;
    }

    public void setTeamA(String teamA){
        this.teamA = teamA;
    }

    public String getTeamA(){
        return teamA;
    }

    public void setImgeURL(String imgeURL){
        this.imgeURL = imgeURL;
    }

    public String getImgeURL(){
        return imgeURL;
    }

    public void setSeriesid(int seriesid){
        this.seriesid = seriesid;
    }

    public int getSeriesid(){
        return seriesid;
    }

    public void setResult(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }

    public void setMatchDate(String matchDate){
        this.matchDate = matchDate;
    }

    public String getMatchDate(){
        return matchDate;
    }

    public void setMatchType(String matchType){
        this.matchType = matchType;
    }

    public String getMatchType(){
        return matchType;
    }

    public void setMatchId(int matchId){
        this.matchId = matchId;
    }

    public int getMatchId(){
        return matchId;
    }

    public void setAppversion(Object appversion){
        this.appversion = appversion;
    }

    public Object getAppversion(){
        return appversion;
    }

    public void setTeamBImage(String teamBImage){
        this.teamBImage = teamBImage;
    }

    public String getTeamBImage(){
        return teamBImage;
    }
}