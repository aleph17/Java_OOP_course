package diet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Represents a takeaway restaurant chain.
 * It allows managing restaurants, customers, and orders.
 */
public class Takeaway {
	LinkedList<Customer> customers = new LinkedList<>();
	TreeMap<String, Restaurant> restaurants = new TreeMap<>();


	/**
	 * Constructor
	 * @param food the reference {@link Food} object with materials and products info.
	 */
	public Takeaway(Food food){
	}

	/**
	 * Creates a new restaurant with a given name
	 *
	 * @param restaurantName name of the restaurant
	 * @return the new restaurant
	 */
	public Restaurant addRestaurant(String restaurantName) {
		Restaurant nr = new Restaurant(restaurantName);
		restaurants.put(restaurantName, nr);
		return nr;
	}

	/**
	 * Retrieves the names of all restaurants
	 *
	 * @return collection of restaurant names
	 */
	public Collection<String> restaurants() {
		Collection<String> ls = new ArrayList<>();
		for (String rkey: restaurants.keySet())
			ls.add(rkey);
		return ls;
	}

	/**
	 * Creates a new customer for the takeaway
	 * @param firstName first name of the customer
	 * @param lastName	last name of the customer
	 * @param email		email of the customer
	 * @param phoneNumber mobile phone number
	 *
	 * @return the object representing the newly created customer
	 */
	public Customer registerCustomer(String firstName, String lastName, String email, String phoneNumber) {
		Customer c = new Customer(firstName, lastName,email, phoneNumber);
		customers.add(c);
		return c;
	}

	/**
	 * Retrieves all registered customers
	 *
	 * @return sorted collection of customers
	 */
	public Collection<Customer> customers(){
		Collections.sort(customers, Comparator.comparing(Customer::getLastName).thenComparing(Customer::getFirstName));
		return customers;
	}


	/**
	 * Creates a new order for the chain.
	 *
	 * @param customer		 customer issuing the order
	 * @param restaurantName name of the restaurant that will take the order
	 * @param time	time of desired delivery
	 * @return order object
	 */
	public Order createOrder(Customer customer, String restaurantName, String time) {
		Restaurant r = restaurants.get(restaurantName);
		Order newOrder = new Order(customer,r,r.putTime(time));
		restaurants.get(restaurantName).orders.add(newOrder);
		return newOrder;
	}

	/**
	 * Find all restaurants that are open at a given time.
	 *
	 * @param time the time with format {@code "HH:MM"}
	 * @return the sorted collection of restaurants
	 */
	public Collection<Restaurant> openRestaurants(String time){
		Collection<Restaurant> opens = restaurants.entrySet().stream().filter(a->a.getValue().isOpenAt(time))
				.sorted(Comparator.comparing(Map.Entry::getKey)).map(Map.Entry::getValue).collect(Collectors.toList());
		return opens;
	}
}
