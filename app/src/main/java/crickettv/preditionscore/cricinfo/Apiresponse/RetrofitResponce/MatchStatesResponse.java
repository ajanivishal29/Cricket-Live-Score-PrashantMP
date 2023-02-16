package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MatchStatesResponse{

    @SerializedName("msg")
    private String msg;

    @SerializedName("success")
    private boolean success;

    @SerializedName("Matchst")
    private List<MatchstStateItem> matchstState;

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setMatchstState(List<MatchstStateItem> matchstState){
        this.matchstState = matchstState;
    }

    public List<MatchstStateItem> getMatchstState(){
        return matchstState;
    }
}