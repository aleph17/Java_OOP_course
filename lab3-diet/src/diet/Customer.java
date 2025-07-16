package diet;


public class Customer extends User{
	private String email;
	private String phone;

	public Customer(String first, String last, String email, String phone){
		this.email = email; this.phone = phone; this.first = first; this.last = last;
	}
	
	public String getLastName() {
		return last;
	}
	
	public String getFirstName() {
		return first;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void SetEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
