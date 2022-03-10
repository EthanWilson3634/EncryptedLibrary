/********************************************************************************
 * EncryptionHandler (EncryptionHandler.java) last edited 11/30
 * Encrypts a Library object before sending it to FileHandler
 * Decrypts a file to then be read by the FileHandler
 * 
 * TODO:
 * Encrypt from different Strings based on modifier
 * Example: lowercase a could be encrypted into a character of any of
 * the three Strings
 **********************************************************************************/
package EncryptedLibrary;

/**
 *
 * @author Ethan Wilson
 */
public class EncryptionHandler {
    public static final int ENCRYPTION_KEY = 7;
    public static final int DECRYPTION_KEY = -7;
    private static final int FIELD_LENGTH = 30;
    private static final String CHARACTERS = "Aa1Bb!Cc2Dd@Ee3Ff#Gg4>h$Ii5Jj%,k6Ll^Mm7Nn&Oo8Pp*Qq9Rr(Ss0Tt)Uu Vv-Ww_Xx=Yy+Zz/?.K<H";
    
    /**
     * Modifier is creted to improve the encryption algotrithm
     */
    private int indexModifier = 0;
    
    /**
     * This method must be reversible for encryption and decryption
     * Take the input library and encrypt or decrypt it
     * Then return the modified library
     */
    public Library encrypt(Library library, int key){
        String appNameEncrypt;
        String usernameEncrypt;
        String passwordEncrypt;
        
        /*
        Assign each element of each account in the library to a temporary variable
        encrypt each temporary variable using the key
        then set the encrypted temporary variables to the library
        
        The modifier variables purpose is to strengthen the encryption
        The idea is if a encrypted is equal to b then
        the next a that is encrypted does not also equal b
        instead the modifier is applied and another encrypted a would
        be equal to the modified
        */
        for(int i = 0; i < library.size; i++){
            // Here assign the values to temporary variables
            appNameEncrypt = library.getList().get(i).getApplication();
            usernameEncrypt = library.getList().get(i).getUsername();
            passwordEncrypt = library.getList().get(i).getPassword();
            
            // Here add spaces so each field is the same length
            appNameEncrypt = concatSpaces(appNameEncrypt);
            usernameEncrypt = concatSpaces(usernameEncrypt);
            passwordEncrypt = concatSpaces(passwordEncrypt);
            
            // Here use the helper function to encrypt the text
            library.getList().get(i).setApplication(alterString(appNameEncrypt, key));
            library.getList().get(i).setUsername(alterString(usernameEncrypt, key));
            library.getList().get(i).setPassword(alterString(passwordEncrypt, key));
            
        }
        indexModifier = 0;

        return library;
    }
    
    /**
     * Reverse the encryption algorithm using the decryption key
     */
    public Library decrypt(Library library){
        return encrypt(library, DECRYPTION_KEY);
    }
    
    /**
     * Helper function for encrypt
     * apply the encryption key to each string
     */
    private String alterString(String word, int key){
        String encryptedString = "";
        int index;
        int shiftedIndex;
        /*
        For each letter in the String passed searchFor the charcter in the string
        if searchFor returns -1 then it is a part of a different source String
        add the encryption key to the index of the String to alter the String
        if adding the key goes beyoind the index of the String loop around to the 
        beginning of the String
        */
        for(int i = 0; i < word.length(); i++){
            index = searchFor(CHARACTERS, word.charAt(i));
            shiftedIndex = indexShift(index, key);
            encryptedString = encryptedString + CHARACTERS.charAt(shiftedIndex);            
        }
        return encryptedString;
    }

    /**
     * indexShift determines the index of the string source at which
     * the key is which is used in alter string to find the encrypted
     * or decryped character
     */
    private int indexShift(int index, int key){
        int shiftedIndex = index;
        // Checking for overflow in both cases
        if(key < 0){
            while(shiftedIndex + key + indexModifier < 0){
                shiftedIndex = shiftedIndex + CHARACTERS.length();
            }
            shiftedIndex = shiftedIndex + key + indexModifier;
        } else if(key > 0){
            while(shiftedIndex + key + indexModifier >= CHARACTERS.length()){
                shiftedIndex = shiftedIndex - CHARACTERS.length();
            }
            shiftedIndex = shiftedIndex + key + indexModifier;
        }
        indexModifier = indexModifier + key;
        return shiftedIndex;
    }
    
    /**
     * Boolean method to determine if a charcter is in a String
     * If the character is found it will set the index varibale
     * to the index of the found character else it will return false
     */
    private int searchFor(String source, char c){
        for(int i = 0; i < source.length(); i++){
            if(source.charAt(i) == c){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Make sure each field is the same length
     */
    private String concatSpaces(String word){
        String result = word;
        for(int i = 0; i <= FIELD_LENGTH - word.length() - 1; i++){
            result = result + " ";
        }
        return result;
    }
}
