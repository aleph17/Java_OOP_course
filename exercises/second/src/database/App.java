package database;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.Iterator;
import java.util.function.Supplier;


public class App {
    public static void main(String[] args){
        CustomerDB database = new CustomerDB(10);
        database.addCustomer(new Customer( 14, "Adam"));
        database.addCustomer(new Customer( 10, "Keily"));
        database.addCustomer(new Customer( 8, "William"));
//        System.out.println("printing as usual");
//        database.printCustomers();
//        System.out.println("printing with behavioral pattern");
//        database.printCustomers(new Consumer<Customer>() {
//            @Override
//            public void accept(Customer customer) {
//                System.out.println(customer.name + " " + customer.id);
//            }
//        });
//        System.out.println("printing with lambda function");
//        database.printCustomers(c->System.out.println(c.name +" has got id of "+ c.id));
//        Iterator<Customer> iter = database.iterator();
//        System.out.println(iter.next().name);
//        System.out.println(iter.next().name);
        /// sorting
//        database.sortCustomers(new Comparator<Customer>() {
//            @Override
//            public int compare(Customer o1, Customer o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });
//        database.printCustomers();
//        database.sortCustomers((x,y)->x.id - y.id);
//        database.sortCustomers(Comparator.comparing(Customer::getName))  ;
//        database.printCustomers();
        /// bifunctioning
//        database.printCustomers(new BiFunction<String, Integer, String> (){
//            @Override
//            public String apply(String s, Integer key) {
//                StringBuffer sb = new StringBuffer(s);
//                for(int i=0; i< sb.length();i+=2){
//                    sb.setCharAt(i, (char)(sb.charAt(i)+key));
//                }
//                return new String(sb);
//            }
//        }, 3);
        /// method referencing
//        Consumer printer = System.out::println;
//        printer.accept("hello");
//        database.printCustomers(Customer::getId);
//        database.addCustomer(Customer::buildDefault);
//        database.addCustomer(Customer::new);//works only when default constrcutor is there
//        Supplier<Customer> generator = database::getRandomCustomer;
//        for(int i=0; i<10;i++){
//            Customer c = generator.get();
//            System.out.println(c.getId()+" "+c.getName());
//        }
//        CustomerDB database2 = new CustomerDB(10);
//        for(int i=0; i<10;i++){
//            database2.addCustomer(database::getRandomCustomer);
//        }
//        database2.printCustomers((Consumer<Customer>) c-> System.out.println(c.name + " "+ c.id));
        /// pair generics
//        Pair<Integer,Integer> intPair = new Pair<>(3,4);
//        Pair<String, String> strPair = new Pair<>("John", "Paul");
//        Pair<Customer, String> mixPair = new Pair<>(new Customer( 8, "William"), "first one");
//
//        System.out.println(mixPair.getClass()==intPair.getClass());

    }
}
