import java.util.List;

public class MovieBuilder {
    private Movie m = new Movie();

    public MovieBuilder setTitle(String title){
        m.setTitle(title);
        return this;
    }

    public MovieBuilder setYear(int year) {
        m.setYear(year);;
        return this;
    }

    public MovieBuilder setDirector(Person director) {
        m.setDirector(director);;
        return this;
    }

    public MovieBuilder setWriter(Person writer) {
        m.setWriter(writer);;
        return this;
    }

    public MovieBuilder setSeries(String series) {
        m.setSeries(series);;
        return this;
    }

    public MovieBuilder setCast(List<Person> cast) {
        m.setCast(cast);;
        return this;
    }

    public MovieBuilder setLocations(List<Place> locations) {
        m.setLocations(locations);;
        return this;
    }

    public MovieBuilder setLanguages(List<String> languages) {
        m.setLanguages(languages);;
        return this;
    }

    public MovieBuilder setGenres(List<String> genres) {
        m.setGenres(genres);;
        return this;
    }

    public MovieBuilder setTelevision(boolean isTelevision) {
        m.setTelevision(isTelevision);;
        return this;
    }

    public MovieBuilder setNetflix(boolean isNetflix) {
        m.setNetflix(isNetflix);;
        return this;
    }

    public MovieBuilder setIndependent(boolean isIndependent) {
        m.setIndependent(isIndependent);;
        return this;
    }

    public Movie build(){
        return m;
    }
}
