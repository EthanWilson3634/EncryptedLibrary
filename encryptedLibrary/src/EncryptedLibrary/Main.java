/*******************************************************************************************************
 * Main (Main.java) last edited 12/20/2021
 * Main collaborates with view to run the program
 * simply calling methods and generated the GUI.
 * TO DO:
 *******************************************************************************************************/
package EncryptedLibrary;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *
 * @author Ethan Wilson
 */
public class Main {
    // Store the library being manipulated
    private Library library;
    
    // For handling encryption IO stuff
    private FileHandler fileHandler;
    
    /**
     * 
     * @param args 
     * Create an instance of main and call run
     */
    public static void main(String[] args) throws IOException{
        new Main().run();
    }
    
    /**
     * Here the program begins
     * Start by opening the Library if there is not an existing library create a new one
     * Add and access accounts until the user exits then call exit()
     */
    private void run() throws IOException {
        // Read the file or create an empty one
        fileHandler = new FileHandler("EncryptedLibrary.txt");
        library = fileHandler.readFile();
        
        // This statement initiates the GUI
        View view = new View(this);
    }
    
    /**
     * Here the program ends
     * Save the library and send it to the FileHandler
     * FileHandler exncrypts the accounts and writes them to a text file
     * the textfile will be named "EncryptedLibrary.txt"
     * Method is public because it is called from View by the save exit button
     */
    public void exit() throws FileNotFoundException{
        fileHandler.writeFile(getLibrary());
        System.exit(0);
    }
    
    /**
     * 
     * Accessor method for library
     */
    public Library getLibrary(){
        return library;
    }
}
