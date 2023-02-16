package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocaladsResponce{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private int success;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}
}