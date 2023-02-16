package crickettv.preditionscore.cricinfo.Apiresponse;

import com.google.gson.annotations.SerializedName;

public class JsonRuns{

    @SerializedName("summary")
    public String summary;

    @SerializedName("stat")
    public String stat;

    @SerializedName("sessionB")
    public String sessionB;

    @SerializedName("sessionA")
    public String sessionA;

    @SerializedName("rateA")
    public String rateA;

    @SerializedName("rateB")
    public String rateB;

    @SerializedName("runxb")
    public String runxb;

    @SerializedName("runxa")
    public String runxa;

    @SerializedName("fav")
    public String fav;

    @SerializedName("sessionOver")
    public String sessionOver;
}