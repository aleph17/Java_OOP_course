package social;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Facade class for the social network system.
 * 
 */
public class Social {

  private final PersonRepository personRepository = new PersonRepository();
  private final GroupRepository groupRepository = new GroupRepository();
  private final PostRepository postRepository = new PostRepository();
  
  /**
   * Creates a new account for a person
   * 
   * @param code    nickname of the account
   * @param name    first name
   * @param surname last name
   * @throws PersonExistsException in case of duplicate code
   */
  public void addPerson(String code, String name, String surname) throws PersonExistsException {
    if (personRepository.findById(code).isPresent())    // check if db already contains the code
        throw new PersonExistsException();

    Person person = new Person(code, name, surname);    // create the person as a POJO
    personRepository.save(person);                      // save it to db
  }

  /**
   * Retrieves information about the person given their account code.
   * The info consists in name and surname of the person, in order, separated by
   * blanks.
   * 
   * @param code account code
   * @return the information of the person
   * @throws NoSuchCodeException if a person with that code does not exist
   */
  public String getPerson(String code) throws NoSuchCodeException {
    Optional<Person> opt = personRepository.findById(code);
    if (opt.isEmpty())  // check if db already contains the code
      throw new NoSuchCodeException();
    Person p = opt.get();
    return code+" "+p.getName()+" "+p.getSurname(); // TO BE IMPLEMENTED
  }

  /**
   * Define a friendship relationship between two persons given their codes.
   * <p>
   * Friendship is bidirectional: if person A is adding as friend person B, that means
   * that person B automatically adds as friend person A.
   * 
   * @param codePerson1 first person code
   * @param codePerson2 second person code
   * @throws NoSuchCodeException in case either code does not exist
   */
  public void addFriendship(String codePerson1, String codePerson2) throws NoSuchCodeException {
    Optional<Person> opt1 = personRepository.findById(codePerson1);
    Optional<Person> opt2 = personRepository.findById(codePerson2);
    if(opt1.isEmpty() || opt2.isEmpty())
      throw new NoSuchCodeException();

    Person p1 = opt1.get();
    Person p2 = opt2.get();
    p1.addFriend(p2);
    p2.addFriend(p1);

    personRepository.update(p1); // persist the updated state
    personRepository.update(p2);
  }

  /**
   * Retrieve the collection of their friends given the code of a person.
   *
   * @param codePerson code of the person
   * @return the list of person codes
   * @throws NoSuchCodeException in case the code does not exist
   */
  public Collection<String> listOfFriends(String codePerson) throws NoSuchCodeException {
    Optional<Person> opt = personRepository.findById(codePerson);
    if (opt.isEmpty())   // check if db already contains the code
      throw new NoSuchCodeException();
    Person p = opt.get();

    List<String> friends = new ArrayList<>();
    for(Person f: p.retrieveFriends()) friends.add(f.getCode());

    return friends;
  }

  /**
   * Creates a new group with the given name
   * 
   * @param groupName name of the group
   * @throws GroupExistsException if a group with given name does not exist
   */
  public void addGroup(String groupName) throws GroupExistsException {
    if (groupRepository.findById(groupName).isPresent())    // check if db already contains the code
      throw new GroupExistsException();

    Group gr = new Group(groupName);    // create the person as a POJO
    groupRepository.save(gr);
  }

  /**
   * Deletes the group with the given name
   * 
   * @param groupName name of the group
   * @throws NoSuchCodeException if a group with given name does not exist
   */
  public void deleteGroup(String groupName) throws NoSuchCodeException {
    Optional<Group> opt = groupRepository.findById(groupName);
    if (opt.isEmpty())    // check if db already contains the code
      throw new NoSuchCodeException();
    Group g = opt.get();
    groupRepository.delete(g);
  }

  /**
   * Modifies the group name
   * 
   * @param groupName name of the group
   * @throws NoSuchCodeException if the original group name does not exist
   * @throws GroupExistsException if the target group name already exist
   */
  public void updateGroupName(String groupName, String newName) throws NoSuchCodeException, GroupExistsException {
    Optional<Group> opt = groupRepository.findById(groupName);
    if (opt.isEmpty())    // check if db already contains the code
      throw new NoSuchCodeException();
    if (groupRepository.findById(newName).isPresent())    // check if db already contains the code
      throw new GroupExistsException();

    Group oldGroup = opt.get();
    Group newGroup = new Group(newName);
    groupRepository.save(newGroup);

    for(Person member: oldGroup.retMember()){
      member.removeGroup(oldGroup);
      newGroup.addMember(member);
      member.addGroup(newGroup);
      personRepository.update(member);
    }

    groupRepository.delete(oldGroup);
    groupRepository.update(newGroup);
  }

  /**
   * Retrieves the list of groups.
   * 
   * @return the collection of group names
   */
  public Collection<String> listOfGroups() {
    List<String> gl = new ArrayList<>();
    for(Group g:groupRepository.findAll()) gl.add(g.getName());
    return gl;
//    return groupRepository.findAll().stream().map(Group::getName).collect(Collectors.toList()); // TO BE IMPLEMENTED
  }

