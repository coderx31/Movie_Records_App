package com.coderx.assignment02;
/*model to store data that getting from API*/
public class ApiMovieModel {
    private String id;  // imdb id
    private String title;  // title
    private float rating; // rate in floating point number
    private String image;

    public ApiMovieModel(String id, String title, float rating, String image){
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // for debug purposes


    @Override
    public String toString() {
        return "ApiMovieModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", image='" + image + '\'' +
                '}';
    }
}
