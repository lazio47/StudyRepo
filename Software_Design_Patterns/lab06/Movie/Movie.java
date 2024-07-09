 // to complete
import java.util.List;

public class Movie {
   private String title;
   private int year;
   private Person director;
   private Person writer;
   private String series;
   private List<Person> cast;
   private List<Place> locations;
   private List<String> languages;
   private List<String> genres;
   private boolean isTelevision;
   private boolean isNetflix;
   private boolean isIndependent;

   public Movie(){};

   public Movie(
      final String movieTitle,
      final int movieYear,
      final Person movieDirector,
      final Person movieWriter,
      final String movieSeries,
      final List<Person> movieCast,
      final List<Place> movieLocations,
      final List<String> movieLanguages,
      final List<String> movieGenres,
      final boolean television,
      final boolean netflix,
      final boolean independent)    {
      this.title = movieTitle;
      this.year = movieYear;
      this.director = movieDirector;
      this.writer = movieWriter;
      this.series = movieSeries;
      this.cast = movieCast;
      this.locations = movieLocations;
      this.languages = movieLanguages;
      this.genres = movieGenres;
      this.isTelevision = television;
      this.isNetflix = netflix;
      this.isIndependent = independent;
   }

   @Override
   public String toString() {
      return "Movie [title=" + title + ", year=" + year + ", director=" + director + ", writer=" + writer + ", series="
            + series + ", cast=" + cast + ", locations=" + locations + ", languages=" + languages + ", genres=" + genres
            + ", isTelevision=" + isTelevision + ", isNetflix=" + isNetflix + ", isIndependent=" + isIndependent + "]";
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public void setDirector(Person director) {
      this.director = director;
   }

   public void setWriter(Person writer) {
      this.writer = writer;
   }

   public void setSeries(String series) {
      this.series = series;
   }

   public void setCast(List<Person> cast) {
      this.cast = cast;
   }

   public void setLocations(List<Place> locations) {
      this.locations = locations;
   }

   public void setLanguages(List<String> languages) {
      this.languages = languages;
   }

   public void setGenres(List<String> genres) {
      this.genres = genres;
   }

   public void setTelevision(boolean isTelevision) {
      this.isTelevision = isTelevision;
   }

   public void setNetflix(boolean isNetflix) {
      this.isNetflix = isNetflix;
   }

   public void setIndependent(boolean isIndependent) {
      this.isIndependent = isIndependent;
   }
}
