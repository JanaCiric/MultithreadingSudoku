//Jana Ciric - class containing the main method
//Here we create 27 threads and use them to test the validity of the sudoku board
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MultithreadTester {

	//number of threads per row, column and box
	static final int NUMTHREADS = 9;
	static final int ROWS = 9;
	static final int COLUMNS = 9;

	//create an array to store the info from the sudoku
	static int [][] sudoku = new int [ROWS][COLUMNS];

	public static void main(String[] args) throws FileNotFoundException {

		//ask user to put in the file

		System.out.println("Please enter the text file to be read in: ");
		Scanner keyboard = new Scanner (System.in);
		String file;
		file = keyboard.nextLine();

		//reading in data from a file that the user chose
		Scanner input = new Scanner (new FileReader (file+".txt"));
		for (int row = 0; row <ROWS; row++) {
			for (int col = 0; col<COLUMNS; col++) {
				sudoku[row][col] = input.nextInt();
			}

		}

		//printing out the sudoku board
		System.out.println("The sudoku looks like this:");
		for (int i = 0; i<9; i++){
			for (int j = 0; j<9; j++){
				System.out.print(sudoku[i][j] + " ");
			}
			System.out.println();
		}

		//27 threads total, one for each row, column and 3x3 box

		Thread[] rowThreads= new Thread[NUMTHREADS];
		Thread[] columnThreads= new Thread[NUMTHREADS];
		Thread[] sectionThreads = new Thread[NUMTHREADS];

		//keeps track of final answer
		Result result = new Result();


		//initalize and start a thread for each row column and box
		for(int i=0;i<NUMTHREADS;i++){
			rowThreads[i]=new CheckRow(sudoku,i,result);
			rowThreads[i].start();
			columnThreads[i]=new CheckColumn(sudoku,i,result);
			columnThreads[i].start();
			sectionThreads[i]=new CheckSection(sudoku,i,result);
			sectionThreads[i].start();
		}
		//wait for threads to complete
		try {
			for (int i = 0; i < NUMTHREADS; i++) {
				rowThreads[i].join();
				columnThreads[i].join();
				sectionThreads[i].join();
			}
		} catch (InterruptedException ie) { }
		//print final answer once all threads are completed
		if(result.getAnswer() == true)
		System.out.println("The sudoku is correct");
		else
			System.out.println("The sudoku is incorrect");
	}
}
