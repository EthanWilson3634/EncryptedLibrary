/******************************************************************
 * Account (Account.java) last edited 11/23/2021
 * Account stores an application name
 * as well as the username and password
 * associated with the application
********************************************************************/
package EncryptedLibrary;

/**
 *
 * @author Ethan Wilson
 */
public class Account {
    
    // Data
    private String application;
    private String username;
    private String password;
    private int counter;
        
    /**
     * Initialize data members
     */
    public Account(String application, String username, String password, int count){
        this.application = application;
        this.username = username;
        this.password = password;
        this.counter = count;
    }
    
    // Accessor methods
    public String getApplication(){
        return application;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public int getCounter(){
        return counter;
    }
    
    // Mutator methods
    public void setApplication(String appName){
        this.application = appName;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}
