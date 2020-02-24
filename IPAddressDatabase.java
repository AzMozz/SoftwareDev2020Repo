/** --------------------------------------------------------
 * Class: IPAddressDatabase
 * 
 * @author Mark O'Reilly
 * 
 * Developed: 2016-2017
 * 
 * Purpose: Sample Java application for managing the Titles, IDs
 *                             and IPs of an office of computers.
 * 
 * Demonstrating the implementation of:
 * - An array of objects
 * - Frames
 * - Action and Window Listeners
 * - Text file data management
 * - Selected modularisation (coupling and cohesion) and OOP concepts
 * 
 * ----------------------------------------------------------
 */


// Import (include) functionality from the various Java libraries

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;


// Primary class file - IPAddressDatabase
public class IPAddressDatabase extends Frame implements ActionListener, WindowListener
{
    int maxEntries = 100;     // Global variable to define the size of the array 
    int numberOfEntries = 0;  // Global variable to remember how many entries are in the array 
    int currentEntry = 0;     // Global variable to remember which entry in the array we are currently looking at / dealing with 

    // Declaration and instantiation of an array of objects for storing the PC/IP data in memory -
    //                 based on the class: PCDataRecord.  The array object is called: PCInfo.
    PCDataRecord[] PCInfo = new PCDataRecord[maxEntries];

    // Declaration of the various screen objects - labels, textfields and buttons
    Label lblPCName, lblPCID, lblIP, lblFind;
    TextField txtPCName, txtPCID, txtIP, txtFind;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;
    
    // Declaration of a global variable to store the data file name
    String dataFileName = "IPAddresses.txt";

    
    /** --------------------------------------------------------
    * Purpose: Entry point to the class and application.
    *          Establish the frame and various properties
    * @param   args (an array of String arguments)
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public static void main(String[] args)
    {
        Frame myFrame = new IPAddressDatabase();
        myFrame.setSize(470,250);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    
    /** --------------------------------------------------------
    * Purpose: Constructor for the class: IPAddressDatabase.
    *          Sets up the screen layout and listeners.
    * @param   None.
    * @returns Not applicable.
    * ----------------------------------------------------------
    */
    public IPAddressDatabase()
    {
        setTitle("IP Address Solution");
        setBackground(Color.yellow);

        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);

