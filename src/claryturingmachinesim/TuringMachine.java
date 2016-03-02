/*
 * ‘******************************************************
 * ‘***  TuringMachine
 * ‘***  Author: Erik Clary
 * ‘******************************************************
 * ‘*** Purpose: This is the turing machine to be used.
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Erik
 */
class TuringMachine{
    int currentState;
    ArrayList <String> inputString;
    ArrayList <Rule> ruleList;
    
    /*
    ‘******************************************************
    ‘***  TuringMachine
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method is the constructor for the turingmachine object.
    ‘*** Method Inputs:
    ‘*** String pathRules: the rules for the turing machine to use when checking words
    ‘*** String pathWords: the words for the turing machine to check
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** April 21, 2015
    ‘******************************************************
    */
    public TuringMachine(String pathRules, String pathWords) throws IOException{
        ruleList = new ArrayList();
        inputString = new ArrayList();
        try {
            BufferedReader scan = new BufferedReader(new FileReader(pathRules));
            String[] parsed;
            
            /*need to fix while check*/
            while(scan.ready()){
                parsed = (scan.readLine()).split(",");
                Rule r = new Rule(Integer.parseInt(parsed[0]),parsed[1].charAt(0),Integer.parseInt(parsed[2]),parsed[3].charAt(0), parsed[4].charAt(0));
                ruleList.add(r);
                /*test*/System.out.println(r);
            }
            scan.close();
            scan = new BufferedReader(new FileReader(pathWords));
            while(scan.ready()){
                inputString.add(scan.readLine());
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not Found.");
        }
          catch (NullPointerException e){
            System.out.println("Reached end of file");
        }
    }
    /*
    ‘******************************************************
    ‘***  run
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method is the running logic for the turing machine.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** April 21, 2015
    ‘******************************************************
    */
    public void run(){
        
        for(int i = 0; i<inputString.size(); i++){
            currentState = 1;
            int indx = 0;
            int inputPos = 1;
            boolean found = false;
                System.out.println("Input word: " + inputString.get(i));
                try{
                    while(currentState != 0){
                        while(!found){
                            if((currentState == ruleList.get(indx).currentState) && 
                                (inputString.get(i).charAt(inputPos) == (ruleList.get(indx).read)))
                                found = true;
                            indx++;
                        }
                        indx -= 1; // corrects the found error in above while loop
                        /*
                        now you've found the rule.
                        */
                        //System.out.println("I found rule: " + ruleList.get(indx));
                        currentState = ruleList.get(indx).nextState;

                        char[] temp = inputString.get(i).toCharArray();
                        temp[inputPos] = ruleList.get(indx).toWrite;
                        inputString.set(i, String.valueOf(temp));

                        if(ruleList.get(indx).direction == 'R'){
                            inputPos+=1;
                        }
                        if(ruleList.get(indx).direction == 'L'){
                            inputPos-=1;
                        }
                        indx = 0;
                        found = false;
                    }
                    /*
                    current state is in accepting state
                    */
                    System.out.println("Word Accepted.");
                }
                catch(IndexOutOfBoundsException e){
                    /*
                    could not find rule to match current state
                    or rejected word.
                    */
                    System.out.println("Word NOT Accepted.");
                }
        }
    }
    
    /*
    * ‘******************************************************
    * ‘***  Rule
    * ‘***  Author: Erik Clary
    * ‘******************************************************
    * ‘*** Purpose: This is the rule class for the turing machine to use.
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
    private class Rule{
        int currentState;
        char read;
        int nextState;
        char toWrite;
        char direction;
        
        /*
        ‘******************************************************
        ‘***  Rule
        ‘***  Author: Erik Clary
        ‘******************************************************
        ‘*** Purpose: This is the rule constructor for this class, it takes in the current state, the character that is read, the state to go to, what character to write, and the direction the turing machine will go next.
        ‘*** Method Inputs:
        ‘*** int cState: the current state the machine is in
        ‘*** char tRead: the character the machine has read
        ‘*** int state: the state that the machine will go next
        ‘*** char write: the charater the machine will write
        ‘*** char dir: the direction the machine will go next. limited to (L/R)
        ‘*** Return value:
        ‘*** N/A
        ‘******************************************************
        ‘*** April 21, 2015
        ‘******************************************************
        */
        public Rule(int cState, char tRead, int state, char write, char dir){
            currentState = cState;
            read = tRead;
            nextState = state;
            toWrite = write;
            direction = dir;
            
        }
        /*
        ‘******************************************************
        ‘***  toString
        ‘***  Author: Erik Clary
        ‘******************************************************
        ‘*** Purpose: This toString method displays the rule's attributes with a comma separator.
        ‘*** Method Inputs:
        ‘*** N/A
        ‘*** Return value:
        ‘*** The rule's attributes in the order: currentState, read, nextState, toWrite, direction
        ‘******************************************************
        ‘*** April 21, 2015
        ‘******************************************************
        */
        public String toString(){
            return "" + currentState +", "+ read +", "+ nextState +", "+ toWrite +", "+ direction;
        }
    }
}


