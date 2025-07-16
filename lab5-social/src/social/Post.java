package social;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    private String id;
    private String author;
    private String text;
    private Long timeStamp;


    public Post(){};
    public Post(String author, String text){
        this.author = author; this.text = text; this.timeStamp = System.currentTimeMillis();
        this.id = "POST_"+ author +System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

}
