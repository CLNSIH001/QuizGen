package DataStructures;

//import java.util.ArrayList;

public class BPTrees{
    private int order;
    private bpNode root;

    public BPTrees(int branches){
        order = branches;
        root = null;
    }

////////////////////////////////////////////////////////////////
    private class bpNode {
        boolean isLeaf;
        int[] keys;
        bpNode[] pointers;
        int ofOrder;
        int totKeys;
        bpNode parent;
        //ArrayList<Integer> data;
        private bpNode(int order, boolean isLeaf) {
            ofOrder = order;
            this.isLeaf = isLeaf;
            totKeys = 0;
            pointers = new bpNode[ofOrder];
            keys = new int[ofOrder - 1];
            //data = new ArrayList<Integer>();
        }

        // A function to traverse all nodes in a subtree rooted with this node
        public void printNodes(){
            int j = 0;
            if (this.isLeaf == true)
                System.out.print("Leaf");
            else
                System.out.print("Non-Leaf");
            System.out.println(" with total Keys:" + totKeys);
            for (int i=0; i < totKeys; i++){
                System.out.print("|");
                System.out.print(keys[i] + "|");
            }
            System.out.println();
            for (; j < totKeys; j++){
                if (this.isLeaf == false)
                    pointers[j].printNodes();
            }
            if (this.isLeaf == false)
                pointers[j].printNodes();
        }
    }
///////////////////////////////////////////////////////////

    public bpNode find(int k) {
        bpNode n = root;
        while (!n.isLeaf) {
            int it = 0;
            while (it < n.totKeys && k > n.keys[it])
                it++;
            n = n.pointers[it];
        }
        return n;
    }

    public void insert(int data) {
        if (root == null) {
            root = new bpNode(order, true);
            root.keys[root.totKeys] = data;
            root.totKeys++;
        }
        else{
            bpNode left = find(data);
            if (left.totKeys < left.ofOrder-1){
                int pos = left.totKeys-1;
                for (; pos>=0; pos--){
                    if (left.keys[pos]>data)
                        left.keys[pos+1] = left.keys[pos];
                    else break;
                }
                left.keys[pos+1] = data;
                left.totKeys++;
            }
            else{
                bpNode right = new bpNode(order, left.isLeaf);
                split(left,right,data);
                putInParent(left, right);
            }
        }
    }

    private void putInParent(bpNode l, bpNode r){
        if (l == root){
            root = new bpNode(order, false);
            root.pointers[0] = l;
            root.pointers[1] = r;
            l.parent = root; r.parent = root;
            while (!r.isLeaf)
                r = r.pointers[0];
            root.keys[0] = r.keys[0];
            root.totKeys++;
        }
        else{
            if (l.parent.totKeys < order - 1) {
                int pos = l.parent.totKeys - 1;
                for (; pos >= 0; pos--) {
                    if (l.parent.keys[pos] > r.keys[0]) {
                        l.parent.keys[pos + 1] = l.parent.keys[pos];
                        l.parent.pointers[pos+2] = l.parent.pointers[pos+1];
                    }
                    else break;
                }
                l.parent.keys[pos + 1] = r.keys[0];
                l.parent.pointers[pos + 2] = r;
                r.parent = l.parent;
                l.parent.totKeys++;
            }
            else {
                bpNode u = new bpNode(order, false);
                u.pointers[0] = r;  //place holder for the new leaf node from the split in the insertion method
                split(l.parent, u, r.keys[0]);
                putInParent(l.parent, u);
            }
        }
    }

    private void split(bpNode l, bpNode r, int d){
        //Get the keys in the correct order and empty left node
        int[] tempK = new int[order];
        bpNode[] tempP = new bpNode[order+1];
        for (int i=0; i<l.totKeys; i++){
            tempK[i] = l.keys[i];
            tempP[i] = l.pointers[i];
            l.keys[i]=0; l.pointers[i]=null;
        }
        tempP[order-1] = l.pointers[order-1];
        l.pointers[order-1] = null;
        int pos = l.totKeys-1; l.totKeys = 0;
        for (; pos>=0; pos--){
            if (tempK[pos]>d) {
                tempK[pos+1] = tempK[pos];
                tempP[pos+2] = tempP[pos+1];
            }
            else break;
        }
        tempK[pos+1] =  d;
        tempP[pos+2] = r.pointers[0];

        //Now to do the actual splitting
        int num = order/2;
        if (l.isLeaf) {
            for (int j=0; j<num; j++){
                l.keys[j] = tempK[j];
                l.totKeys++;
            }
            for (int i = 0; num < order; num++) {
                r.keys[i] = tempK[num];
                ++r.totKeys;
                ++i;
            }
            l.pointers[order-1] = r;
        }
        else{
            for (int j=0; j<num; j++){
                l.keys[j] = tempK[j];
                l.pointers[j] = tempP[j];
                l.totKeys++;
            }
            l.pointers[num] = tempP[num];
            ++num; int i=0;
            for (; num < order; num++) {
                r.pointers[i] = tempP[num];
                r.keys[0] = tempK[num];
                ++r.totKeys;
                ++i;
            }
            r.pointers[i] = tempP[num];
        }
    }

    public void delete(int data) {

    }

    public void printNodes(){
        if (root != null)
            root.printNodes();
        System.out.println();
    }

    public static void main(String[] args){
        BPTrees bpt = new BPTrees(4);
        bpt.insert(20);
        bpt.insert(1);
        bpt.insert(4);
        bpt.insert(16);
        bpt.insert(25);
        bpt.insert(9);
        bpt.insert(13);
        System.out.println();
        bpt.insert(15);
        bpt.insert(10);
        bpt.insert(11);
        bpt.printNodes();
        System.out.println();
        System.out.println("====================================================");
        System.out.println();
        bpt.insert(12);
        bpt.printNodes();
    }
}

