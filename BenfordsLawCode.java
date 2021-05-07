/**
 * Date: May 5, 2021
 * Name: Cynthia Lei & Sophia Nguyen
 * Teacher: Mr. Ho
 * Description: Benfords Law Assignment
 */

// Testing branch
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.io.File; // scanner

class BenfordsLawCode {
    public static void main(String[] args) {
        String userInput, readFileName, generateBargraph, generateCSVFile,exitCondition;
        readFileName = "1";
        generateBargraph = "2";
        generateCSVFile = "3";
        exitCondition = "9";
        Scanner reader = new Scanner(System.in);
        // Prompts user to enter pathway
        System.out.println("What is the pathway to reach the folder?");
        String path = reader.nextLine();
        // Prompts user to enter name
        System.out.println("What is the file name you want read");
        String name = reader.nextLine();

        do{
            // Print the menu option
            printMenu();
            userInput = reader.nextLine();
            // User chooses to read file
            if (userInput.equals(readFileName)){
                readFile(path,name);
            }
            // User chooses to generate bargraph
            else if (userInput.equals(generateBargraph)){
            }
            // User chooses to generate CSV file
            else if (userInput.equals(generateCSVFile)){
            }
            // User chooses to exit
            else if (userInput.equals(exitCondition)){
                reader.close();
                System.out.println("Program Terminated");
            }
            // User chooses an invalid option
            else{
                System.out.println("Please type in a valid option (1 or 9)");
            }
        } while (!userInput.equals(exitCondition));      
        reader.close();
    }
    // Prints menu
    public static void printMenu(){
        System.out.println("Benfords Law Code\n"
        .concat("1. Read Sales File\n")
        .concat("2. Print Bargraph\n")
        .concat("3. Print CSV File\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    // Reads the file
    public static void readFile(String route, String title){
        String line = "";
        try{ 
            File file = new File(route+title);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                // Calls the count value
                countValue(line);
            }
        }
        // Program cannot find file
        catch (IOException e){ 
            System.out.println("Invalid");
        }
    }
    // Takes the first digit
    public static void countValue(String number){
        String line  = number;
        // Print out 1st frequency
        char firstNumber = line.charAt(4);
        System.out.println(firstNumber);
    }
}
