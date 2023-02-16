package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

public class AllMatchItem {

	@SerializedName("TeamAImage")
	private String teamAImage;

	@SerializedName("Venue")
	private String venue;

	@SerializedName("Matchtype")
	private String matchtype;

	@SerializedName("MatchId")
	private int matchId;

	@SerializedName("Title")
	private String title;

	@SerializedName("ImageUrl")
	private String imageUrl;

	@SerializedName("TeamB")
	private String teamB;

	@SerializedName("Matchtime")
	private String matchtime;

	@SerializedName("TeamA")
	private String teamA;

	@SerializedName("TeamBImage")
	private String teamBImage;

	@SerializedName("Result")
	private String result;

	public void setTeamAImage(String teamAImage){
		this.teamAImage = teamAImage;
	}

	public String getTeamAImage(){
		return teamAImage;
	}

	public void setVenue(String venue){
		this.venue = venue;
	}

	public String getVenue(){
		return venue;
	}

	public void setMatchtype(String matchtype){
		this.matchtype = matchtype;
	}

	public String getMatchtype(){
		return matchtype;
	}

	public void setMatchId(int matchId){
		this.matchId = matchId;
	}

	public int getMatchId(){
		return matchId;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setTeamB(String teamB){
		this.teamB = teamB;
	}

	public String getTeamB(){
		return teamB;
	}

	public void setMatchtime(String matchtime){
		this.matchtime = matchtime;
	}

	public String getMatchtime(){
		return matchtime;
	}

	public void setTeamA(String teamA){
		this.teamA = teamA;
	}

	public String getTeamA(){
		return teamA;
	}

	public void setTeamBImage(String teamBImage){
		this.teamBImage = teamBImage;
	}

	public String getTeamBImage(){
		return teamBImage;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}
}