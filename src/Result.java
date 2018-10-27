//Jana Ciric - class to hold the true/false value of the sudoku board
public class Result {
	private boolean answer;
	
	Result() {
		answer = true;
	}

	public boolean getAnswer() {
		return answer;
	}

	public void setAnswer(boolean a) {
		if (a == false) 
			this.answer = a;
	}
}