package QuizGen;

public class BPTrees<T> extends DataStructures<T>{
    private int order;
    private Node root;

    protected BPTrees(int branches){
        order = branches;
        root = null;
    }

    private class Node{
        boolean isLeaf;
        T[] keys;
        Node[] pointers;
        int maxP;
        private Node(int order, boolean isLeaf){
            maxP = order;
            this.isLeaf = isLeaf;
            //pointers = new Node[maxP/2];  min
            //keys = T[maxP-1];   max
        }
    }

    @Override
    public void insert(T data) {

    }

    @Override
    public void delete(T data) {

    }

    @Override
    public void find(T data) {

    }
}

