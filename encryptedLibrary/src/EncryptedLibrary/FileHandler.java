 /***************************************************************************
 * FileHandler (FileHandler.java) last edited 11/30
 * The FileHandler is tasked with making the data managable
 * as well as dealing with the encryption via the EncryptionHandler
 * This class will read encrypted data from an existing file
 * or create a new file and copy all data to a Library object
 **************************************************************************/
package EncryptedLibrary;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ethan Wilson
 */
public class FileHandler {
    
    private PrintWriter out;
    private Scanner in;
    private File file;
    private EncryptionHandler encryptionHandler;
    
    /**
     * Constructor for filename opens the file for reading
     * or creates a new file
     */
    public FileHandler(String filename) throws IOException {
        /* Try to open the file with the name "filename"
         If the file does not exist catch the FileNotFoundException
         and create a new file with the same name */
        encryptionHandler = new EncryptionHandler();
        file = new File(filename);
        try{
            in = new Scanner(file);
        } catch(FileNotFoundException E) { // If the file doesnt exist create a new one
            file.createNewFile();
            in = new Scanner(file);
        }
        
    }
    
    /**
     * While in has next line read the next line
     * An EncryptedLibrary textfile will have the format
     * appName username password
     */
    public Library readFile(){
        Library library = new Library();
        String line;
        String appName;
        String username;
        String password;
        
        while(in.hasNextLine()){
            line = in.nextLine();
            appName = line.substring(0, 30);
            username = line.substring(30, 60);
            password = line.substring(60, 90);
            
            library.addAccount(appName, username, password, Library.NOT_SORTED);
        }
        library = encryptionHandler.decrypt(library);
        
        return library;
    }
    
    /**
     * Writes the encrypted library to a text file
     * in the format: appName username password
     */
    public void writeFile(Library library) throws FileNotFoundException {
        library = encryptionHandler.encrypt(library, encryptionHandler.ENCRYPTION_KEY);
        out = new PrintWriter(file);
        for(int i = 0; i < library.size; i++){
            out.print(library.getList().get(i).getApplication());
            out.print(library.getList().get(i).getUsername());
            out.println(library.getList().get(i).getPassword());
        }
        out.close();
    }
}