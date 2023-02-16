package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NewsdataResponce{

    @SerializedName("msg")
    private String msg;

    @SerializedName("success")
    private boolean success;

    @SerializedName("NewsList")
    private List<NewsListItem> newsList;

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

    public void setNewsList(List<NewsListItem> newsList){
        this.newsList = newsList;
    }

    public List<NewsListItem> getNewsList(){
        return newsList;
    }
}