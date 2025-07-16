package hydraulic;


import java.util.Arrays;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 * 
 * The class is abstract since it is not intended to be instantiated,
 * though all methods are defined to make subclass implementation easier.
 */
public abstract class Element {
	protected String name;
	protected Element[] outputs;
	protected Element input;
	protected double maxFlow;

	/**
	 * getter method for the name of the element
	 * 
	 * @return the name of the element
	 */
	public Element(String name){
		this.name = name;
		outputs = new Element[1];
	}
	public Element(String name, final int num){
		this.name = name;
		outputs = new Element[num];
	}
	public String getName() {
		return name;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * 
	 * In case of element with multiple outputs this method operates on the first one,
	 * it is equivalent to calling {@code connect(elem,0)}. 
	 * 
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem) {
		outputs[0] = elem;
		elem.input = this;
	}
	
	/**
	 * Connects a specific output of this element to a given element.
	 * The given element will be connected downstream of this element
	 * 
	 * @param elem the element that will be placed downstream
	 * @param index the output index that will be used for the connection
	 */
	public void connect(Element elem, int index){
		outputs[index] = elem;
		elem.input = this;
	}
	
	/**
	 * Retrieves the single element connected downstream of this element
	 * 
	 * @return downstream element
	 */
	public Element getOutput(){
		return outputs[0];
	}

	/**
	 * Retrieves the elements connected downstream of this element
	 * 
	 * @return downstream element
	 */
	public Element[] getOutputs(){
		return Arrays.copyOf(outputs, outputs.length);
	}
	public Element getInput(){
		return input;
	}
	public void setInput(Element input){
		this.input = input;
	}
	
	/**
	 * Defines the maximum input flow acceptable for this element
	 * 
	 * @param maxFlow maximum allowed input flow
	 */
	public void setMaxFlow(double maxFlow) {
		this.maxFlow = maxFlow;
	}
	public double getmaxFlow(){
		return maxFlow;
	}
	public boolean destroy(){
		if(outputs.length>1){
			int count=0;
			for(Element e:outputs){
				if (e!=null)
					count++;
			}
			if(count>1) return false;
		}
		Element out = outputs[0];
		if(input!=null){
			for(int i=0;i<input.outputs.length;i++){
				if(input.outputs[i]==this){
					input.outputs[i]=out;
					if(out!=null)
						out.input=input;
				}
			}
		}
		else{
			if(out!=null)
				out.setInput(null);
		}
		return true;
	}
	abstract void simulate(SimulationObserver ovserver, double inFlow, boolean enableMaxFlowCheck);

	protected static String pad(String current, String down){
		int n = current.length();
		final String fmt = "\n%"+n+"s";
		return current + down.replace("\n", fmt.formatted("") );
	}

	@Override
	public String toString(){
		String res = "[%s] ".formatted(getName());
		Element[] out = getOutputs();
		if( out != null){
			StringBuilder buffer = new StringBuilder();
			for(int i=0; i<out.length; ++i) {
				if(i>0) buffer.append("\n");
				if (out[i] == null) buffer.append("+-> *");
				else buffer.append(pad("+-> ", out[i].toString()));
			}
			res = pad(res,buffer.toString());
		}
		return res;
	}


}
