import java.util.ArrayList;
import java.util.Map;

/**
 * Created by fedor on 04.11.15.
 */
public class CodeGenerator
{
//    private Node nodeItem = new Node();
    private ArrayList<String> outputStrings = new ArrayList<String>();

    public CodeGenerator(Node nodeItem, Map variablesMapItem)
    {
        this.generateVarBlock(variablesMapItem);
        this.generateMainBlock(nodeItem);
        this.generateEndBlock();
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
        outputStrings.add("program:");
    }

    //генерация блока begin ... end.
    private void generateMainBlock(Node node)
    {
        if (node.getData().equals(":="))
        {
            this.generateAssigment(node);
            return;
        }
        else if (node.getData().equals("if"))
        {
            this.generateCompare(node);
            return;
        }

        if (node.getLeft() != null)
            this.generateMainBlock(node.getLeft());

        if (node.getRight() != null)
            this.generateMainBlock(node.getRight());
    }

    //генерация присвaивания :=
    private void generateAssigment(Node node)
    {
        if (node.getLeft().getRight() != null || node.getRight().getRight() != null)
        {
            this.generateExpression(node.getRight(), 0);
            outputStrings.add("MOV " + node.getLeft().getData() + ", AX");
        }
        else if (node.getRight().getData().matches("-?\\d+(\\.\\d+)?"))
            outputStrings.add("MOV " + node.getLeft().getData() + ", " + node.getRight().getData());
        else
        {
            outputStrings.add("MOV AX, " + node.getRight().getData());
            outputStrings.add("MOV " + node.getLeft().getData() + ", AX");
        }
    }

    //генерация выражения
    private  void generateExpression(Node node, int level)
    {
        if (node.getLeft() != null && node.getRight() != null)
        {
            this.generateExpression(node.getLeft(), level + 1);
            this.generateExpression(node.getRight(), level + 1);
        }
        else
        {
            outputStrings.add("MOV AX, " + node.getData());

            if (level > 0)
                outputStrings.add("PUSH AX");
        }

        if (node.getData().equals("+"))
        {
            outputStrings.add("POP BX");
            outputStrings.add("POP AX");
            outputStrings.add("ADD AX, BX");

            if (level > 0)
                outputStrings.add("PUSH AX");
        }
        else if (node.getData().equals("-"))
        {
            outputStrings.add("POP BX");
            outputStrings.add("POP AX");
            outputStrings.add("SUB AX, BX");

            if (level > 0)
                outputStrings.add("PUSH AX");
        }
        else if (node.getData().equals("*"))
        {
            outputStrings.add("POP BX");
            outputStrings.add("POP AX");
            outputStrings.add("IMUL AX, BX");

            if (level > 0)
                outputStrings.add("PUSH AX");
        }
        else if (node.getData().equals("/"))
        {
            outputStrings.add("POP BX");
            outputStrings.add("POP AX");
            outputStrings.add("IDIV AX, BX");

            if (level > 0)
                outputStrings.add("PUSH AX");
        }
        else if (node.getData().equals("<")
                || node.getData().equals(">")
                || node.getData().equals("<=")
                || node.getData().equals(">=")
                || node.getData().equals("<>")
                || node.getData().equals("xor"))
        {

        }
        else if (node.getData().equals("nand"))
        {

        }
    }

    //генерация условия перехода (if)
    private void generateCompare(Node node)
    {

//        if (node.getLeft() != null)
//            this.generateMainBlock(node);

    }

    //генераци конца программы
    private void generateEndBlock()
    {
        outputStrings.add("\n");
        outputStrings.add("MOV AX, 4C00h");
        outputStrings.add("INT 21h");
        outputStrings.add("END program");
    }
}
