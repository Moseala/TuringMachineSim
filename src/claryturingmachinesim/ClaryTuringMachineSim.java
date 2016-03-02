/*
 * ‘******************************************************
 * ‘***  ClaryTuringMachineSim
 * ‘***  Author: Erik Clary
 * ‘******************************************************
 * ‘*** Purpose: This is the main class for the program.
 * ‘******************************************************
 * ‘*** April 21, 2015
 * ‘******************************************************
 * ‘*** Apr 21: Initial code written
 * ‘******************************************************
 * ‘*** Look at this!
 * ‘*** List all the places in the code where you did something interesting,
 * ‘*** clever or elegant.  If you did good work in this program and you want
 * ‘*** me to consider it in your grade, point it out here.
 * ‘*******************************************************
 */
package claryturingmachinesim;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author Erik
 */
public class ClaryTuringMachineSim {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Frame frm = new Frame();
	FileDialog fd = new FileDialog(frm, "Please choose the Rule file:", FileDialog.LOAD);
        fd.setVisible(true);
	String selectedRules = fd.getDirectory() + File.separator + fd.getFile();
        
        fd = new FileDialog(frm, "Please choose the word file:", FileDialog.LOAD);
        fd.setVisible(true);
	String selectedWords = fd.getDirectory() + File.separator + fd.getFile();

        TuringMachine test = new TuringMachine(selectedRules,selectedWords);
        test.run();
        fd.dispose();
        frm.dispose();
    }
    
}

