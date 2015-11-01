/**
 * Created by fedor on 26.10.15.
 */
public class Node {
    private Node root;
    private String data;
    private Node left;
    private Node right;

    public Node() {
        root = null;
    }

    public void insert(String[] data) {
        root = insert(root, data);
    }

    private Node insert(Node current, String[] data)
    {
        if (current == null || (data[2] == "0" && data[3] == "0"))
        {
            current = new Node();
            current.setData(data[1]);
            current.setLeft(null);
            current.setRight(null);
        }
        else if (data[2] == "0" && data[3] != "0")
            current.setRight(insert(current.getRight(), data));
        else if (data[2] != "0" && data[3] == "0")
            current.setLeft(insert(current.getLeft(), data));
        else if (data[2] != "0" && data[3] != "0")
        {
            current.setRight(insert(current.getRight(), data));
            current.setRight(insert(current.getLeft(), data));
        }
        return current;
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
