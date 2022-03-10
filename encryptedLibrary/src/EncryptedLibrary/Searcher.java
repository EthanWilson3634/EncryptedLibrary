/*******************************************************************************
 * Searcher (Searcher.java) last edited 11/30/2021
 * Uses a recursive binary search algorithm to search for an
 * object or element in a sorted list
 *******************************************************************************/
package EncryptedLibrary;
import java.util.ArrayList;

/**
 *
 * @author Ethan Wilson
 */
public class Searcher {
    /**
     * Search is the method that is called in view
     * and it calls the recursiveBinarySearch method
     * trim each element when looking for the key
     * returns the index of the searched element or -1
     * if the element doesnt exist in the list
     */
    public static int search(ArrayList<Account> list, String key){
        return recursiveBinarySearch(list, key.trim(), 0, list.size() - 1);
    }
    
    private static int recursiveBinarySearch(ArrayList<Account> list, String key, int low, int high){
        if(low > high){
            return -1;
        }
        int middle = (low + high) / 2;
        if(key.equals(list.get(middle).getApplication().trim())){
            return middle;
        } else if(key.compareTo(list.get(middle).getApplication().trim()) < 0){
            return recursiveBinarySearch(list, key, low, middle - 1);
        } else{
            return recursiveBinarySearch(list, key, middle + 1, high);
        }
    }
}
