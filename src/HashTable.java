package QuizGen;
import java.util.LinkedList;

public class HashTable {

    private int tableSize;
    private int resolutionScheme;
    private int numberOfInsertProbes = 0;
    private int numberOfSearchProbes;
    private int key;
    private int[] table;
    private LinkedList<Integer>[] chainingTable;

    /**
     * Constructor
     * @param tableSize size of table
     * @param resolutionScheme to be chosen
     */
    HashTable(int tableSize, int resolutionScheme) {
        setTableSize(tableSize, resolutionScheme);
        setResolutionScheme(resolutionScheme);
    }
    /**
     * return number of search probes
     * @return numberOfSearchProbes
     */
    int getNumberOfSearchProbes() {
        return this.numberOfSearchProbes;
    }
    /**
     * set the number of search probes to input number
     * @param probes probe count
     */
    private void setNumberOfSearchProbes(int probes) {
        this.numberOfSearchProbes = probes;
    }
    /**
     * get the number of insert probes
     * @return numberOfInsertProbes
     */
    int getNumberOfInsertProbes() {
        return numberOfInsertProbes;
    }
    /**
     * set number of insert probes to input number
     * @param probes new probes count
     */
    private void setNumberOfInsertProbes(int probes) {
        this.numberOfInsertProbes = probes;
    }

    /**
     * get the size of the table
     * @return current table's size
     */
    int getTableSize() {
        return this.tableSize;
    }

    /**
     * Set the size of the hash table
     * @param tableSize new table size
     */
    private void setTableSize(int tableSize, int resolutionScheme) {
        while(!(isPrime(tableSize))) {
            tableSize++;
        }
        this.tableSize = tableSize;
        createTable(tableSize,resolutionScheme);
    }

    /**
     * Set type of table; linear, chaining or quadratic
     * @param resolutionScheme
     */
    private void setResolutionScheme(int resolutionScheme){this.resolutionScheme = resolutionScheme;}


    /**
     * Calculate the load factor of the current hash table
     * @param filledSpaces number of data entries
     * @return the load factor as a double
     */
    double getLoadFactor(int filledSpaces) {

        double loadFactor;
        loadFactor = (double)filledSpaces/this.tableSize;
        return loadFactor;
    }

    /**
     * isPrime code by Derek Banas,Java Hash Tables 2, March 22,2013
     * @param number number being tested
     * @return true if number is prime or false if it is not prime
     */
    private boolean isPrime(int number) {

        //Eliminate the need to check versus even numbers
        if(number % 2 == 0) { return false; }

        //Check against all odd numbers
        for(int i = 3; i*i <= number; i += 2) {
            if(number % i == 0) { return false; }
        }

        return true;
    }

    /**
     * Populate the table with nulls, It cannot be null so instead it is MIN_VALUE.
     * But the MIN_VALUES will be treated as nulls
     * @param table
     * @return
     */
    private int[] fillTable(int[] table){
        for(int i = 0; i < table.length; i++){
            table[i] = Integer.MIN_VALUE;
        }
        return table;
    }

    /**
     * creates the appropriate table, array of String or LinkedList,
     * depending on the resolution scheme
     * @param tableSize size of table to be created
     * @param resolutionScheme chosen resolution scheme
     */
    private void createTable(int tableSize, int resolutionScheme) {
        if (resolutionScheme == 1 || resolutionScheme == 2 ) {
            table = new int[tableSize];
            fillTable(table);
        }
        else {
            chainingTable = new LinkedList[tableSize];
        }
    }

    /**
     * function to create the key from a String
     * @param item date/time to be converted
     * @return the key
     */
    private int hashFunction(int item) {
        int key = 2* + Math.abs(item);

        key = key%(tableSize);

        return key;
    }

    /**
     * insert method that uses linear probing to insert the data
     * @param item of data
     */
    void linearInsert(int item) {

        key = hashFunction(item);

        while (table[key] != Integer.MIN_VALUE) {
            numberOfInsertProbes++;
            key = (key+1)%tableSize;
        }

        table[key] = item;
    }

    /**
     * search method for table that used linear probing
     * @param item line of data
     */
    void linearFind(int item) {

        key = hashFunction(item);

        setNumberOfSearchProbes(0);

        while(table[key] != Integer.MIN_VALUE) {
            if(table[key] == item) {
                return;
            }
            key = (key+1)%tableSize;
            numberOfSearchProbes++;

        }
    }

