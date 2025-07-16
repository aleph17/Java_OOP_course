package humanresources;

public class Employee {
    protected String name;
    protected int idNumber;
    protected int wage;
    public Employee(){System.out.println("going through default");
    }
    public Employee(String name, int idNumber){
        this.name = name;
        this.idNumber = idNumber;
        System.out.println("Employee constructed");
    }
    public void print(){
        System.out.println(name);
        System.out.println(idNumber);
    }
    public int addWage(int bonus){
        this.wage+=bonus;
        return wage;
    }
    @Override public boolean equals(Object ot){
        Employee other = (Employee) ot;
        return this.name.equals(other.name);
    }
    @Override public String toString(){
        return this.name;
    }
}
