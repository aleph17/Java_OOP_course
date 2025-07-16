public class Counter{
        public int i;
        public class Incrementer{
            private int step = 1;
            public void doIncrement(){i+= step;}
            public Incrementer(int step){ this.step =step;}
            }
        public Incrementer buildIncrementer(int step){
                return new Incrementer(step);}
        public int getValue(){
            return i;
        }
        
        public static void main(String[] args){
            Counter c = new Counter();
            Incrementer byOne = c.buildIncrementer(1);
            Incrementer byFour = c.buildIncrementer(4);
            byOne.doIncrement();
            byFour.doIncrement();
            System.out.println(c.i);
        }
    }

/*
        Counter c = new Counter();
        Counter.Incrementer byOne = c.Incrementer.buildIncrementer(1);
        Counter.Incrementer byFour = c.Incrementer.buildIncrementer(4);
        c.Incrementer.doIncrement();
        c.Incrementer.doIncrement(); */