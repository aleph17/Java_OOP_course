package zoo;

public class Horse extends Animal implements Pet, Vehicle {

    public Horse(int numOfLimbs, String color){
        super(numOfLimbs, color);
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
            System.out.println("hiii");
            vitality -=10*numberOfLimbs;
        }
    }

    @Override
    public void cuddling() {
        System.out.println("yeahh");
    }

    @Override
    public void ride(){
        System.out.println("riding the horse");
        this.move();
    }
    @Override
    public void getOff(){
        System.out.println("getting off");
        this.cuddling();
    }
}
