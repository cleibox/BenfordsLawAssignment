/**
 * Date: May 5, 2021
 * Name: Cynthia Lei & Sophia Nguyen
 * Teacher: Mr. Ho
 * Description: Benfords Law Assignment
 */

// Testing branch
import java.io.IOException;
import java.util.Scanner; // scanner
import java.io.*;
import java.io.File; 

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtils;

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
                generateBarGraph();
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
            // Outside the while loop since we only get the total frequency
            // when the while loop is finished (all lines are read)
            percentageValue(frequencyArr); 
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

    /**
     * @author Cynthia Lei
     * Determining the relative frequency of each digit frequency 
     * 
     * @param arr this contains all the digit frequencies 
     */
    public static void percentageValue(int[] arr){
        int totalFrequency = sumArrElements(arr); // get the total frequency
        double[] percentageArr = new double[arr.length]; // 
        
        // read through each digit frequency
        for (int i = 0; i < arr.length; i++){
            // round to 2 decimal places and as percentage (%)
            percentageArr[i] = Math.round((arr[i]*1.0/totalFrequency) * 100 * 100.0) / 100.0;
        }
        printArrayDouble(percentageArr); // testing to see if it works
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

    public static void generateBarGraph() {
        final String fiat = "FIAT";
        final String audi = "AUDI";
        final String ford = "FORD";
        final String speed = "Speed";
        final String millage = "Millage";
        final String userrating = "User Rating";
        final String safety = "safety";
  
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 1.0 , fiat , speed );
        dataset.addValue( 3.0 , fiat , userrating );
        dataset.addValue( 5.0 , fiat , millage );
        dataset.addValue( 5.0 , fiat , safety );
  
        dataset.addValue( 5.0 , audi , speed );
        dataset.addValue( 6.0 , audi , userrating );
        dataset.addValue( 10.0 , audi , millage );
        dataset.addValue( 4.0 , audi , safety );
  
        dataset.addValue( 4.0 , ford , speed );
        dataset.addValue( 2.0 , ford , userrating );
        dataset.addValue( 3.0 , ford , millage );
        dataset.addValue( 6.0 , ford , safety );
  
        JFreeChart barChart = ChartFactory.createBarChart(
           "Benford's Law Distribution Leading Digit", // Graph title 
           "Digit", "Percent", // x-axis title, y-axis title
           dataset,PlotOrientation.VERTICAL, // range axis is vertical
           true, true, false);
           
        int width = 640;    // Image Width
        int height = 480;   // Image Height
        File barChartName = new File( "BenfordBarChart.png" ); // File name
        
        try{
            ChartUtils.saveChartAsPNG(barChartName , barChart , width , height );
        }
        catch (IOException e){
            System.out.println("Error saving file");
        }
     }
   
}
