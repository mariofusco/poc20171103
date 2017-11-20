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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requestedDecision == null) ? 0 : requestedDecision.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Requested other = (Requested) obj;
        if (requestedDecision == null) {
            if (other.requestedDecision != null)
                return false;
        } else if (!requestedDecision.equals(other.requestedDecision))
            return false;
        return true;
    }

}
