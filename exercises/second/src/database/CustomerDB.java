package database;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;


public class CustomerDB implements Iterable {
    protected Customer[] customers;
    protected int size;

    public CustomerDB(int capacity){
        customers = new Customer[capacity];
        size = 0;
    }

    public void printCustomers(){
        for(int i=0;i<size;i++)
            System.out.println(customers[i].name);
    }
    public void printCustomers(Consumer<Customer> printer){
        for (int i=0;i<size;i++){
            printer.accept(customers[i]);
        }
    }
    public void printCustomers(BiFunction<String, Integer, String> encoder, int key){
        for(int i=0; i<size; i++){
            String encryptedName = encoder.apply(customers[i].name, key);
            System.out.println(encryptedName);
        }
    }
    public void printCustomers(Function<Customer, String> extractor){
        for(int i=0; i<size; i++)
            System.out.println(extractor.apply(customers[i]));
    }
    public void addCustomer(Customer customer){
        if(size<customers.length)
            customers[size++]=customer;
        else
            System.out.println("full!");
    }
    public void addCustomer(Supplier<Customer> source){
        if(size<customers.length)
            customers[size++]=source.get();
        else
            System.out.println("full!");
    }
    public Customer getRandomCustomer(){
        Random rand = new Random();
        int i = rand.nextInt(size-1);
        return customers[i];
    }

    public void sortCustomers(){
        Arrays.sort(customers, 0, size);
    }
    public void sortCustomers(Comparator<Customer> comp){
        Arrays.sort(customers, 0,size,comp);
    }

    @Override
    public Iterator<Customer> iterator() {
        return new Iterator<Customer>(){
            int index =0;
            @Override
            public boolean hasNext() {
                return index< size;
            }
            @Override
            public Customer next() {
                return customers[index++];
            }
        };
    }

}
