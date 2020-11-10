package model;

public class ExceptionHandler {
	Logger logger;
	int exceptionCounter = 0;
	
	ExceptionHandler(Logger l){
		super();
		logger = l;
	}
	
	public void handle(Exception e) {
		exceptionCounter++;
		e.printStackTrace();
		logger.appendToLog(e.getMessage() + " at num " + Integer.toString(exceptionCounter));
	}
}
