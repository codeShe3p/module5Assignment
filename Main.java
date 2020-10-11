package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) { 
		
		//recursive loop variable declaration
		int j = 0;
		long[] recurArray = new long[40];
		
		//recursion runtime for loop
		for(int i = 0; i < 40; i++) {
			
			//checks runtime before and after method execution to find time in nanos
			long startTime = System.nanoTime();
			fibonacciRecursion(i);
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		
			recurArray[j] = duration;
			j++;
		}
		
		//iteration loop variable declaration
		int n = 0;
		long[] iterArray = new long[40];
		
		//iteration runtime for loop
		for(int l = 0; l < 40; l++) {
			
			//checks runtime before and after method execution to find time in nanos
			long startTime = System.nanoTime();
			fibinacciIteration(l);
			long endTime = System.nanoTime();
			long duration = (endTime - startTime); 
			
			iterArray[n] = duration;
			n++;
		}
		
		
		//Just chose a random number for the graph y axis and the increments
		long endTime = 3000000;
		long increment = endTime / 10;
        //Defining Axis sets parameters for x and y axis
        NumberAxis yAxis = new NumberAxis(0,endTime,increment);
        yAxis.setLabel("Nanoseconds");

        NumberAxis xAxis = new NumberAxis(0,40,2);
        xAxis.setLabel("Fibonacci Index");

        //create new lineChart
        LineChart lineChart = new LineChart(xAxis, yAxis);
       
        //adjust lineChart size for readability
        lineChart.setMinSize(700, 700);
        lineChart.setMaxSize(800,800);
        
        
        //LineChart Data series creation
        XYChart.Series recursionSeries = new XYChart.Series();
        recursionSeries.setName("Recursion");
        XYChart.Series iterationSeries = new XYChart.Series();
        iterationSeries.setName("Iteration");
        
        //Runs a for loop to insert both arrays stored times into the newly created series
        for(int k = 0; k < 40; k++) {
       recursionSeries.getData().add(new XYChart.Data(k,recurArray[k]));
       iterationSeries.getData().add(new XYChart.Data(k,iterArray[k]));
        }
        
         
       //Adds series and data to lineChart
       lineChart.getData().add(recursionSeries);
       lineChart.getData().add(iterationSeries);

       //Scene and Stage
        Group root = new Group(lineChart);
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("fibonacci runtime comparison");
        primaryStage.setScene(scene);
        primaryStage.show();}
	
	
	//LaunchPad
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	//Fibonacci Recursice method
	public static int fibonacciRecursion(int nth) {
        //use recursion
        if (nth == 0) {

            return 0;

        } else if (nth == 1) {

            return 1;
        }
        return fibonacciRecursion(nth - 1) + fibonacciRecursion(nth - 2);
    }

	//Fibonacci Iterative method
    public static int fibinacciIteration(int nth) {
        //use loop
        int previouspreviousNumber, previousNumber = 0, currentNumber = 1;

        for (int i = 1; i < nth ; i++) {

            previouspreviousNumber = previousNumber;

            previousNumber = currentNumber;

            currentNumber = previouspreviousNumber + previousNumber;

        }
        return currentNumber;
    }
	
}
