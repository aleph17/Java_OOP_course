package mountainhuts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {
	private String name;
	private String[] ranges;
	private HashMap<String, Municipality> municips = new HashMap<>();
	private HashMap<String, MountainHut> huts = new HashMap<>();

	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name = name;
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		this.ranges = ranges;
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		if(ranges!=null){
			for(String s:ranges){
				if(Integer.parseInt(s.split("-")[0])<=altitude &&
						Integer.parseInt(s.split("-")[1])>=altitude)
					return s;
			}
		}
		return "0-INF";
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * The returned collection is unmodifiable
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return Collections.unmodifiableCollection(municips.values());
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * The returned collection is unmodifiable
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return Collections.unmodifiableCollection(huts.values());
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		if(municips.keySet().contains(name))
			return municips.get(name);
		Municipality mn = new Municipality(name, province, altitude);
		municips.put(name, mn);
		return mn;
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, 
											  Integer bedsNumber, Municipality municipality) {
		if(huts.keySet().contains(name))
			return huts.get(name);
		MountainHut mh = new MountainHut(name, category, bedsNumber, municipality);
		huts.put(name, mh);
		return mh;
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, 
											  Integer bedsNumber, Municipality municipality) {
		if(huts.keySet().contains(name))
			return huts.get(name);
		MountainHut mh = new MountainHut(name, category, bedsNumber, municipality, altitude);
		huts.put(name, mh);
		return mh;
	}

	/**
	 * Creates a new region and loads its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region r = new Region(name);
		List<String> lines = new ArrayList<>(Region.readData(file));
		if(lines==null) return null;
		lines.remove(0);
		lines.forEach(row->{
			String[] cells = row.split(";");
			Municipality m = r.createOrGetMunicipality(cells[1],cells[0], Integer.parseInt(cells[2]));
			if(cells[4].equals(""))
				r.createOrGetMountainHut(cells[3], cells[5], Integer.parseInt(cells[6]),m);
			else
				r.createOrGetMountainHut(cells[3], Integer.parseInt(cells[4]), cells[5], Integer.parseInt(cells[6]),m);
		});
		return r;
	}

	/**
	 * Reads the lines of a text file.
	 *
	 * @param file path of the file
	 * @return a list with one element per line
	 */
	public static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().toList();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		Map<String, Long> prov = new HashMap<>();
		for(Municipality m: municips.values()){
			if(prov.keySet().contains(m.getProvince()))
				prov.put(m.getProvince(), prov.get(m.getProvince())+1);
			else
				prov.put(m.getProvince(), Long.valueOf(1));
		}
		return prov;
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		return huts.values().stream()
				.collect(Collectors.groupingBy(
						x->x.getMunicipality().getProvince(),
							Collectors.groupingBy(x->x.getMunicipality().getName(), Collectors.counting())
				));
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() { //check
//		return Arrays.stream(ranges).collect(Collectors.toMap(r -> r, r->huts.values().stream()
//				.filter(x->getAltitudeRange(x.getAltitude().orElse(x.getMunicipality().getAltitude())).equals(r)).count()));
		return huts.values().stream().map(hut->getAltitudeRange(hut.getAltitude().orElse(hut.getMunicipality().getAltitude())))
				.collect(Collectors.groupingBy(r->r, Collectors.counting()));
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		return huts.values().stream().collect(Collectors.groupingBy(
				x->x.getMunicipality().getProvince(),
				Collectors.summingInt(h->h.getBedsNumber())));
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		return huts.values().stream().collect(
				Collectors.groupingBy(hut->getAltitudeRange(hut.getAltitude().orElse(hut.getMunicipality().getAltitude())),
						Collectors.mapping(MountainHut::getBedsNumber, Collectors.maxBy(Integer::compare)))
		);

	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		return huts.values().stream().map(hut->hut.getMunicipality().getName()).collect(
				Collectors.groupingBy(x->x, TreeMap::new, Collectors.counting()))
				.entrySet().stream().collect(Collectors.groupingBy(
				x->x.getValue(),
				Collectors.mapping(x->x.getKey(),Collectors.toList())));
	}

}
