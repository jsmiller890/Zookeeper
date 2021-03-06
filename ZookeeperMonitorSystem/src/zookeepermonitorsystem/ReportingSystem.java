package zookeepermonitorsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Author Jeremy Miller
 */
public class ReportingSystem {
    
        public static void DetectProblem(String testString) {
        if (testString.contains("*****")) {
            testString = testString.replace("*****", "");
            JOptionPane.showMessageDialog(null, testString, "Alert", 2);
            return;
        }
        else {
            return;
        }
    }
    
    public static void ReadFile(String category) throws IOException {
        
        /*
            Variable Declaration
        */
        
        File file = new File(category + ".txt");
        FileInputStream fileByteStream = new FileInputStream(file);
        Scanner choiceScnr = new Scanner(fileByteStream);
        Scanner userScnr = new Scanner(System.in);
        int choiceCount = 0;
        String userInput;
        String userCategory = category;
        String displayOption = null;
        String testString = "none";
        String fullUserChoice;
        List<String> choices = new ArrayList<>();
        boolean loopDone = false;
        boolean choiceBool = false;
        
        while(!testString.equals("")) {
            testString = choiceScnr.nextLine();
            if (testString.equals("")) {
            }
            else {
                System.out.println(testString);
                choices.add(choiceCount, testString);
                choices.set(choiceCount, choices.get(choiceCount).substring(11, choices.get(choiceCount).length()));
                ++choiceCount;
            }
        }
        
        System.out.println("\n");
        /*
            Ask for input for which animal or habitat to monitor.
            Put the user's input through a for loop to check if it matches any of the choices from the file.
        */
        while(choiceBool != true) {
            
            System.out.println("Which " + category + " would you like to view?\nType \"back\" to go back\n");
            userInput = userScnr.nextLine();
            userInput = userInput.toLowerCase();
            if (userInput.equals("back")) {
                return;
            }
            for (int i = 0; i != choices.size(); ++i) {
                if (userInput.equals(choices.get(i))) {
                    displayOption = userInput;
                    choiceBool = true;
                    break;
                }
                else {
                    continue;
                }
            }
        }
        
        System.out.println("\n");
        
        /*
            Change the animal or habitat chosen so it matches the file
            Then store it in a new string
        */
        
        if (category.equals("animal")) {
            displayOption = displayOption.substring(0, 1).toUpperCase() + displayOption.substring(1);
            displayOption = displayOption.replace(displayOption.substring(displayOption.length() - 1), "");
            userCategory = category.substring(0, 1).toUpperCase() + category.substring(1);
        }
        else if (category.equals("habitat")) {
            if (displayOption.contains(" ")) {
                displayOption = displayOption.replace(displayOption.substring(displayOption.indexOf(" ")), "");
            }
            displayOption = displayOption.substring(0, 1).toUpperCase() + displayOption.substring(1);
            userCategory = category.substring(0, 1).toUpperCase() + category.substring(1);
        }

        /*
            Compare user inputed category by user chosen animal/habitat to the data in file
            Continue going down the file until it finds a match or give error message
            if no match was found
        */
        
        fullUserChoice = userCategory + " - " + displayOption;
        testString = choiceScnr.nextLine();
        while (!testString.equals(fullUserChoice)) {
            testString = choiceScnr.nextLine();
            if (testString == null) {
                System.out.println("Error finding the " + category + " you are looking for.");
                return;
            }
        }
        
        /*
            Prints the details of the chosen animal/habitat until it reaches the next
            animal/habitat. After printing all the data close the file.
        */
        
        while (!testString.equals("")) {
            if (choiceScnr.hasNextLine() == true) {
                DetectProblem(testString);
                System.out.println(testString);
                testString = choiceScnr.nextLine();
            }
            else {
                DetectProblem(testString);
                System.out.println(testString);
                break;
            }
            System.out.println("");
        }
        System.out.println("====================================================\n");
        
        fileByteStream.close();
        
        /*
            Ask user if they would like to view anymore data on the current category
            and lets user input another animal/habitat or go back to category choice menu
        */
        
        while (loopDone == false) {
            System.out.println("Is there another " + category + " you would like to monitor? Y or N");
            userInput = userScnr.nextLine();
            System.out.println("\n");
            if (userInput.equals("Y") || userInput.equals("y")) {
                ReadFile(category);
                loopDone = true;
            }
            else if (userInput.equals("N") || userInput.equals("n")) {;
                return;
            }
            else {
                System.out.println("Error: Incorrect Input\n");
            }
        }
    }
    
}
