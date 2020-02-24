// With Remaining Code

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


/**
 * @author Mark O'Reilly
 */

public class IPAddressDatabase extends Frame implements ActionListener, WindowListener
{
    // DECLARATIONS --------------------------------------------------------------------------
    int maxEntries = 100;     
    int numberOfEntries = 0;  
    int currentEntry = 0;     
    
    String[] PCName = new String[maxEntries];   
    String[] PCID = new String[maxEntries];
    String[] IPAddresses = new String[maxEntries];

    Label lblPCName, lblPCID, lblIP, lblFind;
    TextField txtPCName, txtPCID, txtIP, txtFind;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;


	//--------------------------------------------------------------------------

    public static void main(String[] args)
    {
        Frame myFrame = new IPAddressDatabase();
        myFrame.setSize(470,250);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    public IPAddressDatabase()
    {
        setTitle("IP Address Database");
        setBackground(Color.yellow);

        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);

        this.addWindowListener(this);
    }
	
	
	//--------------------------------------------------------------------------

    public void LocateLabels(SpringLayout myLabelLayout)
    {
        lblPCName = LocateALabel(myLabelLayout, lblPCName, "PC Name", 30, 25);
        lblPCID = LocateALabel(myLabelLayout, lblPCID, "PC ID", 30, 50);
        lblIP = LocateALabel(myLabelLayout, lblIP, "IP", 30, 75);
        lblFind = LocateALabel(myLabelLayout, lblFind, "Search", 30, 120);
    }

    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
        myLabel = new Label(LabelCaption);
        add(myLabel);        
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }
   
    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtPCName  = LocateATextField(myTextFieldLayout, txtPCName, 20, 130, 25);
        txtPCID = LocateATextField(myTextFieldLayout, txtPCID, 20, 130, 50);
        txtIP = LocateATextField(myTextFieldLayout, txtIP, 20, 130, 75);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 20, 130, 120);
    }

    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }

    public void LocateButtons(SpringLayout myButtonLayout)
    {
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

	
	// NEW CODE ADDED: -------------------------------------------------------------------
	
    public void actionPerformed(ActionEvent e)
    {
        // BUTTON FIRST -----------------------------------------
        if(e.getSource() == btnFirst)
        {
            // The currentEntry variable is used to define which record will be displayed
            //     on screen.
            // In this instance, set the currentEntry to 0 (ie: the index of the first entry)
            currentEntry = 0;
            
            // The displayEntry method will display the currentEntry on the screen
            // In this instance, display the first entry (currentEntry = 0) on the screen.   
            displayEntry(currentEntry);
        }

        // BUTTON PREVIOUS --------------------------------------
        if(e.getSource() == btnPrev)
        {
            // Only go to the previous record if we have a previous entry in the array...
            if(currentEntry > 0)
            {
                // Reduce the value of currentEntry by 1
                currentEntry--;
				// Display the current entry
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
                // Increase the value of currentEntry by 1
                currentEntry++;
				// Display the current entry
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
                // Blank out any existing data in the 3 arrays, ready
                //       for adding the new record.
                PCName[currentEntry] = ""; 
                PCID[currentEntry] = ""; 
                IPAddresses[currentEntry] = ""; 
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
            // Move all the later entries up one line each in the arrays, covering over
            //      the current entry in the process
            for (int i = currentEntry; i < numberOfEntries - 1; i++)
            {
                PCName[i] = PCName[i + 1];
                PCID[i]  = PCID[i + 1];
                IPAddresses[i] = IPAddresses[i + 1];
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
            // Declare a boolean valuable: found (to remember whether
            //         the required entry has been found yet.)
            boolean found = false;
			// Declare a counter (i)
            int i = 0;
            // While there are more entries to check and the 'search' entry has not been found... 
            while (i < numberOfEntries && found == false)
            {
                // If the current PCName is equal to the 'search' entry...
                if (PCName[i].equals( txtFind.getText()))
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
            writeFile();
            // Exit from the application
            System.exit(0);
        }

    }

	
	//--------------------------------------------------------------------------
	
    public void windowClosing(WindowEvent we)
    {
        writeFile();
        System.exit(0);
    }

    public void windowIconified(WindowEvent we)
    {
    }

    public void windowOpened(WindowEvent we)
    {
        readFile();
        displayEntry(currentEntry);
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

	
	//--------------------------------------------------------------------------
	
    public void displayEntry(int index)
    {
        txtPCName.setText(PCName[index]);
        txtPCID.setText(PCID[index]);
        txtIP.setText(IPAddresses[index]);
    }

    public void saveEntry(int index)
    {
        PCName[index] = txtPCName.getText();
        PCID[index] = txtPCID.getText();
        IPAddresses[index] = txtIP.getText();
        writeFile();
    }

	// Read in the data from the data file - IPAddresses.txt - one line at a time and store in the 3 arrays.
	// Remember the number of entries read in, in the global variable: numberOfEntries
    public void readFile()
    {
        try
        {
            FileInputStream fstream = new FileInputStream("IPAddresses.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
			int i = 0;   
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split(",");
                PCName[i] = temp[0];
                PCID[i] = temp[1];
                IPAddresses[i] = temp[2];
                i++;  
            }
            numberOfEntries = i;
            br.close();
            in.close();
            fstream.close();
        }
        catch (Exception e)
        {
            System.err.println("Error Reading File: " + e.getMessage());
        }
    }

    
	// Write the data back out to the data file - one line at a time
    public void writeFile()
    {
        try
        {
            // After testing has been completed, replace the hard-coded filename: "IPAddresses_New.txt"
            //       with the parameter variable: fileName 
            PrintWriter out = new PrintWriter(new FileWriter("IPAddresses_New.txt"));
            for(int m = 0; m < numberOfEntries; m++){
                out.println(PCName[m] +"," +PCID[m] + "," + IPAddresses[m]);
            }
            out.close();
        }
        catch (Exception e)
        {
            System.err.println("Error Writing File: " + e.getMessage());
        }
    }
  
}
