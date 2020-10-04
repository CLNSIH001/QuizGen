package DataStructures;

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
        private bpNode(int order, boolean isLeaf) {
            ofOrder = order;
            this.isLeaf = isLeaf;
            totKeys = 0;
            pointers = new bpNode[ofOrder];
            keys = new int[ofOrder - 1];
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

        public void Drop(int k){
            int i=0;
            for (; i<totKeys; i++){
                if (keys[i] == k){
                    keys[i] = 0;
                    break;
                }
            }
            for (; i+1<totKeys; i++)
                keys[i] = keys[i+1];
            --totKeys;
        }

        public boolean fullEnough(){
            int minKeysLeaf = (order-1)/2;
            int minPointersLeaf = order/2;
            if (isLeaf && totKeys >= minKeysLeaf) return true;
            else if (!isLeaf && countPointers() >= minPointersLeaf) return true;
            else return false;
        }

        public boolean moreThanHalfFull(){
            int minKeysLeaf = (order-1)/2;
            int minPointersLeaf = order/2;
            if (isLeaf && totKeys > minKeysLeaf) return true;
            else if (!isLeaf && countPointers() > minPointersLeaf) return true;
            else return false;
        }

        public int countPointers(){
            int i = 0;
            for (; i<order; i++)
                if (pointers[i] == null) break;
            return i;
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
    private void insert(bpNode node, int data){
        if (node.totKeys < node.ofOrder-1) {
            int pos = node.totKeys - 1;
            for (; pos >= 0; pos--) {
                if (node.keys[pos] > data)
                    node.keys[pos + 1] = node.keys[pos];
                else break;
            }
            node.keys[pos + 1] = data;
            node.totKeys++;
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
        bpNode N = find(data);
        N.Drop(data);
        bpNode p = N.parent;
        if (N.fullEnough()) return;
        else if (couldTakeFromSibling(p, N)) return;
        //else if (hasLeftSibling)      use parent node to do this
    }

    protected bpNode getRightSibling(bpNode p, bpNode n){
        int i = 0;
        for (; i<p.countPointers(); i++){
            if (p.pointers[i]==n)
                break;
        }
        if (p.pointers[++i] != null)
            return p.pointers[i];
        else return null;
    }
    protected bpNode getLeftSibling(bpNode p, bpNode n){
        int i = 0;
        for (; i<p.countPointers(); i++){
            if (p.pointers[i]==n)
                break;
        }
        if (i == 0)
            return null;
        else return p.pointers[--i];
    }

    private boolean couldTakeFromSibling(bpNode parent, bpNode node){
        bpNode left = getLeftSibling(parent, node);
        bpNode right = getRightSibling(parent, node);
        int movedKey = 0;
        if (left != null) {
            if (left.moreThanHalfFull()) {
                movedKey = left.keys[left.totKeys - 1];
                left.Drop(movedKey);
                insert(node, movedKey);
                //update n's parent (keys)
                return true;
            }
        }
        if (right == null) return false;
        else if (right.moreThanHalfFull()){
            movedKey = right.keys[0];
            right.Drop(movedKey);
            insert(node, movedKey);
            //update r's parent (keys)
            return true;
        }
        return false;
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
        bpt.insert(15);
        bpt.insert(10);
        bpt.insert(11);
        bpt.insert(12);
        bpt.printNodes();
    }
}
