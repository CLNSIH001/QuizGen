import java.util.LinkedList;

public class HashTable {

    private int tableSize;
    private int resolutionScheme;
    private int numberOfInsertProbes = 0;
    private int numberOfSearchProbes;
    private int key;
    private int numberOfSearchKeys;
    private int[] table;
    private LinkedList[] chainingTable;

    /**
     * Constructor
     * @param tableSize size of table
     * @param resolutionScheme to be chosen
     */
    HashTable(int tableSize, int resolutionScheme) {
        setTableSize(tableSize, resolutionScheme);
        setResolutionScheme(resolutionScheme);
    }

}
