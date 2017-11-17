package temp.kiedmn;


public class Computed implements NecessaryStep {

    private DRGNode computedNode;

    public Computed(DRGNode computedNode) {
        super();
        this.computedNode = computedNode;
    }

    @Override
    public DRGNode getStep() {
        return computedNode;
    }

    @Override
    public String toString() {
        return "Computed [computedNode=" + computedNode + "]";
    }

}
