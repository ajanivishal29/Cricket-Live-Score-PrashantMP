package crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

public class NewsListItem{

    @SerializedName("URLToImage")
    private String uRLToImage;

    @SerializedName("author")
    private String author;

    @SerializedName("description")
    private String description;

    @SerializedName("title")
    private String title;

    @SerializedName("URL")
    private String uRL;

    @SerializedName("content")
    private String content;

    @SerializedName("PublishedAT")
    private String publishedAT;

    public void setURLToImage(String uRLToImage){
        this.uRLToImage = uRLToImage;
    }

    public String getURLToImage(){
        return uRLToImage;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setURL(String uRL){
        this.uRL = uRL;
    }

    public String getURL(){
        return uRL;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setPublishedAT(String publishedAT){
        this.publishedAT = publishedAT;
    }

    public String getPublishedAT(){
        return publishedAT;
    }
}