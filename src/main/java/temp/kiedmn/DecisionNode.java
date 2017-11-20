package temp.kiedmn;

import java.util.function.Function;

import org.kie.dmn.api.core.DMNContext;

public class DecisionNode extends DRGNode {

    private Function<DMNContext, DMNUnit> factoryFn;

    public DecisionNode(String id, String name, Function<DMNContext, DMNUnit> factoryFn) {
        super(name);
        this.factoryFn = factoryFn;
    }

    public DMNUnit getUnit(DMNContext ctx) {
        return factoryFn.apply(ctx);
    }
}
