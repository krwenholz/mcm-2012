/**
 * A group of folks travelling together
 **/
public class TravelGroup {
    public int avgSpeed;
    public int waterTime;
    public int lowDepartureDay;
    //public int highDepartureDay;
    public int travel;
    public int latestDay;

    /**
     * Basic constructor initializes values and does some calculation.
     **/
    public TravelGroup(int speed, int time, int lowDD, /*int highDD,*/ int dist){
	this.avgSpeed = speed;
	this.waterTime = time;
	this.lowDepartureDay = lowDD;
	//this.highDepartureDay = highDD;
	this.travel = speed*time/dist;
    }

    //IMPLEMENT COMPARE_TO

}
