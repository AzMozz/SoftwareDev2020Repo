// With Sample Modularisation

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;

/**
 * @author Mark O'Reilly
 */

public class IPAddressDatabase extends Frame implements WindowListener
{
    Label lblPCName, lblPCID, lblIP, lblFind;
    TextField txtPCName, txtPCID, txtIP, txtFind;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;

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
        
	// Call the methods below to instantiate and place the various screen components
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);

        this.addWindowListener(this);
    }

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Labels to the screen.
    // Each line requests the LocateALabel method to instantiate, add and place a specific Label  
    public void LocateLabels(SpringLayout myLabelLayout)
    {
        lblPCName = LocateALabel(myLabelLayout, lblPCName, "PC Name", 30, 25);
        lblPCID = LocateALabel(myLabelLayout, lblPCID, "PC ID", 30, 50);
        lblIP = LocateALabel(myLabelLayout, lblIP, "IP", 30, 75);
        lblFind = LocateALabel(myLabelLayout, lblFind, "Search", 30, 120);
    }

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual labels:
    *    - reduces overall code, especially in the
    *         LocateLabels method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
        myLabel = new Label(LabelCaption);
        add(myLabel);        
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }
   

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple TextFields to the screen.
    // Each line requests the LocateATextField method to instantiate, add and place a specific TextField  
    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtPCName  = LocateATextField(myTextFieldLayout, txtPCName, 20, 130, 25);
        txtPCID = LocateATextField(myTextFieldLayout, txtPCID, 20, 130, 50);
        txtIP = LocateATextField(myTextFieldLayout, txtIP, 20, 130, 75);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 20, 130, 120);
    }

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual textboxes:
    *    - reduces overall code, especially in the
    *         LocateTextFields method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }


    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Buttons to the screen.
    // Each line requests the LocateAButton method to instantiate, add and place a specific Button  
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

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual buttons:
    *    - reduces overall code, especially in the
    *         LocateButtons method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h)
    {    
        myButton = new Button(ButtonCaption);
        add(myButton);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w,h));
        return myButton;
    }
  

    // Manage responses to the various Window events
    public void windowClosing(WindowEvent we)
    {
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
