package netflix;

import java.util.*;
import java.util.Map.Entry;

public class App {
    public static void main(String[] Args) throws Exception{
        Set<Movie> movieSet = new HashSet<>();
        movieSet.add(new Movie("Oppenhaimer", "2023", "Nolan"));
        movieSet.add(new Movie("Barbie", "2024", "Gerwig"));
        movieSet.add(new Movie("Kill bill 1","2003","Tarantino"));
//        System.out.println("now - "+movieSet.size());
//        movieSet.forEach(System.out::println);

        Map<Integer, User> users = new HashMap<>();
        users.put(1, new User("crocodile", 1));
        users.put(23, new User("savur", 23));
        users.put(24, new User("giraffe", 24));

        Map<String, Movie> movieMap = new HashMap<>();
        for(Movie m:movieSet)
             movieMap.put(m.getDirector()+":"+m.getTitle(), m);
        movieMap.get("Nolan:Oppenhaimer").addReview(new Review("crocodile", "off", 5, "04/04/2025"));
//
//        for(Movie m:movieSet)
//             m.printReviews();

//        for(String id:movieMap.keySet())
//            System.out.println(movieMap.get(id));
        for(Entry<String, Movie> entry: movieMap.entrySet())
            System.out.println(entry.getKey()+" "+entry.getValue());
//        movieMap.forEach((id,m)->System.out.println(id+" "+m));
//        movieMap.get("Nolan:Oppenhaimer").addReview(new Review("savur", "too bad", 2, "24/12/2025"));
//        movieMap.forEach((id, m)-> System.out.println(m.getTitle()+" has "+m.getAvgRating()));
//        Collection<Movie> toWatch = movieMap.values();
//        users.get(1).addToWatchLIst(toWatch);
//        users.get(1).printWatchList();
//        System.out.println(users.get(23).watchNewMovie().getTitle());
        Optional<Movie> next = users.get(23).watchNewMovie();
        if(next.isPresent()) {
            Movie m=next.get();
            System.out.println(m.getTitle());
        }
        else
            System.out.println("no movies");
        Movie m23 = next.orElse(new Movie("None", "0000", "NaN"));
        System.out.println(m23.getTitle());
    }
}
