package diet;

public class RawMaterial implements NutritionalElement{
    public String name;
    public double proteins;
    public double calories;
    public double carbs;
    public double fat;
    public boolean perCentoG;
    public RawMaterial(String name, double proteins, double calories, double carbs, double fat, boolean perCentoG){
        this.name = name; this.calories = calories; this.proteins = proteins; this.carbs = carbs;
        this.fat = fat; this.perCentoG = perCentoG;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public double getProteins() {
        return proteins;
    }

    @Override
    public double getCarbs() {
        return carbs;
    }

    @Override
    public double getFat() {
        return fat;
    }

    @Override
    public boolean per100g() {
        return perCentoG;
    }
}
