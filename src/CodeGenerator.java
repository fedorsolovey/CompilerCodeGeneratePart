import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedor on 04.11.15.
 */
public class CodeGenerator
{
    private Node nodeItem = new Node();
    private Map variableMapItem;// = new HashMap<String, VariableItem>();

    public CodeGenerator(Node nodeItem, Map variablesMapItem)
    {
        this.nodeItem = nodeItem;
        this.variableMapItem = variablesMapItem;

        this.generateVarBlock();
    }

    private void generateVarBlock()
    {

    }
}
