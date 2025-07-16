package streams;
import static  java.util.stream.Collectors.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
	
  static class Dummy<T> {
		private T dummyValue;

		public Dummy(T dummyValue) {
			this.dummyValue=dummyValue;
		}
		public T getDummy(){
			return dummyValue;
		}
	}

	public static void main(String[] args) {
      /// start
//      String[] animals = {"lion", "rabbit", "monkey"};
//      Stream<String> animalsStream = Arrays.stream(animals);
//      animalsStream.forEach(System.out::println);
//      Stream<String> fruitsStream = Stream.of("banana", "lemon");
//      fruitsStream.forEach(System.out::println);
        /// dummy
//        List<Dummy<String>> dummyList = new LinkedList<>();
//        dummyList.add(new Dummy<>("fkalsjflds"));
//        dummyList.add(new Dummy<>("fads"));
//        dummyList.add(new Dummy<>("flkj"));
//        dummyList.forEach(d->System.out.println(d.getDummy()));
        /// infinite double
//        Stream<Double> randomDoubles = Stream.generate(Math::random);
//        randomDoubles.limit(10).forEach(System.out::println);
        /// empty() and count
//        Stream emptyStream = Stream.empty();
//        System.out.println(emptyStream.count());
        /// .generate rand string
//        Random random = new Random();
//        int low = 100;
//        int up = 120;
//        Stream<String> randSStream = Stream.generate(
//                ()-> new StringBuffer().append((char) random.nextInt(low, up))
//                                .append((char) random.nextInt(low, up))
//                                .append((char) random.nextInt(low, up)).toString());
//        randSStream.limit(10).forEach(System.out::println);
        /// .iterate even
//        Stream<Integer> evenStream = Stream.iterate(0,i->i+2);
//        evenStream.limit(10).forEach(System.out::println);
        /// filter, skip, distinct, sorted
//        Stream<Integer> numStream = Stream.of(9,45,23,33,33,120,45,2,4,4);
//        numStream.skip(1).distinct().filter(n->n>5 && n<100).sorted().forEach(System.out::println);
        /// map
//        List<Dummy<String>> dummyList = new LinkedList<>();
//        dummyList.add(new Dummy<>("fkalsjflds"));
//        dummyList.add(new Dummy<>("fadsjlm"));
//        dummyList.add(new Dummy<>("flkj"));
//        dummyList.forEach(d->System.out.println(d.getDummy()));
//
//        Stream<Dummy<String>> dummyStream = dummyList.stream();
//        dummyStream.map(Dummy::getDummy).filter(x->x.length()>4)
//                .map(String::length).forEach(System.out::println);
//        Stream<String> strStream = Stream.of("lion", "rabbit", "monkey",
//                "banana", "lemon", "lion");
//        strStream.filter(x->x.length()<=5).
//                sorted((a,b)->-(a.length()-b.length())).distinct()
//                .map(String::length)
//                .forEach(System.out::println);
		/// flatMap
//		List<Person> persons = new LinkedList<Person>();
//		persons.add(new Person("Maria",46, Arrays.asList("Horse Riding", "Youtube watching")));
//		persons.add(new Person("Umberto",34, Arrays.asList("Volleyball")));
//		persons.add(new Person("Mia",21, Arrays.asList("Martial Arts", "Youtube watching")));

		//		System.out.println(persons.stream().map(Person::getHobbies)
//				.flatMap(Collection::stream).reduce("", (a,b)-> a+" "+b));

//		persons.stream().map(Person::getHobbies)
//				.flatMap(Collection::stream).distinct().forEach(System.out::println);
//		System.out.println(persons.stream().map(p->p.getHobbies())
//				.flatMap(Collection::stream)
//				.anyMatch(w-> w.equals("Volleyball")));
//		System.out.println(persons.stream()
//				.anyMatch(w-> w.getHobbies().contains ("Volleyball")));

//		Stream<String> strStream = Stream.of("Roses are red", "Second line of the text");
//		strStream.flatMap(s->Stream.of(s.split(" "))).forEach(System.out::println);
//		int i = persons.stream().map(Person::getAge).reduce(0,Math::max);
//		System.out.println(i);
//		Stream<String> strStream = Stream.of("Roses are red", "Second line of the text");
//		System.out.println(strStream.max(Comparator.comparing(String::length)));
		/// collect
//		Stream<Integer> integerStream = Stream.of(2,5,7,93,4,6,18);
//		System.out.println(integerStream.filter(a->a%2==0).reduce(0,(a,b)->a+b));
//		List<Integer> listy = integerStream.filter(a->a%2!=0)
//				.collect(Collectors.toList());
//		LinkedList<Integer> listy = integerStream.filter(a->a%2!=0)
//				.collect(LinkedList::new,
//						List::add,
//						List::addAll);
//		listy.forEach(System.out::println);

//		List<Person> persons = new LinkedList<Person>();
//		persons.add(new Person("Maria",46, Arrays.asList("Horse Riding", "Youtube watching")));
//		persons.add(new Person("Umberto",34, Arrays.asList("Volleyball")));
//		persons.add(new Person("Mia",21, Arrays.asList("Martial Arts", "Youtube watching")));
//		System.out.println(persons.stream().filter(p->p.getHobbies().size()>1)
//				.collect(Collectors.toList()));
//		persons.stream().filter(p->p.getAge()>30)
//			.map(p->p.getHobbies()).flatMap(Collection::stream)
//				.collect(TreeSet::new, TreeSet::add, TreeSet::addAll).forEach(System.out::println);
//		Person[] arrrayOfPersons = {
//				new Person("Maria",46, Arrays.asList("Horse Riding", "Youtube watching")),
//				new Person("Umberto",34, Arrays.asList("Volleyball")),
//				new Person("Mia",34, Arrays.asList("Martial Arts", "Youtube watching"))};
//		System.out.println(Arrays.stream(arrrayOfPersons)
//				.collect(Collectors.toMap(Person::getName, p-> p.getHobbies().size())));
//		System.out.println(Arrays.stream(arrrayOfPersons).map(Person::getHobbies)
//				.flatMap(c->c.stream()).collect(Collectors.joining(";", "--","--")));
//		Map<Integer, List<Person>> mapped = Arrays.stream(arrrayOfPersons)
//				.collect(Collectors.groupingBy(Person::getAge, TreeMap::new, Collectors.toList()));
//		System.out.println(mapped);
//		Map<String, Long> freq = Arrays.stream(arrrayOfPersons)
//				.map(Person::getHobbies).flatMap(Collection::stream)
//				.collect(Collectors.groupingBy(h->h, Collectors.counting()));
//		System.out.println(freq);
		/// Comparators.groupingBy
//		String text = "You talkin' to me? You talkin' to me. Why are You speaking at all?";
//		Arrays.stream(text.split(" "))
//				.collect(Collectors.groupingBy(h->h, Collectors.counting()))
//				.entrySet().stream()
//				.sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()
//						.thenComparing(Comparator.comparing(Map.Entry::getKey)))
//				.map(e->e.getKey()+":"+e.getValue())
//				.forEach(System.out::println);
		/// Collectors.collectingAndThen
//		Arrays.stream(text.split(" "))
//				.collect(Collectors.collectingAndThen(
//						Collectors.groupingBy(h->h, Collectors.counting()), a->a.entrySet().stream()))
//						.sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()
//								.thenComparing(Comparator.comparing(Map.Entry::getKey)))
//						.map(e->e.getKey()+":"+e.getValue())
//						.forEach(System.out::println);
//
 		/// Collectors.mapping
//		System.out.println(Arrays.stream(arrrayOfPersons)
//				.map(Person::getAge).map(String::new).forEach(System.out::println));
//		System.out.println(Arrays.stream(arrrayOfPersons)
//				.collect(Collectors.partitioningBy(p->p.getHobbies().contains("Youtube watching"),
//						Collectors.collectingAndThen(Collectors.mapping(Person::getName,Collectors.toList()), LinkedList::new))
//						));
//		System.out.println(Arrays.stream(arrrayOfPersons)
//				.collect(Collectors.partitioningBy(p->p.getHobbies().contains("Youtube watching"),
//						Collectors.collectingAndThen(Collectors.mapping(Person::getName,Collectors.toList()), LinkedList::new))
//				));
	}
}

        
    
    



