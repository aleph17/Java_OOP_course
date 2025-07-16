package hydraulic;

/**
 * Hydraulics system builder providing a fluent API
 */
public class HBuilder {
    public static final int MAX_DEPTH = 10;
    private HSystem system;
    private Element latest=null;

    private boolean splitOutput = false;
    private int outputIndex = 0;
    private Element[] parent = new Element[MAX_DEPTH];
    private int[] count = new int[MAX_DEPTH];
    private int topParent = -1;
    /**
     * Add a source element with the given name
     * 
     * @param name name of the source element to be added
     * @return the builder itself for chaining 
     */
    private void pushParent(Element e){
        parent[++topParent]=e;
        count[topParent] = outputIndex;
        outputIndex =0;
    }
    private Element popParent(){
        outputIndex = count[topParent];
        return parent[topParent--];
    }
    private Element currentParent(){
        return parent[topParent];
    }
    public HBuilder(){
        this.system = new HSystem();
    }
    public HBuilder addSource(String name) {
        Source newSource = new Source(name);
        system.addElement(newSource);
        latest = newSource;
        return this;
    }

    /**
     * returns the hydraulic system built with the previous operations
     * 
     * @return the hydraulic system
     */
    public HSystem complete() {
        return system;
    }
    private void link(Element e){
        system.addElement(e);
        if(splitOutput)
            currentParent().connect(e,outputIndex);
        else
            latest.connect(e);
        latest = e;
        splitOutput = false;
    }
    /**
     * creates a new tap and links it to the previous element
     * 
     * @param name name of the tap
     * @return the builder itself for chaining 
     */
    public HBuilder linkToTap(String name) {
        link(new Tap(name));
        return this;
    }

    /**
     * creates a sink and link it to the previous element
     * @param name name of the sink
     * @return the builder itself for chaining 
     */
    public HBuilder linkToSink(String name) {
        link(new Sink(name));
        return this;
    }

    /**
     * creates a split and links it to the previous element
     * @param name of the split
     * @return the builder itself for chaining 
     */
    public HBuilder linkToSplit(String name) {
        link(new Split(name));
        return this;
    }

    /**
     * creates a multisplit and links it to the previous element
     * @param name name of the multisplit
     * @param numOutput the number of output of the multisplit
     * @return the builder itself for chaining 
     */
    public HBuilder linkToMultisplit(String name, int numOutput) {
        link(new Multisplit(name, numOutput));
        return this;
    }

    /**
     * introduces the elements connected to the first output 
     * of the latest split/multisplit.
     * The element connected to the following outputs are 
     * introduced by {@link #then()}.
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder withOutputs() {
        pushParent(latest);
        splitOutput=true;
        return this;     
    }

    /**
     * inform the builder that the next element will be
     * linked to the successive output of the previous split or multisplit.
     * The element connected to the first output is
     * introduced by {@link #withOutputs()}.
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder then() {
        splitOutput = true;
        outputIndex++;
        return this;
    }

    /**
     * completes the definition of elements connected
     * to outputs of a split/multisplit. 
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder done() {
        popParent();
        return this;
    }

    /**
     * define the flow of the previous source
     * 
     * @param flow flow used in the simulation
     * @return the builder itself for chaining 
     */
    public HBuilder withFlow(double flow) {
        if(latest instanceof Source){
            Source t = (Source) latest;
            t.setFlow(flow);
        }
        return this;
    }

    /**
     * define the status of a tap as open,
     * it will be used in the simulation
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder open() {
        if(latest instanceof Tap){
            Tap t = (Tap) latest;
            t.setOpen(true);
        }
        return this;
    }

    /**
     * define the status of a tap as closed,
     * it will be used in the simulation
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder closed() {
        if(latest instanceof Tap){
            Tap t = (Tap) latest;
            t.setOpen(false);
        }
        return this;
    }

    /**
     * define the proportions of input flow distributed
     * to each output of the preceding a multisplit
     * 
     * @param props the proportions
     * @return the builder itself for chaining 
     */
    public HBuilder withPropotions(double[] props) {
        if(latest instanceof Multisplit) {
            Multisplit m = (Multisplit) latest;
            m.setProportions(props);
        }
        return this;
    }

    /**
     * define the maximum flow theshold for the previous element
     * 
     * @param max flow threshold
     * @return the builder itself for chaining 
     */
    public HBuilder maxFlow(double max) {
        latest.setMaxFlow(max);
        return this;
    }
}
