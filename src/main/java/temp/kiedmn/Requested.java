package temp.kiedmn;


public class Requested implements NecessaryStep {

    private DecisionNode requestedDecision;

    public Requested(DecisionNode requestedDecision) {
        super();
        this.requestedDecision = requestedDecision;
    }

    @Override
    public DecisionNode getStep() {
        return requestedDecision;
    }

    @Override
    public String toString() {
        return "Requested [requestedDecision=" + requestedDecision + "]";
    }

}
