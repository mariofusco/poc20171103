package temp.kiedmn;


public class ToBeEvaluated implements DRGNodeWrapper {

    private DRGNode nextDecision;

    public ToBeEvaluated(DRGNode nextDecision) {
        super();
        this.nextDecision = nextDecision;
    }

    @Override
    public DRGNode getStep() {
        return nextDecision;
    }

    @Override
    public String toString() {
        return "ToBeEvaluated [nextDecision=" + nextDecision + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nextDecision == null) ? 0 : nextDecision.hashCode());
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
        ToBeEvaluated other = (ToBeEvaluated) obj;
        if (nextDecision == null) {
            if (other.nextDecision != null)
                return false;
        } else if (!nextDecision.equals(other.nextDecision))
            return false;
        return true;
    }

}
