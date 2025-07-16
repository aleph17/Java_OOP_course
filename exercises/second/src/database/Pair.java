package database;

public class Pair<T,U> {
    public T a;
    public U b;
    public Pair(T a, U b){
        this.a = a;
        this.b = b;
    }
    public T first(){return a;}
    public U second(){return b;}
    public void set1st(T x){this.a = x;}
    public void set2nd(U y){this.b = y;}
    public static <T> boolean contains(Pair p, T element){
        return p.a.equals(element) || p.b.equals(element);
    }
    @Override
    public String toString(){
        return a.toString()+" "+b.toString();
    }
}
