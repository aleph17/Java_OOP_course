package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {

	/**
	 * Constructor
	 * @param name name of the sink element
	 */
	public Sink(String name) {
		super(name);
	}
	@Override
	public void connect(Element elem){
		// do nothing
	}
	@Override
	void simulate(SimulationObserver observer, double inFlow, boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck && inFlow > maxFlow){
			observer.notifyFlowError("Sink", getName(), inFlow, maxFlow);
		}

		observer.notifyFlow("Sink", getName(), inFlow, SimulationObserver.NO_FLOW);
	}

}
