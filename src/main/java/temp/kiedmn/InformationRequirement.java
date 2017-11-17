package temp.kiedmn;

import org.kie.api.definition.type.Position;

public class InformationRequirement {

    @Position(0)
    private DecisionNode decision;

    @Position(1)
    private DRGNode requires;

    public InformationRequirement(DecisionNode decision, DRGNode requires) {
        super();
        this.decision = decision;
        this.requires = requires;
    }

    public DecisionNode getDecision() {
        return decision;
    }

    public DRGNode getRequires() {
        return requires;
    }

}
