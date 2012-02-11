/**
 * The heavy lifting class.  Runs the primary search algorithm, heuristic
 * assesments, and various other high level solution processes.
 */
public class ConstraintSatisfaction {
    /**
     *WE SHOULD INTERN OUR VARIABLE NAMES SINCE THEY WILL BE COMPARED OFTEN,
     *then use ==
     **/

    /**
     * The constructor just initializes an instance of this class and takes
     * in some general items necessary to finding a problem solution.
     *@param variable list
     **/
    public ConstraintSatisfaction(){

    }

    /**
     * Attempts a backtracking search to satisfy the problem passed in
     * through the constructor.
     *@return a string specifying the result of the constraint satisfaction
     **/
    public String satisfy(){
    	return "This is supposed to be your solution to the problem.";
    }

    /**
     * A general solution to backtracking depth first for solving the
     * constraint satisfaction problem passed in.
     *@param ass the current assignment
     *@param csp the constraint satisfaction problem
     **/

    //THIS GUY WILL BE DONE EACH DAY, THE CALLER SHOULD CONSTRUCT THE ASSIGNMENT
    // AND CSP SUCH THAT IT IS LIMITED TO WHAT IT NEEDS TO DO THAT DAY
    public ArrayList<TravellingAssignment> backtrackingRecurse(TravellingAssignment ass, CSP_Scheduling csp){
	//check if everything is assigned (we have already been checking for correctness)
	if(csp.isComplete()){
	    //FORMULATE A NEW CSP FOR DAY me++
	    //FIND A SOLUTION
	    //RETURN IT OR NOT
	}
	//select the next unassigned variable
	String var = selectUnassignedVar(csp.getVariables(), ass, csp);
	for(String val: orderDomainValues(var, ass, csp)){
	    //attempt to assign domain values to this variable
	    if(isConsistent(val, ass, csp)){
		//if the assignment was valid so far then here we go
		ass.add(var,val);
		//HERE WE SHOULD CHECK OUR ASSIGNMENT WITH FILTERING OR SOMETHING
		ArrayList<TravellingAssignment> isSuccess = backtrackingRecurse(ass, csp);
		if(isSuccess){
		    return ass;
		}ass.remove(var,val);
	    }
	}
	//we failed to create a valid assignment
	return null;
    }


    /**
     * Returns an unassigned variable that should get an assignment next.
     **/
    public Variable selectUnassignedVar(Variables[] vars, TravellingAssignment ass, CSP_Scheduling csp){

    }

    /**
     * Orders the possible values for a given variable and returns an array
     * of these.
     **/
    public Value orderDomainValues(Variable var, TravellingAssignment ass, CSP_Scheduling csp){

    }


}
