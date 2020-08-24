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
     * get the number of keys that were searched
     * @return number of keys
     */
    int getNumberOfSearchKeys() {
        return this.numberOfSearchKeys;
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

}
