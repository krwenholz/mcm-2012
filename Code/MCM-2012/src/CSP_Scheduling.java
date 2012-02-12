import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Keeps track of the CSP: variables, domain
 **/
public class CSP_Scheduling {
	public PriorityQueue<TravelGroup> leftToLeave;
	public ArrayList<TravelGroup> needsAssignment;
	public TravelGroup[] positions;
	public int today;
	/**
	 * Bleh. . . . Constructor: destroyer of worlds.
	 **/
	public CSP_Scheduling(TravelGroup[] positions,
			PriorityQueue<TravelGroup> leftToLeave,
			int today){
		this.leftToLeave = leftToLeave;
		this.positions = positions;
		this.needsAssignment = new ArrayList<TravelGroup>();
		this.today = today;
		for(TravelGroup t: positions){
			if(t!=null){
				this.needsAssignment.add(t);
			}
		}while(!this.leftToLeave.isEmpty() && 
				this.leftToLeave.peek().lowDepartureDay == today){
			//System.out.println("adding a not-yet-left");
			TravelGroup camper = this.leftToLeave.poll();
			camper.latestDay = Math.min(Main.SEASON_DAYS,
					this.today+(Main.NUM_SITES/camper.travel));//season days
			this.needsAssignment.add(camper);
		}
	}

	/**
	 * Returns the next travel group up for assignment.
	 **/
	public TravelGroup getNextGroup(){
		//WE SHOULD BE SORTING BASED ON MINIMUM REMAINING VALUES
		return this.needsAssignment.remove(this.needsAssignment.size()-1);
	}

	/**
	 * Returns the possible campsites for a given group.
	 **/
	public ArrayList<Integer> getCampsites(TravelGroup g, TravellingAssignment ass){
		ArrayList<Integer> sites = null;
		int site = Main.NOT_IN_POSITIONS;
		for(int i=0; i<positions.length; i++){
			if(positions[i]==g){
				//XXXX may not actually work to use .equals
				site = i;
				break;
			}
		}if(site==Main.NOT_IN_POSITIONS){
			//just starting off
			sites = new ArrayList<Integer>();
			for(int i=g.travel-Main.TRAVEL_TOLERANCE; i<g.travel+Main.TRAVEL_TOLERANCE; i++){
				sites.add(i);
			}
		}else{
			//at campsite b
			if(g.latestDay<((Main.NUM_SITES-site)/g.travel)){
				return new ArrayList<Integer>();
			}
			sites = new ArrayList<Integer>();
			for(int i=site+g.travel-Main.TRAVEL_TOLERANCE; 
					i<site+g.travel+Main.TRAVEL_TOLERANCE; i++){
				sites.add(i);
			}
		}for(int i=0; i<sites.size(); i++){
			if(sites.get(i)>Main.NUM_SITES-1){
				sites.remove(i);//finishing
				sites.add(i, Main.FINISH);
			}
		}return sites;
	}

	/**
	 * Adds a TravelGroup back into the assignment list.
	 **/
	public void addBack(TravelGroup g){
		this.needsAssignment.add(g);
	}

	public boolean isComplete(){
		return this.needsAssignment.isEmpty();
	}

	public PriorityQueue<TravelGroup> cloneLeftToLeave(){
		PriorityQueue<TravelGroup> ret = new PriorityQueue<TravelGroup>();
		PriorityQueue<TravelGroup> n = new PriorityQueue<TravelGroup>();
		for(TravelGroup g: this.leftToLeave){
			ret.add(g);
			n.add(g);
		}
		this.leftToLeave = n;
		return ret;
	}
}
