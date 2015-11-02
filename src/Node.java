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
        root = null;
    }

    //вставка
    public void insert(String[] data) {
        root = insert(root, data);
    }

    private Node insert(Node current, String[] data)
    {
        if (data.length < 4)
            System.out.println("WTAF" + data[0]);

        if (current == null || (data[2] == "0" && data[3] == "0"))
        {
            current = new Node();
            current.setData(data[1]);
            current.setLeft(null);
            current.setRight(null);
        }
        else if (data[3] != "0")
            current.setRight(insert(current.getRight(), data));
        else if (data[2] != "0")
            current.setLeft(insert(current.getLeft(), data));
//        else if (data[2] != "0" && data[3] != "0")
//        {
//            current.setRight(insert(current.getRight(), data));
//            current.setLeft(insert(current.getLeft(), data));
//        }
        return current;
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
