package BinaryTree;



class Node {
    Integer key;
    Integer value;
    Node left, right;

    Node(Integer key, Integer value) {
        this.key = key;
        this.value = value;
        left = null;
        right = null;
    }

    void addRecursive(Integer key, Integer value) {
        if (key.equals(this.key)) {
            this.value = value;
        } else if (key > this.key) {
            if (this.right == null) {
                this.right = new Node(key, value);
            } else {
                this.right.addRecursive(key, value);
            }
        } else {
            if (this.left == null) {
                this.left = new Node(key, value);
            } else {
                this.left.addRecursive(key, value);
            }
        }
    }

    void addIterative(Integer key, Integer value) {
        Node current = this;
        while (true) {
            if (key.equals(current.key)) {
                current.value = value;
            }
            else if (key > current.key) {
                if (current.right == null) {
                    current.right = new Node(key, value);
                    break;
                } else {
                    current = current.right;
                }
            }
            else {
                if (current.left == null) {
                    current.left = new Node(key, value);
                    break;
                }
                else {
                    current = current.left;
                }
            }
        }
    }

    Integer loookUpRecursive(Integer key) {
        Integer found = null;

        if (this.key.equals(key)) {
            found = this.value;
        } else if (key > this.key && this.right != null) {
            found = this.right.loookUpRecursive(key);
        } else if (key < this.key && this.left != null) {
            found = this.left.loookUpRecursive(key);
        }
        return found;
    }

    void print() {
        if (left != null)
            left.print();
        System.out.println(" key: " + key + "\tvalue: " + value);
        if (right != null)
            right.print();
    }
}

