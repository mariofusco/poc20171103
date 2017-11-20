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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((intermediateDecision == null) ? 0 : intermediateDecision.hashCode());
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
        IntermediateStep other = (IntermediateStep) obj;
        if (intermediateDecision == null) {
            if (other.intermediateDecision != null)
                return false;
        } else if (!intermediateDecision.equals(other.intermediateDecision))
            return false;
        return true;
    }

}
