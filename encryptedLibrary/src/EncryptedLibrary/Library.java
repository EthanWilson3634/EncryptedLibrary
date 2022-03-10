/****************************************************************
 * Library (Library.java) last edited 11/24/2021
 * Library stores an ArrayList of accounts
 * a library will be created by the FileHandler
 * and it will sort the list of accounts alphabetically
 ***************************************************************/
package EncryptedLibrary;
import java.util.ArrayList;

/**
 *
 * @author Ethan Wilson
 */
public class Library {
    private ArrayList<Account> list;
    public int size;
    /**
     * Each field is this length to help with encryption
     */
    public static final int length = 30;
    
    /**
     * When writing the list to the text file we want to write in sorted order
     * When we read the encrypted accounts we dont want to add them in sorted order
     * we just want to add them in the order they are in the list
     */
    public static final int SORTED = 0;
    public static final int NOT_SORTED = 1;
    
    public Library(){
        list = new ArrayList<Account>();
        size = 0;
    }
    
    /**
     * Accessor for list
     */
    public ArrayList<Account> getList(){
        return list;
    }
    
    /**
     * Method for adding an account to the ArrayList
     * This method will add the accounts in sorted order
     * Alphabetically by the appName
     */
    public void addAccount(String newAppName, String newUsername, String newPassName, int sort){
        if(size == 0){
            list.add(new Account(newAppName, newUsername, newPassName, 1));
            size++;
            return;
        } else if(sort == SORTED){
            for(int i = 0; i <= list.size() - 1; i++){
                if(newAppName.compareTo(list.get(i).getApplication()) < 0){
                    list.add(i, new Account(newAppName, newUsername, newPassName, i));
                    size++;
                    return;
                }
            }
        }
        list.add(list.size(), new Account(newAppName, newUsername, newPassName, list.size()));
        size++;
    }
    
    /**
     * Add an account to the beginning of the list
     */
    public void prepend(String newAppName, String newUsername, String newPassName){
        list.add(0, new Account(newAppName, newUsername, newPassName, list.size()));
        size++;
    }
    
    /**
     * remove the account at index "index"
     * and decrement the size
     * @param index 
     */
    public void removeAccount(int index){
        list.remove(index);
        size--;
    }
}
