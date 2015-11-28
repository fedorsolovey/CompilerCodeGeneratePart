import java.util.ArrayList;
import java.util.Map;

/**
 * Created by fedor on 04.11.15.
 */
public class CodeGenerator
{
    private ArrayList<String> outputStrings = new ArrayList<String>(); //выходная очередь строк asm кода
    private int labels; //счетчик меток

    public CodeGenerator(Node nodeItem)
    {
        this.generateVarBlock(nodeItem);
        this.generateMainBlock(nodeItem);
        this.generateEndBlock();
    }

    public ArrayList<String> getOutputStrings() {
        return outputStrings;
    }

    private void generateVarBlock(Node node)
    {
        if (node.getLeft() != null && node.getLeft().getData().equals("var"))
        {
            if (node.getLeft().getRight() != null)
                outputStrings.add(node.getLeft().getLeft().getData() + " DW " + node.getLeft().getRight().getData());
            else
                outputStrings.add(node.getLeft().getLeft().getData() + " DW ?");
        }
        else if (node.getData().equals("begin"))
        {
            outputStrings.add("\t");
            outputStrings.add("program:");
            return;
        }

        if (node.getRight() != null)
            this.generateVarBlock(node.getRight());
    }

    //генерация блока begin ... end.
    private void generateMainBlock(Node node)
    {
        if (node.getData().equals(":="))
        {
            this.generateAssignment(node);
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

    /**
     * генерация присвaивания :=
     * @param node
     */
    private void generateAssignment(Node node)
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

    /**
     * генерация выражения
     * @param node
     * @param level
     */
    private void generateExpression(Node node, int level)
    {
        if (node.getLeft() != null && node.getRight() != null)
        {
            this.generateExpression(node.getLeft(), level + 1);
            this.generateExpression(node.getRight(), level + 1);
        }
        else
        {
            outputStrings.add("MOV AX, " + node.getData());

            this.generatePush(level);
        }

        if (node.getData().equals("+"))
        {
            this.generatePops();

            outputStrings.add("ADD AX, BX");

            this.generatePush(level);
        }
        else if (node.getData().equals("-"))
        {
            this.generatePops();

            outputStrings.add("SUB AX, BX");

            this.generatePush(level);
        }
        else if (node.getData().equals("*"))
        {
            this.generatePops();

            outputStrings.add("IMUL AX, BX");

            this.generatePush(level);
        }
        else if (node.getData().equals("/"))
        {
            this.generatePops();

            outputStrings.add("IDIV AX, BX");

            this.generatePush(level);
        }
        else if (node.getData().equals("<")
                || node.getData().equals(">")
                || node.getData().equals("<=")
                || node.getData().equals(">=")
                || node.getData().equals("<>")
                || node.getData().equals("=="))
        {
            int label1 = labels;
            labels++;
            int label2 = labels;
            labels++;

            this.generatePops();

            outputStrings.add(" CMP AX, BX");

            if (node.getData().equals("<"))
                outputStrings.add(" JL m" + label1);
            else if (node.getData().equals("<="))
                outputStrings.add(" JLE m" + label1);
            else if (node.getData().equals("=="))
                outputStrings.add(" JE m" + label1);
            else if (node.getData().equals("<>"))
                outputStrings.add(" JNZ m" + label1);
            else if (node.getData().equals(">"))
                outputStrings.add(" JG m" + label1);
            else if (node.getData().equals(">="))
                outputStrings.add(" JGE m" + label1);

            outputStrings.add(" XOR AX, AX");
            outputStrings.add(" JMP m" + label2);
            outputStrings.add("m" + label1 + ": MOV AX, 1");
            outputStrings.add("m" + label2 + ":");

            this.generatePush(level);
        }
        else if (node.getData().equals("nand"))
        {
            this.generatePops();

            outputStrings.add(" NOT BX");
            outputStrings.add(" AND AX, BX");

            this.generatePush(level);
        }
        else if (node.getData().equals("xor"))
        {
            this.generatePops();

            outputStrings.add("XOR AX, BX");

            this.generatePush(level);
        }
    }

    //генерация условия перехода (if)
    private void generateCompare(Node node)
    {
        int label1 = labels;
        labels++;
        int label2 = labels;
        labels++;
    
        if (node.getLeft() != null)
            this.generateExpression(node.getLeft(), 0);
            
        outputStrings.add(" CMP AX, 0");
        outputStrings.add(" JZ m" + label1);
        node = node.getRight();

        if (node.getLeft() != null)
            this.generateMainBlock(node.getLeft());

        outputStrings.add(" JMP m" + label2);
        outputStrings.add("m" + label1 + ":");

        if (node.getRight() != null)
            this.generateMainBlock(node.getRight());

        outputStrings.add("m" + label2 + ":");
    }

    //генераци конца программы
    private void generateEndBlock()
    {
        outputStrings.add("\n");
        outputStrings.add("MOV AX, 4C00h");
        outputStrings.add("INT 21h");
        outputStrings.add("END program");
    }

    /**
     * Генерация извлечения из стэка
     */
    private void generatePops()
    {
        outputStrings.add("POP BX");
        outputStrings.add("POP AX");
    }

    /**
     * Генерация занесения в стэка
     * @param level
     */
    private void generatePush(int level)
    {
        if (level > 0)
            outputStrings.add("PUSH AX");
    }
}
