package netflix;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Movie implements Comparable<Movie> {
    private String title;
    private String releseaYear;
    private String director;
    private List<Review> reviews;

    public Movie(String title, String releseaYear, String director){
        this.title = title;
        this.releseaYear = releseaYear;
        this.director = director;
        this.reviews = new LinkedList<>();
    }

    public String getTitle(){
        return this.title;
    }
    public String getReleseaYear(){
        return releseaYear;
    }
    public String getDirector(){
        return director;
    }

    public void addReview(Review r){
        reviews.add(r);
    }
    public void printReviews(){
        reviews.forEach(System.out::println);
    }

    @Override
    public String toString(){
        return "Movie [title="+title+", releaseYear="+releseaYear+", director="+director+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director);
    }

    public Float getAvgRating(){
        float total = 0;
        for (Review r:reviews)
            total +=r.getScore();
        if (reviews.size() ==0) return (float) -1.0;
        return Float.valueOf(total/reviews.size());
    }

    @Override
    public int compareTo(Movie o) {
        return this.releseaYear.compareTo(o.releseaYear);
    }
}
