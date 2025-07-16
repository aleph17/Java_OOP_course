package zoo;

public abstract class Animal {
    protected int numberOfLimbs;
    protected String color;
    protected int vitality; // <=0 animal is dead >0<100
    final static int maxVitality = 100;

    public abstract void eat();
    public abstract void sleep();
    public abstract void move();
    public abstract void makeNoise();

    public Animal(int numberOfLimbs, String color){
        this.color = color;
        this.numberOfLimbs = numberOfLimbs;
        this.vitality = maxVitality;
    }
    public boolean isAlive(){
        return (vitality)>0 ? true:false;
    }
    public int getVitality(){
        return this.vitality;
    }
}
