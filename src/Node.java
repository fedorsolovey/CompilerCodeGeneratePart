/**
 * Created by fedor on 26.10.15.
 */
public class Node {
    private Node root;
    private int data;
    private Node left;
    private Node right;

    public void Node()
    {
        root = null;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node current, int data) {
        if (current == null) {
            current = new Node();
            current.setData(data);
            current.setLeft(null);
            current.setRight(null);
        } else if (data < current.getData()) {
            current.setLeft(insert(current.getLeft(), data));
        } else {
            current.setRight(insert(current.getRight(), data));
        }
        return current;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
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