  /**
   * Add a person to a group
   * 
   * @param codePerson person code
   * @param groupName  name of the group
   * @throws NoSuchCodeException in case the code or group name do not exist
   */
  public void addPersonToGroup(String codePerson, String groupName) throws NoSuchCodeException {
    Optional<Group> optGroup = groupRepository.findById(groupName);
    Optional<Person> optPerson = personRepository.findById(codePerson);
    if (optGroup.isEmpty() || optPerson.isEmpty())    // check if db already contains the code
      throw new NoSuchCodeException();

    Group g = optGroup.get();
    Person p = optPerson.get();
    g.addMember(p);
    p.addGroup(g);

    groupRepository.update(g);
    personRepository.update(p);
  }

  /**
   * Retrieves the list of people on a group
   * 
   * @param groupName name of the group
   * @return collection of person codes
   */
  public Collection<String> listOfPeopleInGroup(String groupName) {
    Optional<Group> optGroup = groupRepository.findById(groupName);
    if (optGroup.isEmpty())    // check if db already contains the code
      return null;
    Group g = optGroup.get();
    return g.retMember().stream().map(p->p.getCode()).collect(Collectors.toList());
  }

  /**
   * Retrieves the code of the person having the largest
   * group of friends
   * 
   * @return the code of the person
   */
  public String personWithLargestNumberOfFriends() {
    return personRepository.findAll().stream().
            collect(Collectors.toMap(Person::getCode, p->p.retrieveFriends().size()))
            .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).orElse(null);
  }

  /**
   * Find the name of group with the largest number of members
   * 
   * @return the name of the group
   */
  public String largestGroup() {
    return groupRepository.findAll().stream()
            .collect(Collectors.toMap(Group::getName, g->g.retMember().size()))
            .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
            .map(Map.Entry::getKey).orElse(null); // TO BE IMPLEMENTED
  }

  /**
   * Find the code of the person that is member of
   * the largest number of groups
   * 
   * @return the code of the person
   */
  public String personInLargestNumberOfGroups() {
    return personRepository.findAll().stream()
            .collect(Collectors.toMap(Person::getCode, p->p.retrieveGroups().size()))
            .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
            .map(Map.Entry::getKey).orElse(null);
     // TO BE IMPLEMENTED
  }

  // R5

  /**
   * add a new post by a given account
   * 
   * @param authorCode the id of the post author
   * @param text   the content of the post
   * @return a unique id of the post
   */
  public String post(String authorCode, String text) {
    Post np = new Post(authorCode, text);
    Person p = personRepository.findById(authorCode).orElse(null);
    p.addPost(np);

    postRepository.save(np);
    personRepository.update(p);
    return np.getId(); // TO BE IMPLEMENTED
  }

  /**
   * retrieves the content of the given post
   * 
   * @param pid    the id of the post
   * @return the content of the post
   */
  public String getPostContent(String pid) {
    Optional<Post> p =  postRepository.findById(pid);
    if (p.isEmpty()) return null;
    return p.get().getText(); // TO BE IMPLEMENTED
  }

  /**
   * retrieves the timestamp of the given post
   * 
   * @param pid    the id of the post
   * @return the timestamp of the post
   */
  public long getTimestamp(String pid) {
    Optional<Post> p =  postRepository.findById(pid);
    if (p.isEmpty()) return -1;
    return p.get().getTimeStamp();
  }

  /**
   * returns the list of post of a given author paginated
   * 
   * @param author     author of the post
   * @param pageNo     page number (starting at 1)
   * @param pageLength page length
   * @return the list of posts id
   */
  public List<String> getPaginatedUserPosts(String author, int pageNo, int pageLength) {
    return personRepository.findById(author).orElse(null)
            .retreivePosts().stream().sorted(Comparator.comparing(Post::getTimeStamp).reversed())
            .map(Post::getId).skip((long) (pageNo-1)*pageLength)
            .limit(pageLength).collect(Collectors.toList()); // TO BE IMPLEMENTED
  }

  /**
   * returns the paginated list of post of friends.
   * The returned list contains the author and the id of a post separated by ":"
   * 
   * @param author     author of the post
   * @param pageNo     page number (starting at 1)
   * @param pageLength page length
   * @return the list of posts key elements
   */
  public List<String> getPaginatedFriendPosts(String author, int pageNo, int pageLength) {
    List<Post> all = personRepository.findById(author).orElseThrow()
            .retrieveFriends().stream()
            .flatMap(f -> personRepository.findById(f.getCode()).orElseThrow().retreivePosts().stream())
            .sorted(Comparator.comparing(Post::getTimeStamp).reversed())
            .collect(Collectors.toList());
    return all.stream()
            .skip((long)(pageNo - 1) * pageLength)
            .limit(pageLength)
            .map(p -> p.getAuthor() + ":" + p.getId())
            .collect(Collectors.toList());
  }

}