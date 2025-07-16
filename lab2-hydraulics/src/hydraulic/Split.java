package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {

	/**
	 * Constructor
	 * @param name name of the split element
	 */
	public Split(String name) {
		super(name, 2);
	}
	public Split(String name, int numOutput) {
		super(name, numOutput);
	}
	@Override
	void simulate(SimulationObserver observer, double inFlow, boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck && inFlow > maxFlow){
			observer.notifyFlowError("Split", getName(), inFlow, maxFlow);
		}
		observer.notifyFlow("Split", getName(), inFlow, inFlow/2,inFlow/2);
		for(Element e : getOutputs()){
			e.simulate(observer,inFlow/2, enableMaxFlowCheck);
		}
	}

}
