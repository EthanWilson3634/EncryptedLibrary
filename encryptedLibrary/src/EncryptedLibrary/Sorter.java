/**********************
 * Sorter (Sorter.java) last edited 12/21/2021
 * 
 * 
 * 
 * 
 * 
 */
package EncryptedLibrary;

import java.util.ArrayList;

/**
 *
 * @author Ethan Wilson
 */
public class Sorter {
    
    public static final int AZ = 0;
    public static final int ZA = 1;
    public static final int RECENT = 2;
    
    public static void sort(ArrayList<Account> list, int sortBy){
        switch(sortBy){
            case AZ:
                
                break;
                
            case ZA:
                
                break;
                
            case RECENT:
                
                break;
        }
    }
    
    /**
     * Sort the list alphabetically from A to Z
     */
    private void aToZSort(){
        
    }
    
    /**
     * Sort the list reverse alphabetically from Z to A
     */
    private void zToASort(){
        
    }
    
    /**
     * Sort the list by the most recently added account
     */
    private void recentSort(){
        
    }
    
    // Swaps the two accounts
    private void swap(ArrayList<Account> list, Account i, Account j){
        Account temp = i;
        i = j;
        j = temp;
        
    }
    
    /**
     * quickly sort an array of accounts
     */
    /*
    private void quickSort(ArrayList<Account> list, int low, int high){
        if(low < high){
            //int  partitionIndex = partition(list, low, high);
            
            quickSort(list, low, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, high);
        }
    }
    
    private int partition(){
        
    }
*/
}