package diet;

import diet.Order.OrderStatus;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Represents a restaurant class with given opening times and a set of menus.
 */
public class Restaurant {
	private String name;
	private String[] working_hours;
	private LinkedList<Menu> menus = new LinkedList<>();
	public LinkedList<Order> orders = new LinkedList<>();
	/**
	 * retrieves the name of the restaurant.
	 *
	 * @return name of the restaurant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define opening times.
	 * Accepts an array of strings (even number of elements) in the format {@code "HH:MM"},
	 * so that the closing hours follow the opening hours
	 * (e.g., for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00,
	 * arguments would be {@code "08:15", "14:00", "19:00", "00:00"}).
	 *
	 * @param hm sequence of opening and closing times
	 */

	public Restaurant(){}
	public Restaurant(String name){
		this.name = name;
	}
	public void setHours(String ... hm) {
		working_hours = hm;
	}

	/**
	 * Checks whether the restaurant is open at the given time.
	 *
	 * @param time time to check
	 * @return {@code true} is the restaurant is open at that time
	 */
	public boolean isOpenAt(String time){
		boolean works = false;
		int target = timer(time);
		for (int i=0;i<working_hours.length;i+=2){
			if(target>=timer(working_hours[i]) && target<=timer(working_hours[i+1]))
				works =true;
		}
		return works;
	}
	public int timer(String time){
		return Integer.parseInt(time.split(":")[0])*60+Integer.parseInt(time.split(":")[1]);
	}
	public String padder(String time){
		if (time.split(":")[0].length()==1)
			return "0"+time;
		else
			return time;
	}
	public String putTime(String time){
		if (this.isOpenAt(time))
			return padder(time);
		else{
			String minTime = null;
			int minDiff = Integer.MAX_VALUE;

			for(int i=0; i< working_hours.length;i+=2){
				int diff = (timer(working_hours[i]) - timer(time) + 1440) % 1440; // circular difference
				if (diff < minDiff) {
					minDiff = diff;
					minTime = working_hours[i];
				}

			}
			return padder(minTime);
		}
	}

	/**
	 * Adds a menu to the list of menus offered by the restaurant
	 *
	 * @param menu	the menu
	 */
	public void addMenu(Menu menu) {
		menus.add(menu);
	}

	/**
	 * Gets the restaurant menu with the given name
	 *
	 * @param name	name of the required menu
	 * @return menu with the given name
	 */
	public Menu getMenu(String name) {
		for(Menu m: menus){
			if(m.getName().equals(name))
				return m;
		}
		return null;
	}

	/**
	 * Retrieve all order with a given status with all the relative details in text format.
	 *
	 * @param status the status to be matched
	 * @return textual representation of orders
	 */
	public String ordersWithStatus(OrderStatus status) {
		StringBuffer str = new StringBuffer();
		orders.sort(Comparator.comparing(Order::getCustomer).thenComparing(Order::getTime));
		for(Order or: orders){
			if(or.getStatus()==status)
				str.append(or);
		}
		return str.toString();
	}
}
