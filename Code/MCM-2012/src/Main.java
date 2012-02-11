import java.util.*;
public class Main {
	public static final int NOT_IN_POSITIONS = 1111;
	public static final int FINISH = 9999;//campsite "value" for the ended trip
	public static final int SEASON_DAYS = 10; //180 in end
	public static final int NUM_SITES = 40;//128 is norm
	public static final int TRAVEL_TOLERANCE = 1;
	public static final int RIVER_LENGTH = 225;
	/**
	 * The main method should initialize variables we want to set for
	 * absolutely ANYTHING.  This way we can avoid screwing around with
	 * multiple files.
	 * @param args
	 */
	public static void main(String[] args) {
		TravelGroup camper = new TravelGroup(4, 8, 2);
		TravelGroup camper2 = new TravelGroup(4, 7, 2);
		TravellingAssignment ass = new TravellingAssignment();
		PriorityQueue<TravelGroup> q = new PriorityQueue<TravelGroup>();
		q.add(camper);
		q.add(camper2);
		CSP_Scheduling csp = new CSP_Scheduling(new TravelGroup[0],
				q, 1);
		ArrayList<TravellingAssignment> a = ConstraintSatisfaction.go(ass, csp, 1);
		for(TravellingAssignment t: a ){
			for(int i = 0; i<t.campsites.length; i++){
				if(t.campsites[i]!=null){
					System.out.println(t.campsites[i] + "at campsite "+i); 
				}
			}
		}
	}

}
