package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MatchResultResponce{

    @SerializedName("msg")
    private String msg;

    @SerializedName("Playerslist")
    private Object playerslist;

    @SerializedName("success")
    private boolean success;

    @SerializedName("AllMatch")
    private List<AllMatchItem> allMatch;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getPlayerslist() {
        return playerslist;
    }

    public void setPlayerslist(Object playerslist) {
        this.playerslist = playerslist;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<AllMatchItem> getAllMatch() {
        return allMatch;
    }

    public void setAllMatch(List<AllMatchItem> allMatch) {
        this.allMatch = allMatch;
    }
}