        this.addWindowListener(this);
    }

    
    /** --------------------------------------------------------
    * Purpose: Method for managing the addition of multiple
    *                                  Labels to the screen.
    * @param   The layout manager being used (SpringLayout myLayout).
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void LocateLabels(SpringLayout myLabelLayout)
    {
	// Each line calls the LocateALabel method to establish each Label
        lblPCName = LocateALabel(myLabelLayout, lblPCName, "PC Name", 30, 25);
        lblPCID = LocateALabel(myLabelLayout, lblPCID, "PC ID", 30, 50);
        lblIP = LocateALabel(myLabelLayout, lblIP, "IP", 30, 75);
        lblFind = LocateALabel(myLabelLayout, lblFind, "Search", 30, 120);
    }

        
    /** --------------------------------------------------------
    * Purpose: Locate a single Label within the frame.
    * @param   Current_layout_manager, Label_name, Label_caption, X_position, Y_Position (within the Frame)
    * @returns The Label.
    * 
    * Discussion:
    *    Method demonstrating low coupling and high cohesion 
    *      for adding individual labels:
    *        - reduces overall code, especially in the
    *            LocateLabels method.
    *        - makes this method re-usable with minimal
    *            adjustment as it is moved from one
    *            program to another.
    * ----------------------------------------------------------
    */
    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
	// Instantiate the Label
        myLabel = new Label(LabelCaption);
	// Add the Label to the screen
        add(myLabel); 
	// Set the position of the Label (From left hand side of the frame (West), and from top of frame (North))
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
	// Return the label to the calling method
        return myLabel;
    }
   

    /** --------------------------------------------------------
    * Purpose: Method for managing the addition of multiple
    *                              TextFields to the screen.
    * @param   The layout manager being used (SpringLayout myLayout).
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
	// Each line calls the LocateATextField method to establish each TextField
        txtPCName  = LocateATextField(myTextFieldLayout, txtPCName, 20, 130, 25);
        txtPCID = LocateATextField(myTextFieldLayout, txtPCID, 20, 130, 50);
        txtIP = LocateATextField(myTextFieldLayout, txtIP, 20, 130, 75);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 20, 130, 120);
    }

        
    /** --------------------------------------------------------
    * Purpose: Locate a single TextField within the frame.
    * @param   Layout_manager, TextField_name, Width, X_position, Y_Position
    * @returns The TextField.
    * ----------------------------------------------------------
    */
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }


    /** --------------------------------------------------------
    * Purpose: Method for managing the addition of multiple
    *                                 Buttons to the screen.
    * @param   The layout manager being used (SpringLayout myLayout).
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void LocateButtons(SpringLayout myButtonLayout)
    {
	// Each line calls the LocateAButton method to establish each Button
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 320, 25, 80, 25);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 320, 50, 80, 25);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 320, 75, 80, 25);
        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 320, 100, 80, 25);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 320, 170, 80, 25);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 140, 170, 30, 25);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 180, 170, 30, 25);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 220, 170, 30, 25);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 260, 170, 30, 25);
    }

        
    /** --------------------------------------------------------
    * Purpose: Locate a single Button within the frame.
    * @param   Layout_manager, Button_name, Button_caption, X_position, Y_Position, Width, Height
    * @returns The Button.
    * ----------------------------------------------------------
    */
    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h)
    {    
        myButton = new Button(ButtonCaption);
        add(myButton);
        myButton.addActionListener(this);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w,h));
        return myButton;
    }

    
    /** --------------------------------------------------------
    * Purpose: Respond to user action events, such as clicking the New button.
    * @param   args Array of String arguments.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
   public void actionPerformed(ActionEvent e)
    {
        // BUTTON FIRST -----------------------------------------
        if(e.getSource() == btnFirst)
        {
            // The currentEntry variable is used to define which
            //     record will be displayed on screen.
            currentEntry = 0;
            
            // The displayEntry method will display the currentEntry
            //     on the screen
            displayEntry(currentEntry);
        }

        // BUTTON PREVIOUS --------------------------------------
        if(e.getSource() == btnPrev)
        {
            // Only go the previous record if we have a previous
            //     entry in the array...
            if(currentEntry > 0)
            {
                currentEntry--;
                displayEntry(currentEntry);
            }
        }

        // BUTTON NEXT ------------------------------------------
        if (e.getSource()== btnNext)
        {
            // Only go the next record if we have a next existing entry in the array...    
            // NOTE: the use of numberOfEntries as opposed to maxEntries.
            if(currentEntry < numberOfEntries - 1)
            {
                currentEntry++;
                displayEntry(currentEntry);
            }
        }

        // BUTTON LAST ------------------------------------------
        if(e.getSource() == btnLast)
        {
            currentEntry = numberOfEntries - 1;
            displayEntry(currentEntry);
        }

        // BUTTON NEW -------------------------------------------
        if(e.getSource() == btnNew)
        {
            // Only if the array is large enough to store another record...
            if (numberOfEntries < maxEntries - 1)
            {
                // Increment the numberOfEntries
                numberOfEntries++;
                // Set the current entry to the new record
                currentEntry = numberOfEntries - 1;
                // Instantiate a new PCInfo object for this new entry
                //    and make all the record entries blank.
                PCInfo[currentEntry] = new PCDataRecord("","","");
                // Display this new blank entry on screen
                displayEntry(currentEntry);
            }
        }

        // BUTTON SAVE ------------------------------------------
        if(e.getSource() == btnSave)
        {
            // Call the saveEntry method that will copy the current
            //     TextField entries from the screen to the current
            //     record in the array in memory.
            saveEntry(currentEntry);
        }

        // BUTTON DELETE ----------------------------------------
        if(e.getSource()== btnDel)
        {
            // Move all the later entries up one line in the array, covering over
            //      the current entry in the process
            for (int i = currentEntry; i < numberOfEntries - 1; i++)
            {
                PCInfo[i].setPCInfo(PCInfo[i+1].getPCName(), PCInfo[i+1].getPCID(), PCInfo[i+1].getIPAddress());
            }
            // Reduce the current total number of entries stored in the array by one.
            // Then check if the current entry is now further down the array than
            //      the last entry.  If so, reduce the value of currentEntry by 1.
            numberOfEntries--;
            if (currentEntry > numberOfEntries - 1)
            {
                currentEntry = numberOfEntries - 1;
            }
            // Display the currentEntry
            displayEntry(currentEntry);
        }

        // BUTTON FIND ------------------------------------------
        if(e.getSource() == btnFind)
        {   
            // Declare a boolean valuable: found (to remember whether the required entry
            //         has been found yet.)
            boolean found = false;
            // Declare a counter (i)
            int i = 0;
            // While there are more entries to check and the 'search' entry has not been found... 
            while (i < numberOfEntries && found == false)
            {
                // If the current entry is equal to the 'search' entry...
                if (PCInfo[i].getPCName().equals( txtFind.getText()))
                {
                    // Set found = true
                    found = true;
                }
                // Increment the counter (i) so the loop will move onto the next record
                i++;
            }
            // If the entry was found, then set the value of currentEntry and then display the entry.
            if (found) 
            {
                currentEntry = i - 1;
                displayEntry(currentEntry);
            }
        }
        
        // BUTTON EXIT ------------------------------------------
        if(e.getSource() == btnExit)
        {
            // Write all the records that are currently in the array (in memory)
            //       to your data file on the hard drive (USB, SSD, or equivalent)
            writeFile(dataFileName);
            // Exit from the application
            System.exit(0);
        }

    }

    /** --------------------------------------------------------
    * Purpose: Respond to Window events, such as clicking the Close ( X ) button
    * @param   Window Event.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void windowClosing(WindowEvent we)
    {
        // Write all the records that are currently in the array (in memory)
        //       to your data file on the hard drive (USB, SSD, or equivalent)
        writeFile(dataFileName);
        // Exit from the application
        System.exit(0);
    }

    public void windowOpened(WindowEvent we)
    {
        // Read in all the records within your data file on the hard
        //      drive (USB, SSD, or equivalent), into the array (in memory)
        readFile(dataFileName);
        // Display the currentEntry on the screen
        displayEntry(currentEntry);
    }

    public void windowIconified(WindowEvent we)
    {
    }

    public void windowClosed(WindowEvent we)
    {
    }

    public void windowDeiconified(WindowEvent we)
    {
    }

    public void windowActivated(WindowEvent we)
    {
    }

    public void windowDeactivated(WindowEvent we)
    {
    }


    /** --------------------------------------------------------
    * Purpose: Display the indexed entry (int i) required on screen.
    * @param   The index (i) of the required entry.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void displayEntry(int i)
    {
        txtPCName.setText(PCInfo[i].getPCName());
        txtPCID.setText(PCInfo[i].getPCID());
        txtIP.setText(PCInfo[i].getIPAddress());
    }

    
    /** --------------------------------------------------------
    * Purpose: Copy the current entry (int i) from the screen
    *          to the array in memory.
    * @param   The index (i) of the required entry.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void saveEntry(int i)
    {
        PCInfo[i].setPCInfo(txtPCName.getText(),txtPCID.getText(),txtIP.getText()); 

        // You may also wish to write all the records that are currently in the array
        //       to your data file on the hard drive (USB, SSD, or equivalent)
        writeFile(dataFileName);
    }

        
    /** --------------------------------------------------------
    * Purpose: Read in the data from the data file - IPAddresses.txt.
    *          Read in one line at a time and store each the arrays of
    *          PCDataRecord objects.  Record the number of entries read in
    *          and store it in the global variable: numberOfEntries.
    * @param   A filename (ie: the global dataFileName)
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void readFile(String fileName)
    {
        // Try to read in the data and if an exception occurs go to the Catch section 
        try
        {
            // Set up various streams for reading in the content of the data file.
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            int i = 0;    // i is used as the line counter
            String line;  // line is used to temporarily store the line read in from the data file
            
            // Read a line from the data file into the buffer and then check whether
            //      it is null.  The while loop continues until a line read in is null.
            while ((line = br.readLine()) != null)
            {
                // Split the line of data (from the text file) and put each entry into the
				//                                             temporary array - temp[]
                String[] temp = line.split(",");
                // Save each entry into its respective PCDataRecord object.
                PCInfo[i] = new PCDataRecord(temp[0],temp[1],temp[2]); 
                i++;  // Increment i so we can keep a count of how many entries have been read in.
            }
            numberOfEntries = i;   // Set numberOfEntries equal to i, to remember how many entries are now in the array 
            br.close();            // Close the BufferedReader
            in.close();            // Close the DataInputStream
            fstream.close();       // Close the FileInputStream
        }
        catch (Exception e)
        {
            System.err.println("Error Reading File: " + e.getMessage());
        }
    }

    
    /** --------------------------------------------------------
    * Purpose: Write the data back out to the data file - one line at a time.
    *          Note: You may wish to use a different data file name while initially
    *                developing, so as not to not accidently corrupt your input file.
    * @param   A filename (ie: the global dataFileName)
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void writeFile(String fileName)
    {
        // Try to print out the data and if an exception occurs go to the Catch section 
        try
        {
            // After testing has been completed, replace the hard-coded filename: "IPAddresses_New.txt"
            //       with the parameter variable: fileName 
            // Set up a PrintWriter for printing the array content out to the data file.
            PrintWriter printFile = new PrintWriter(new FileWriter("IPAddresses_New.txt"));   
            
            // Print out each line of the array into your data file.
            // Each line is printed out in the format:  PCName,PCID,IPAddress
            for(int i = 0; i < numberOfEntries; i++)
            {
                printFile.println(PCInfo[i].getPCName() + "," + PCInfo[i].getPCID() + "," + PCInfo[i].getIPAddress());
            }
            
            // Close the printFile (and in so doing, empty the print buffer)
            printFile.close();
        }
        catch (Exception e)
        {
            System.err.println("Error Writing File: " + e.getMessage());
        }
    }    
    
}





/**  --------------------------------------------------------
 * Class: PCDataRecord
 * 
 * @author Mark O'Reilly
 * 
 * Developed: 2016-2017
 * 
 * Purpose: To record the PC_Name, PC_ID and PC_IP_Address for a SINGLE computer.
 * 
 * Demonstrating the implementation of:
 * - constructor overloading
 * - Getters and Setters
 * 
 * ----------------------------------------------------------
 */

