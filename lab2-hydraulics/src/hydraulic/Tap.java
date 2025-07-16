package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {
	public boolean open;
	/**
	 * Constructor
	 * @param name name of the tap element
	 */
	public Tap(String name) {
		super(name);
	}

	/**
	 * Set whether the tap is open or not. The status is used during the simulation.
	 *
	 * @param open opening status of the tap
	 */
	public void setOpen(boolean open){
		this.open = open;
	}
	@Override
	void simulate(SimulationObserver observer, double inFlow, boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck && inFlow > maxFlow){
			observer.notifyFlowError("Tap", getName(), inFlow, maxFlow);
		}
		double outFlow = open?inFlow:0.0;
		observer.notifyFlow("Tap", getName(), inFlow, outFlow);
		getOutput().simulate(observer,outFlow, enableMaxFlowCheck);
	}


}
