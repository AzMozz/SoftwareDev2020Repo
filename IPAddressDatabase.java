// With SpringLayout 

// import required components from existing java libraries
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;

/**
 * @author Mark O'Reilly
 */

 // The class header...
 // This class is being established as a program that will display in a frame within the operating system
 //      and will have access to a window listener should the user wish to close the frame.
public class IPAddressDatabase extends Frame implements WindowListener
{
	// Declare the Buttons, TextFields and Labels required for this program.
    Label lblPCName, lblPCID, lblIP, lblFind;
    TextField txtPCName, txtPCID, txtIP, txtFind;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;

    // Main entry point to the class and application.
    // String[] args allows the user to type in additional parameters on the console when first
    //          running the program.  This feature is not utilised in this program.
    public static void main(String[] args)
    {
        // Declares and instantiates the Frame and in so doing, calls the: public IPAddressDatabase
        //          constructor below.  The additional code lines set the various properties of the Frame.
        Frame myFrame = new IPAddressDatabase();
        myFrame.setSize(470,250);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }


    public IPAddressDatabase()
    {
        // Set the Title of the frame
        setTitle("IP Address Database");
        // Sets the background colour of the Frame to yellow.
        setBackground(Color.yellow);

        // Set the layout manager of the various screen components to Java's: SpringLayout
        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        
        // Call the methods below to instantiate and place the various screen components
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);
        
        // Add the WindowListener to this Frame
        this.addWindowListener(this);
    
    }

    public void LocateLabels(SpringLayout myLabelLayout)
    {
        // Instantiate a new Label
        lblPCName = new Label("PC Name");
        // Add the label to the Frame
        add(lblPCName);   
        // Set the positioning of this Label to 30 pixels from the left of the frame and
        //     25 pixels from the top.
        myLabelLayout.putConstraint(SpringLayout.WEST, lblPCName, 30, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, lblPCName, 25, SpringLayout.NORTH, this);

        lblPCID = new Label("PC ID");
        add(lblPCID);        
        myLabelLayout.putConstraint(SpringLayout.WEST, lblPCID, 30, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, lblPCID, 50, SpringLayout.NORTH, this);
        
        lblIP = new Label("IP");
        add(lblIP);        
        myLabelLayout.putConstraint(SpringLayout.WEST, lblIP, 30, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, lblIP, 75, SpringLayout.NORTH, this);

        lblFind = new Label ("Search: ");
        add(lblFind);    
        myLabelLayout.putConstraint(SpringLayout.WEST, lblFind, 30, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, lblFind, 120, SpringLayout.NORTH, this);
    }

    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtPCName = new TextField (20);
        add(txtPCName);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, txtPCName, 130, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, txtPCName, 25, SpringLayout.NORTH, this);

        txtPCID = new TextField (20);
        add(txtPCID);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, txtPCID, 130, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, txtPCID, 50, SpringLayout.NORTH, this);
                
        txtIP = new TextField (20);
        add(txtIP);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, txtIP, 130, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, txtIP, 75, SpringLayout.NORTH, this);

        txtFind = new TextField (20);
        add(txtFind);    
        myTextFieldLayout.putConstraint(SpringLayout.WEST, txtFind, 130, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, txtFind, 120, SpringLayout.NORTH, this);
    }
    
    public void LocateButtons(SpringLayout myButtonLayout)
    {
        // Intantiate a new Button
        btnNew = new Button("New Entry");
        // Add the Button to the Frame
        add(btnNew);
        // Set the positioning of this Label to 320 pixels from the left of the frame and
        //     25 pixels from the top.
        myButtonLayout.putConstraint(SpringLayout.WEST, btnNew, 320, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnNew, 25, SpringLayout.NORTH, this);
        // Set the preferred size of this button to 80 pixels wide and 25 pixels high.
        btnNew.setPreferredSize(new Dimension(80,25));
        
        btnSave = new Button("Save Entry");
        add(btnSave);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnSave, 320, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnSave, 50, SpringLayout.NORTH, this);
        btnSave.setPreferredSize(new Dimension(80,25));
        
        btnDel = new Button("Delete Entry");
        add(btnDel);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnDel, 320, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnDel, 75, SpringLayout.NORTH, this);
        btnDel.setPreferredSize(new Dimension(80,25));
        
        btnFind = new Button("Find Entry");
        add(btnFind);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnFind, 320, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnFind, 100, SpringLayout.NORTH, this);
        btnFind.setPreferredSize(new Dimension(80,25));
        
        btnExit = new Button("Exit");
        add(btnExit);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnExit, 320, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnExit, 170, SpringLayout.NORTH, this);
        btnExit.setPreferredSize(new Dimension(80,25));
        
        btnFirst = new Button("|<");
        add(btnFirst);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnFirst, 140, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnFirst, 170, SpringLayout.NORTH, this);
        btnFirst.setPreferredSize(new Dimension(30,25));
        
        btnPrev = new Button("<");
        add(btnPrev);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnPrev, 180, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnPrev, 170, SpringLayout.NORTH, this);
        btnPrev.setPreferredSize(new Dimension(30,25));
        
        btnNext = new Button(">");
        add(btnNext);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnNext, 220, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnNext, 170, SpringLayout.NORTH, this);
        btnNext.setPreferredSize(new Dimension(30,25));
        
        btnLast = new Button(">|");
        add(btnLast);
        myButtonLayout.putConstraint(SpringLayout.WEST, btnLast, 260, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, btnLast, 170, SpringLayout.NORTH, this);
        btnLast.setPreferredSize(new Dimension(30,25));
    }


    // Manage responses to the various Window events
    public void windowClosing(WindowEvent we)
    {
        // Exit the program
        System.exit(0);
    }

    public void windowIconified(WindowEvent we)
    {
    }

    public void windowOpened(WindowEvent we)
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

}
