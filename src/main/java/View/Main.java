package View;

import BinaryTree.Tree;
import org.LL.Benchmark;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        for (int i = 2; i < 8000000; i= i*2) {
            Benchmark.benchmarkTree(i);
        }
    }
}
