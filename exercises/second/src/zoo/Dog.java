package zoo;

public class Dog extends Animal implements Pet, Comparable{
    public String name;
    private int height;

    public Dog(String name, String color, int height){
        super(4, color);
        this.name = name;
        this.height = height;
    }
    /// made comparable
    @Override
    public int compareTo(Object ot){
        Dog other = (Dog) ot;
        return this.height-other.height;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public void eat() {
        if(!this.isAlive())
            System.out.println("too late - dead");
        else if( vitality + 10> maxVitality)
            vitality = maxVitality;
        else{
            vitality +=10;
        }
    }

    @Override
    public void sleep() {
        if(!this.isAlive())
            System.out.println("too late - dead");
        else if( vitality + 10> maxVitality)
            vitality = maxVitality;
        else{
            vitality +=10;
        }
    }

    @Override
    public void move() {
        if(!this.isAlive())
            System.out.println("dead");
        else{
            System.out.println("running");
            vitality -=10*numberOfLimbs;
        }
    }

    @Override
    public void makeNoise() {
        if(!this.isAlive())
            System.out.println("dead");
        else{
            System.out.println("woof woof");
            vitality -=10*numberOfLimbs;
        }
    }

    @Override
    public void cuddling() {
        System.out.println("yeahh");
    }
}
