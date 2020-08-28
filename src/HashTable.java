package QuizGen;

import java.util.LinkedList;

public class HashTable {

    private int tableSize;
    private int resolutionScheme;
    private int numberOfInsertProbes = 0;
    private int numberOfSearchProbes;
    private int key;
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
     * @param item number to be converted
     * @return the key
     */
    private int hashFunction(int item) {
        int key = 2* + Math.abs(item);

        key = key%(tableSize);

        return key;
    }

}
