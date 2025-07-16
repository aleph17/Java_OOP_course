package nested;

public class App {
    public static void main(String[] Args){
        Counter c = new Counter();
        Counter.Incrementer i = c.new Incrementer(1);
        i.doIncrement();
    }
}
