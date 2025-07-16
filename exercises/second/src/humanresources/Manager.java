package humanresources;

public class Manager extends Employee {
     public String managedUnit;
     public Manager(){};
     public Manager(String name, int idNumber){
//         super(name, idNumber);
         this.name = name;
         this.idNumber = idNumber;
         System.out.println("Manager constructed");
     }
     public Manager(String name, int idNumber, String unit){
         this.name = name;
         this.idNumber = idNumber;
         this.managedUnit = unit;
     }
     public void giveMoney(){
         System.out.println("giving $$$");
     }
     @Override
     public void print(){
//         super.print();
        System.out.println(name);
        System.out.println(idNumber);
        System.out.println(managedUnit);
     }
}
