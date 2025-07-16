package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class trash {
    /*
    * //Stream source generation

		//Method 1: using arrays
        String [] animals={"lion", "rabbit", "monkey", "dog"};
        Stream<String> animalsStream=Arrays.stream(animals);
		animalsStream.forEach(System.out::println);
		System.out.println();

		//Method 2: directly from a list of parameters
		Stream<String> fruitsStream=Stream.of("Orange", "Lemon", "Banana");
        fruitsStream.forEach(System.out::println);

		System.out.println();

		//Method 3: from a collection
		List<Dummy<String>> dummyList= new LinkedList<>();
        dummyList.add(new Dummy<String>("jhgjdff"));
        dummyList.add(new Dummy<String>("jvvdfvd"));
        dummyList.add(new Dummy<String>("abhc"));
        Stream<Dummy<String>> dummyStream=dummyList.stream();
       //dummyStream.forEach(System.out::println);
        dummyStream.forEach(x->System.out.println(x.getDummy()));


		System.out.println();

        //Method 4: source generation
        Stream<Double> randomDoubleStream=Stream.generate(Math::random);
        randomDoubleStream.limit(10).forEach(System.out::println);

		//Case 1: create an empty stream
        System.out.println();
		Stream emptyStream=Stream.empty();
        if(emptyStream.count()==0)
            System.out.println("Ops, this stream is empty!");

		System.out.println();

		//Case 2: use a Supplier to generate the elements
		System.out.println();

		//another example with Supplier : generate a stream made of random strings (ajck, zkdc, ldrkj)
		Random random= new Random();
		int low=97; //a
        int high=122; //z
		System.out.println();
		Stream<String> randomStringsStream=Stream.generate(
            ()->new StringBuilder().append((char)(random.nextInt(high-low)+low))
                                    .append((char)(random.nextInt(high-low)+low))
                                    .append((char)(random.nextInt(high-low)+low))
                                    .append((char)(random.nextInt(high-low)+low)).toString());

        randomStringsStream.limit(10).forEach(System.out::println);

		//Case 3: use an UnaryOperator interface and generate stream form a seed
        Stream<Integer> evenNumbersStream=Stream.iterate(0, i->i+2);
		evenNumbersStream.limit(10).forEach(System.out::println);
		System.out.println();

		//Intermediate operations examples
		//filter: apply a Predicate interface to verify a condition
		//sorted: default method of Stream<T> to perform sorting given a Comparator interface
		//limit: stop considering elements, after a certain position
		//skip: skip first n elements in the stream
		//distinct: generate a stream containing only unique elements

        Stream<String> familyStream=Stream.of("Dad", "Mom", "grandmother", "uncle", "niece", "daughter");
        familyStream.filter(x->x.contains("m")).forEach(System.out::println);


        Stream<Integer> numbersStream=Stream.of(9, 2, 33, 33, 78, 10, 23, 9, 2, 2);
        numbersStream.skip(1)
                      .sorted()
                      .filter(x->x>30 && x<100)
                      .distinct()
                      .forEach(System.out::println);




		//Map examples
		System.out.println();
		//Example 1: print all dummies using map
        Stream<Dummy<Integer>> dummyIntStream=Stream.of(new Dummy<Integer>(9), new Dummy<Integer>(20), new Dummy<Integer>(1));
        dummyIntStream.map(Dummy::getDummy).forEach(System.out::println);
		System.out.println();

		//Example 2: map each String in an array of String to their length
		System.out.println();
		Stream<String> vegetablesStream=Stream.of("pumpkin", "carrot", "cabbage", "tomato", "onion");
	   	vegetablesStream.mapToInt(String::length)
                        .distinct()
                        .forEach(System.out::println);
		//Example 3: two level mapping, first from Dummy object to String, then int mapping to string length
		System.out.println();
		Stream<Dummy<String>> anotherDummyStream=dummyList.stream();
        anotherDummyStream.map(Dummy::getDummy)
                          .map(String::length)
                          .forEach(x->System.out.println(x*10));
		//Try it out1: mapping strings to their length after filtering
        System.out.println();
        Stream<String> stringStream2=Stream.of("mother", "mother", "daughter", "father", "son", "daughter", "hi", "onion", "glass");
        stringStream2.filter(x->x.length()>4)
                     .sorted(Comparator.comparing(String::length).reversed())
                     .distinct()
                     .map(String::length)
                     .forEach(System.out::println);




		//Try it out2: map values in a Stream according to rule in a dictionary
        System.out.println();
        Map<Character, Character> encodingMap=new HashMap<>();
        encodingMap.put('a', '0');
		encodingMap.put('e', '1');
        encodingMap.put('i', '2');
        encodingMap.put('o', '3');
        encodingMap.put('u', '4');

        Function<String, String> encoder;
        encoder=(str)->{
            StringBuffer encr=new StringBuffer(str);
            for(int i=0; i<encr.length(); i++){
                Character c=encr.charAt(i);
                if(encodingMap.containsKey(c)){
                    encr.setCharAt(i, encodingMap.get(c));
                }
            }
            return encr.toString();
        };

        Stream<String> anotherStringStream=Stream.of("mother", "mother", "daughter", "father", "son", "daughter", "hi", "onion", "glass");
        anotherStringStream.map(encoder)
                           .filter(s->{
                            int count=0;
                            for(int i=0; i<s.length(); i++){
                                if(Character.isDigit(s.charAt(i))){
                                    count++;
                                }
                            }
                            return count>2;
                           }
                           )
                           .forEach(System.out::println);

		System.out.println();

        //END LESSON 2/05 FIRST HALF

          //Example 1: class Person contains name, age of the person and a list of hobbies
        //using flatmap find all the unique hobbies
        Person[] personsArray= {new Person("Alice", 37, Arrays.asList("Running")),
                                new Person("Bob", 24, Arrays.asList("Cooking", "Meditating")),
                                new Person("Paul", 16, Arrays.asList("Singing", "Meditating"))};

        Stream<Person> personStream=Arrays.stream(personsArray);
        personStream.map(Person::getHobbies)
                    .flatMap(Collection::stream)
                    .distinct()
                    .forEach(System.out::println);


        personStream.flatMap(p->p.getHobbies().stream())
                    .distinct()
                    .forEach(System.out::println);

        System.out.println();

    //Example 2 flatmap
    //Given a list of sentences, generate a stream containing each word in the sentence
    List<String> sentences= new LinkedList<String>();
         sentences.add("The quick brown fox");
         sentences.add("jumps over the lazy dog");
         sentences.add("The dog sleeps");

    Stream<String> distinctWords=sentences.stream();
        /*distinctWords.flatMap(sentence-> Arrays.stream(sentence.split(" ")))
                     .map(String::toLowerCase)
                     .distinct()
                     .forEach(System.out::println);

    //version using map and then flatmap from string -> array of string -> then flatmap to string again
         distinctWords.map(x->x.split(" "))
            .flatMap(Arrays::stream)
                      .map(String::toLowerCase)
                      .distinct()
                      .forEach(System.out::println);

    //alternative version using only flatmap from string


          System.out.println();

    //Examples on TERMINAL OPERATIONS

    //Examples forEach vs map and integers


    //alternative way
    //someNumbers.limit(5).forEach(x->System.out.print((int)(x*10)+" "));

           System.out.println();

    //Example findAny -get any element from the stream. IT'S TERMINAl! (LIKELY THE FIRST)
    Stream<String> againStringStream =Stream.of("Panda", "sunglasses", "kyoto");
    Optional<String> anyString=againStringStream.findAny();
          System.out.println(anyString.orElse("Empty"));


    ////Example findAny on empty stream
    Stream<String> emptyStringStream =Stream.of("Panda", "sunglasses", "kyoto");
    Optional<String> emptyString=emptyStringStream.skip(3).findAny();
           System.out.println(emptyString.orElse("Empty"));


    //Example findFirst -get first according to sorting of elements.
    Optional<String> firstString=Stream.of("Baldur's", "gate", "iii", "is amazing").findFirst();
          System.out.println(firstString.orElse("Empty"));


    //Example min or max according to given comparator
    //get the longest string in the stream
    Optional<String> minString=Stream.of("Beet", "bears", "battlestar galactica").min(Comparator.comparingInt(String::length));
          System.out.println(minString.orElse("Empty!"));

          System.out.println();

    //Example count
    //Count how many Person have Meditating as hobby
    Stream<Person> anotherPersonsStream=Arrays.stream(personsArray);

    Long countMeditating= anotherPersonsStream.map(Person::getHobbies)
            .flatMap(Collection::stream)
            .filter(s->s.equals("Meditating"))
            .count()
            ;

          System.out.print(countMeditating);

    //Example anyMatch
    //Find if at least a Person has Reading as hobby
    boolean somebodyReads=Arrays.stream(personsArray)
            .map(Person::getHobbies)
            .flatMap(Collection::stream)
            .anyMatch(h->h.equals("Reading"));


        System.out.println();
        System.out.println(somebodyReads);
        System.out.println();

    //REDUCE

    //Example 1 : a stream of numbers to reduce in the sum of even numbers in the stream
    List<Integer> numbers=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    int sumOfEvenumbers=numbers.stream()
            .filter(x->x%2==0)
            .reduce(0,(n1, n2)->n1+n2);
        System.out.println("The sum of the even numbers is:"+sumOfEvenumbers);

    //Example 2: concatenate single words into a sentence
    Stream<String> words=Stream.of("Hello,", "how", "are", "you?");
    String sentence=words.reduce("", (a, b)->a.isEmpty() ? b  : a+" "+b);
        System.out.println(sentence);

        System.out.println();
    //COLLECT
    //Collect in a candidatePool object all people who meditate to offer them a discount on meditation books
    CandidatesPool meditationCandidates=Arrays.stream(personsArray)
            .collect(CandidatesPool::new, //supplier
                    (cp, c)->cp.addCandidate(p->p.getHobbies().contains("Meditating"), c), //consumer
                    (cp1, cp2)->cp1.getCandidates().addAll(cp2.getCandidates()));

        System.out.println(meditationCandidates);
        System.out.println();
    //Example 1: counting even numbers
    long count=Stream.of(1,2,3,4,5,6,7,8)
            .filter(x->x%2==0)
            .collect(Collectors.counting());
        System.out.println(count);
        System.out.println();
    //Example 2: summing
    long sum=Stream.of(1,2,3,4,5,6,7,8)
            .filter(x->x%2==0)
            .collect(Collectors.summingInt(Integer::new));
        System.out.println(sum);

    //Example 3: summarizing
    IntSummaryStatistics summary=Stream.of(1,2,3,4,5,6,7,8)
            .filter(x->x%2==0)
            .collect(Collectors.summarizingInt(Integer::new));

        System.out.println(summary);

    //Example 4: get only even numbers as a list
    List<Integer> evenNumbersList=Stream.of(1,2,3,4,5,6,7,8)
            .filter(x->x%2==0)
            .collect(Collectors.toList());

        System.out.println();
        System.out.println(evenNumbersList);

    //Example 5: Return the list of hobbies that people older than 30 years old do as a TreeSet
    List<Person> people = Arrays.asList(
            new Person("Alice", 25, Arrays.asList("Swimming", "Hiking")),
            new Person("Bob", 35, Arrays.asList("Gardening", "Cooking")),
            new Person("Charlie", 40, Arrays.asList("Painting", "Cooking")),
            new Person("David", 30, Arrays.asList("Photography", "Cooking")),
            new Person("Eve", 45, Arrays.asList("Reading", "Gardening", "Hiking"))
    );

        System.out.println();
    TreeSet<String> hobbiesAfter30=people.stream()
            .filter(p->p.getAge()>30)
            .map(Person::getHobbies)
            .flatMap(List::stream)
            .collect(Collectors.toCollection(TreeSet::new));

        System.out.println(hobbiesAfter30);

    //Example 6: collecting in a map: Count the number of hobbies for each person
    Map<String, Integer> numHobbies= people.stream()
            .collect(Collectors.toMap(Person::getName,p->p.getHobbies().size()));

        System.out.println(numHobbies);

    //Example 7: joining strings
    Stream<String> words2=Stream.of("Hey", "how", "are", "you");
    String fullSentence=words2.collect(Collectors.joining(" ", "\n-", "?"));

        System.out.println(fullSentence);

    // GROUPING BY EXAMPLES
    Person[] arrayOfPersons= {new Person("Gloria", 40, Arrays.asList("Singing", "Cooking")),
            new Person("Phil", 42, Arrays.asList("Basketball", "Do magic tricks")),
            new Person("Cam", 40, Arrays.asList("Singing", "Cooking")),
            new Person("Manny", 13, Arrays.asList("Poetry", "Acting")),
            new Person("Luke", 13, Arrays.asList("Basketball", "Do magic tricks"))};

    //Example 1: GroupingBy by Age (version 1)
    Map<Integer, List<Person>> peopleByAge=Arrays.stream(arrayOfPersons)
            .collect(groupingBy(Person::getAge, TreeMap::new, toList()));
        System.out.println(peopleByAge);

    //Example 2: Alternative downstream collections (counting frequency of hobbies)
    Map<String, Long> hobbies=Arrays.stream(arrayOfPersons)
            .map(Person::getHobbies)
            .flatMap(List::stream)
            .collect(groupingBy(h->h, counting())) ;

              System.out.println();
        System.out.println(hobbies);


    //Example from Slides: print the word frequency in a text in descending frequency order
    String txta="You talkin’ to me? You talkin’ to me? You talkin’ to me? Then who the hell else are you talkin’ to? You talkin’ to me? Well, I’m the only one here.";
    //Version 1: without using CollectorComposition
    List<String> freqSorted = Arrays.stream(txta.split(" "))
            .collect(groupingBy(w->w, counting()))
            .entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry<String,Long>::getValue)
                    .reversed()
                    .thenComparing(Map.Entry::getKey))
            .map( e -> e.getValue() + ":" + e.getKey())
            .collect(toList());

    Map<String, Long> occurencesMap = Arrays.stream(txta.split(" "))
            .collect(groupingBy(w->w, counting()));
        System.out.println(occurencesMap);
        occurencesMap.entrySet().stream()
                                .sorted(Comparator.comparing(Map.Entry<String,Long>::getValue)
                                                  .reversed()
                                                  .thenComparing(Map.Entry::getKey))
            .map( e -> e.getValue() + ":" + e.getKey())
            .collect(toList());

    // System.out.println(freqSorted);

    //Version2: Exploiting composition with CollectindAndThen
    freqSorted = Arrays.stream(txta.split(" "))
            .collect(collectingAndThen(groupingBy(w->w, counting()), //grouping
    m->m.entrySet().stream() //andThen on the obtained map do...
                                                                                    .sorted(Comparator.comparing(Map.Entry<String,Long>::getValue).reversed())
            .map(e->e.getValue()+ ":" +e.getKey())
            .collect(toList())));


    //Version3: Exploiting composition with mapping

        System.out.println(freqSorted);

    //Example 3: GroupingBy by Age (version 2)
    // use Collectors.mapping! the mapping is applied before grouping
    Map<Integer, List<String>> peopleByAgev2=Arrays.stream(arrayOfPersons)
            .collect(groupingBy(Person::getAge, TreeMap::new, Collectors.mapping(Person::getName, toList())));
         System.out.println(peopleByAgev2);


    //Try it out-group according to hobby
    Map<String, List<String>> peoplePerHobby=Arrays.stream(personsArray)
            .flatMap(person->person.getHobbies().stream().map(hobby->Map.entry(hobby, person.getName())))
            .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toList())));


        System.out.println(peoplePerHobby);
    //Partition by example: Partition by having singing as hobby
    Map<Boolean, List<String>> singingMap=Arrays.stream(arrayOfPersons)
            .collect(partitioningBy(p->p.getHobbies().contains("Singing"),
                    collectingAndThen(mapping(Person::getName, toList()), LinkedList::new)));
    //System.out.println(singingMap);




    /* EXAMPLE: INVENTORY
     * Model an inventory where each product has a name and a list of all the order placed for that product.
     * Use streams to:
     *
     *  - print the sorted list of elements in the inventory (by name, by n of orders)
     *  - place an order for every product in the inventory once
     *  - place an order for one specific product
     *  - check if there is at least one request for each product
     *  - count how many products have more than one order placed
     *  - select the most popular product (n orders, n pieces)
     *  - group products by number of orders

    //Try it out:
    List<Product> products = Arrays.asList(
            new Product("Shantaram", "Book"),
            new Product("Charger", "Electronic device"),
            new Product("Kajal", "Cosmetic"),
            new Product("Lipstick", "Cosmetic"),
            new Product("Adapter", "Electronic device"),
            new Product("Sunscreen", "Cosmetic")
    );

    Inventory inventory = new Inventory(products);
        System.out.println("Products sorted by name:");
		inventory.printNameSortedProducts();
		System.out.println();

		inventory.orderAllProductsOnce();
		inventory.orderSpecificProduct("Kajal", 1);
		inventory.orderSpecificProduct("Kajal", 1);
		inventory.orderSpecificProduct("Kajal", 1);
		inventory.orderSpecificProduct("Lipstick", 10);

		System.out.println("Products sorted by n orders:");
		inventory.printNOrderSortedProducts();
		System.out.println();

        System.out.println("Do all products have at least an order placed?");
		System.out.println(inventory.atLeastOneRequestPerProduct());
		System.out.println();

		System.out.println("Number of products with more than one order placed:");
		System.out.println(inventory.countOrderedMoreThanOnce());
		System.out.println();

		System.out.println("Most popular by n orders");
		System.out.println(inventory.mostPopularProductByNOrders());
		System.out.println();

		System.out.println("Most popular by n pieces");
		System.out.println(inventory.mostPopularProductByNPieces());
		System.out.println();

        System.out.println("Number of pieces ordered per product");
		System.out.println(inventory.countPiecesPerProduct());
		System.out.println();

		System.out.println("Grouped by n orders");
		System.out.println(inventory.groupProductsByNOrders());
		System.out.println();

		System.out.println("Grouped by type");
		System.out.println(inventory.groupProductsByType()); */
}
