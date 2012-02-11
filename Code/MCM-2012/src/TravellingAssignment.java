/**
 * A class for containing assignment information for each day.
 **/
public class TravellingAssignment {
    public TravelGroup[] campsites;

    /**
     * A basic initializer.
     **/
    public TravellingAssignment(){
	this.campsites = new TravelGroup[Main.NUM_SITES];
    }
    /**
     *Is this assignment consistent?
     **/
    public boolean isConsistent(int site){
    if(site == Main.FINISH){
    	return true;
    }
	if(campsites[site]!=null){
	    return false;
	}return true;
    }

    /**
     * Assign someone to a campsite for the day.
     **/
    public void add(int site, TravelGroup camper){
    	if(site == Main.FINISH){
    		return;
    	}
    	campsites[site] = camper;
    }

    /**
     * Remove the assignment.
     **/
    public void remove(int site){
    	if(site == Main.FINISH){
    		return;
    	}
    	campsites[site] = null;
    }

}
