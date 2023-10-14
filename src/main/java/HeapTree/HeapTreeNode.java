package HeapTree;

public class HeapTreeNode {
    int priority;
    int depth;
    int children;
    HeapTreeNode left;
    HeapTreeNode right;
    HeapTreeNode(int priority){
        this.priority = priority;
        depth = 1;
        left = null;
        right = null;
        children = 0;
    }

    void add(HeapTreeNode newNode){
        if (newNode.priority < priority) {
            swapPrio(this, newNode);
        }
        children +=1;
        newNode.depth += 1;
        if (left == null) {

                left = newNode;
        }
        else if (right == null) {

                right = newNode;
        }
        else {
                HeapTreeNode smallestSubtree = (left.children < right.children)
                        ? left:right;
                smallestSubtree.add(newNode);
        }
    }

    void lift() {
        children--;
        if (left != null && right != null) {
            if (left.priority < right.priority) {
                priority = left.priority;
                if (left.children == 0) {
                    left = null;
                } else {
                    left.lift();
                }
            } else {
                priority = right.priority;
                if (right.children == 0) {
                    right = null;
                } else {
                    right.lift();
                }
            }
        }
        else if (left != null) {
            priority = left.priority;
            if (left.children == 0) {
                left = null;
            } else {
                left.lift();
            }
        }
        else {
            priority = right.priority;
            if (right.children == 0) {
                right = null;
            } else {
                right.lift();
            }
        }
    }

    int push(){
        int level = 1;
        HeapTreeNode current = this;
        while (true) {
            if((current.right !=null) && (current.left != null)){
                if ((current.priority > current.right.priority) && (current.right.priority < current.left.priority)) {
                    swapPrio(current, current.right);
                    swapChild(current,current.right);
                    current = current.right;
                    level++;
                    current.children--;
                    continue;
                }
                else if (current.priority > current.left.priority) {
                    swapPrio(current, current.left);
                    swapChild(current,current.left);
                    current = current.left;
                    level++;
                    current.children--;
                    continue;
                }
            }
            if ((current.left == null) && (current.right != null)) {
                if (current.priority > current.right.priority){
                    swapPrio(current, current.right);
                    swapChild(current,current.right);
                    current = current.right;
                    level++;
                    current.children--;
                    continue;
                }
            }
            if ((current.left!=null) && (current.right == null)) {
                if (current.priority > current.left.priority) {
                    swapPrio(current, current.left);
                    swapChild(current,current.left);
                    current = current.left;
                    level++;
                    current.children--;
                    continue;
                }
            }
            return level;
        }
    }


    private void swapPrio(HeapTreeNode current, HeapTreeNode next){
        int prio = current.priority;

        current.priority = next.priority;

        next.priority = prio;

    }
    private void swapChild(HeapTreeNode current, HeapTreeNode next){

        int child = current.children;

        current.children = next.children;

        next.children = child;
    }


    public void print() {
        if(this.left != null)
            left.print();
        System.out.println(" Priority: " + priority + "\t depth: " + depth + "\t Children: " + children);
        if(right != null)
            right.print();
    }
}
