import java.util.ArrayList;

/**
 * The heavy lifting class.  Runs the primary search algorithm, heuristic
 * assessments, and various other high level solution processes.
 */
public class ConstraintSatisfaction {

    /**
     * The constructor just initializes an instance of this class and takes
     * in some general items necessary to finding a problem solution.
     *@param variable list
     **/
    public ConstraintSatisfaction(){

    }

    /**
     * A general solution to backtracking depth first for solving the
     * constraint satisfaction problem passed in.
     *@param ass the current assignment
     *@param csp the constraint satisfaction problem
     **/
    public static ArrayList<TravellingAssignment> go(
    		TravellingAssignment ass, CSP_Scheduling csp,
    		int today){
	//check if everything is assigned (we have already been checking for correctness)
	if(csp.isComplete()){
		if(today==Main.SEASON_DAYS){
			ArrayList<TravellingAssignment> ret = new ArrayList<TravellingAssignment>();
			ret.add(ass);
			return ret;
		}
		TravellingAssignment newAss = new TravellingAssignment();
		CSP_Scheduling newCSP = new CSP_Scheduling(ass.campsites,
				csp.cloneLeftToLeave(), today+1);
	    //FORMULATE A NEW CSP FOR DAY me++
	    //FIND A SOLUTION
	    //RETURN IT OR NOT
		ArrayList<TravellingAssignment> ret = go(newAss, newCSP, today+1);
		if(ret==null){
			return null;//something failed further down so our day needs to "restart" somewhere
		}
		ret.add(ass);
		return ret;
	}
	//select the next unassigned variable
	TravelGroup g = csp.getNextGroup();
	System.out.println(csp.getCampsites(g, ass));
	for(int val: csp.getCampsites(g, ass)){
		System.out.println("assign variables"); 
	    //attempt to assign domain values to this variable
	    if(ass.isConsistent(val)){
		//if the assignment was valid so far then here we go
		ass.add(val, g);
		//HERE WE SHOULD CHECK OUR ASSIGNMENT WITH FILTERING OR SOMETHING
		ArrayList<TravellingAssignment> ret = go(ass, csp, today);
		if(ret!=null){
			return ret;
		}ass.remove(val);
	    }
	}
	//we failed to create a valid assignment
	csp.addBack(g);
	return null;
    }
}
