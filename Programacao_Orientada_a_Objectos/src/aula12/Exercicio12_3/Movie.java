package aula12.Exercicio12_3;

public class Movie {
    private String name;
    private Double score;
    private String rating;
    private String genre;
    private Double runningTime;
    public Movie(String name, Double score, String rating, String genre, Double runningTime) {
        this.name = name;
        this.score = score;
        this.rating = rating;
        this.genre = genre;
        this.runningTime = runningTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Double getRunningTime() {
        return runningTime;
    }
    public void setRunningTime(Double runningTime) {
        this.runningTime = runningTime;
    }
    @Override
    public String toString() {
        return name + ", score: " + score + ", rating: " + rating + ", genre: " + genre + ", runningTime: "
                + runningTime;
    }

}
