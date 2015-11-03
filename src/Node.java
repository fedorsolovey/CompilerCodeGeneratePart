import java.util.ArrayList;

/**
 * Created by fedor on 26.10.15.
 */
public class Node {
    private Node root;
    private String data;
    private Node left;
    private Node right;

    public enum TraverseType {
        INORDER,
        PREORDER,
        POSTORDER;
    }

    public Node() {

    }

    //вставка
    public static Node createTree(ArrayList<Object> data)
    {
        String[] stringArray = (String[]) data.get(0);
        return insert(stringArray[0], data);
    }

    private static Node insert(String rootAddress, ArrayList<Object> data)
    {
        String[] current_line = findLine(rootAddress, data);
        if (current_line == null)
            return null;

        Node nodeItem = new Node();

        nodeItem.setData(current_line[1]);
        nodeItem.setRight(null);
        nodeItem.setLeft(null);

        if (!current_line[3].equals("0"))
            nodeItem.setRight(insert(current_line[3], data));
        if (!current_line[2].equals("0"))
            nodeItem.setLeft(insert(current_line[2], data));

        return nodeItem;
    }

    private static String[] findLine(String address, ArrayList<Object> data)
    {
        for (int i = 0; i < data.size(); i++)
        {
            String[] stringArray = (String []) data.get(i);
            if (stringArray[0].equals(address))
                return stringArray;
        }
        return null;
    }

    // обход
    public void traverseTree(TraverseType traverseType)
    {
        traverseTree(root, traverseType);
        System.out.println();
    }

    private void traverseTree(Node current, TraverseType traverseType)
    {
        if (current == null)
            return;
        switch (traverseType) {
            case INORDER:
                traverseTree(current.getLeft(), traverseType);
                System.out.println(current.getData());
                traverseTree(current.getRight(), traverseType);
                break;
            case PREORDER:
                System.out.println(current.getData());
                traverseTree(current.getLeft(), traverseType);
                traverseTree(current.getRight(), traverseType);
                break;
            case POSTORDER:
                traverseTree(current.getLeft(), traverseType);
                traverseTree(current.getRight(), traverseType);
                System.out.println(current.getData());
                break;
        }
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


}
