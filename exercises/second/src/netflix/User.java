package netflix;

import java.util.Collection;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class User {
    private String username;
    private int id;
    private Queue<Movie> watchList;

    public User(String username, int id){
        this.id = id;
        this.username = username;
        this.watchList = new PriorityQueue<>();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void printWatchList(){
        System.out.println(watchList);
    }

    public void addToWatchLIst(Collection<Movie> toWatch){
        watchList.addAll(toWatch);
    }

//    public Movie watchNewMovie(){
//         return watchList.poll();
//    }
    public Optional<Movie> watchNewMovie(){
        return Optional.ofNullable(watchList.poll());
    }

    @Override
    public String toString(){
        return "User [username="+username+" ,id="+id+"]";
    }
}
