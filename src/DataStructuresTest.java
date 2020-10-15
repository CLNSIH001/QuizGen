import DataStructures.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DataStructuresTest<T extends Comparable<? super T>>{
    private BinarySearchTree<T> bst;
    private AVLTree<T> avl;
    private RedBlackTree<T> rbt;
    private BinaryHeap<T> bheap;
    private Graph digraph;
    private BPTrees bpt;
    private HashTable hash;

    private final int nil = Integer.MIN_VALUE;

    BPTrees populateBPlusTree(BPTrees bpTree){
        //2019 test 2
        bpTree.insert(23);
        bpTree.insert(31);
        bpTree.insert(43);
        bpTree.insert(13);
        bpTree.insert(47);
        bpTree.insert(17);
        bpTree.insert(29);
        bpTree.insert(37);
        bpTree.insert(41);
        bpTree.insert(19);
        return bpTree;
    }

    HashTable populateTable(HashTable table){
        table.linearInsert(1);
        table.linearInsert(10);
        table.linearInsert(100);
        table.linearInsert(1000);
        table.linearInsert(10000);
        return table;
    }
    HashTable populateQuadraticTable(HashTable table){
        table.quadraticInsert(1);
        table.quadraticInsert(10);
        table.quadraticInsert(100);
        table.quadraticInsert(1000);
        table.quadraticInsert(10000);
        return table;
    }
    HashTable populateChainingTable(HashTable table){
        table.chainingInsert(1);
        table.chainingInsert(10);
        table.chainingInsert(100);
        table.chainingInsert(1000);
        table.chainingInsert(10000);
        return table;
    }

    @Test
    void testBPlusTreeDeletion(){
        //Question f.2
        bpt = new BPTrees(4);
        bpt = populateBPlusTree(bpt);
        bpt.delete(23);
        int[][] comp = {{13,17,0},{19,29,0},{19,31,43}};
        int j=0;
        for (int i : bpt.testKeys().get(0))
            assertEquals(comp[0][j++], i);
        j=0;
        for (int i : bpt.testKeys().get(1))
            assertEquals(comp[1][j++], i);
        j=0;
        for (int i : bpt.testKeys().get(4))
            assertEquals(comp[2][j++], i);
        System.out.println("Passed deletion test for B+ Tree.");
    }

    @Test
    void testBPlusTreeInsertion(){
        //Question f.1
        bpt = new BPTrees(4);
        bpt = populateBPlusTree(bpt);
        bpt.insert(48); bpt.insert(16);
        int[][] comp = {{13,16,0},{17,19,0},{23,29,0},{17,23,0},{31,37,41},{43,47,48},{43,0,0},{31,0,0}};
        int k=0;
        for (int[] i : bpt.testKeys()){
            for (int j=0; j<comp[k].length; j++)
                assertEquals(comp[k][j], i[j]);
            ++k;
        }
        System.out.println("Passed insertion test for B+ Tree.");
    }

    @Test
    void getNumberOfInsertProbes() {
        hash = new HashTable(5, 1);
        hash = populateTable(hash);
        assertEquals(8,hash.getNumberOfInsertProbes());
    }

    @Test
    void getTableSize() {
        try{
            hash = new HashTable(5, 2);
            assertEquals(5,hash.getTableSize());
        }catch(AssertionError e){
            System.out.println("Test initializing when passed a prime number");
        }
        try{
            hash = new HashTable(10, 2);
            assertEquals(11,hash.getTableSize());
        }catch(AssertionError e){
            System.out.println("Test initializing when passed an even non-prime number");
        }
        try{
            hash = new HashTable(9, 2);
            assertEquals(11,hash.getTableSize());
        }catch(AssertionError e){
            System.out.println("Test initializing when passed an odd non-prime number");
        }

    }

    @Test
    void linearInsert() {
        try{
            hash = new HashTable(11, 1);
            hash = populateTable(hash);

            assertEquals(1, hash.getFromTable(2));
            assertEquals(10, hash.getFromTable(3));
            assertEquals(100, hash.getFromTable(4));
            assertEquals(1000, hash.getFromTable(9));
            assertEquals(10000, hash.getFromTable(10));

            assertEquals(nil, hash.getFromTable(0));
            assertEquals(nil, hash.getFromTable(1));
            assertEquals(nil, hash.getFromTable(5));
            assertEquals(nil, hash.getFromTable(6));
            assertEquals(nil, hash.getFromTable(7));
            assertEquals(nil, hash.getFromTable(8));
            System.out.println("Passed Linear Probing insertion");
        }catch(AssertionError e){
            System.out.println("Failed Linear Probing insertion");
        }
    }

    @Test
    void quadraticInsert() {
        try{
            hash = new HashTable(11, 2);
            populateQuadraticTable(hash);

            assertEquals(1, hash.getFromTable(2));
            assertEquals(10, hash.getFromTable(9));
            assertEquals(100, hash.getFromTable(3));
            assertEquals(1000, hash.getFromTable(10));
            assertEquals(10000, hash.getFromTable(7));

            assertEquals(nil, hash.getFromTable(0));
            assertEquals(nil, hash.getFromTable(1));
            assertEquals(nil, hash.getFromTable(4));
            assertEquals(nil, hash.getFromTable(5));
            assertEquals(nil, hash.getFromTable(6));
            assertEquals(nil, hash.getFromTable(8));
            System.out.println("Passed Quadratic Probing insertion");
        }catch(AssertionError e){
            System.out.println("Failed Quadratic Probing insertion");
        }
    }

    @Test
    void chainingInsert() {
        try{
            hash = new HashTable(5, 3);
            hash = populateChainingTable(hash);

            assertEquals(1, hash.getChainArray(2).get(0));
            assertEquals(10, hash.getChainArray(0).get(0));
            assertEquals(100, hash.getChainArray(0).get(1));
            assertEquals(1000, hash.getChainArray(0).get(2));
            assertEquals(10000, hash.getChainArray(0).get(3));

            assertNull(hash.getChainArray(1));
            assertNull(hash.getChainArray(3));
            assertNull(hash.getChainArray(4));
            System.out.println("Passed Quadratic Probing insertion");
        }catch(AssertionError e){
            System.out.println("Failed Quadratic Probing insertion");
        }
    }
}
