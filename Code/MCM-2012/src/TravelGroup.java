/**
 * A group of folks travelling together
 **/
public class TravelGroup implements Comparable<TravelGroup>{
    public int avgSpeed;
    public int waterTime;
    public int lowDepartureDay;
    //public int highDepartureDay;
    public int travel;
    public int latestDay;

    /**
     * Basic constructor initializes values and does some calculation.
     **/
    public TravelGroup(int speed, int time, int lowDD/*, int highDD,*/){
	this.avgSpeed = speed;
	this.waterTime = time;
	this.lowDepartureDay = lowDD;
	//this.highDepartureDay = highDD;
	this.travel = (speed*time)/(Main.RIVER_LENGTH/Main.NUM_SITES);
    }

    //IMPLEMENT COMPARE_TO
    /**
     * Compares based on lowDepartureDay for the priority queue
     */
    public int compareTo(TravelGroup g){
    	if(g.lowDepartureDay<this.lowDepartureDay){
    		return -1;
    	}if(g.lowDepartureDay>this.lowDepartureDay){
    		return 1;
    	}return 0;
    }

}
