import java.util.*;

public class BinaryTree<E> {
    private TreeNode<E> root;

    public BinaryTree(TreeNode<E> root) {
        this.root = root;
    }

    public double findClosest(E target) {
        BinaryHeap<TreeNodeWithDistance<E>> heap = new BinaryHeap<>();
        heap.insert(new TreeNodeWithDistance<>(root, 0.0));

        while (!heap.isEmpty()) {
            TreeNodeWithDistance<E> current = heap.extractMin();
            TreeNode<E> node = current.node;
            double distanceFromRoot = current.distanceFromRoot;

            if (node.element.equals(target)) {
                return distanceFromRoot;
            }

            for (TreeNode<E> child : node.children) {
                heap.insert(new TreeNodeWithDistance<>(child, distanceFromRoot + child.distance));
            }
        }

        throw new NoSuchElementException("Element " + target + " not found!");
    }

    private static class TreeNodeWithDistance<E> implements Comparable<TreeNodeWithDistance<E>> {
        TreeNode<E> node;
        double distanceFromRoot;

        TreeNodeWithDistance(TreeNode<E> node, double distanceFromRoot) {
            this.node = node;
            this.distanceFromRoot = distanceFromRoot;
        }

        public int compareTo(TreeNodeWithDistance<E> other) {
            return Double.compare(this.distanceFromRoot, other.distanceFromRoot);
        }
    }

    public static <E> BinaryTree<E> readFlexibleTree(Scanner scanner) {
        Stack<Object> stack = new Stack<>();

        while (scanner.hasNext()) {
            String token = scanner.next();

            if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                List<TreeNode<E>> children = new ArrayList<>();
                while (!stack.isEmpty() && stack.peek() instanceof TreeNode) {
                    children.add(0, (TreeNode<E>) stack.pop());
                }
                stack.pop(); // remove "("
                TreeNode<E> parent = (TreeNode<E>) stack.pop();
                parent.children.addAll(children);
                stack.push(parent);
            } else {
                E element = (E) token;
                double dist = Double.parseDouble(scanner.next());
                TreeNode<E> node = new TreeNode<>(element, dist);
                stack.push(node);
            }
        }

        return new BinaryTree<>((TreeNode<E>) stack.pop());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your space and parentheses-separated tree below:");
        String input = scanner.nextLine();
        Scanner treeScanner = new Scanner(input);

        BinaryTree<String> tree = BinaryTree.readFlexibleTree(treeScanner);

        try {
            double distance = tree.findClosest("*");
            System.out.println("Found '*' at distance " + distance + ".");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
