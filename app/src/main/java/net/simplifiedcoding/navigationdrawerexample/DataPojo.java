package net.simplifiedcoding.navigationdrawerexample;

/**
 * Created by owner on 12/18/2016.
 */

public class DataPojo {
    private int likes,favorites;
    private String previewURL,webformatURL;

    public DataPojo() {
    }

    public DataPojo(int likes, int favorites, String previewURL, String webformatURL) {

        this.likes = likes;
        this.favorites = favorites;
        this.previewURL = previewURL;
        this.webformatURL = webformatURL;
    }



    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getpreviewURL() {
        return previewURL;
    }

    public void setpreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }
}