class PCDataRecord 
{
    // Declarations of 3 Strings, used to store the PCName, PCID and IP_Address
    //      data in memory for EACH PCDataRecord.  That is: when one object is instantiated
	//      from this PCDataRecord class, it will record the detail for one PC.
    // These properties - PCName, PCID and IP_Address - are set to private so a calling
    //      class is not able to assign or access the respective values directly.
    //      Access to these properties would be best managed through the respective
    //      getters and setters - see below.
    private String PCName = new String();   
    private String PCID = new String();
    private String IPAddress = new String();
    

    /** --------------------------------------------------------
    * Purpose: Constructor for the class: PCDataRecord.
    *          When a PCDataRecord is instantiated, and no default entries
    *          for the 3 properties - PC_Name/PC_ID/IP_Address - are provided,
    *          this method will set default values for each.
    * @param   None.
    * @returns Not applicable.
    * ----------------------------------------------------------
    */    
    public PCDataRecord()
    {    
        PCName = "PC_Name";
        PCID = "PC_ID";
        IPAddress = "IP_Address";
    }


    /** --------------------------------------------------------
    * Purpose: A second constructor for the class: PCDataRecord.
    *          When a PCDataRecord is instantiated and default entries
    *          for the 3 properties - PC_Name/PC_ID/IP_Address - are
	*          provided by the calling class, this constructor will run.
    * @param   PC_Name, PC_ID and IP_Address.
    * @returns Not applicable.
    * ----------------------------------------------------------
    */    
    public PCDataRecord(String name, String ID, String IP)
    {    
        PCName = name;
        PCID = ID;
        IPAddress = IP;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow the calling class to set the
    *          3 properties - Name/ID/IP_Address - all at the one time.
    * @param   PC_Name, PC_ID and IP_Address.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */    
   public void setPCInfo(String name, String ID, String IP)
    {    
        PCName = name;
        PCID = ID;
        IPAddress = IP;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow the calling class to set
    *          the PCName property.
    *          This method could include code to validate incoming
    *          PC_Name data.
    * @param   PC_Name.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void setPCName(String name)
    {    
        PCName = name;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow the calling class to set
    *          the PCID property.
    *          This method could include code to validate incoming
    *          PC_ID data.
    * @param   PC_ID.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void setPCID(String ID)
    {    
        PCID = ID;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow the calling class to set
    *          the IPAddress property.
    *          This method could include code to validate incoming
    *          IP_Address data.
    * @param   PC_IP_Address.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void setIPAddress(String IP)
    {    
        IPAddress = IP;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow this PCDataRecord class
    *          to provide the calling class with the PC_Name data.
    *          This method allows this class to manage outgoing
    *          PC_Names.
    * @param   None.
    * @returns PCName (String).
    * ----------------------------------------------------------
    */
    public String getPCName()
    {    
        return PCName;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow this PCDataRecord class
    *          to provide the calling class with the PC_ID data.
    *          This method allows this class to manage outgoing
    *          PC_IDs.
    * @param   None.
    * @returns PCID (String).
    * ----------------------------------------------------------
    */
    public String getPCID()
    {    
        return PCID;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow this PCDataRecord class to
    *          provide the calling class with the IP_Address data.
    *          This method allows this class to manage outgoing
    *          IP_Addresses.
    * @param   None.
    * @returns IPAddress (String).
    * ----------------------------------------------------------
    */
    public String getIPAddress()
    {    
        return IPAddress;
    }

}

