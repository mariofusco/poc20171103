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


}
