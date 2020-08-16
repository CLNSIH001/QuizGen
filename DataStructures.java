package QuizGen;
public abstract class DataStructures<T> {
    public abstract void insert(T data);
    public abstract void delete(T data);
    public abstract void find(T data);

    public static void main(String[] args){
        //DataStructures<datatype> = new DerivedClass(parameters);
        //Examples:
        //DataStructures<String> avl = new AVL(parameters); //could be tree of alphabet
        //DataStructures<int> = new RedBlackTrees(parameters);     //Data type stored is a number
    }
}