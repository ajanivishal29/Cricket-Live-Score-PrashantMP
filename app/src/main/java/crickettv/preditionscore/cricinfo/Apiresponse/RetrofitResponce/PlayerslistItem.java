package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

public class PlayerslistItem{

    @SerializedName("TeamRuns")
    private String teamRuns;

    @SerializedName("six")
    private int six;

    @SerializedName("seqno")
    private int seqno;

    @SerializedName("isnotout")
    private int isnotout;

    @SerializedName("TeamName")
    private String teamName;

    @SerializedName("Runs")
    private int runs;

    @SerializedName("TeamSide")
    private String teamSide;

    @SerializedName("four")
    private int four;

    @SerializedName("outby")
    private String outby;

    @SerializedName("PlayerName")
    private String playerName;

    @SerializedName("MatchId")
    private int matchId;

    @SerializedName("inning")
    private int inning;

    @SerializedName("Balls")
    private int balls;

    @SerializedName("PlayerImage")
    private String playerImage;

    public void setTeamRuns(String teamRuns){
        this.teamRuns = teamRuns;
    }

    public String getTeamRuns(){
        return teamRuns;
    }

    public void setSix(int six){
        this.six = six;
    }

    public int getSix(){
        return six;
    }

    public void setSeqno(int seqno){
        this.seqno = seqno;
    }

    public int getSeqno(){
        return seqno;
    }

    public void setIsnotout(int isnotout){
        this.isnotout = isnotout;
    }

    public int getIsnotout(){
        return isnotout;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setRuns(int runs){
        this.runs = runs;
    }

    public int getRuns(){
        return runs;
    }

    public void setTeamSide(String teamSide){
        this.teamSide = teamSide;
    }

    public String getTeamSide(){
        return teamSide;
    }

    public void setFour(int four){
        this.four = four;
    }

    public int getFour(){
        return four;
    }

    public void setOutby(String outby){
        this.outby = outby;
    }

    public String getOutby(){
        return outby;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setMatchId(int matchId){
        this.matchId = matchId;
    }

    public int getMatchId(){
        return matchId;
    }

    public void setInning(int inning){
        this.inning = inning;
    }

    public int getInning(){
        return inning;
    }

    public void setBalls(int balls){
        this.balls = balls;
    }

    public int getBalls(){
        return balls;
    }

    public void setPlayerImage(String playerImage){
        this.playerImage = playerImage;
    }

    public String getPlayerImage(){
        return playerImage;
    }
}