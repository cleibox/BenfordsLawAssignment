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

// CSV reader
// Get the included jar file in the github
// In VSCode, Explorer > JAVA PROJECTS > Referenced Libraries > Add library (the jar file)
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        // Boolean to alert user if file is not found and loops until inputted correctly
        boolean fileExists = false;
        // Initializing variables
        String path = "";
        String name = "";
        char firstNumber = '\0';

        // Force user to reinput if no file is found
        do{
            // Prompts user to enter pathway
            System.out.println("What is the pathway to reach the folder?");
            path = reader.nextLine();
            
            // Prompts user to enter name
            System.out.println("What is the file name you want read");
            name = reader.nextLine();
            
            System.out.println();

            fileExists = readFile(frequencyArr, firstNumber, path, name, percentageArr);
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
     * @param numArr is the frequency in integers
     * @param first is used to grab the first number
     * @param pathway is the path to access file
     * @param title is the name of file
     * @param percentArr is the frequency in percentage
     * @return false if file is not found, otherwise true
     */
    public static boolean readFile(int[] numArr, char first, String pathway, String title, double[] percentArr){
        try{
            Reader reader = Files.newBufferedReader(Paths.get(pathway + title));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            // Reads all of the lines
            for (CSVRecord csvRecord : csvParser) {
                // Reads second column
                String sales = csvRecord.get(1);
                // Grabs first character
                first = sales.charAt(0);
                // Populates the array 
                numArr = countValue(first, numArr);
            }
            // Turning into percentage
            percentageValue(numArr,percentArr);
            // Closing parser
            csvParser.close();
        }
        // File path or name was inputted incorrectly or file was not found
        catch(Exception e){
            System.out.println("Error occured. Please reinput.");
            // Main method loops
            return false;
        }
        return true;
    }

    /**
     * @author Sophia Nguyen
     * To count up how many times the first number occurs to prove Benfords law of distribution
     * 
     * @param information is the first digit read by buffered reader (br)
     * @param numArr is the array that stores each frequency
     * @return an array that carries the frequency
     */
    public static int[] countValue(char information, int[] numArr){
        int firstDigit = Character.getNumericValue(information);  
        // Checking frequency by for looping to check for matches
        // if it matches, then it would at 1 to that index of the array
        for (int i = 0; i < 9; i++){
            // Since loop starts at 0, i+1 is used to check for match
            // If first digit in CSV matches i+1
            if(firstDigit==i+1){
                numArr[i] += 1;
            }
        }
        return numArr;
    }
    /**
     * @author Cynthia Lei
     * Determining the relative frequency of each digit frequency 
     * 
     * @param numArr this contains all the digit frequencies 
     * @param percentArr this contains all the digit relative frequencies 
     */
    public static void percentageValue(int[] numArr, double[] percentArr){
        int totalFrequency = sumArrElements(numArr); // get the total frequency 
        
        // read through each digit frequency
        for (int i = 0; i < numArr.length; i++){
            // round to 2 decimal places and as percentage (%)
            percentArr[i] = Math.round((numArr[i]*1.0/totalFrequency) * 100 * 100.0) / 100.0;
        }

        System.out.println("Fraud (likeliness) present: " + isThereFraudValidation(percentArr));
    }

    /**
     * @author Cynthia Lei
     * Summing up the elements in a given array
     * 
     * @param numArr this contains all the digit frequencies
     * @return the sum of all elements in the array
     */
    public static int sumArrElements(int[] numArr){
        int sum = 0;
        for (int i = 0; i < numArr.length; i++){
            sum += numArr[i]; // accumulator variable
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
     * 
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
     * 
     * @param reader to take in user input
     * @param pathway to reach the directory folder
     * @param percentArr to print out information
     */
    public static void generateCustomerDataFile(Scanner reader, String pathway, double[] percentArr){
        String content = "";
        try{
            String fileName = "results.csv";
            System.out.println("View the created csv file called '" + fileName +  "' in the directory with sales.csv.");
            
            // File name
            String info = (pathway + fileName);
            
            // Creating the new csv file
            BufferedWriter bw = new BufferedWriter(new FileWriter(info));  
            PrintWriter pw = new PrintWriter(bw);
            
            // Title of results.csv
            String title = ("First Digit | Relative Frequency (%)");
            
            // Typing out the information
            pw.print((title+ "\n")
            .concat(contentCSV(percentArr,content))
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
     * 
     * @param percentArr to retrieve content of array stored as a percent
     * @param content String that will be printed on seperate CSV file
     * @return the string that will be printed later
     */
    public static String contentCSV(double[] percentArr, String content){
        // For looping to read each line
        for(int i = 0; i < percentArr.length; i++){
            // The string stores the information that will later be printed
            content += ((i+1) + " | " + percentArr[i] + "\n");
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