package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AllplyerResponce{

    @SerializedName("msg")
    private String msg;

    @SerializedName("Playerslist")
    private List<PlayerslistItem> playerslist;

    @SerializedName("success")
    private boolean success;

    @SerializedName("AllMatch")
    private Object allMatch;

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setPlayerslist(List<PlayerslistItem> playerslist){
        this.playerslist = playerslist;
    }

    public List<PlayerslistItem> getPlayerslist(){
        return playerslist;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setAllMatch(Object allMatch){
        this.allMatch = allMatch;
    }

    public Object getAllMatch(){
        return allMatch;
    }
}