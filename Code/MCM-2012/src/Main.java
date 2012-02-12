import java.util.*;
public class Main {
	public static final int NOT_IN_POSITIONS = 1111;
	public static final int FINISH = 9999;//campsite "value" for the ended trip
	public static final int SEASON_DAYS = 180; //180 in end
	public static final int NUM_SITES = 100;//128 is norm
	public static final int TRAVEL_TOLERANCE = 2;
	public static final int RIVER_LENGTH = 225;
	public static final int NUM_GROUPS = 200;
	public static final double HAPPINESS_MAX = 10;
	public static final int TRIES = 500;
	/**
	 * The main method should initialize variables we want to set for
	 * absolutely ANYTHING.  This way we can avoid screwing around with
	 * multiple files.
	 * @param args
	 */
	public static void main(String[] args) {
		int fails = 0;
        int successes = 0;
        double[] happiness = new double[Main.TRIES];
        double[] sds = new double[Main.TRIES];
        for(int i = 0; i < Main.TRIES; i++){
            boolean failed = false;
            try{
                TravellingAssignment ass = new TravellingAssignment();
                PriorityQueue<TravelGroup> q = generateCampers(Main.NUM_GROUPS);
                CSP_Scheduling csp = new CSP_Scheduling(new TravelGroup[0],
                        q, 0);
                //System.out.println(csp.needsAssignment);
                ConstraintSatisfaction.TIME = System.currentTimeMillis();
                ConstraintSatisfaction.WAIT = 10000; //Time to run before throwing an error, in ms
                ArrayList<TravellingAssignment> allAssignments = ConstraintSatisfaction.go(ass, csp, 0);
                //System.out.println("Assessment size "+allAssignments.size());
                //String s = prettyPrintAssessment(allAssignments);
                //System.out.println(s);
                FinalItinerary[] itins = createItins(allAssignments);
        		double[] happinesses = calculateHappiness(itins);
        		double[] stats = doStats(happinesses);
        		happiness[i] = stats[0];
        		sds[i] = stats[1];
            }catch(Exception e){
                fails++;
                failed = !failed;
            }
            if(!failed){
                successes++;
            }
        }
        System.out.println("Groups: " + NUM_GROUPS + "\nTolerance: " + TRAVEL_TOLERANCE +
                "\nFailed " + fails + " times and succeeded " + successes + " out of " +
                Main.TRIES + " tries.");
        double[] avgStats = doStats(happiness);
        double[] sdsStats = doStats(sds);
        System.out.println("Avg happiness is: "+avgStats[0]);
        System.out.println("SD of happiness is: "+sdsStats[0]);
	}
	
	private static double[] doStats(double[] nums){
		double avg = 0;
		double sd = 0;
		for(int i = 0; i <nums.length; i++){
			avg += nums[i];
		}avg = avg/(double)nums.length;
		for(int i = 0; i<nums.length; i++){
			sd += Math.pow(((double)nums[i]-avg),2);
		}sd = sd/(double)nums.length;
		sd = Math.sqrt(sd);
		double[] ret = {avg, sd};
		return ret;
	}
	/**
	 * returns the average happiness quotient of this run
	 * @param itins
	 * @return
	 */
	private static double[] calculateHappiness(FinalItinerary[] itins){
		//THIS IS HAPPINESS
		double[] scores = new double[itins.length];
		for(int i = 0; i<itins.length;i++){			
			scores[i] = Main.HAPPINESS_MAX-Math.abs(1-(((double)(Main.RIVER_LENGTH/((double)itins[i].sites.size()+1)))/
					((double)itins[i].group.avgSpeed*(double)itins[i].group.waterTime)))*Main.HAPPINESS_MAX/2;			
		}int[] encounters = new int[itins.length];
		for(int i = 0; i<itins.length; i++){
			//through each travel group
			for(int j = i+1; j<itins.length-1; j++){
				//compare to every other travel group I haven't looked at yet
				for(int cSite = 0; cSite < itins[i].sites.size()-1 && 
						cSite < itins[j].sites.size()-1; cSite++){
					//look at all of my campsites
					if(itins[i].group.lowDepartureDay+cSite==itins[j].group.lowDepartureDay+cSite){
						//compare only if the day of camping is the same
						if(itins[i].sites.get(cSite)<itins[j].sites.get(cSite)){
							//did this one start less than the other?
							if(itins[i].sites.get(cSite+1)>itins[j].sites.get(cSite+1)){
								//did this one end greater than the other? = passed someone
								encounters[i] = encounters[i]++;
								encounters[j] = encounters[j]++;
							}
						}if(itins[i].sites.get(cSite)>itins[j].sites.get(cSite)){
							//did this one start greater than the other?
							if(itins[i].sites.get(cSite+1)<itins[j].sites.get(cSite+1)){
								//did this one end less than the other? = got passed
								encounters[i] = encounters[i]++;
								encounters[j] = encounters[j]++;
							}
						}	
					}
				}
			}
		}for(int i = 0; i<scores.length; i++){
			scores[i] = scores[i] - (Main.HAPPINESS_MAX/itins[i].sites.size())*(encounters[i]/2);
		}return scores;
		//happiness = 100-[(100/visitedSites/2)meetups+(avgSpeed/desiredSpeed*100/2)]
	}
	
	private static FinalItinerary[] createItins(ArrayList<TravellingAssignment> assignments){
		FinalItinerary[] finalItins = new FinalItinerary[Main.NUM_GROUPS];
		for(TravellingAssignment t: assignments){
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
		}return finalItins;
	}
	
	/**
	 * Craft a nice printout of the final results for the assignment problem.
	 * @param assessment the final assignments for our problem
	 */
	private static String prettyPrintAssessment(
			FinalItinerary[] itins) {
		//first we want to organize the output by grouping the campers
		String ret = "";
		//System.out.println(finalItins[finalItins.length-1]);
		for(FinalItinerary f: itins){
			//System.out.println("itin string");
			ret = ret+f.toString()+"\n";
		}
		return ret;
	}
	
	public static PriorityQueue<TravelGroup> generateCampers(int numCampers){
		PriorityQueue<TravelGroup> pq = new PriorityQueue<TravelGroup>();
		Random r = new Random();
		for(int i =0; i<numCampers; i++){
			int speed = (r.nextInt(2)+1)*4;
			int hours = 0;
			if(speed==4){
				hours = r.nextInt(4)+4;
			}else{
				hours = r.nextInt(3)+2;
			}
			int travelDays = (Main.NUM_SITES/
					((speed*hours)/(Main.RIVER_LENGTH/Main.NUM_SITES)));
			//System.out.println(travelDays);
			int departure = r.nextInt(Main.SEASON_DAYS-travelDays+1);
			//System.out.println("days: "+travelDays+"\t dDay: "+departure);
			TravelGroup g = new TravelGroup(i,
					speed, hours, departure);
			pq.add(g);
		}
		/*int i=0;
		for(TravelGroup g: pq){
			System.out.print(g.lowDepartureDay+" ");
			if(i%3==0){
				System.out.print("\n");
			}i++;
		}*/
		return pq;
	}
	

//	ArrayList<Integer> x = new ArrayList<Integer>();
//	x.add(1);
//	x.add(3);
//	x.add(0);
//	Collections.sort(x, Collections.reverseOrder());
//	System.out.println(x);
}
