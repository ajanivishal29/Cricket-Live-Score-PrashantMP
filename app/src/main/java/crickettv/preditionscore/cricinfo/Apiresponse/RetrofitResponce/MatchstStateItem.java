package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

public class MatchstStateItem{

    @SerializedName("stat2name")
    private String stat2name;

    @SerializedName("stat1name")
    private String stat1name;

    @SerializedName("matchname")
    private String matchname;

    @SerializedName("stat1descr")
    private String stat1descr;

    @SerializedName("MatchId")
    private int matchId;

    @SerializedName("stat2descr")
    private String stat2descr;

    @SerializedName("stat3name")
    private String stat3name;

    @SerializedName("stat3descr")
    private String stat3descr;

    public void setStat2name(String stat2name){
        this.stat2name = stat2name;
    }

    public String getStat2name(){
        return stat2name;
    }

    public void setStat1name(String stat1name){
        this.stat1name = stat1name;
    }

    public String getStat1name(){
        return stat1name;
    }

    public void setMatchname(String matchname){
        this.matchname = matchname;
    }

    public String getMatchname(){
        return matchname;
    }

    public void setStat1descr(String stat1descr){
        this.stat1descr = stat1descr;
    }

    public String getStat1descr(){
        return stat1descr;
    }

    public void setMatchId(int matchId){
        this.matchId = matchId;
    }

    public int getMatchId(){
        return matchId;
    }

    public void setStat2descr(String stat2descr){
        this.stat2descr = stat2descr;
    }

    public String getStat2descr(){
        return stat2descr;
    }

    public void setStat3name(String stat3name){
        this.stat3name = stat3name;
    }

    public String getStat3name(){
        return stat3name;
    }

    public void setStat3descr(String stat3descr){
        this.stat3descr = stat3descr;
    }

    public String getStat3descr(){
        return stat3descr;
    }
}