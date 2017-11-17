package temp.kiedmn;


public abstract class DRGNode {

    private String name;

    public DRGNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DRGNode [name=" + name + "]";
    }

}
