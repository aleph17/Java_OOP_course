public class BicycleStorage {
    public Bicycle [] storage;
    public int occupation;

    public BicycleStorage(int initialSize){
        this.storage = new Bicycle[initialSize];
        this.occupation = 0;
    }

    public void addBike(Bicycle arriving){
        if(this.occupation<storage.length){
            storage[occupation] = arriving;
            occupation++;
        }
        else{
            System.out.println("Full!!");
        }
    }

    public void findBikeByColor(String color){
        boolean found =false;
        for(int i=0; i < occupation; i++){
            if(storage[i].getColor().equals(color)){
                System.out.println(storage[i].getidNumber());
            }
        }
        if (!found) System.out.println("missing!");
    }

    public void addSpace(int newSize){
        Bicycle [] newStorage = new Bicycle[newSize];
        for(int i=0; i< occupation; i++)
            newStorage[i]= this.storage[i];
        this.storage = newStorage;
    }
}