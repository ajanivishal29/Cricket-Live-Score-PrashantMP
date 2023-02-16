package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CrickapiResponce {

	@SerializedName("msg")
	private String msg;

	@SerializedName("Playerslist")
	private Object playerslist;

	@SerializedName("success")
	private boolean success;

	@SerializedName("AllMatch")
	private List<AllMatchItem> allMatch;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setPlayerslist(Object playerslist){
		this.playerslist = playerslist;
	}

	public Object getPlayerslist(){
		return playerslist;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setAllMatch(List<AllMatchItem> allMatch){
		this.allMatch = allMatch;
	}

	public List<AllMatchItem> getAllMatch(){
		return allMatch;
	}
}