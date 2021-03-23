package com.coderx.assignment02;

public class Movie {
    private int id;
    private String title;
    private int year;
    private String director;
    private String actors;
    private int rating;
    private String review;
    private boolean isFav;

    public Movie(int id, String title, int year, String director, String actors, int rating, String review, boolean isFav) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.review = review;
        this.isFav = isFav;
    }

    public Movie(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    /*for testing purposes*/
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", isFav=" + isFav +
                '}';
    }
}
