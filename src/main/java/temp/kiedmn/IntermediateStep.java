package temp.kiedmn;

public class IntermediateStep implements NecessaryStep {

    private DRGNode intermediateDecision;

    public IntermediateStep(DRGNode requestedDecision) {
        super();
        this.intermediateDecision = requestedDecision;
    }

    @Override
    public DRGNode getStep() {
        return intermediateDecision;
    }

    @Override
    public String toString() {
        return "IntermediateStep [intermediateDecision=" + intermediateDecision + "]";
    }

}
