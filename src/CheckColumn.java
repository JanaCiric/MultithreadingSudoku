//Jana Ciric
//Class to check each column if it contains the numbers 1-9 without repetition
//and to check whether the sum is equal to 45 in each column
public class CheckColumn extends Thread {

	private int[][] sudoku;
	private int column;
	private Result result;
	public boolean value = true;

	// Constructor
	CheckColumn(int[][] sudoku, int column, Result result) {
		this.sudoku = sudoku;
		this.column = column;
		this.result = result;
	}

	//called when a thread is started
	public void run() {
		System.out.println(this.getName()+ " Column #" + column +" start time: " + System.currentTimeMillis());	
		checkForDuplicates();
		checkSum();
		result.setAnswer(value);	
	}


	public void checkForDuplicates() {
		//an array to read in each column from the sudoku table
		int[] array = new int[9];
		
		//store the column in the array from the sudoku board
		for (int i = 0; i < sudoku.length; i++) {
			array[i] = sudoku[i][column];
			//System.out.print("Element " + i + " in column " + column + " : " + array[i] + " \n"); 
		}

		//checking for duplicate elements in the array/column
				for(int j = 0; j < sudoku.length; j++){
					for(int k = j+1; k < sudoku.length; k++){
						if (array[j] == array[k]) {
							System.out.println("Duplicate found in column #" + column);
							value = false;
							
						}
					}

				}
	}


	//checking whether the sum in each column equals 45
	public void checkSum() {
		int sum = 0;
		for (int i = 0; i< sudoku.length; i++)
			sum += sudoku[i][column];
		if (sum != 45) {
			value = false;
			System.out.println("The sum in column " + column + " is " + sum);
		}

	}


}
