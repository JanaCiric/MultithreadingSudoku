//Jana Ciric - Class to check each row if it contains the numbers 1-9 without repetition
//and to check whether the sum is equal to 45 in each row
public class CheckRow extends Thread {

	private int [][] sudoku;
	private int row;
	private Result result;
	public boolean value = true;

	// Constructor
	//Passing the information about the 2D array we are looking at
	//Holds the column number at which the thread is looking at
	//Stores the true/false value in answer to determine whether the sudoku is valid
	public CheckRow (int [][] sudoku, int row, Result result) {
		this.sudoku = sudoku;
		this.row = row;
		this.result = result;
	}


	public void run() {
		System.out.println(this.getName()+ " Row #" + row +" start time: " + System.currentTimeMillis());
		checkRows();
		checkSum();
		result.setAnswer(value);	
	}   

	public void checkRows() {
		//an array to read in each row from the sudoku table
		int [] array = new int [9];

		//store the row in the array from the sudoku board
		for (int i = 0; i < sudoku.length; i ++) {
			array[i] = sudoku[row][i];
			//System.out.print("Element " + i + " in row " + row + " : " + array[i] + " \n"); 
		}

		//checking for duplicate elements in the array/row
		for(int j = 0; j < sudoku.length; j++){
			for(int k = j+1; k < sudoku.length; k++){
				if (array[j] == array[k]) {
					System.out.println("Duplicate found in row #" + row);
					value = false;
				}
			}

		}
	}

   //checking whether the sum in each column equals 45
	public void checkSum() {
		int sum = 0;
		for (int i = 0; i < sudoku.length; i++)
			sum +=sudoku[row][i];
		if(sum != 45) {
			value = false;
			System.out.println("The sum in row " + row + " is " + sum);
		}
	}

}
