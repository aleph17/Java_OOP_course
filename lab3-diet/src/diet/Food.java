package diet;

import java.util.*;

/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {
	private TreeMap<String, NutritionalElement> materials = new TreeMap<>();
	private TreeMap<String, NutritionalElement> products = new TreeMap<>();
	private TreeMap<String, NutritionalElement> recipes = new TreeMap<>();


	/**
	 * Define a new raw material.
	 * The nutritional values are specified for a conventional 100g quantity
	 * @param name unique name of the raw material
	 * @param calories calories per 100g
	 * @param proteins proteins per 100g
	 * @param carbs carbs per 100g
	 * @param fat fats per 100g
	 */
	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat) {
		RawMaterial rm = new RawMaterial(name, proteins, calories, carbs, fat, true);
		materials.put(name, rm);
	}

	/**
	 * Retrieves the collection of all defined raw materials
	 * @return collection of raw materials though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> rawMaterials() {
		List<NutritionalElement> needed = new ArrayList<>(materials.values());
		Collections.sort(needed, Comparator.comparing(NutritionalElement::getName));
		return needed;
	}

	/**
	 * Retrieves a specific raw material, given its name
	 * @param name  name of the raw material
	 * @return  a raw material though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRawMaterial(String name) {
		return materials.get(name);
	}

	/**
	 * Define a new packaged product.
	 * The nutritional values are specified for a unit of the product
	 * @param name unique name of the product
	 * @param calories calories for a product unit
	 * @param proteins proteins for a product unit
	 * @param carbs carbs for a product unit
	 * @param fat fats for a product unit
	 */
	public void defineProduct(String name, double calories, double proteins, double carbs, double fat) {
		Product p = new Product(name, proteins, calories, carbs, fat, false);
		products.put(name, p);
	}

	/**
	 * Retrieves the collection of all defined products
	 * @return collection of products though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> products() {
		List<NutritionalElement> needed = new ArrayList<>(products.values());
		Collections.sort(needed, (a,b)->a.getName().compareTo(b.getName()));
		return needed;
//		return products.values();
	}

	/**
	 * Retrieves a specific product, given its name
	 * @param name  name of the product
	 * @return  a product though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getProduct(String name) {
		return products.get(name);
	}

	/**
	 * Creates a new recipe stored in this Food container.
	 *  
	 * @param name name of the recipe
	 * @return the newly created Recipe object
	 */
	public Recipe createRecipe(String name) {
		Recipe rp = new Recipe(name, this);
		recipes.put(name, rp);
		return rp;
	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes() {
		List<NutritionalElement> needed = new ArrayList<>(recipes.values());
		Collections.sort(needed, Comparator.comparing(NutritionalElement::getName));
		return needed;
	}

	/**
	 * Retrieves a specific recipe, given its name
	 * @param name  name of the recipe
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name) {
		return recipes.get(name);
	}

	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		return new Menu(name, this);
	}
}