package diet;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	public HashMap<String, NutritionalElement> materials = new HashMap<>();
	public HashMap<String, Double> quantities = new LinkedHashMap<>();
	private String name;
	private Food food;
	/**
	 * Adds the given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe(String name, Food food){
		this.name = name; this.food = food;
	}
	public Recipe addIngredient(String material, double quantity) {
		materials.put(material, food.getRawMaterial(material));
		quantities.put(material, quantity);
		return this;
	}
	private double compute(Function<NutritionalElement, Double> functional){
		double result=0.0;
		double totalWeight=0.0;
		if(materials.isEmpty())
			return 0.0;
		for(NutritionalElement n: materials.values()){
			result += functional.apply(n)*quantities.get(n.getName());
			totalWeight += quantities.get(n.getName());
		}
		return result/totalWeight;
	}
	@Override
	public String getName() {
		return name;
	}
	private double calories = 0.0;
	private double proteins = 0.0;
	private double carbs = 0.0;
	private double fats = 0.0;

	
	@Override
	public double getCalories() {
		return compute(NutritionalElement::getCalories);
	}
	

	@Override
	public double getProteins() {
		return compute(NutritionalElement::getProteins);
	}

	@Override
	public double getCarbs() {
		return compute(NutritionalElement::getCarbs);
	}

	@Override
	public double getFat() {
		return compute(NutritionalElement::getFat);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String name : quantities.keySet()) {
			sb.append(name).append("\n");
		}
		return sb.toString();
	}
	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
}
