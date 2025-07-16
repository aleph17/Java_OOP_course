package social;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "socialGroup")
public class Group {
    @Id
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Person> members = new LinkedList<>();

    public Group(){
    }
    public Group(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void addMember(Person p){
        members.add(p);
    }
    public List<Person> retMember(){
        return members;
    }
}
