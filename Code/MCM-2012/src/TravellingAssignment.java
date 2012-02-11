/**
 * A class for containing assignment information for each day.
 **/
public class TravellingAssignment {
    private TravelGroup[] campsites;

    /**
     * A basic initializer.
     **/
    public TravellingAssignment(int numSites){
	this.campsites = new TravelGroup[numSites];
    }
    /**
     *Is this assignment consistent?
     **/
    public boolean isConsistent(int site){
	if(campsites[site]!=null){
	    return false;
	}return true;
    }

    /**
     * Assign someone to a campsite for the day.
     **/
    public void add(int site, TravelGroup camper){
	campsites[site] = camper;
    }

    /**
     * Remove the assignment.
     **/
    public void remove(int site){
	campsites[site] = null;
    }

}
