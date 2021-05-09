/**
 * Date: May 5, 2021
 * Name: Cynthia Lei & Sophia Nguyen
 * Teacher: Mr. Ho
 * Description: Benfords Law Assignment
 */

import java.util.Scanner; // scanner

// File imports
import java.io.IOException;
import java.io.*;
import java.io.File;

// jfreechart imports (bar graph)
// Get the included jar file in the github
// In VSCode, Explorer > JAVA PROJECTS > Referenced Libraries > Add library (the jar file)
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtils;

class BenfordsLawCode {
    public static void main(String[] args) {
        // Initializing the arrays
        // Length of array is 9 since there are only 9 possibilities
        int[] frequencyArr = new int[9];
        double[] percentageArr = new double[frequencyArr.length];
        
        Scanner reader = new Scanner(System.in); // Scanner
        
        boolean fileExists = false;
        String path = "";
        String name = "";

        // Force user to reinput if no file is found
        do{
            // Prompts user to enter pathway
            System.out.println("What is the pathway to reach the folder?");
            path = reader.nextLine();
            
            // Prompts user to enter name
            System.out.println("What is the file name you want read");
            name = reader.nextLine();
            
            System.out.println();

            fileExists = readFile(path, name, frequencyArr, percentageArr);
        } while (fileExists == false);

        System.out.println("Generating the csv file and bar graph image ......");
        generateBarGraph(percentageArr);
        generateCustomerDataFile(reader, path, percentageArr);

        System.out.println("\nEnd of Program");
        
        reader.close();
    }

    /**
     * @author Sophia Nguyen
     * Method to read the file that user has provided
     * If the file is not found, user will be alerted
     * 
     * @param route is the pathway to get to the file
     * @param title is the name of the file
     * @param numArr is the frequency in integers
     * @param valueArr is the frequency in percentage
     * @return false if file is not found, otherwise true
     */
    public static boolean readFile(String route, String title, int[] numArr, double[] valueArr){
        String line = "";
        try{ 
            File file = new File(route+title);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                // Populates the array
                numArr = countValue(line, numArr);
            }
            // Outside the while loop since we only get the total frequency when the while loop is finished (all lines are read)
            percentageValue(numArr,valueArr); 
            
            br.close(); // close buffered reader
        }
        // Program cannot find file
        catch (IOException e){ 
            System.out.println("Cannot find file. Please reinput.");
            return false;
        }
        return true;
    }
    /**
     * @author Sophia Nguyen
     * Method that finds where the comma is located
     * @param row is the line of information from the sales.csv file
     * @return the number where the comma is located
     */
    public static int findSpace(String row){
        int len = row.length();
        for (int i = 0; i < len; i++){
            // If statements to check for the comma
            if (row.charAt(i) == ','){
                return i;
            }
        }
        // Base case for if there is a string that has no commas
        return 0;

    }
    /**
     * @author Sophia Nguyen
     * To count up how many times the first number occurs to prove Benfords law of distribution
     * 
     * @param information is the information read by buffered reader (br)
     * @param frequencyArr is the array that stores each frequency
     * @return an array that carries the frequency
     */
    public static int[] countValue(String information, int[] frequencyArr){
        // Finding the comma of the line on sales.csv
        int comma = findSpace(information);
        // This will be the first number since it is located at the space right after comma
        char firstNumber = information.charAt(comma+1);
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
     * @param percentArr this contains all the digit relative frequencies 
     */
    public static void percentageValue(int[] arr, double[] percentArr){
        int totalFrequency = sumArrElements(arr); // get the total frequency 
        
        // read through each digit frequency
        for (int i = 0; i < arr.length; i++){
            // round to 2 decimal places and as percentage (%)
            percentArr[i] = Math.round((arr[i]*1.0/totalFrequency) * 100 * 100.0) / 100.0;
        }

        System.out.println("Fraud (likeliness) present: " + isThereFraudValidation(percentArr));
    }

    /**
     * @author Cynthia Lei
     * Summing up the elements in a given array
     * 
     * @param arr this contains all the digit frequencies
     * @return the sum of all elements in the array
     */
    public static int sumArrElements(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i]; // accumulator variable
        }
        return sum;    
    }

    /**
     * @author Cynthia Lei
     * Determining if there is fraud
     * 
     * @param arr this contains all the digit relative frequencies 
     * @return false if there is no fraud; otherwise true
     */
    public static boolean isThereFraudValidation(double[] percentArr){
        // Checking validity of the appearance of number 1 using Benfords Law of Distribution 
        if (percentArr[0] < 32.0 && percentArr[0] > 29.0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * @author Sophia Nguyen
     * Print the given array
     * Procedural method as its only executing a command
     * @param arr that needs to be printed
     */
    public static void printArray(int[] arr){
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
            String fileName = "results.csv";
            System.out.println("View the created csv file called '" + fileName +  "' in the directory with sales.csv.");
            
            // File name
            String info = (pathway + fileName);
            
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
        catch(Exception e){
            System.out.println("Fail");
        }
    }

    /**
     * @author Sophia nguyen
     * A method for content that will be printed later
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

    /**
     * @author Cynthia Lei
     * Generate bar graph
     * 
     * @param percentArr array that contains the relative frequencies of the first digits
     */
    public static void generateBarGraph(double[] percentArr) {
        String[] labelsArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; // x-axis labels
        String graphTitle = "Benford's Law Distribution Leading Digit";
        String xAxisTitle = "First Digit";
        String yAxisTitle = "Relative Frequency (%)";
  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //jfreechart dataset
        
        for (int i = 0; i < labelsArr.length; i++){
            // add each data point (y-value, legend, x-value)
            dataset.addValue(percentArr[i], "Relative Frequency", labelsArr[i]); 
        }

        JFreeChart barChart = ChartFactory.createBarChart(
           graphTitle, // Graph title 
           xAxisTitle, yAxisTitle, // x-axis title, y-axis title
           dataset,PlotOrientation.VERTICAL, // range axis is vertical
           true, true, false);
           
        int width = 640;    // Image Width
        int height = 480;   // Image Height
        String chartFileName = "BenfordBarChart.png";
        File barChartName = new File(chartFileName); // File name
        
        System.out.println("View the bar graph image in this program's directory.");
        
        try{
            ChartUtils.saveChartAsPNG(barChartName, barChart, width, height); // save chart as png image file
        }
        catch (IOException e){
            System.out.println("Error saving file");
        }
     }
}