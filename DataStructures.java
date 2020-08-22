package QuizGen;

public abstract class Driver<T> {
    Graph g;

    Driver<T>(){
        g = new Graph();
    }

    public abstract void insert(T data);
    public abstract void delete(T data);
    public abstract void find(T data);

    public void Graph(){
        //this is just an idea of how it will work
        g.insert("A", "B", -5);
        g.insert("A", "D", 7);
        g.insert("A", "E", 2);
        g.insert("B", "C", 5);
        g.insert("C", "A", 1);
        g.insert("C", "D", -4);
        g.insert("D", "E", 3);
        g.insert("E", "C", 1);
        g.insert("E", "B", 6);
        g.bellmanFord("A");
        g.printPath("E");
        Graph dg = new Graph();
        dg.insert("A", "B", 15);
        dg.insert("A", "D", 80);
        dg.insert("B", "E", 10);
        dg.insert("C", "A", 11);
        dg.insert("D", "B", 25);
        dg.insert("D", "C", 24);
        dg.insert("E", "D", 47);
        dg.dijkstra("A");
        dg.printPath("B");
        dg.dijkstra("B");
        dg.printPath("C");
    }

    public static void main(String[] args){
        Driver<String> test;
        test.Graph();
        //DataStructures<datatype> = new DerivedClass(parameters);
        //Examples:
        //DataStructures<String> avl = new AVL(parameters); //could be tree of alphabet
        //DataStructures<int> = new RedBlackTrees(parameters);     //Data type stored is a number
    }
}