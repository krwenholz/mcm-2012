/**
 * Keeps track of the CSP: variables, domain
 **/
public class CSP_Scheduling {
    private PriorityQueue<TravelGroup> leftToLeave;
    private List<TravelGroup> needsAssignment;
    private TravelGroup[] positions;
    private int today;
    private int travelTolerance;
    private int numSites;
    /**
     * Bleh. . . . Constructor: destroyer of worlds.
     **/
    public CSP_Scheduling(TravelGroup[] positions,
			  PriorityQueue<TravelGroup> leftToLeave,
			  int today, int travelTolerance){
	this.leftToLeave = leftToLeave;
	this.positions = positions;
	this.needsAssignment = new ArrayList<TravelGroup>();
	this.today = today;
	this.travelTolerance = travelTolerance;
	this.numSites = Main.NUM_SITES;
	for(TravelGroup t: positions){
	    this.needsAssignment.add(t);
	}while(this.leftToLeave.peek().lowDepartureDay == today){
	    TravelGroup camper = this.leftToLeave.pop();
	    camper.latestDay = Math.min(Main.SEASON_DAYS,
					this.today+(this.numSites/camper.travel));//season days
	    this.needsAssignment.add(camper);
	}
    }

    /**
     * Returns the next travel group up for assignment.
     **/
    public TravelGroup getNextGroup(){
	//WE SHOULD BE SORTING BASED ON MINIMUM REMAINING VALUES
	return this.needsAssignment.remove(this.needsAssignment.length());
    }

    /**
     * Returns the possible campsites for a given group.
     **/
    public ArrayList<Integer> getCampsites(TravelGroup g, TravellingAssignment ass){
	ArrayList<Integer> sites = null;
	int site = Main.NOT_IN_POSITIONS;
	for(int i=0; i<positions.size; i++){
	    if(positions[i].compareTo(g)){
		site = i;
		break;
	    }
	}if(site==Main.NOT_IN_POSITIONS){
	    //just starting off
	    sites = new ArrayList<Integer>();
	    for(i=g.travel-this.travelTolerance; i<g.travel+this.travelTolerance; i++){
		sites.add(i);
	    }
	}else{
	    //at campsite b
	    if(g.latestDay<((this.numSites-b)/g.travel)){
		return new ArrayList<Integer>();
	    }
	    sites = new ArrayList<Integer>();
	    for(i=b+g.travel-this.travelTolerance; i<b+g.travel+this.travelTolerance; i++){
		sites.add(i);
	    }
	}for(i=0; i<sites.length(); i++){
	    if(sites.get(i)>this.numSites-1){
		sites.set(i, Main.FINISH);//finishing
	    }
	}return sites;
    }

    /**
     * Adds a TravelGroup back into the assignment list.
     **/
    public void addBack(TravelGroup g){
	this.needsAssignment.add(g);
    }
}
