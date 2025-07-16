package nested;

public class Stack {
    public static class Element{
        private int value;
        private Element next;

        public Element(int v){
            this.value = v;
        }

        public int getValue(){
            return value;
        }
        public void setNext(Element n){
            next = n;
        }
    }
    private Element top;

    public void push(int v){
        Element e = new Element(v);
        e.setNext(top);
        top = e;
    }

    public int pop(){
        Element n = top;
        top = top.next;
        return n.getValue();
    }
}
