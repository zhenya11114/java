package run;

import model.*;

public class Starter {
	public static void main(String[] args) {
//		UserConsoleInput uci = new UserConsoleInput();
//		uci.start();
		DataBaseOperationGraph dbog = new DataBaseOperationGraph();
		dbog.build();
	}
}