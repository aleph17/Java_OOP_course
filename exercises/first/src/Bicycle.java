public class Bicycle{
    //attributes
    private String color;
    private int idNumber;
    private Integer prodDate;
    public static int numProduced;

    boolean isWorking = false;
    boolean isRepainted = false;

    //constructors
    public Bicycle(String color, int idNumber){
        this.color = color;
        this.idNumber = idNumber;
        numProduced++;
    }

    public Bicycle(int idNumber, String color){
        this.color = color;
        this.idNumber = idNumber;
        numProduced++;
    }
    public Bicycle(Bicycle newBike){
        this.color = newBike.getColor();
        this.idNumber = newBike.getidNumber();
        this.prodDate = newBike.getProdDate();
    }

    public static Bicycle[] createBikes(int n){
        Bicycle[] bikes = new Bicycle[n];
        for (int i=0;i<n;i++){
            // Bicycle b = new Bicycle("white", 3+i);
            bikes[i] = new Bicycle("white", 3+i);
        }
        return bikes;
    }

    //methods
    public void color(String newc){
        this.color = newc;
    }
    public String getColor(){
        return this.color;
    }
    public int getidNumber(){
        return this.idNumber;
    }
    public Integer getProdDate(){
        return this.prodDate;
    }
    public void setColor(String color){
        this.color = color;
    }

    public Bicycle fix(){
        this.isWorking = true;
        return this;
    }
    public Bicycle paint(){
        this.isRepainted = true;
        return this;
    }
    public boolean restored(){
        if (this.isRepainted=true && this.isWorking)
            return true;
        else
            return false;
    }
}