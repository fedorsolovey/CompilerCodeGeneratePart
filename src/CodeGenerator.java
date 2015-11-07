import java.util.ArrayList;
import java.util.Map;

/**
 * Created by fedor on 04.11.15.
 */
public class CodeGenerator
{
    private Node nodeItem = new Node();
    private ArrayList<String> outputStrings = new ArrayList<String>();

    public CodeGenerator(Node nodeItem, Map variablesMapItem)
    {
        this.nodeItem = nodeItem;

        this.generateVarBlock(variablesMapItem);
        this.generateMainBlock(nodeItem);
    }

    public ArrayList<String> getOutputStrings() {
        return outputStrings;
    }

    //генерация блока Var
    private void generateVarBlock(Map<String, VariableItem> variablesMapItem)
    {
        for(Map.Entry<String, VariableItem> itemMap : variablesMapItem.entrySet())
            outputStrings.add(itemMap.getKey() + " DW " + itemMap.getValue().getInitValue());

        outputStrings.add("\t");
        outputStrings.add("program_start:");
    }

    //генерация блока begin ... end.
    private void generateMainBlock(Node node)
    {
        if (node.getData().equals(":="))
        {
            this.generateAssigment(node);
            return;
        }

    }

    //генерация присвaивания :=
    private void generateAssigment(Node node)
    {
        if (node.getLeft().getRight() != null || node.getRight().getRight() != null)
        {

        }

    }

}
