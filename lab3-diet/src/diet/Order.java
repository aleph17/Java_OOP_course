package diet;

import java.util.HashMap;

/**
 * Represents and order issued by an {@link Customer} for a {@link Restaurant}.
 *
 * When an order is printed to a string, it should look like:
 * <pre>
 *  RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
 *  	MENU_NAME_1->MENU_QUANTITY_1
 *  	...
 *  	MENU_NAME_k->MENU_QUANTITY_k
 * </pre>
 */
public class Order {
	private Customer orderer;
	private Restaurant server;
	private String time;
	private OrderStatus status = OrderStatus.ORDERED;
	private PaymentMethod method = PaymentMethod.CASH;
	private HashMap<String, Integer> menus = new HashMap<String, Integer>();


	/**
	 * Possible order statuses
	 */
	public Order(Customer orderer, Restaurant server, String time){
		this.orderer = orderer; this.server = server; this.time = time;
	}
	public enum OrderStatus {ORDERED, READY, DELIVERED}

	/**
	 * Accepted payment methods
	 */
	public enum PaymentMethod {PAID, CASH, CARD}

	/**
	 * Set payment method
	 * @param pm the payment method
	 */
	public void setPaymentMethod(PaymentMethod pm) {
		this.method = pm;
	}

	/**
	 * Retrieves current payment method
	 * @return the current method
	 */
	public PaymentMethod getPaymentMethod() {
		return method;
	}

	/**
	 * Set the new status for the order
	 * @param os new status
	 */
	public void setStatus(OrderStatus os) {
		this.status = os;
	}

	/**
	 * Retrieves the current status of the order
	 *
	 * @return current status
	 */
	public OrderStatus getStatus() {
		return status;
	}
	public String getCustomer(){
		return orderer.getFirstName();
	}
	public String getTime(){
		return this.time;
	}

	/**
	 * Add a new menu to the order with a given quantity
	 *
	 * @param menu	menu to be added
	 * @param quantity quantity
	 * @return the order itself (allows method chaining)
	 */
	public Order addMenus(String menu, int quantity) {
		menus.put(menu, quantity);
		return this;
	}
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(server.getName()+", ").append(orderer.getFirstName()+" ").append(orderer.getLastName()+" : (")
				.append(time+"):\n");
		for(String key: menus.keySet())
			str.append("\t"+key+"->"+menus.get(key)+"\n");
		return str.toString();
	}
	
}
