/**
 * Created by fedor on 26.10.15.
 */
public class Tree {
    private Node root;

    public Tree() {
        this.root = null;
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


}
