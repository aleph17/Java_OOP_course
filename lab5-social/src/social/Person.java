package social;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
class Person {
  @Id
  private String code;
  private String name;
  private String surname;
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Person> friends = new LinkedList<>();
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Group> groups = new LinkedList<>();
  @OneToMany(fetch = FetchType.EAGER)
  private List<Post> posts = new LinkedList<>();

  public Person() {
    // default constructor is needed by JPA
  }

  Person(String code, String name, String surname) {
    this.code = code;
    this.name = name;
    this.surname = surname;
  }

  String getCode() {
    return code;
  }

  String getName() {
    return name;
  }

  String getSurname() {
    return surname;
  }

  void addFriend(Person p){
    friends.add(p);
  }
  List<Person> retrieveFriends(){
    return friends;
  }
  void addGroup(Group g){
    if(groups==null) groups = new LinkedList<>();
    groups.add(g);
  }
  void removeGroup(Group g){
    groups.remove(g);
  }
  List<Group> retrieveGroups(){
    if(groups==null) groups = new LinkedList<>();
    return groups;
  }
  void addPost(Post p){
    posts.add(p);
  }
  List<Post> retreivePosts(){
    return posts;
  }
  //....
}
