package nested;

public class Counter {
    private int i;
    public class Incrementer{
        private int step;
        public Incrementer(int step){
            this.step = step;
        }
        public void doIncrement(){
            i+=step;
        }
    }
    public int getValue(){
        return i;
    }
}
