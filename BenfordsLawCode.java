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
                System.out.println("Please type in a valid option");
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
        int frequency = 0;
        int count = 0;
        int[] frequencyArr = new int[9];
        int[] tempArr = new int[9];
        try{ 
            File file = new File(route+title);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                // Calls the count value
                //count = countValue(line,frequency);
                //frequency = count;
                //System.out.println(frequency);
                
                // Populates the array
                tempArr = countValue(line, frequencyArr);
                frequencyArr = tempArr;
                
            }
            
            printArray(frequencyArr);
        }
        // Program cannot find file
        catch (IOException e){ 
            System.out.println("Invalid");
        }
    }
    // Takes the first digit
    public static int[] countValue(String number, int[] frequencyArr){
        String line  = number;
        char firstNumber = line.charAt(4);
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

    

    public static int sumArrElements(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        return sum;    
    }

    // Print the array
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
}