    /**
     * insert method that probes quadratically
     * @param item of data
     */
    void quadraticInsert(int item) {

        int i = 1;
        key = hashFunction(item);
        while (table[key] != Integer.MIN_VALUE) {
            numberOfInsertProbes++;
            key = (key+(i*i))%tableSize;
            if(key<0) {
                key = Math.abs(key);
            }
            i++;
        }

        table[key] = item;
    }

    /**
     * find method that probes quadratically
     * @param item line of data
     */
    void quadraticFind(int item) {

        int i = 1;
        key = hashFunction(item);

        setNumberOfSearchProbes(0);

        while(table[key] != Integer.MIN_VALUE) {
            if(table[key] == item) {
                return;
            }
            key = (key+(i*i))%tableSize;
            if(key<0) {
                key = Math.abs(key);
            }
            i++;
            numberOfSearchProbes++;
        }
    }

    /**
     * insert method used when resolution scheme is chaining
     * @param item number to inserted
     *
     */
    void chainingInsert(int item) {

        key = hashFunction(item);

        if (chainingTable[key] != null) {
            setNumberOfInsertProbes(numberOfInsertProbes+chainingTable[key].size());
            chainingTable[key].add(item);
        }
        else if(chainingTable[key] == null){
            chainingTable[key] = new LinkedList<Integer>();
            chainingTable[key].add(item);

        }

    }
    
    /**
     * find method used when resolution scheme is chaining
     * @param item date/time being searched
     */
    void chainingFind(int item) {

        key = hashFunction(item);
        setNumberOfSearchProbes(0);

        for(int i = 0; i < chainingTable[key].size(); i++) {
            if(chainingTable[key].get(i) == item) {
                setNumberOfSearchProbes(i);
                return;
            }
        }

    }

    /**
     * used to delete an item from the hash table
     * @param item date/time being deleted
     * @param t the table from which it is being deleted
     */
    public void delete(int item, HashTable t) {

        key = hashFunction(item);

        if(t.resolutionScheme == 1 || t.resolutionScheme == 2) {
            while (t.table[key] != Integer.MIN_VALUE) {

                key = (key+1)%tableSize;

            }

            t.table[key] = Integer.MIN_VALUE;
        }
        else {
            while (t.chainingTable[key] != null) {

                key = (key+1)%tableSize;

            }
            chainingFind(item);
            t.chainingTable[key].remove(numberOfSearchProbes);
        }

    }

    /**
     * method to print a linked list in the hash table that used chaining
     * @param T linked list to be printed
     */
    public void printLinkedList(LinkedList<?> T) {
        for(int i = 0; i < T.size(); i++) {
            if(T.get(i)==null) {
                System.out.println("null");
            }else {
                System.out.println(T.get(i));
            }
        }
    }
    /**
     * method to print out the hash table to help visualize it
     * @param t HashTable to be printed
     */
    public void printTable(HashTable t) {
        for(int i = 0; i < t.tableSize; i++) {
            System.out.printf("%4s | ", i+1);
            if(t.chainingTable[i] == null) {
                System.out.println("null");
            }
            else {
                System.out.println(t.chainingTable[i].element());
            }
        }
    }

    public void print(HashTable t){
        for(int i = 0; i < t.tableSize; i++){
            System.out.printf("%4s | ", i+1);
            if(t.table[i]==Integer.MIN_VALUE)
                System.out.println("Nothing");
            else
                System.out.println(t.table[i]);
        }
    }
    /**
     * checks if the hash table is full when trying to insert an item
     * @param t HashTable being checked
     * @return true if it is full and false if it is not full
     */
    boolean isFull(HashTable t) {
        if(t.resolutionScheme == 1 || t.resolutionScheme == 2) {
            for(int i = 0; i < t.getTableSize(); i++) {
                if(t.table[i] == Integer.MIN_VALUE) {
                    return false;
                }
            }
        }
        else {
            for(int i = 0; i < t.getTableSize(); i++) {
                if(t.chainingTable[i] == null) {
                    return false;
                }
            }
        }
        return true;
    }

}
