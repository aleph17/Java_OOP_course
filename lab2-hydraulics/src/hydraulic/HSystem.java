package hydraulic;

import java.util.Arrays;

/**
 * Main class that acts as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	public static final int MAX_ELEMENTS =100;
	Element[] elements = new Element[MAX_ELEMENTS];
	public int nextElem = 0;
// R1
	/**
	 * Adds a new element to the system
	 * 
	 * @param elem the new element to be added to the system
	 */
	public void addElement(Element elem){
		elements[nextElem++] = elem;
	}

	/**
	 * returns the number of element currently present in the system
	 * 
	 * @return count of elements
	 */
	public int size() {
		return nextElem;
    }

	/**
	 * returns the element added so far to the system
	 * 
	 * @return an array of elements whose length is equal to 
	 * 							the number of added elements
	 */
	public Element[] getElements(){
		return Arrays.copyOfRange(elements, 0,nextElem);
	}

// R4
	/**
	 * starts the simulation of the system
	 * 
	 * The notification about the simulations are sent
	 * to an observer object
	 * 
	 * Before starting simulation the parameters of the
	 * elements of the system must be defined
	 * 
	 * @param observer the observer receiving notifications
	 */
	public void simulate(SimulationObserver observer){
		simulate(observer, false);
	}
	@Override
	public String toString(){
		for(Element e:elements){
			if(e instanceof Source){
				return e.toString();
			}
		}
		return "";
	}


// R6
	/**
	 * Deletes a previously added element 
	 * with the given name from the system
	 */
	public boolean deleteElement(String name) {
		Element elem=null;
		for(int i=0;i<nextElem;i++){
			if(elements[i].name.equals(name))
				elem = elements[i];
		}
		if(elem==null) return false;
		if(elem.destroy()){
			remove(elem);
			return true;
		}
		else return false;
	}
	private void remove(Element elem){
		boolean found=false;
		for(int i=0;i<nextElem-1;i++){
			if(elements[i]==elem)
				found = true;
			if(found)
				elements[i] = elements[i+1];
		}
		elements[nextElem-1]=null;
		nextElem--;
	}

// R7
	/**
	 * starts the simulation of the system; if {@code enableMaxFlowCheck} is {@code true},
	 * checks also the elements maximum flows against the input flow
	 * 
	 * If {@code enableMaxFlowCheck} is {@code false}  a normals simulation as
	 * the method {@link #simulate(SimulationObserver)} is performed
	 * 
	 * Before performing a checked simulation the max flows of the elements in thes
	 * system must be defined.
	 */
	public void simulate(SimulationObserver observer, boolean enableMaxFlowCheck) {
		for(Element e: elements){
			if(e instanceof Source){
				e.simulate(observer,SimulationObserver.NO_FLOW, enableMaxFlowCheck);
			}
		}
	}

// R8
	/**
	 * creates a new builder that can be used to create a 
	 * hydraulic system through a fluent API 
	 * 
	 * @return the builder object
	 */
    public static HBuilder build() {
		return new HBuilder();
    }
}
