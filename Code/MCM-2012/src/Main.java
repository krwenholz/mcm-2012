
public class Main {
	public static final int NOT_IN_POSITIONS = 1111;
	public static final int FINISH = 9999;//campsite "value" for the ended trip
	public static final int SEASON_DAYS = 10; //180 in end
	public static final int NUM_SITES = 40;//128 is norm
	public static final int TRAVEL_TOLERANCE = 1;
	/**
	 * The main method should initialize variables we want to set for
	 * absolutely ANYTHING.  This way we can avoid screwing around with
	 * multiple files.
	 * @param args
	 */
	public static void main(String[] args) {
		
		ConstraintSatisfaction.go(ass, csp, 1);
	    System.out.println("Hello World.");
	}

}
