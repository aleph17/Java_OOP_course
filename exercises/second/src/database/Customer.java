package database;
import java.util.Comparator;
import java.util.function.Consumer;

public class Customer implements Consumer<Customer>, Comparable {
    protected String name;
    protected int id;

    public Customer(){}
    public Customer(int id, String name){
        this.id = id;
        this.name = name;
    }
    public static Customer buildDefault(){
        return new Customer(-1,"NaN");
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return String.valueOf(id);
    }

    public static <T> Comparator<T> reverse(Comparator<T> comp){
        return (a,b)-> comp.compare(b,a);
    }

    @Override
    public void accept(Customer t){
        System.out.println("Processing customer: " + t.name);
    }
    @Override
    public int compareTo(Object o){
        Customer other = (Customer) o;
        return this.id-other.id;
    }
    @Override
    public String toString(){
        return this.name;
    }

}
