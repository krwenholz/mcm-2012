import java.util.*;
public class Main {
	public static final int NOT_IN_POSITIONS = 1111;
	public static final int FINISH = 9999;//campsite "value" for the ended trip
	public static final int SEASON_DAYS = 30; //180 in end
	public static final int NUM_SITES = 40;//128 is norm
	public static final int TRAVEL_TOLERANCE = 1;
	public static final int RIVER_LENGTH = 225;
	public static final int NUM_GROUPS =4;
	/**
	 * The main method should initialize variables we want to set for
	 * absolutely ANYTHING.  This way we can avoid screwing around with
	 * multiple files.
	 * @param args
	 */
	public static void main(String[] args) {
		TravellingAssignment ass = new TravellingAssignment();
		PriorityQueue<TravelGroup> q = generateCampers(Main.NUM_GROUPS);
		CSP_Scheduling csp = new CSP_Scheduling(new TravelGroup[0],
				q, 0);
		System.out.println(csp.needsAssignment);
		ArrayList<TravellingAssignment> allAssignments = ConstraintSatisfaction.go(ass, csp, 0);
		//System.out.println("Assessment size "+allAssignments.size());
		String s = prettyPrintAssessment(allAssignments);
		System.out.println(s);
	}
	
	/**
	 * Craft a nice printout of the final results for the assignment problem.
	 * @param assessment the final assignments for our problem
	 */
	private static String prettyPrintAssessment(
			ArrayList<TravellingAssignment> assessment) {
		//first we want to organize the output by grouping the campers
		String ret = "";
		FinalItinerary[] finalItins = new FinalItinerary[Main.NUM_GROUPS];
		for(TravellingAssignment t: assessment){
			for(int i = 0; i<t.campsites.length; i++){
				TravelGroup g = t.campsites[i];
				if(g!=null){
					if(finalItins[g.uniqueID]==null){
						//System.out.println("Adding itinerary");
						finalItins[g.uniqueID]=
								new FinalItinerary(new ArrayList<Integer>(), g);
					}finalItins[g.uniqueID].sites.add(i);
				}
			}
		}
		//System.out.println(finalItins[finalItins.length-1]);
		for(FinalItinerary f: finalItins){
			//System.out.println("itin string");
			ret = ret+f.toString()+"\n";
		}
		return ret;
	}
	
	public static PriorityQueue<TravelGroup> generateCampers(int numCampers){
		PriorityQueue<TravelGroup> pq = new PriorityQueue<TravelGroup>();
		Random r = new Random();
		for(int i =0; i<numCampers; i++){
			int speed = 8;//(r.nextInt(2)+1)*4;
			int hours = 4;//0;
			/*if(speed==4){
				hours = r.nextInt(4)+3;
			}else{
				hours = r.nextInt(3)+2;
			}*/
			int travelDays = (Main.NUM_SITES/
					((speed*hours)/(Main.RIVER_LENGTH/Main.NUM_SITES)));
			//System.out.println(travelDays);
			int departure = i;//r.nextInt(Main.SEASON_DAYS-travelDays+1);
			//System.out.println("days: "+travelDays+"\t dDay: "+departure);
			TravelGroup g = new TravelGroup(i,
					speed, hours, departure);
			pq.add(g);
		}return pq;
	}
}
