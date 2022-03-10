/*******************************************************************************************************
 * View (View.java) last edited 11/30/2021
 * View collaborates with Main to
 * generate and access the GUI.
 * 
 * TODO:
 * Sorted list of application names top of GUI
 * 
 *******************************************************************************************************/
package EncryptedLibrary;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Dimension;

/**
 *
 * @author Ethan Wilson
 */
public class View extends JFrame implements ActionListener{
    
    // Store an object of the main class
    private Main main;
    
    // GUI Components
    public static final int FRAME_HEIGHT = 800;
    public static final int FRAME_WIDTH = 600;
    public static final int USER_OK = 0;
    public static final String[] sortOptions = {"A-Z", "Z-A", "Recently added"};
    private JButton addButton;
    private JButton displayButton;
    private JButton exitButton;
    private JButton removeButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JScrollPane scroll;
    private JList accountList;
    private JComboBox<String> dropdown;
    
    /**
     * View Constructor creates the user interface
     */
    public View(Main main){
        this.main = main;
        
        // scrollPanel contains a scrollpane of the list of account names
        // the User will be able to look at them in sorted order
        // either alphabetically or by most recently added/modified
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        accountList = new JList(updateArray(main.getLibrary().getList())); // This line initializes the scrollpanes' list of account names
        scroll = new JScrollPane(accountList);
        scroll.setMinimumSize(new Dimension(100, 80));
        scrollPanel.add(Box.createHorizontalGlue());
        scrollPanel.add(Box.createHorizontalGlue());
        scrollPanel.add(Box.createHorizontalGlue());
        scrollPanel.add(scroll);
        scrollPanel.add(Box.createHorizontalGlue());
        dropdown = new JComboBox<>(sortOptions);
        dropdown.setMaximumSize(new Dimension(50, 50));
        dropdown.addActionListener(this);
        scrollPanel.add(dropdown);
        scrollPanel.add(Box.createHorizontalGlue());
        
        // displayPanel will display the account selected
        // in the scroll list when the display button is clicked\
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        usernameLabel = new JLabel();
        displayPanel.add(usernameLabel);
        passwordLabel = new JLabel();
        displayPanel.add(passwordLabel);
        
        // buttonPanel conatains each button in a flowlayout
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        buttonPanel.add(removeButton);
        displayButton = new JButton("Display");
        displayButton.addActionListener(this);
        buttonPanel.add(displayButton);
        exitButton = new JButton("Save & Exit");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);
        
        // mainPanel contains searchPanel and buttonPanel in an easy flowlayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(scrollPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(displayPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
        
        // Add mainPanel to the view
        add(mainPanel);
        
        // Set the size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screenSize.width/2, screenSize.height/2, screenSize.width/2, screenSize.height/2);
        
        // Put the frame in the center of the screen
        setLocationRelativeTo(null);
        
        // Do nothing when the red X is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        // Set the frame title
        setTitle("EncryptedLibrary.exe");
        
        // Make it visible
        setVisible(true);
    }
    
    /**
     *  
     * This method determines what happens when each button is pushed
     */
    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == addButton){
            addAccount();
        } else if(event.getSource() == exitButton){
            try{
                main.exit();
            } catch(FileNotFoundException e){
                displayMessageBox("Oops there was a problem");
            }
        } else if(event.getSource() == removeButton){
            remove();
        } else if(event.getSource() == displayButton){
            findPassword();
        } else if(event.getSource() == dropdown){
            viewSort(dropdown.getSelectedItem().toString());
        }
    }
    
    private void viewSort(String source){
        for(int i = 0; i < sortOptions.length; i++){
            if (source.equals(sortOptions[i])){
                Sorter.sort(main.getLibrary().getList(), i);
                return;
            }
        }
        displayMessageBox("An error has occured");
    }
    /**
     * Find the searched password and display it to the user
     */
    private void findPassword(){
        int index = Searcher.search(main.getLibrary().getList(), accountList.getSelectedValue().toString());
        if(index == -1){
            displayMessageBox("Account not found. We check for capitol letters!");
        } else {
            displayAccount(main.getLibrary().getList().get(index));
        }
    }
    
    /**
     * display account acc for the user to see
     * trim each field to get rid of extra spaces
     * @param acc 
     */
    private void displayAccount(Account acc){
        usernameLabel.setText(acc.getUsername());
        passwordLabel.setText(acc.getPassword());
    }
    
    /**
     * Display a message box with three input textfields
     * take the strings from those texfields and create an account
     */
    private void addAccount(){
        int input;
        JTextField appName = new JTextField(30);
        appName.setText("Application");
        JTextField userName = new JTextField(30);
        userName.setText("Username");
        JTextField passName = new JTextField(30);
        passName.setText("Password");
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(3, 0));
        accountPanel.add(appName);
        accountPanel.add(userName);
        accountPanel.add(passName);

        // Prompt the user to enter the account fields
        // Make sure the user fills out all the fields
        do{
            input = JOptionPane.showConfirmDialog(this, accountPanel, "Adding an account...", JOptionPane.OK_CANCEL_OPTION);
        } while((appName.getText().equals("") || userName.getText().equals("") || passName.getText().equals("")) && input == USER_OK);
        
        if(input == USER_OK){
            main.getLibrary().addAccount(appName.getText(), userName.getText(), passName.getText(), Library.SORTED);
        }
        // Add the account using the information in each of the text fields
        accountList.setListData(updateArray(main.getLibrary().getList()));
    }
    
    /**
     * Remove an account
     */
    private void remove(){
        int index = Searcher.search(main.getLibrary().getList(), accountList.getSelectedValue().toString());
        if(index == -1){
            displayMessageBox("Account not found. We check for capitol letters!");
        } else {
            main.getLibrary().removeAccount(index);
        }
        // Update the array used by the JList scroll bar
        accountList.setListData(updateArray(main.getLibrary().getList()));
    }
    
    /**
     * Call this method at the initialization of the JList accountList
     * Also call this method whenever an account is added or removed
     */
    private String[] updateArray(ArrayList<Account> list){
        String [] result = new String[list.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = list.get(i).getApplication();
        }
        return result;
    }
        
    /**
     * Display a message on the screen
     * @param message 
     */
    private void displayMessageBox(String message){
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }    
}
