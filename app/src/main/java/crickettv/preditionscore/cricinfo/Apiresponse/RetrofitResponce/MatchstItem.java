package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

public class MatchstItem{

    @SerializedName("subdate")
    private String subdate;

    @SerializedName("overs")
    private String overs;

    @SerializedName("MrateB")
    private String mrateB;

    @SerializedName("favourite")
    private String favourite;

    @SerializedName("MrateA")
    private String mrateA;

    @SerializedName("SessionA")
    private String sessionA;

    @SerializedName("Score")
    private String score;

    @SerializedName("isfirstinning")
    private String isfirstinning;

    @SerializedName("SessionB")
    private String sessionB;

    @SerializedName("MatchId")
    private int matchId;

    @SerializedName("Battingteam")
    private String battingteam;

    @SerializedName("id")
    private int id;

    @SerializedName("wickets")
    private String wickets;

    public void setSubdate(String subdate){
        this.subdate = subdate;
    }

    public String getSubdate(){
        return subdate;
    }

    public void setOvers(String overs){
        this.overs = overs;
    }

    public String getOvers(){
        return overs;
    }

    public void setMrateB(String mrateB){
        this.mrateB = mrateB;
    }

    public String getMrateB(){
        return mrateB;
    }

    public void setFavourite(String favourite){
        this.favourite = favourite;
    }

    public String getFavourite(){
        return favourite;
    }

    public void setMrateA(String mrateA){
        this.mrateA = mrateA;
    }

    public String getMrateA(){
        return mrateA;
    }

    public void setSessionA(String sessionA){
        this.sessionA = sessionA;
    }

    public String getSessionA(){
        return sessionA;
    }

    public void setScore(String score){
        this.score = score;
    }

    public String getScore(){
        return score;
    }

    public void setIsfirstinning(String isfirstinning){
        this.isfirstinning = isfirstinning;
    }

    public String getIsfirstinning(){
        return isfirstinning;
    }

    public void setSessionB(String sessionB){
        this.sessionB = sessionB;
    }

    public String getSessionB(){
        return sessionB;
    }

    public void setMatchId(int matchId){
        this.matchId = matchId;
    }

    public int getMatchId(){
        return matchId;
    }

    public void setBattingteam(String battingteam){
        this.battingteam = battingteam;
    }

    public String getBattingteam(){
        return battingteam;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setWickets(String wickets){
        this.wickets = wickets;
    }

    public String getWickets(){
        return wickets;
    }
}