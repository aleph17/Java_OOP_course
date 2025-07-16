package mountainhuts;

import java.util.Optional;

/**
 * Represents a mountain hut
 * 
 * It includes a name, optional altitude, category,
 * number of beds and location municipality.
 *  
 *
 */
public class MountainHut {
	private String name;
	private Integer altitude;
	private String category;
	private Municipality municipality;
	private Integer bedNumber;

	public MountainHut(String name, String category, Integer bedNumber, Municipality municipality, Integer altitude){
		this.name = name; this.altitude = altitude; this.bedNumber = bedNumber; this.municipality = municipality; this.category = category;
	}
	public MountainHut(String name, String category, Integer bedNumber, Municipality municipality){
		this.name = name; this.bedNumber = bedNumber; this.municipality = municipality; this.category = category;
	}

	/**
	 * Retrieves the name of the hut
	 * @return name of the hut
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves altituted if available
	 * 
	 * @return optional hut altitude
	 */
	public Optional<Integer> getAltitude() {
		return Optional.ofNullable(altitude);
	}

	/**
	 * Retrieves the category of the hut
	 * @return hut category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Retrieves the number of beds available in the hut
	 * @return number of beds
	 */
	public Integer getBedsNumber() {
		return bedNumber;
	}

	/**
	 * Retrieves the municipality of the hut
	 * @return hut municipality
	 */
	public Municipality getMunicipality() {
		return municipality;
	}
}
