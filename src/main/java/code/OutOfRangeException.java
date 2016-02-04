package code;

public class OutOfRangeException extends Exception{

	private String error;
	
	public OutOfRangeException(String error){
		this.error = error;
	}
	
	public String getError(){
		return error;
	}
}