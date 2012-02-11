import java.util.ArrayList;
import java.util.Collections;

/**
 * Stores the information for a group's final travel itinerary.
 * @author kwenholz
 *
 */
public class FinalItinerary{
	public ArrayList<Integer> sites;
	public TravelGroup group;

	public FinalItinerary(ArrayList<Integer> sites, TravelGroup g){
		this.sites = sites;
		this.group = g;			
	}
	
	public String toString(){
		String ret = "Group number: "+this.group.uniqueID+"\n Campsites: ";
		Collections.sort(this.sites);
		for(Integer i: this.sites){
			ret += i+", ";
		}return ret+"\n Average Daily Distance: "+
			((double)Main.RIVER_LENGTH/(double)this.sites.size())+
			"\n base travel: "+this.group.travel;
	}
}