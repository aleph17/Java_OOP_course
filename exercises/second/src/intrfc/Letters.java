package intrfc;

public class Letters implements Iterable {
    char[] chars;
    public Letters(String given){
        this.chars = given.toCharArray();
    }
    public Iterator iterator(){return new LI();}
    public class LI implements Iterator {
        private int i;
        @Override
        public boolean hasNext() {
            return i<chars.length;
        }
        @Override
        public Object next() {
            return chars[i++];
        }
    }
}


/// main function
//public static void main(String[] Args) {
//    String a = "Muhammad Ali";
//    Letters l = new Letters(a);
//    Iterator it = l.iterator();
//    while(it.hasNext())
//        System.out.println(it.next());
//}