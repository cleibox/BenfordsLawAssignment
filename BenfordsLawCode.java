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
        String userInput, generateBargraph, generateCSVFile,exitCondition;
        generateBargraph = "1";
        generateCSVFile = "2";
        exitCondition = "9";
        // Initializing the arrays
        int[] frequencyArr = new int[9];
        double[] percentageArr = new double[frequencyArr.length];
        Scanner reader = new Scanner(System.in);
        // Prompts user to enter pathway
        System.out.println("What is the pathway to reach the folder?");
        String path = reader.nextLine();
        // Prompts user to enter name
        System.out.println("What is the file name you want read");
        String name = reader.nextLine();
        readFile(path, name, frequencyArr, percentageArr);

        do{
            // Print the menu option
            printMenu();
            userInput = reader.nextLine();
            // User chooses to generate bargraph
            if (userInput.equals(generateBargraph)){
            }
            // User chooses to generate CSV file
            else if (userInput.equals(generateCSVFile)){
                generateCustomerDataFile(reader, path, percentageArr);
            }
            // User chooses to exit
            else if (userInput.equals(exitCondition)){
                reader.close();
                System.out.println("Program Terminated");
            }
            // User chooses an invalid option
            else{
                System.out.println("Please type in a valid option");
            }
        } while (!userInput.equals(exitCondition));      
        reader.close();
    }
    // Prints menu
    public static void printMenu(){
        System.out.println("Benfords Law Code\n"
        .concat("1. Print Bargraph\n")
        .concat("2. Print CSV File\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /**
     * @author Sophia Nguyen
     * @param route is the pathway to get to the file
     * @param title is the name of the file
     * @param numArr is the frequency in integers
     * @param valueArr is the frequency in percentage
     */
    public static void readFile(String route, String title, int[] numArr, double[] valueArr){
        String line = "";
        try{ 
            File file = new File(route+title);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                // Populates the array
                numArr = countValue(line, numArr);
            }
            // Outside the while loop since we only get the total frequency
            // when the while loop is finished (all lines are read)
            percentageValue(numArr,valueArr); 
            printArray(numArr);
        }
        // Program cannot find file
        catch (IOException e){ 
            System.out.println("Invalid");
        }
    }
    /**
     * @author Sophia Nguyen
     * @param information is the information read by br
     * @param frequencyArr is the array that stores each frequency
     * @return
     */
    public static int[] countValue(String information, int[] frequencyArr){
        // Taking the fourth character of each line
        // This will be the first number
        char firstNumber = information.charAt(4);
        // Checking frequency for 1
        if(Character.compare(firstNumber, '1') == 0){
            frequencyArr[0] += 1;
        }
        // Checking frequency for 2
        else if(Character.compare(firstNumber, '2') == 0){
            frequencyArr[1] += 1;
        }
        // Checking frequency for 3
        else if(Character.compare(firstNumber, '3') == 0){
            frequencyArr[2] += 1;
        }
        // Checking frequency for 4
        else if(Character.compare(firstNumber, '4') == 0){
            frequencyArr[3] += 1;
        }
        // Checking frequency for 5
        else if(Character.compare(firstNumber, '5') == 0){
            frequencyArr[4] += 1;
        }
        // Checking frequency for 6
        else if(Character.compare(firstNumber, '6') == 0){
            frequencyArr[5] += 1;
        }
        // Checking frequency for 7
        else if(Character.compare(firstNumber, '7') == 0){
            frequencyArr[6] += 1;
        }
        // Checking frequency for 8
        else if(Character.compare(firstNumber, '8') == 0){
            frequencyArr[7] += 1;
        }
        // Checking frequency for 9
        else if(Character.compare(firstNumber, '9') == 0){
            frequencyArr[8] += 1;
        }
        return frequencyArr;
    
    }

    /**
     * @author Cynthia Lei
     * Determining the relative frequency of each digit frequency 
     * 
     * @param arr this contains all the digit frequencies 
     */
    public static void percentageValue(int[] arr, double[] valueArr){
        int totalFrequency = sumArrElements(arr); // get the total frequency 
        
        // read through each digit frequency
        for (int i = 0; i < arr.length; i++){
            // round to 2 decimal places and as percentage (%)
            valueArr[i] = Math.round((arr[i]*1.0/totalFrequency) * 100 * 100.0) / 100.0;
        }
        printArrayDouble(valueArr); // testing to see if it works
    }

    /**
     * @author Cynthia Lei
     * Summing up the elements in a given array
     * 
     * @param arr this contains all the digit frequencies
     * @return the sum of all elements in this array
     */
    public static int sumArrElements(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i]; // accumulator variable
        }
        return sum;    
    }
    /**
     * @author Sophia Nguyen
     * Print the given array
     * Procedural method as its only executing a commang
     * @param arr that needs to be printed
     */
    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            System.out.print("|");
        }
        System.out.println();
    }

    // I WILL DELETE THIS IN THE FUTURE SINCE WE DON'T REALLY NEED TO PRINT OUT THE PERCENTAGE ARRAY
    public static void printArrayDouble(double[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            System.out.print("|");
        }
        System.out.println();
    }
    /**
     * @author Sophia Nguyen
     * Procedural method to create a csv file
     * @param reader to take in user input
     * @param pathway to reach the directory folder
     * @param percentageArr to print out information
     */
    public static void generateCustomerDataFile(Scanner reader, String pathway, double[] percentageArr){
        String content = "";
        try{
            System.out.println("Would you like to store your information on a csv file?");
            String store = reader.nextLine();
            // User wants to store their info on a csv file
            if(store.equals("Yes"))
            {
                // Allows user to name the file
                System.out.println("What would you like to name your file?");
                String name = reader.nextLine();
                String info = (pathway + name + ".csv");
                // Creating the new csv file
                BufferedWriter bw = new BufferedWriter(new FileWriter(info));  
                PrintWriter pw = new PrintWriter(bw);
                // Adding info into the database file
                String title = ("First Digit | Relative Frequency (%)");
                // Typing out the information
                pw.print((title+ "\n")
                .concat(contentCSV(percentageArr,content))
                );
                bw.close();
                pw.close();
            }
        }
        catch(Exception e){
            System.out.println("Fail");
        }
    }
    /**
     * @author Sophia nguyen
     * @param arr to retrieve content of array
     * @param content String that will be printed on seperate CSV file
     * @return the string that will be printed later
     */
    public static String contentCSV(double[] arr, String content){
        // For looping to read each line
        for(int i = 0; i < arr.length; i++){
            // The string stores the information that will later be printed
            content += ((i+1) + " | " + arr[i] + "\n");
        }
        return content;
    }
}
