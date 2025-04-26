import java.util.*;

public class TreeNode<E> implements Comparable<TreeNode<E>> {
    public E element;
    public double distance; // distance from parent
    public List<TreeNode<E>> children;

    public TreeNode(E element, double distance) {
        this.element = element;
        this.distance = distance;
        this.children = new ArrayList<>();
    }

    public int compareTo(TreeNode<E> other) {
        return Double.compare(this.distance, other.distance);
    }

    public String toString() {
        return element.toString();
    }
}
