//Jana Ciric - Class to check each of the nine subsections of the sudoku board to see
//if it contains the numbers 1-9 without repetition
//and to check whether the sum is equal to 45 in each of these boxes
public class CheckSection extends Thread {

    private int [][] sudoku;
    private int box;
    private Result result;
    public boolean value = true;

    public CheckSection(int [][] sudoku, int box, Result result)
    {
        this.sudoku = sudoku;
        this.box = box;
        this.result = result;
    }

    public void run() {
    	System.out.println(this.getName()+" Box #" + box +" start time: " + System.currentTimeMillis());
    	checkBoxes();
		result.setAnswer(value);
    }

   //checks each box;
   //box 0 is in the top left corner
   //box 8 is in the bottom right corner
  	private void checkBoxes(){
    	//load in all of the elements of each box in the array
  		int []array = new int[9];
  		int index=0;
  		int sum=0;
  			for(int row = box - box %3; row < box-box%3+3;row++){
  				for(int column=0;column<3;column++){					
  					sum=sum+sudoku[row][column+(3*box%3)];
  					array[index]=sudoku[row][column+(3*box%3)];
  					index=index+1;
  				}
  			}
  			//check if the sum = 45
		if(sum!=45)
		{
			value = false;
			System.out.println("The sum in box " + box + " is " + sum);
		}

		//checking for duplicate elements in the array/box
		for(int j = 0; j < sudoku.length; j++){
			for(int k = j+1; k < sudoku.length; k++){
				if (array[j] == array[k]) {
					System.out.println("Duplicate found in box #" + box);
					value = false;

				}
			}

		}
  	}
}

