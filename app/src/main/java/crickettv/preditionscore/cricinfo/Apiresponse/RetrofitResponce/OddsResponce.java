package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OddsResponce{

    @SerializedName("msg")
    private String msg;

    @SerializedName("Matchst")
    private List<MatchstItem> matchst;

    @SerializedName("success")
    private boolean success;

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setMatchst(List<MatchstItem> matchst){
        this.matchst = matchst;
    }

    public List<MatchstItem> getMatchst(){
        return matchst;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return success;
    }
}